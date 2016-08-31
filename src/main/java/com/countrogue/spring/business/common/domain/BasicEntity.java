package com.countrogue.spring.business.common.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by socrates on 8/30/16.
 */
@MappedSuperclass
@Data
public class BasicEntity implements Serializable {

    private static final long serialVersionUID = 1409715715962049158L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
}
