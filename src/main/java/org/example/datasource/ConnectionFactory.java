package org.example.datasource;

import org.example.exceptions.DataSourceException;

import java.sql.Connection;

public abstract class ConnectionFactory {
    public abstract String getURL();
    public abstract String getUser();
    public abstract String getPassword();
    public abstract String getDriverName();
    public abstract Connection createConnection() throws DataSourceException;

    public static ConnectionFactory getConnectionFactory(DataSourceType factory){

        switch (factory){
            case ORACLE:
                return new OracleConnectionFactory();
            case MYSQL:
                return null;
            default:
                throw new AssertionError();
        }

    }

}
