package org.example.datasource;

import org.example.exceptions.DataSourceException;
import org.example.common.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnectionFactory extends ConnectionFactory {

    private static final String CONFIG_FILE="src/resources/db.properties";
    private static final String DRIVER_NAME="Oracle";
    private static final  String URL;
    private static final String USER;
    private static final String PASSWORD;

    private static final String ERROR_MSG = "Cant connect to the DB, please check your credentials";

    private static final PropertiesReader READER;

    static {
        READER = new PropertiesReader(CONFIG_FILE);
        USER = READER.get("user");
        PASSWORD = READER.get("password");
        URL = READER.get("url");
//        System.out.println("URL : "+URL);
//        System.out.println("USER : "+USER);
//        System.out.println("Password : "+PASSWORD);
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public String getUser() {
        return USER;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public String getDriverName() {
        return DRIVER_NAME;
    }

    @Override
    public Connection createConnection() throws DataSourceException {
        try {
            return  DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException ex) {
            throw new DataSourceException(ERROR_MSG, ex);
        }
    }
}
