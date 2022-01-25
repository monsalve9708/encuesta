package com.robin.encuesta.serializable;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.Respuesta;
import com.robin.encuesta.dto.TipoPregunta;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreguntaSerializable implements JsonSerializer<Pregunta>, JsonDeserializer<Pregunta> {
    Type respuesta = new TypeToken<ArrayList<Respuesta>>(){}.getType();
    @Override
    public Pregunta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement respuestaJson = jsonObject.get("respuestas").getAsJsonObject();
        ArrayList<Respuesta> respuestas = context.deserialize(respuestaJson,respuesta);

        return Pregunta.of(jsonObject.get("id").getAsInt(),jsonObject.get("descripcion").getAsString(), TipoPregunta.valueOf(jsonObject.get("TipoPregunta").getAsString()),
                jsonObject.get("idEncuesta").getAsInt(),respuestas);
    }

    @Override
    public JsonElement serialize(Pregunta src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id",src.getId());
        parameters.put("descripcion",src.getDescripcion());
        parameters.put("TipoPregunta",src.getTipoPregunta());
        parameters.put("idEncuesta",src.getIdEncuesta());
        parameters.put("respúestas",src.getRespuestas());
        return context.serialize(parameters);
    }
}
