//  Copyright (c) 2024, WSO2 LLC. (http://www.wso2.org).
//
//  WSO2 LLC. licenses this file to you under the Apache License,
//  Version 2.0 (the "License"); you may not use this file except
//  in compliance with the License.
//  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing,
//  software distributed under the License is distributed on an
//  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
//  KIND, either express or implied. See the License for the
//  specific language governing permissions and limitations
//  under the License.
//  

import ballerina/jballerina.java;

public isolated client class Client {

    # Initializes AWS Redshift Data API client.
    #
    # + connectionConfig - Configurations related to redshift data api
    # + return - The `redshiftdata:Client` or `redshiftdata:Error` if the initialization fails
    public isolated function init(*ConnectionConfig connectionConfig) returns Error? {
        return self.externInit(connectionConfig);
    }

    isolated function externInit(ConnectionConfig connectionConfig)
    returns Error? = @java:Method {
        name: "init",
        'class: "io.ballerina.lib.aws.redshiftdata.NativeClientAdaptor"
    } external;
        
    # Closes the AWS Redshift Data API client.
    # ```ballerina
    # check redshift->close();
    # ```
    # 
    # + return - A `redshiftdata:Error` if there is an error while closing the client resources or else nil
    remote function close() returns Error? = @java:Method {
        'class: "io.ballerina.lib.aws.redshiftdata.NativeClientAdaptor"
    } external;

}
