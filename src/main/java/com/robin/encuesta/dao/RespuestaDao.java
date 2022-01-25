package com.robin.encuesta.dao;

import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.Respuesta;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class RespuestaDao {
    JdbcTemplate jdbcTemplate;

    public int crearRespuesta(Respuesta respuesta, int idPregunta){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("respuesta").usingGeneratedKeyColumns("id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id_pregunta",idPregunta);
        parameters.put("descripcion",respuesta.getDescripcion());
        parameters.put("respuesta_correcta",respuesta.getRespuestaCorrecta());
        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }
    public List<Respuesta> getRespuesta(int idPregunta){
        String sql = "SELECT id,id_pregunta, descripcion,respuesta_correcta FROM respuesta where id_pregunta = ?";
        Object[] args = {idPregunta};
        RowMapper<Respuesta> rowMapper = (rs, rowNum) -> Respuesta.of(rs.getInt("id"),rs.getInt("id_pregunta"),rs.getString("descripcion"),rs.getInt("respuesta_correcta"));
        return Try.of(() -> jdbcTemplate.query(sql,rowMapper,args)).getOrNull();
    }
}
