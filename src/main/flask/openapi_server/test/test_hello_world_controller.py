# coding: utf-8

from __future__ import absolute_import
import unittest

from flask import json
from six import BytesIO

from openapi_server.test import BaseTestCase


class TestHelloWorldController(BaseTestCase):
    """HelloWorldController integration test stubs"""

    def test_hello_world(self):
        """Test case for hello_world

        Returns a string
        """
        headers = { 
            'Accept': '*/*',
        }
        response = self.client.open(
            '/v1/hello',
            method='GET',
            headers=headers)
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    unittest.main()
