package com.robin.encuesta.service;

import com.robin.encuesta.dao.EncuestaDao;
import com.robin.encuesta.dao.PreguntaDao;
import com.robin.encuesta.dao.RespuestaDao;
import com.robin.encuesta.dto.Encuesta;
import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.Respuesta;
import com.robin.encuesta.dto.TipoPregunta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EncuestaService {
    EncuestaDao encuestaDao;
    PreguntaDao preguntaDao;
    RespuestaDao respuestaDao;

    public Encuesta CrearEncuesta(Encuesta encuesta){
        int keyEncuesta = encuestaDao.crearEncuesta(encuesta);
        ArrayList<Pregunta> preguntas = new ArrayList<>();

        encuesta.getPreguntas().forEach(x -> {
            int keyPregunta = preguntaDao.crearPregunta(x,keyEncuesta);
            ArrayList<Respuesta> respuestas = new ArrayList<>();
            if (x.getTipoPregunta().equals(TipoPregunta.MULTIPLE)){
                x.getRespuestas().forEach(r -> {
                   int keyRespuesta = respuestaDao.crearRespuesta(r, keyPregunta);
                   respuestas.add(Respuesta.of(keyRespuesta,keyPregunta,r.getDescripcion(),r.getRespuestaCorrecta()));
                });
            }
            preguntas.add(Pregunta.of(keyPregunta,x.getDescripcion(),x.getTipoPregunta(),keyEncuesta,respuestas));
        });
        return Encuesta.of(keyEncuesta,encuesta.getNombre(),preguntas);
    }

    public Encuesta getEncuesta(int idEncuesta){
        Encuesta encuesta = encuestaDao.getEncuesta(idEncuesta);
        if (encuesta != null) {
            ArrayList<Pregunta> preguntas = new ArrayList<>();
            preguntaDao.getPregunta(encuesta.getId()).forEach(x -> {
                preguntas.add(Pregunta.of(x.getId(), x.getDescripcion(), x.getTipoPregunta(), x.getIdEncuesta(),(ArrayList<Respuesta>) respuestaDao.getRespuesta(x.getId())));
            });
        return Encuesta.of(encuesta.getId(),encuesta.getNombre(),preguntas);
        }
        return null;
    }
}
