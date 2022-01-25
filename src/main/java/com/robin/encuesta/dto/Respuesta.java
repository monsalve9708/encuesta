package com.robin.encuesta.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class Respuesta {
    int id;
    int idPregunta;
    String descripcion;
    int respuestaCorrecta;

}
