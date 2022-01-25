package com.robin.encuesta.controller;

import com.robin.encuesta.dto.Encuesta;
import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.TipoPregunta;
import com.robin.encuesta.service.EncuestaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EncuestaControllerTest {
    @Mock
    EncuestaService encuestaService;
    @InjectMocks
    EncuestaController encuestaController;

    @Test
    void create() {
        Pregunta pregunta = Pregunta.of(1,"Que tal te parecio el menu", TipoPregunta.ABIERTA,1,null);
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        Encuesta encuesta = Encuesta.of(1,"Encuesta sobre menu",preguntas);
        ResponseEntity<Encuesta> responseEntity = ResponseEntity.ok(encuesta);
        Mockito.when(encuestaService.CrearEncuesta(Mockito.any())).thenReturn(encuesta);
        assertEquals(responseEntity,encuestaController.create(encuesta));
    }
    @Test
    void createNull() {
        Pregunta pregunta = Pregunta.of(1,"Que tal te parecio el menu", TipoPregunta.ABIERTA,1,null);
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        Encuesta encuesta = Encuesta.of(1,"Encuesta sobre menu",preguntas);
        ResponseEntity<Encuesta> responseEntity = ResponseEntity.badRequest().body(null);
        Mockito.when(encuestaService.CrearEncuesta(Mockito.any())).thenReturn(null);
        assertEquals(responseEntity,encuestaController.create(encuesta));
    }

    @Test
    void get() {
        Pregunta pregunta = Pregunta.of(1,"Que tal te parecio el menu", TipoPregunta.ABIERTA,1,null);
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        Encuesta encuesta = Encuesta.of(1,"Encuesta sobre menu",preguntas);
        ResponseEntity<Encuesta> responseEntity = ResponseEntity.ok(encuesta);
        Mockito.when(encuestaService.getEncuesta(Mockito.anyInt())).thenReturn(encuesta);
        assertEquals(responseEntity,encuestaController.get(1));
    }
    @Test
    void getNull() {
        Pregunta pregunta = Pregunta.of(1,"Que tal te parecio el menu", TipoPregunta.ABIERTA,1,null);
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        Encuesta encuesta = Encuesta.of(1,"Encuesta sobre menu",preguntas);
        ResponseEntity<Encuesta> responseEntity = ResponseEntity.badRequest().body(null);
        Mockito.when(encuestaService.getEncuesta(Mockito.anyInt())).thenReturn(null);
        assertEquals(responseEntity,encuestaController.get(1));
    }
}