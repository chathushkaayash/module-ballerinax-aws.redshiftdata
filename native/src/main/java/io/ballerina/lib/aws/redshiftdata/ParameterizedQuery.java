 package io.ballerina.lib.aws.redshiftdata;

 import io.ballerina.runtime.api.values.BArray;
 import io.ballerina.runtime.api.values.BObject;
 import software.amazon.awssdk.services.redshiftdata.model.SqlParameter;

 /**
  * Represents a parameterized query.
  */
 public class ParameterizedQuery {
     private final String[] strings;
     private final String[] insertions;

     public ParameterizedQuery(BObject bSqlStatement) {
         String[] strings = bSqlStatement.getArrayValue(Constants.QUERY_STRINGS).getStringArray();
         BArray bInsertionsArray = bSqlStatement.getArrayValue(Constants.QUERY_INSERTIONS);
         String[] insertions = new String[bInsertionsArray.size()];
         for (int i = 0; i < bInsertionsArray.size(); i++) {
             insertions[i] = bInsertionsArray.get(i).toString();
         }
         this.strings = strings;
         this.insertions = insertions;
     }

     public String getQueryString() {
         StringBuilder query = new StringBuilder();
         for (int i = 0; i < strings.length; i++) {
             query.append(strings[i]);
             if (i < insertions.length) {
                 query.append(":param").append(i);
             }
         }
         return query.toString();
     }

     public String getPreparedQuery() {
         StringBuilder query = new StringBuilder();
         for (int i = 0; i < strings.length; i++) {
             query.append(strings[i]);
             if (i < insertions.length) {
                 query.append(insertions[i]);
             }
         }
         return query.toString();
     }

     public SqlParameter[] getParameters() {
         SqlParameter[] parameters = new SqlParameter[insertions.length];
         for (int i = 0; i < insertions.length; i++) {
             parameters[i] = SqlParameter.builder().name("param" + i).value(insertions[i]).build();
         }
         return parameters;
     }

     public boolean hasParameters() {
         return insertions.length > 0;
     }
 }
