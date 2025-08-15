package br.com.alura.challenge_LiterAlura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
