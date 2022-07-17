package org.example.support;

public class QueryBuilder {

    public static String selectAll(final String TABLE_NAME){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ");
        sql.append(TABLE_NAME);
        return sql.toString();
    }
}
