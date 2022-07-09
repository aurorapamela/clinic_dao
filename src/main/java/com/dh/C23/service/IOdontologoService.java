package com.dh.C23.service;

import com.dh.C23.dominio.Odontologo;

import java.util.List;

public interface IOdontologoService {
    List<Odontologo> listarOdontologos();
    Odontologo buscarOdontologoXId(int id);
    Odontologo guardarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(int id);
    Odontologo actualizarOdontologo(Odontologo odontologo);
}
