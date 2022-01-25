package com.robin.encuesta.dto;

import lombok.Value;

import java.util.ArrayList;

@Value(staticConstructor = "of")
public class Pregunta {
    int id;
    String descripcion;
    TipoPregunta TipoPregunta;
    int idEncuesta;
    ArrayList<Respuesta> respuestas;

}
