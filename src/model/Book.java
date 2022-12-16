package model;


import enums.Genre;
import enums.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Book {
    private Long id;
    private String name;
    private Genre genre;
    private BigDecimal price;
    private String author;
    private Language language;
    private LocalDate publishedYear;


}
