package com.robin.encuesta.dao;

import com.robin.encuesta.dto.Encuesta;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class EncuestaDao {
    JdbcTemplate jdbcTemplate;

    public int crearEncuesta(Encuesta encuesta){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("encuesta").usingGeneratedKeyColumns("id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("nombre",encuesta.getNombre());
        return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
    }
    public Encuesta getEncuesta(int id){
        String sql = "SELECT id,nombre FROM encuesta where id = ?";
        Object[] args = {id};
        RowMapper<Encuesta> rowMapper = (rs, rowNum) -> Encuesta.of(rs.getInt("id"),rs.getString("nombre"),null);
        return Try.of(() -> jdbcTemplate.queryForObject(sql,rowMapper,args)).getOrNull();
    }

}
