// Copyright (c) 2024 WSO2 LLC. (http://www.wso2.com).
//
// WSO2 LLC. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
import ballerina/sql;

# Additional configurations related to redshift data api
#
# + region - The AWS region with which the connector should communicate
# + authConfig - The authentication configurations for the redshift data api
# + databaseConfig - The database configurations
# This can be overridden in the individual execute and batchExecute requests.
public type ConnectionConfig record {|
    Region region;
    AuthConfig authConfig;
    DatabaseConfig databaseConfig;
|};

# An Amazon Web Services region that hosts a set of Amazon services.
public enum Region {
    AF_SOUTH_1 = "af-south-1",
    AP_EAST_1 = "ap-east-1",
    AP_NORTHEAST_1 = "ap-northeast-1",
    AP_NORTHEAST_2 = "ap-northeast-2",
    AP_NORTHEAST_3 = "ap-northeast-3",
    AP_SOUTH_1 = "ap-south-1",
    AP_SOUTH_2 = "ap-south-2",
    AP_SOUTHEAST_1 = "ap-southeast-1",
    AP_SOUTHEAST_2 = "ap-southeast-2",
    AP_SOUTHEAST_3 = "ap-southeast-3",
    AP_SOUTHEAST_4 = "ap-southeast-4",
    AWS_CN_GLOBAL = "aws-cn-global",
    AWS_GLOBAL = "aws-global",
    AWS_ISO_GLOBAL = "aws-iso-global",
    AWS_ISO_B_GLOBAL = "aws-iso-b-global",
    AWS_US_GOV_GLOBAL = "aws-us-gov-global",
    CA_WEST_1 = "ca-west-1",
    CA_CENTRAL_1 = "ca-central-1",
    CN_NORTH_1 = "cn-north-1",
    CN_NORTHWEST_1 = "cn-northwest-1",
    EU_CENTRAL_1 = "eu-central-1",
    EU_CENTRAL_2 = "eu-central-2",
    EU_ISOE_WEST_1 = "eu-isoe-west-1",
    EU_NORTH_1 = "eu-north-1",
    EU_SOUTH_1 = "eu-south-1",
    EU_SOUTH_2 = "eu-south-2",
    EU_WEST_1 = "eu-west-1",
    EU_WEST_2 = "eu-west-2",
    EU_WEST_3 = "eu-west-3",
    IL_CENTRAL_1 = "il-central-1",
    ME_CENTRAL_1 = "me-central-1",
    ME_SOUTH_1 = "me-south-1",
    SA_EAST_1 = "sa-east-1",
    US_EAST_1 = "us-east-1",
    US_EAST_2 = "us-east-2",
    US_GOV_EAST_1 = "us-gov-east-1",
    US_GOV_WEST_1 = "us-gov-west-1",
    US_ISOB_EAST_1 = "us-isob-east-1",
    US_ISO_EAST_1 = "us-iso-east-1",
    US_ISO_WEST_1 = "us-iso-west-1",
    US_WEST_1 = "us-west-1",
    US_WEST_2 = "us-west-2"
}

# Auth configurations for the redshift data api
#
# + accessKeyId - The AWS access key ID
# + secretAccessKey - The AWS secret access key
# + sessionToken - The session token if the credentials are temporary
public type AuthConfig record {|
    string accessKeyId;
    string secretAccessKey;
    string sessionToken?;
|};

# Database configurations
#
# + clusterId - The cluster identifier
# + databaseName - The name of the database
# + databaseUser - The database user
public type DatabaseConfig record {|
    string clusterId;
    string databaseName;
    string databaseUser;
|};

# Configuration for the execution of the query
#
# + timeout - The timeout to be used getting the query results and execution results in `seconds`
# + pollingInterval - The polling interval to be used getting the query results and execution results in `seconds`
public type ExecutionConfig record {|
    decimal timeout = 30;
    decimal pollingInterval = 5;
|};

public type ExecutionResult record {
    *sql:ExecutionResult;
};
