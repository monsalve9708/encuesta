package com.robin.encuesta.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;

@Value(staticConstructor = "of")
public class Encuesta {
    int id;
    String nombre;
    ArrayList<Pregunta> preguntas;

}
