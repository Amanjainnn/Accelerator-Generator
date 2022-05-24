# HelloWorld.HelloWorldApi

All URIs are relative to *https://localhost:8080/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**helloWorld**](HelloWorldApi.md#helloWorld) | **GET** /hello | Returns a string



## helloWorld

> String helloWorld()

Returns a string

### Example

```javascript
import HelloWorld from 'hello_world';

let apiInstance = new HelloWorld.HelloWorldApi();
apiInstance.helloWorld((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*

