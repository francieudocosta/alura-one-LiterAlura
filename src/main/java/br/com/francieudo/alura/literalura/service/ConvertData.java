package br.com.francieudo.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getDados(String json, Class<T> tClass) {

        try {

            return mapper.readValue(json, tClass);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getDados(String json, TypeReference<T> typeReference){

        try{

            JsonNode rootNode = mapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results");
            
            return mapper.readValue(resultsNode.toString(), typeReference);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
