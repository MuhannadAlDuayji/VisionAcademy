package online.visionacademy.support;

import java.util.Arrays;
import java.util.List;

public class QueryBuilder {

    // array columns index [0] always its key.
    public static String selectAll(final String TABLE_NAME,String [] columns){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(columnsAndColon(columns));
        sql.append(" FROM ");
        sql.append(TABLE_NAME);
        sql.append(" ");

        return sql.toString();
    }

    public static String selectById(final String TABLE_NAME,String [] columns){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(columnsAndColon(columns));
        sql.append(" FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ");
        sql.append(columns[0]);
        sql.append(" = ? ");

        return sql.toString();
    }

    public static <T> String selectAllById(final String TABLE_NAME, String [] columns,List<T> ids ){

        StringBuilder sql = new StringBuilder();

        sql.append(selectAll(TABLE_NAME,columns));
        sql.append(" WHERE ");
        sql.append(columns[0]);
        sql.append(" IN ");
        sql.append(" ( ");
        sql.append(questionMarkAndColon(ids));
        sql.append(" ) ");
        return sql.toString();
    }

    public static String insert(final String TABLE_NAME, String [] columns){

        StringBuilder sql = new StringBuilder();

        // Key is auto generated no need to pass it.
        String [] requiredColumns = Arrays.copyOfRange(columns,1,columns.length);

        sql.append("INSERT INTO ");
        sql.append(TABLE_NAME);
        sql.append(" ( ");
        sql.append(columnsAndColon(requiredColumns));
        sql.append(" ) VALUES ( ");
        sql.append(questionMarkAndColon(Arrays.asList(requiredColumns)));
        sql.append(" ) ");

        return sql.toString();
    }

    public static String update (final String TABLE_NAME,String [] columns){

        StringBuilder sql = new StringBuilder();

        sql.append("UPDATE  ");
        sql.append(TABLE_NAME);
        sql.append(" SET ");
        sql.append(columnsUpdate(columns));
        sql.append(" WHERE ");
        sql.append(columns[0]);
        sql.append(" = ? ");

        return sql.toString();
    }

    public static  String delete(final String TABLE_NAME, String column){

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ");
        sql.append(column);
        sql.append(" = ? ");

        return sql.toString();
    }

    public static String count(final String TABLE_NAME){

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT COUNT(*) FROM ");
        sql.append(TABLE_NAME);

        return sql.toString();
    }


    private static <T> String questionMarkAndColon(List<T> ids){

        StringBuilder sql = new StringBuilder();

        for(T id:ids)
            sql.append(" ?,");
        sql.deleteCharAt(sql.length()-1);

        return sql.toString();
    }

    private static String columnsAndColon(String [] columns){

        StringBuilder sql = new StringBuilder();
        for(String s : columns)
            sql.append(s).append(",");
        sql.deleteCharAt(sql.length()-1);

        return sql.toString();
    }

    private static String columnsUpdate(String [] columns){

        StringBuilder sql = new StringBuilder();

        // start from 1 because we assume the index 0 is always key
        for (int i = 1 ; i < columns.length ; i++){
            sql.append(columns[i]);
            sql.append(" = ? ,");
        }

        sql.deleteCharAt(sql.length()-1);

        return sql.toString();
    }
}
