package com.dh.C23.controller;

import com.dh.C23.dominio.Odontologo;
import com.dh.C23.dominio.Paciente;
import com.dh.C23.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteServiceImpl pacienteService;

    @Autowired
    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> traerPacientes(){
        return pacienteService.listarPacientes();
    }

//    @GetMapping("/{id}")
//    public Paciente traerPacientePorId(@PathVariable("id") int id){
//        return pacienteService.buscarXId(id);
//    }

    @PostMapping
    public Paciente registrarNuevoPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable int id){
        Paciente pacienteBuscado = pacienteService.buscarXId(id);
        if(pacienteBuscado != null){
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado = pacienteService.buscarXId(paciente.getId());
        if(pacienteBuscado != null){
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable int id){
        Paciente pacienteBuscado = pacienteService.buscarXId(id);
        if(pacienteBuscado != null){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Se eliminó al paciente con id = " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar al paciente con id = " + id + "al no ser encontrado en la base de datos");
        }
    }


}
