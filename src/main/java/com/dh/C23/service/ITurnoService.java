package com.dh.C23.service;

import com.dh.C23.dominio.Turno;

import java.util.List;

public interface ITurnoService {
    List<Turno> listarTurnos();
    Turno registrarTurno(Turno turno);
    Turno actualizarTurno(Turno turno);
    void eliminarTurno(int id);
    Turno buscarTurnoXId(int id);

}
