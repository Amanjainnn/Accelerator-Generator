/**
 * Hello World
 * This is a default server
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */


import ApiClient from "../ApiClient";

/**
* HelloWorld service.
* @module api/HelloWorldApi
* @version 1.0.0
*/
export default class HelloWorldApi {

    /**
    * Constructs a new HelloWorldApi. 
    * @alias module:api/HelloWorldApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instance} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }


    /**
     * Callback function to receive the result of the helloWorld operation.
     * @callback module:api/HelloWorldApi~helloWorldCallback
     * @param {String} error Error message, if any.
     * @param {String} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * Returns a string
     * @param {module:api/HelloWorldApi~helloWorldCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link String}
     */
    helloWorld(callback) {
      let postBody = null;

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = [];
      let contentTypes = [];
      let accepts = ['*/*'];
      let returnType = 'String';
      return this.apiClient.callApi(
        '/hello', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }


}