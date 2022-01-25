package com.robin.encuesta.serializable;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.robin.encuesta.dto.Encuesta;
import com.robin.encuesta.dto.Pregunta;
import com.robin.encuesta.dto.Respuesta;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EncuestaSerializable implements JsonSerializer<Encuesta>, JsonDeserializer<Encuesta> {
    Type pregunta = new TypeToken<ArrayList<Pregunta>>(){}.getType();
    @Override
    public Encuesta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement preguntaJson = jsonObject.get("preguntas").getAsJsonObject();
        ArrayList<Pregunta> preguntas = context.deserialize(preguntaJson,pregunta);

        return Encuesta.of(jsonObject.get("id").getAsInt(),jsonObject.get("nombre").getAsString(),preguntas);
    }

    @Override
    public JsonElement serialize(Encuesta src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id",src.getId());
        parameters.put("nombre",src.getNombre());
        parameters.put("preguntas",src.getPreguntas());
        return context.serialize(parameters);
    }
}
