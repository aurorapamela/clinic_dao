package com.dh.C23;

import com.dh.C23.repository.H2Aux;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class C23Application {

	public static void main(String[] args){
		H2Aux.startBDWithScript();
		SpringApplication.run(C23Application.class, args);
	}


}
