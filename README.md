# spring-http-patch-example
This is a sample project for supporting HTTP Patch in spring framework.
This follows a simple HTTP Patch mechanism, same as the way google does it.
It uses the following
* spring-boot with json(jackson) response
* swagger for viewing
* in memory database(h2)

## PATCH Example
The following describes the and example flow on the HTTP Patch implemented here
### Current JSON Model
```
{
  "id": "1",
  "name": "Computer X",
  "dateAcquired": 1472617178.936,
  "count": 101,
  "locations": [
    {
      "id": "canada",
      "name": "Canada"
    },
    {
      "id": "philippines",
      "name": "Philippines"
    },
    {
      "id": "newzealand",
      "name": "New Zealand"
    }
  ]
}
```
### HTTP Patch Request
As you can see here an incomplete Asset model is passed with only the property *name*, this
tells the server to only update the name property.
```
curl -X PATCH -H "Content-Type: application/json" -d '{
"name": "New Asset Name"
}' http://localhost:8080/api/v1/assets/1
```
### Updated JSON Model
```
{
  "id": "1",
  "name": "New Asset Name",
  "dateAcquired": 1472617178.936,
  "count": 101,
  "locations": [
    {
      "id": "canada",
      "name": "Canada"
    },
    {
      "id": "philippines",
      "name": "Philippines"
    },
    {
      "id": "newzealand",
      "name": "New Zealand"
    }
  ]
}
```


## Usage
1. Controller methods for PATCH should be annotated with @Patch where you can specify
    * id - type of id the entity used
    * service - bean service class that retrieves the entity.
1. Instead of using spring's @RequestBody use @PatchRequestBody, this will retrieve the object from
the service specified and update it accordingly with the body in the request.
