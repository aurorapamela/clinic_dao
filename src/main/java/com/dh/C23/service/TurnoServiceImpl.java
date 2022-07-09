package com.dh.C23.service;

import com.dh.C23.repository.IDao;
import com.dh.C23.dominio.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImpl implements ITurnoService{
    private IDao<Turno> turnoIDao;

    @Autowired
    public TurnoServiceImpl(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    @Override
    public List<Turno> listarTurnos() {
        return turnoIDao.listarElementos();
    }

    @Override
    public Turno registrarTurno(Turno turno) {
        return turnoIDao.guardar(turno);
    }

    @Override
    public Turno actualizarTurno(Turno turno) {
        return turnoIDao.actualizar(turno);
    }

    @Override
    public void eliminarTurno(int id) {
        turnoIDao.eliminar(id);
    }

    @Override
    public Turno buscarTurnoXId(int id) {
        return turnoIDao.buscarXId(id);
    }
}
