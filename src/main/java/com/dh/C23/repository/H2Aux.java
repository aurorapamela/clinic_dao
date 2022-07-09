package com.dh.C23.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Aux {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/C23";
    private static final String WITH_SCRIPT = ";INIT=RUNSCRIPT FROM 'create.sql'";
    private static final String USER = "sa";
    private static final String PASS = "sa";


    // CONECTAR A LA BASE DE DATOS
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // CONECTAR PARA INICIALIZAR LAS TABLAS DE LA BASE DE DATOS
    public static void startBDWithScript(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            DriverManager.getConnection(URL+WITH_SCRIPT, USER, PASS);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


}
