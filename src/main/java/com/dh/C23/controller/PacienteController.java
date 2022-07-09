package com.dh.C23.controller;

import com.dh.C23.dominio.Paciente;
import com.dh.C23.service.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public Paciente traerPacientePorId(@PathVariable("id") int id){
        return pacienteService.buscarXId(id);
    }

    @PostMapping
    public Paciente registrarNuevoPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    /*
    @GetMapping("/paciente")
    public String traerPacienteXEmail(Model model, @RequestParam("email") String email){
        Paciente paciente = pacienteService.buscarXEmail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "paciente";
    }
    */
}
