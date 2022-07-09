package com.dh.C23.controller;

import com.dh.C23.dominio.Odontologo;
import com.dh.C23.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listaOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologos(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable int id){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(id);
        if(odontologoBuscado != null){
            return ResponseEntity.ok(odontologoBuscado);
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(odontologo.getId());
        if(odontologoBuscado != null){
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable int id){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(id);
        if(odontologoBuscado != null){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Se eliminó al odontólogo con id = " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar al odontólogo con id = " + id + "al no ser encontrado en la base de datos");
        }
    }

}
