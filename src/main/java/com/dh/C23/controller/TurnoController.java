package com.dh.C23.controller;

import com.dh.C23.repository.OdontologoDAOH2;
import com.dh.C23.repository.TurnoDAO;
import com.dh.C23.dominio.Odontologo;
import com.dh.C23.dominio.Paciente;
import com.dh.C23.dominio.Turno;
import com.dh.C23.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private ITurnoService turnosService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        Paciente pacienteBuscado = pacienteService.buscarXId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(turno.getOdontologo().getId());
        if (pacienteBuscado != null & odontologoBuscado != null){
            respuesta = ResponseEntity.ok(turnosService.registrarTurno(turno));
            if(pacienteBuscado.equals(turno.getPaciente()) &&  odontologoBuscado.equals(turno.getOdontologo())){
                respuesta = ResponseEntity.ok(turnosService.registrarTurno(turno));
            } else {
                respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> traerTurnos(){
        return ResponseEntity.ok(turnosService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable int id){
        Turno turnoBuscado = turnosService.buscarTurnoXId(id);
        if(turnoBuscado != null){
            return ResponseEntity.ok(turnoBuscado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        Turno turnoBuscado = turnosService.buscarTurnoXId(turno.getId());
        if(turnoBuscado != null){
            return ResponseEntity.ok(turnosService.actualizarTurno(turno));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable int id){
        Turno turnoBuscado = turnosService.buscarTurnoXId(id);
        if(turnoBuscado != null){
            turnosService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó al turno con id = " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar al turno con id = " + id + "al no ser encontrado en la base de datos");
        }
    }
}
