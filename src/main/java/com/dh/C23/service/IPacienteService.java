package com.dh.C23.service;

import com.dh.C23.dominio.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Paciente buscarXEmail(String email);
    Paciente guardarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Paciente paciente);
    Paciente buscarXId(int id);

}
