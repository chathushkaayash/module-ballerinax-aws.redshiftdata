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
import io.ballerina.runtime.api.values.BMap;
import io.ballerina.runtime.api.values.BObject;
import io.ballerina.runtime.api.values.BString;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.services.redshiftdata.RedshiftDataClient;
import software.amazon.awssdk.services.redshiftdata.model.ExecuteStatementRequest;

import java.util.Objects;

/**
 * Representation of {@link software.amazon.awssdk.services.redshiftdata.RedshiftDataClient} with
 * utility methods to invoke as inter-op functions.
 */
public class NativeClientAdaptor {
    private NativeClientAdaptor() {
    }

    public static Object init(BObject bClient, BMap<BString, Object> bConnectionConfig) {
        try {
            ConnectionConfig connectionConfig = new ConnectionConfig(bConnectionConfig);
            AwsCredentials credentials = getCredentials(connectionConfig.authConfig());
            AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
            RedshiftDataClient nativeClient = RedshiftDataClient.builder()
                    .region(connectionConfig.region())
                    .credentialsProvider(credentialsProvider)
                    .build();
            bClient.addNativeData(Constants.NATIVE_CLIENT, nativeClient);
            bClient.addNativeData(Constants.NATIVE_DATABASE_CONFIG, connectionConfig.databaseConfig());
        } catch (Exception e) {
            String errorMsg = String.format("Error occurred while initializing the Redshift client: %s",
                    e.getMessage());
            return CommonUtils.createError(errorMsg, e);
        }
        return null;
    }

    private static AwsCredentials getCredentials(AuthConfig authConfig) {
        if (Objects.nonNull(authConfig.sessionToken())) {
            return AwsSessionCredentials.create(authConfig.accessKeyId(), authConfig.secretAccessKey(),
                    authConfig.sessionToken());
        } else {
            return AwsBasicCredentials.create(authConfig.accessKeyId(), authConfig.secretAccessKey());
        }
    }

    @SuppressWarnings("unchecked")
    public static Object executeStatement(BObject bClient, BObject bSqlStatement, Object bDatabaseConfig) {
        try {
            RedshiftDataClient nativeClient = (RedshiftDataClient) bClient.getNativeData(Constants.NATIVE_CLIENT);
            DatabaseConfig databaseConfig;
            if (bDatabaseConfig == null)
                databaseConfig = (DatabaseConfig) bClient.getNativeData(Constants.NATIVE_DATABASE_CONFIG);
            else {
                databaseConfig = new DatabaseConfig((BMap<BString, Object>) bDatabaseConfig);
            }
            ParameterizedQuery parameterizedQuery = new ParameterizedQuery(bSqlStatement);
            ExecuteStatementRequest.Builder requestBuilder = ExecuteStatementRequest.builder()
                    .clusterIdentifier(databaseConfig.clusterId())
                    .database(databaseConfig.databaseName())
                    .dbUser(databaseConfig.databaseUser())
                    .sql(parameterizedQuery.getQueryString());
            // Set sql query parameters if available
            if (parameterizedQuery.hasParameters())
                requestBuilder.parameters(parameterizedQuery.getParameters());
            ExecuteStatementRequest statementRequest = requestBuilder.build();
            String statementId = nativeClient.executeStatement(statementRequest).id();
            return StringUtils.fromString(statementId);
        } catch (Exception e) {
            String errorMsg = String.format("Error occurred while executing the statement: %s",
                    e.getMessage());
            return CommonUtils.createError(errorMsg, e);
        }
    }

    public static Object close(BObject bClient) {
        RedshiftDataClient nativeClient = (RedshiftDataClient) bClient.getNativeData(Constants.NATIVE_CLIENT);
        try {
            nativeClient.close();
        } catch (Exception e) {
            String errorMsg = String.format("Error occurred while closing the Redshift client: %s",
                    e.getMessage());
            return CommonUtils.createError(errorMsg, e);
        }
        return null;
    }
}
