package br.com.alura.challenge_LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutores(
         @JsonAlias("birth_year") Integer anoNascimento,
         @JsonAlias("death_year") Integer anoFalecimento,
         @JsonAlias("name") String nome
) {
}
