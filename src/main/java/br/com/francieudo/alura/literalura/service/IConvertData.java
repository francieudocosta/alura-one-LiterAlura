package br.com.francieudo.alura.literalura.service;

public interface IConvertData {

    public <T> T getDados(String json, Class<T> object);
}
