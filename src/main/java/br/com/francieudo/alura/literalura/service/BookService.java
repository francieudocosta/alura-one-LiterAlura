package br.com.francieudo.alura.literalura.service;

import br.com.francieudo.alura.literalura.model.*;
import br.com.francieudo.alura.literalura.repository.AuthorRepository;
import br.com.francieudo.alura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void saveDataBooks(DataBooks dataBooks){

        if(dataBooks.authors() == null || dataBooks.authors().isEmpty()){
            throw new IllegalArgumentException("Impossivel salvar, livro sem autor especificado!");
        }

        DataAuthors dataAuthor = dataBooks.authors().get(0);

        Optional<Author> searchAuthor = authorRepository.findAll()
                .stream()
                .filter(a -> a.getName().equalsIgnoreCase(dataAuthor.name()))
                .findFirst();

        Author author = searchAuthor.orElseGet(() -> {

                    Author newAuthor = mapToEntity(dataAuthor);
                    return authorRepository.save(newAuthor);
                });

        Book book = mapToEntity(dataBooks, author);
        bookRepository.save(book);
    }

    public Optional<List<Book>> getRegisteredBook(){

        List<Book> books =  bookRepository.findAll();

        return books.isEmpty() ? Optional.empty() : Optional.of(books);
    }

    public Optional<List<Book>> getLanguageBookFor(String encode){

        List<Book> books = bookRepository.findBooksByLanguages(Languages.fromAcronym(encode));

        return books.isEmpty() ? Optional.empty() : Optional.of(books);
    }

    private Book mapToEntity(DataBooks dataBooks, Author author){

        Book book = new Book();

        book.setTitle(dataBooks.title());
        book.setLanguages(Languages.fromAcronym(dataBooks.languages().get(0)));
        book.setDownloadCount(dataBooks.downloadCount());
        book.setAuthor(author);

        return book;
    }

    private Author mapToEntity(DataAuthors dataAuthor){

        Author author = new Author();
        author.setName(dataAuthor.name());
        author.setBirthYear(dataAuthor.birthYear());
        author.setDeathYear(dataAuthor.deathYear());

        return author;
    }
}
