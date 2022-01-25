package com.robin.encuesta.controller;

import com.robin.encuesta.dto.Encuesta;
import com.robin.encuesta.service.EncuestaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "api/v1/encuesta")
public class EncuestaController {
    EncuestaService encuestaService;

    @PostMapping(value = "/create")
    public ResponseEntity<Encuesta> create(@RequestBody Encuesta encuesta){
        Encuesta encuestaResponse = encuestaService.CrearEncuesta(encuesta);
        if (encuestaResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(encuestaResponse);
    }
    @GetMapping(value = "/get")
    public ResponseEntity<Encuesta> get(@RequestParam("cdencuesta") int idEncuesta){
        Encuesta encuestaResponse = encuestaService.getEncuesta(idEncuesta);
        if (encuestaResponse == null){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(encuestaResponse);
    }
}
