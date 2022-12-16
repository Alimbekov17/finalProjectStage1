package service.impl;

import enums.Gender;
import enums.Genre;
import enums.Language;
import model.Book;
import service.BookService;

import javax.annotation.processing.SupportedSourceVersion;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookServiceImpl implements BookService {
    List<Book> books = new ArrayList<>();
    @Override
    public List<Book> createBooks(List<Book> books) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your id: ");
        Long id = scanner.nextLong();
        System.out.print("Enter name of book: ");
        String name = scanner.next();
        System.out.print("Genre: ");
        Genre setGenre = null;
        String genre = scanner.next();
        for (Genre value : Genre.values()) {
            if (value.name().equalsIgnoreCase(genre)){
                setGenre = value;
            }
        }
        System.out.print("Price: ");
        BigDecimal price = scanner.nextBigDecimal();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Language: ");
        String language = scanner.next();
        Language language1 = null;
        for (Language value : Language.values()) {
            if (language.equalsIgnoreCase(value.name())){
                language1 = Language.valueOf(language);
            }
        }
        System.out.print("Published year: ");
        LocalDate year = LocalDate.of(scanner.nextInt(), 1, 1);
        if (year.getYear() > LocalDate.now().getYear()) {
            throw new DateTimeException("Year cant be over 2022");
        }

        books.add(new Book(id, name, setGenre, price, author, language1, year.withYear(year.getYear())));
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        if (books.isEmpty()) System.out.println("No books found!");
        else {
            return books;
        }
        return null;
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        if (books.isEmpty()) System.out.println("No books found!");
        else {

        }
        return null;
    }

    @Override
    public Book removeBookById(Long id) {
        return null;
    }

    @Override
    public List<Book> sortBooksByPriceInDescendingOrder() {
        return null;
    }

    @Override
    public List<Book> filterBooksByPublishedYear() {
        return null;
    }

    @Override
    public List<Book> getBookByInitialLetter() {
        return null;
    }

    @Override
    public Book maxPriceBook() {
        return null;
    }
}
