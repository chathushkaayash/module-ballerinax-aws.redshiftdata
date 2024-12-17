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

import io.ballerina.runtime.api.values.BMap;
import io.ballerina.runtime.api.values.BString;

/**
 * {@code DatabaseConfig} represents the database configuration required for the
 * ballerina Redshift Data Client.
 *
 * @param clusterId    The Redshift cluster ID.
 * @param databaseName The name of the database.
 * @param databaseUser The username of the database.
 */
public record DatabaseConfig(String clusterId, String databaseName, String databaseUser) {

    public DatabaseConfig(BMap<BString, Object> bDatabaseConfig) {
        this(getClusterId(bDatabaseConfig), getDatabaseName(bDatabaseConfig), getDatabaseUser(bDatabaseConfig));
    }

    private static String getClusterId(BMap<BString, Object> bDatabaseConfig) {
        return bDatabaseConfig.getStringValue(Constants.CLUSTER_ID).getValue();
    }

    private static String getDatabaseName(BMap<BString, Object> bDatabaseConfig) {
        return bDatabaseConfig.getStringValue(Constants.DATABASE_NAME).getValue();
    }

    private static String getDatabaseUser(BMap<BString, Object> bDatabaseConfig) {
        return bDatabaseConfig.getStringValue(Constants.DATABASE_USER).getValue();
    }
}
