package com.robin.encuesta.dao;

import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.TipoPregunta;
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
public class PreguntaDao {

    JdbcTemplate jdbcTemplate;

    public int crearPregunta(Pregunta pregunta, int idEncuesta){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("pregunta").usingGeneratedKeyColumns("id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("descripcion",pregunta.getDescripcion());
        parameters.put("tipopregunta",pregunta.getTipoPregunta());
        parameters.put("id_encuesta",idEncuesta);
        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }
    public List<Pregunta> getPregunta(int idEncuesta){
        String sql = "SELECT id,descripcion, tipopregunta,id_encuesta FROM pregunta where id_encuesta = ?";
        Object[] args = {idEncuesta};
        RowMapper<Pregunta> rowMapper = (rs, rowNum) -> Pregunta.of(rs.getInt("id"),rs.getString("descripcion"), TipoPregunta.valueOf(rs.getString("tipopregunta")),
                rs.getInt("id_encuesta"), null);
        return Try.of(() -> jdbcTemplate.query(sql,rowMapper,args)).getOrNull();
    }
}
