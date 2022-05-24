"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _ApiClient = _interopRequireDefault(require("../ApiClient"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } }

function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }

/**
* HelloWorld service.
* @module api/HelloWorldApi
* @version 1.0.0
*/
var HelloWorldApi = /*#__PURE__*/function () {
  /**
  * Constructs a new HelloWorldApi. 
  * @alias module:api/HelloWorldApi
  * @class
  * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
  * default to {@link module:ApiClient#instance} if unspecified.
  */
  function HelloWorldApi(apiClient) {
    _classCallCheck(this, HelloWorldApi);

    this.apiClient = apiClient || _ApiClient["default"].instance;
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


  _createClass(HelloWorldApi, [{
    key: "helloWorld",
    value: function helloWorld(callback) {
      var postBody = null;
      var pathParams = {};
      var queryParams = {};
      var headerParams = {};
      var formParams = {};
      var authNames = [];
      var contentTypes = [];
      var accepts = ['*/*'];
      var returnType = 'String';
      return this.apiClient.callApi('/hello', 'GET', pathParams, queryParams, headerParams, formParams, postBody, authNames, contentTypes, accepts, returnType, null, callback);
    }
  }]);

  return HelloWorldApi;
}();

exports["default"] = HelloWorldApi;