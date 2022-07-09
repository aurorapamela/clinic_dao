package com.dh.C23.service;

import com.dh.C23.repository.IDao;
import com.dh.C23.repository.PacienteDAOH2;
import com.dh.C23.dominio.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService{

    @Autowired
    private IDao<Paciente> pacienteIDao;

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarElementos();
    }

    @Override
    public Paciente buscarXEmail(String email) {
        return pacienteIDao.buscarXCriterio(email);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    @Override
    public Paciente buscarXId(int id) {
        return pacienteIDao.buscarXId(id);
    }

    @Override
    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);
    }
}
