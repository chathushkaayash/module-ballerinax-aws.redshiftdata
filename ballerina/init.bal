import ballerina/jballerina.java;

function init() {
    setModule();
}

function setModule() = @java:Method {
    'class: "io.ballerina.lib.aws.redshiftdata.ModuleUtils"
} external;
