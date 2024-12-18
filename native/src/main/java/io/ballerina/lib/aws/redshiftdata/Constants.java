/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.org).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.lib.aws.redshiftdata;

import io.ballerina.runtime.api.utils.StringUtils;
import io.ballerina.runtime.api.values.BString;

/**
 * Represents the constants related to Ballerina AWS Redshift Data Connector.
 */
public interface Constants {
    // Constants Related to Native Client Adapter
    String NATIVE_CLIENT = "nativeClient";
    String NATIVE_DATABASE_CONFIG = "nativeDatabaseConfig";

    // Constants Related to Connection Config
    BString REGION = StringUtils.fromString("region");
    BString AUTH_CONFIG = StringUtils.fromString("authConfig");
    BString DATABASE_CONFIG = StringUtils.fromString("databaseConfig");

    // Constants Related to Auth Config
    BString AWS_ACCESS_KEY_ID = StringUtils.fromString("accessKeyId");
    BString AWS_SECRET_ACCESS_KEY = StringUtils.fromString("secretAccessKey");
    BString AWS_SESSION_TOKEN = StringUtils.fromString("sessionToken");

    // Constants Related to Database Config
    BString CLUSTER_ID = StringUtils.fromString("clusterId");
    BString DATABASE_NAME = StringUtils.fromString("databaseName");
    BString DATABASE_USER = StringUtils.fromString("databaseUser");

    // Constants Related to Parameterized Query
    BString QUERY_STRINGS = StringUtils.fromString("strings");
    BString QUERY_INSERTIONS = StringUtils.fromString("insertions");

}
