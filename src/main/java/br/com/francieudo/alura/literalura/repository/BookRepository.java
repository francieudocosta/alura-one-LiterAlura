package br.com.francieudo.alura.literalura.repository;

import br.com.francieudo.alura.literalura.model.Book;
import br.com.francieudo.alura.literalura.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByLanguages(Languages languages);
}
