import lombok.Lombok;
import model.User;
import service.BookService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Бардык Id лер уникальный болуш керек. Эгер уникальный болбосо озубуз тузгон UniqueConstraintException класс ыргытсын.
        // User дин email адресси уникальный болуш керек жана @ болуусу керек. Эгер уникальный болбосо UniqueConstraintException класс,
        // @ болбосо озубуз тузгон BadRequestException класс ыргытсын.
        // User дин телефон номери +996 дан башталып 13 символдон турсун. Болбосо BadRequestException класс ыргытсын.
        // Китептин баасы терс сан болбошу керек. Болбосо NegativeNumberException ыргытсын.
        // Китептин чыккан жылы келечек убакыт болбошу керек.Болбосо DateTimeException ыргытсын.
        // Китептин автору бош болбошу керек. Болбосо EmptyStackException ыргытсын.


        BookServiceImpl bookService = new BookServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        List<User> users = new ArrayList<>();
        Long id = null;

        while (true) {
            String press = new Scanner(System.in).next();
            switch (press) {
                case "1" -> userService.createUser(users);
                case "2" -> userService.findUserById(id);
                case "3" -> userService.findAllUsers();
                case "4" -> {
                    String name = "";
                    userService.removeUserByName(name);
                }
//                case "5" ->
//                case "6" ->
//                case "7" ->
//                case "8" ->
//                case "9" ->
//                case "10" ->
//                case "11" ->
//                case "12" ->
//                case "13" ->
//                case "14" ->
//                case "15" ->
                default -> System.out.println("Wrong command!");

            }
        }
    }
        public static void commands() {
            System.out.println("""
                1  -  
                2  - 
                3  -
                4  -
                5  -
                6  -
                7  -
                8  -
                9  -
                10 -
                11 -
                12 -
                13 -
                14 -
                15 -
                """);
        }



}