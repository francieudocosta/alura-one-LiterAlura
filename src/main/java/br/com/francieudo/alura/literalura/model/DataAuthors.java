package br.com.francieudo.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataAuthors(
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear,
        @JsonAlias("name") String name
) { }
