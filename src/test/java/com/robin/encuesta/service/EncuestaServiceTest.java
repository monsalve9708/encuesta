package com.robin.encuesta.service;

import com.robin.encuesta.controller.EncuestaController;
import com.robin.encuesta.dao.EncuestaDao;
import com.robin.encuesta.dao.PreguntaDao;
import com.robin.encuesta.dao.RespuestaDao;
import com.robin.encuesta.dto.Encuesta;
import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.TipoPregunta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncuestaServiceTest {
    @Mock
    EncuestaDao encuestaDao;
    @Mock
    PreguntaDao preguntaDao;
    @Mock
    RespuestaDao respuestaDao;
    @InjectMocks
    EncuestaService encuestaService;


    @Test
    void create() {
        int response = 1;
        Pregunta pregunta = Pregunta.of(1,"Que tal te parecio el menu", TipoPregunta.ABIERTA,1,null);
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        Encuesta encuesta = Encuesta.of(1,"Encuesta sobre menu",preguntas);
        Mockito.when(encuestaDao.crearEncuesta(Mockito.any())).thenReturn(response);
        assertEquals(encuesta,encuestaService.CrearEncuesta(encuesta));
    }

    @Test
    void get() {
        Pregunta pregunta = Pregunta.of(1,"Que tal te parecio el menu", TipoPregunta.ABIERTA,1,null);
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        Encuesta encuesta = Encuesta.of(1,"Encuesta sobre menu",preguntas);
        Mockito.when(encuestaDao.getEncuesta(Mockito.anyInt())).thenReturn(encuesta);
        Mockito.when(preguntaDao.getPregunta(Mockito.anyInt())).thenReturn(preguntas);
        assertEquals(encuesta,encuestaService.getEncuesta(1));
    }

}