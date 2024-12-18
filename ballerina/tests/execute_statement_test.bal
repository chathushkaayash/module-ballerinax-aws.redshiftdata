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

import ballerina/test;

@test:Config {
    groups: ["execute"]
}
isolated function testBasicStatement() returns error? {
    Client redshift = check new Client(testConnectionConfig);
    string statementId = check redshift->executeStatement(`SELECT * FROM Users`);
    test:assertTrue(statementId != "", "Statement ID is empty");
}

@test:Config {
    groups: ["execute"]
}
isolated function testParameterizedStatement() returns error? {
    Client redshift = check new Client(testConnectionConfig);
    string tableName = "Users";
    string statementId = check redshift->executeStatement(`SELECT * FROM ${tableName}`);
    test:assertTrue(statementId != "", "Statement ID is empty");
}

@test:Config {
    groups: ["execute"]
}
isolated function testEmptyStatement() returns error? {
    Client redshift = check new Client(testConnectionConfig);
    string|Error statementId = redshift->executeStatement(``);
    test:assertTrue(statementId is Error, "Statement ID is not an error");
}

@test:Config {
    groups: ["execute"]
}
isolated function testWithDbConfigs() returns error? {
    ConnectionConfig mockConnectionConfig = {
        region: testRegion,
        authConfig: testAuthConfig,
        databaseConfig: {
            clusterId: "",
            databaseName: "",
            databaseUser: ""
        }
    };
    Client redshift = check new Client(mockConnectionConfig);
    string statementId = check redshift->executeStatement(`SELECT * FROM Users`, testDatabaseConfig);
    test:assertTrue(statementId != "", "Statement ID is empty");
}
