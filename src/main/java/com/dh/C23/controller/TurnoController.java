package com.dh.C23.controller;

import com.dh.C23.dao.OdontologoDAOH2;
import com.dh.C23.dao.TurnoDAO;
import com.dh.C23.dominio.Odontologo;
import com.dh.C23.dominio.Paciente;
import com.dh.C23.dominio.Turno;
import com.dh.C23.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private ITurnoService turnosService = new TurnoServiceImpl(new TurnoDAO());
    private IPacienteService pacienteService = new PacienteServiceImpl();
    private IOdontologoService odontologoService = new OdontologoServiceImpl(new OdontologoDAOH2());

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        Paciente pacienteBuscado = pacienteService.buscarXId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(turno.getOdontologo().getId());
        if (pacienteBuscado != null & odontologoBuscado != null){
            respuesta = ResponseEntity.ok(turnosService.registrarTurno(turno));
        } else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> traerTurnos(){
        return ResponseEntity.ok(turnosService.listarTurnos());
    }
}
