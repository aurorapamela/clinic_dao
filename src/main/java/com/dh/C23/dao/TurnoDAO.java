package com.dh.C23.dao;

import com.dh.C23.dominio.Turno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TurnoDAO implements IDao<Turno>{
    private List<Turno> turnos;

    public TurnoDAO(List<Turno> turnos) {
        turnos = new ArrayList<>();
    }

    public TurnoDAO() {

    }

    @Override
    public List<Turno> listarElementos() {
        return turnos;
    }

    @Override
    public Turno buscarXId(int id) {
        Turno turnoBusccado = null;
        for(Turno turno : turnos){
            if(turno.getId()==id){
                turnoBusccado=turno;
            }
        }
        return turnoBusccado;
    }

    @Override
    public Turno buscarXCriterio(String criterio) {
        return null;
    }

    @Override
    public void eliminar(int id) {
        Turno turnoBusccado = buscarXId(id);
        turnos.remove(turnoBusccado);
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        return guardar(turno);
    }
}
