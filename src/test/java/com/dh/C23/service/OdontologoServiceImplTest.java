package com.dh.C23.service;

import com.dh.C23.dominio.Odontologo;
import com.dh.C23.repository.H2Aux;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceImplTest {
    @Autowired
    IOdontologoService odontologoService;

    @BeforeAll
    public static void prepararBDParaTest(){
        H2Aux.startBDWithScript();
    }

    @Test
    @Order(1)
    public void agregarOdontologoTest(){
        Odontologo odontologoParaAgregar = new Odontologo("Mabel", "Seteve", 928);
        odontologoService.guardarOdontologo(odontologoParaAgregar);
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(2);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(2)
    public void buscarOdontologoXIdTest(){
        int odontologoIdBuscado = 1;
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(odontologoIdBuscado);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    public void listarOdontologosTest(){
        List<Odontologo> listaDePrueba = odontologoService.listarOdontologos();
        assertTrue(listaDePrueba.size()==2);

    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Odontologo odontologoConDatosNuevos = new Odontologo(2, "Vereda", "Roberta", 8372);
        odontologoService.actualizarOdontologo(odontologoConDatosNuevos);
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(2);
        assertEquals("Vereda", odontologoBuscado.getApellido());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest(){
        int idBuscadoABorrar = 2;
        odontologoService.eliminarOdontologo(idBuscadoABorrar);
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(idBuscadoABorrar);
        assertNull(odontologoBuscado);
    }
}