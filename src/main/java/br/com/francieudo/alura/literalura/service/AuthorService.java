package br.com.francieudo.alura.literalura.service;

import br.com.francieudo.alura.literalura.model.Author;
import br.com.francieudo.alura.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Optional<List<Author>> getRegistredAuthors(){

        List<Author> authors = authorRepository.findAll();

        return authors.isEmpty() ? Optional.empty() : Optional.of(authors);
    }

    public Optional<List<Author>> getAuthorAliveInTheYear(Integer year){

        List<Author> authors = authorRepository.authorsAliveInTheYear(year);

        return authors.isEmpty() ? Optional.empty() : Optional.of(authors);
    }
}
