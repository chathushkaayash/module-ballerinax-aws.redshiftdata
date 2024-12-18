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

import ballerina/os;

configurable string TEST_AWS_ACCESS_KEY_ID = os:getEnv("AWS_ACCESS_KEY_ID");
configurable string TEST_AWS_SECRET_ACCESS_KEY = os:getEnv("AWS_SECRET_ACCESS_KEY");
configurable string TEST_AWS_REGION = os:getEnv("AWS_REGION");

configurable string TEST_DATABASE_NAME = os:getEnv("DATABASE_NAME");
configurable string TEST_CLUSTER_ID = os:getEnv("CLUSTER_ID");
configurable string TEST_DB_USER = os:getEnv("DB_USER");

final Region & readonly testRegion = "us-east-2";

final AuthConfig & readonly testAuthConfig = {
    accessKeyId: TEST_AWS_ACCESS_KEY_ID,
    secretAccessKey: TEST_AWS_SECRET_ACCESS_KEY
};

final DatabaseConfig & readonly testDatabaseConfig = {
    clusterId: TEST_CLUSTER_ID,
    databaseName: TEST_DATABASE_NAME,
    databaseUser: TEST_DB_USER
};

final ConnectionConfig & readonly testConnectionConfig = {
    region: testRegion,
    authConfig: testAuthConfig,
    databaseConfig: testDatabaseConfig
};
