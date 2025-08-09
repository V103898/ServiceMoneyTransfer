# DefaultApi

All URIs are relative to *http://localhost:8080/transfer*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**confirmOperationPost**](DefaultApi.md#confirmOperationPost) | **POST** /confirmOperation | Confirm operation |
| [**transferPost**](DefaultApi.md#transferPost) | **POST** /transfer | Transfer money card to card |


<a name="confirmOperationPost"></a>
# **confirmOperationPost**
> TransferPost200Response confirmOperationPost(confirmOperationPostRequest)

Confirm operation

Confirming operataion with code

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/transfer");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    ConfirmOperationPostRequest confirmOperationPostRequest = new ConfirmOperationPostRequest(); // ConfirmOperationPostRequest | Confirm operation
    try {
      TransferPost200Response result = apiInstance.confirmOperationPost(confirmOperationPostRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#confirmOperationPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **confirmOperationPostRequest** | [**ConfirmOperationPostRequest**](ConfirmOperationPostRequest.md)| Confirm operation | |

### Return type

[**TransferPost200Response**](TransferPost200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success confirmation |  -  |
| **400** | Error input data |  -  |
| **500** | Error confirmation |  -  |

<a name="transferPost"></a>
# **transferPost**
> TransferPost200Response transferPost(transferPostRequest)

Transfer money card to card

Call to send money between cards

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/transfer");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    TransferPostRequest transferPostRequest = new TransferPostRequest(); // TransferPostRequest | Card from and card to
    try {
      TransferPost200Response result = apiInstance.transferPost(transferPostRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#transferPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **transferPostRequest** | [**TransferPostRequest**](TransferPostRequest.md)| Card from and card to | |

### Return type

[**TransferPost200Response**](TransferPost200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success transfer |  -  |
| **400** | Error input data |  -  |
| **500** | Error transfer |  -  |

