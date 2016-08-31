package com.countrogue.spring.config.patch.resolver;

import com.countrogue.spring.config.patch.json.Patch;
import com.countrogue.spring.config.patch.json.PatchRequestBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

/**
 * This ArgumentResolver retrieves the actual entity from the service specified in the {@link Patch}.
 * It calls a method called "find(id)" using the "id" specified in the query parameter of the method.
 * Created by socrates on 7/19/16.
 */
@Component
public class PartialUpdateArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired ObjectMapper objectMapper;
    @Autowired ApplicationContext context;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        RequestMapping methodAnot = parameter.getMethodAnnotation(RequestMapping.class);
        if( methodAnot == null ) return false;

        if( !Arrays.asList(methodAnot.method()).contains(RequestMethod.PATCH) ) return false;

        return parameter.hasParameterAnnotation(PatchRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        ServletServerHttpRequest req = createInputMessage(webRequest);

        Patch patch = parameter.getMethodAnnotation(Patch.class);
        Class serviceClass = patch.service();
        Class idClass = patch.id();
        Object service = context.getBean(serviceClass);

        String idStr = getPathVariables(webRequest).get("id");
        Object id = idClass.cast(idStr);
        Method method = ReflectionUtils.findMethod(serviceClass, "find", idClass);

        Object obj = ReflectionUtils.invokeMethod(method, service, id);
        obj = readJavaType(obj, req);
        return obj;
    }

    private Map<String, String> getPathVariables(NativeWebRequest webRequest) {

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return new ServletServerHttpRequest(servletRequest);
    }

    private Object readJavaType(Object object, HttpInputMessage inputMessage) {
        try {
            return this.objectMapper.readerForUpdating(object).readValue(inputMessage.getBody());
        }
        catch (IOException ex) {
            throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
        }
    }
}

