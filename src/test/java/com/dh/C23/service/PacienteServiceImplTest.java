package com.dh.C23.service;

import com.dh.C23.dominio.Paciente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PacienteServiceImplTest {
    @BeforeAll

    public static void prepararBD(){
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


        @Test
    public void listarPacientesTest(){
            PacienteServiceImpl pacienteService = new PacienteServiceImpl();
            List<Paciente> listaDePrueba = pacienteService.listarPacientes();
            assertTrue(listaDePrueba.size()>0);

        }

    @Test
    public void buscarXEmailTest(){
        PacienteServiceImpl pacienteService = new PacienteServiceImpl();
        String emailBuscado = "aurora@gmail.com";
        Paciente pacienteBuscado = pacienteService.buscarXEmail(emailBuscado);
        assertTrue(pacienteBuscado != null);

    }

}