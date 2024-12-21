package br.com.francieudo.alura.literalura.luancher;

import br.com.francieudo.alura.literalura.model.Author;
import br.com.francieudo.alura.literalura.model.Book;
import br.com.francieudo.alura.literalura.model.DataBooks;
import br.com.francieudo.alura.literalura.service.AuthorService;
import br.com.francieudo.alura.literalura.service.BookService;
import br.com.francieudo.alura.literalura.service.ConsumptionAPI;
import br.com.francieudo.alura.literalura.service.ConvertData;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainMenu {

    private Scanner input = new Scanner(System.in);
    private final ConsumptionAPI consumptionAPI = new ConsumptionAPI();
    private final ConvertData convertData = new ConvertData();
    private final String URL = "https://gutendex.com/books/";
    @Autowired
    private BookService bookService ;
    @Autowired
    private AuthorService authorService;

    public void displayMenu(){

        var option = -1;

        while(option != 0){

            var menu = """
                    **** Seja Bem vindo ao LiterAlura! ***
                    
                    **************************************
                    ESCOLHA UM OPÇÃO ABAIXO:
                    
                    **************************************
                    
                    1- Buscar livro pelo titulo
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivo em um determinado ano
                    5- Listar livros em um determinado idioma
                    0- Sair;
                    
                    """;

            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();

            switch (option){

                case 1:
                    searchForBookByTitleByAPI();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listAuthorAliveInYear();
                    break;
                case 5:
                    listLanguageBookIn();
                    break;
                case 0:
                    System.out.println("Bye! Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void listLanguageBookIn() {

        String opcoes = """
                Insira o idioma para realizar a busca:
                
                es - espanhol
                en - Inglês
                fr - francês
                pt - português
                
                """;

        System.out.println(opcoes);
        var idioma = input.nextLine();

        Optional<List<Book>> books = bookService.getLanguageBookFor(idioma);
        books.ifPresentOrElse(
                //Se há livros, a lista é passadacomo argumento
                b -> {
                    System.out.println("Livros no idioma: " + idioma + " :  \n");
                    b.forEach(this::formatBookInfo);
                },
                () -> System.out.println("Nenhum livro encontrado")
        );
    }

    private void listAuthorAliveInYear() {

        System.out.println("Digite o ano de referência para saber os autores vivos: ");
        var year = input.nextInt();

        Optional<List<Author>> authorsAlive = authorService.getAuthorAliveInTheYear(year);

        authorsAlive.ifPresentOrElse(
                a -> {
                    System.out.println("Autores Vivos em " + year + ": \n");
                    a.forEach(this::formatAuthorInfo);
                },
                () -> System.out.println("Nenhum autor encontrado!")
        );
    }

    private void listRegisteredAuthors() {

        Optional<List<Author>> registeredAuthors = authorService.getRegistredAuthors();

        registeredAuthors.ifPresentOrElse(
                a -> {
                    System.out.println("Autores Registrados: \n");
                    a.forEach(this::formatAuthorInfo);
                },
                () -> System.out.println("Nenhum autor encontrado!")
        );
    }

    private void listRegisteredBooks() {

        Optional<List<Book>> registeredBooks = bookService.getRegisteredBook();

        registeredBooks.ifPresentOrElse(
                //Se há livros, a lista é passadacomo argumento
                b -> {
                    System.out.println("Livros Registrados: \n");
                    b.forEach(this::formatBookInfo);
                },
                () -> System.out.println("Nenhum livro encontrado")
        );
    }

    private void searchForBookByTitleByAPI() {

        System.out.println("Digite o titulo ou nome autor do livro que deseja procurar: ");
        var title = input.nextLine();

        String json = consumptionAPI.getData(URL + "?search="+ URLEncoder.encode(title, StandardCharsets.UTF_8));

        List<DataBooks> dataBooks = convertData.getDados(json, new TypeReference<List<DataBooks>>() {});

        System.out.println("Digite o titulo do livro que deseja salvar");
        System.out.println("****************");
        dataBooks.forEach(n ->
                System.out.println("Titulo "+n.title() + "\n"+"Autores: " + "\n" + n.authors() + "\n" + "Idioma: " + n.languages().get(0) + "\n"
                + "Quantidade de downloads: " + n.downloadCount())
        );
        System.out.println("****************");

        var opcaoEscolhida = input.nextLine();

        Optional<DataBooks> databookChoice = dataBooks.stream().filter(n -> n.title().trim().equalsIgnoreCase(opcaoEscolhida.trim())).findFirst();


        databookChoice.ifPresent(books -> bookService.saveDataBooks(books));
    }

    private void formatBookInfo(Book book){

        System.out.printf(
                "Título: %s\nAutor: %s\nIdioma: %s\nQuantidade de download: %d\n\n%n",
                book.getTitle(),
                book.getAuthor(),
                book.getLanguages(),
                book.getDownloadCount()
        );
    }

    private void formatAuthorInfo(Author author){

        System.out.printf(
                "Nome do autor: %s\nAno de nascimento: %d\nAno de Falecimento: %d\n\n%n",
                author.getName(),
                author.getBirthYear(),
                author.getDeathYear()
        );
    }
}
