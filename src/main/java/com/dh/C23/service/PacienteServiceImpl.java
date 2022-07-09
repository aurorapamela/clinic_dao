package com.dh.C23.service;

import com.dh.C23.dao.IDao;
import com.dh.C23.dao.PacienteDAOH2;
import com.dh.C23.dominio.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService{

    @Override
    public List<Paciente> listarPacientes() {
        IDao<Paciente> pacienteIDao = new PacienteDAOH2();
        return pacienteIDao.listarElementos();
    }

    @Override
    public Paciente buscarXEmail(String email) {
        IDao<Paciente> pacienteIDao = new PacienteDAOH2();
        return pacienteIDao.buscarXCriterio(email);
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        IDao<Paciente> pacienteIDao = new PacienteDAOH2();
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente buscarXId(int id) {
        IDao<Paciente> pacienteIDao = new PacienteDAOH2();
        return pacienteIDao.buscarXId(id);
    }
}
