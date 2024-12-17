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

import io.ballerina.runtime.api.values.BString;
import io.ballerina.runtime.api.values.BMap;

/**
 * {@code ConnectionConfig} represents the authentication configuration required
 * for the ballerina Redshift Data Client.
 *
 * @param accessKeyId     The AWS access key, used to identify the user
 *                        interacting with AWS.
 * @param secretAccessKey The AWS secret access key, used to authenticate the
 *                        user interacting with AWS.
 * @param sessionToken    The AWS session token, retrieved from an AWS token
 *                        service, used for authenticating that
 *                        * this user has received temporary permission to
 *                        access some resource.
 */
public record AuthConfig(String accessKeyId, String secretAccessKey, String sessionToken) {

    public AuthConfig(BMap<BString, Object> bAuthConfig) {
        this(getAccessKeyId(bAuthConfig), getSecretAccessKey(bAuthConfig), getSessionToken(bAuthConfig));
    }

    private static String getAccessKeyId(BMap<BString, Object> bAuthConfig) {
        return bAuthConfig.getStringValue(Constants.AWS_ACCESS_KEY_ID).getValue();
    }

    private static String getSecretAccessKey(BMap<BString, Object> bAuthConfig) {
        return bAuthConfig.getStringValue(Constants.AWS_SECRET_ACCESS_KEY).getValue();
    }

    private static String getSessionToken(BMap<BString, Object> bAuthConfig) {
        if (bAuthConfig.containsKey(Constants.AWS_SESSION_TOKEN)) {
            return bAuthConfig.getStringValue(Constants.AWS_SESSION_TOKEN).getValue();
        }
        return null;
    }
}
