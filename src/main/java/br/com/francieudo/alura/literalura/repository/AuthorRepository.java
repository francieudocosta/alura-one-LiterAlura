package br.com.francieudo.alura.literalura.repository;

import br.com.francieudo.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND a.deathYear > :year ")
    List<Author> authorsAliveInTheYear(Integer year);
}
