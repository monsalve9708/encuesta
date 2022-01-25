package com.robin.encuesta.serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.google.gson.*;
import com.robin.encuesta.dto.Respuesta;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class RespuestasAdapter implements JsonSerializer<Respuesta>, JsonDeserializer<Respuesta> {

    @Override
    public Respuesta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return Respuesta.of(jsonObject.get("id").getAsInt(),jsonObject.get("idPregunta").getAsInt(),jsonObject.get("descripcion").getAsString(),jsonObject.get("respuestaCorrecta").getAsInt());
    }

    @Override
    public JsonElement serialize(Respuesta src, Type typeOfSrc, JsonSerializationContext context) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id",src.getId());
        parameters.put("idPregunta",src.getIdPregunta());
        parameters.put("descripcion",src.getDescripcion());
        parameters.put("respuestaCorrecta",src.getRespuestaCorrecta());
        return context.serialize(parameters);
    }
}
