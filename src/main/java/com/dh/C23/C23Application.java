package com.dh.C23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class C23Application {

	public static void main(String[] args) {
		prepararBD();
		SpringApplication.run(C23Application.class, args);
	}

	private static void prepararBD(){
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/C23;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
		} catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
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
