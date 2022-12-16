package service.impl;

import enums.Gender;
import exception.UniqueConstraintException;
import model.Book;
import model.User;
import service.UserService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    List<User> users = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    BookServiceImpl bookService = new BookServiceImpl();

    @Override
    public String createUser(List<User> users) {
        boolean isCreated = false;

        try {
            System.out.print("Set ID: ");
            Long id = new Scanner(System.in).nextLong();

            for (User user : users) {
                if (user.getId().equals(id))
                    throw new UniqueConstraintException("This ID is already taken. Try another ID!");
            }
            System.out.print("Enter your name: ");
            String name = new Scanner(System.in).next();
            if (name.matches("[0-9]*")) throw new Exception("Name can not contain numbers!");
            System.out.print("Surname: ");
            String surname = new Scanner(System.in).next();
            if (surname.matches("[0-9]*")) throw new Exception("Name can not contain numbers!");
            System.out.print("Set email: ");
            String email = new Scanner(System.in).next();
            for (User user : users) {
                if (user.getEmail().equals(email))
                    throw new UniqueConstraintException("This email is already taken. Try another email!");
            }
            System.out.print("Phone number: ");
            String phoneNumber = new Scanner(System.in).next();
            if (phoneNumber.matches("[a-zA-Z]*")) throw new Exception("Phone number cannot contain letters!");
            else if (phoneNumber.length() != 9) throw new Exception("Phone number must be 9 digits!");
            for (User user : users) {
                if (user.getPhoneNumber().equals(phoneNumber))
                    throw new UniqueConstraintException("Phone number you provided is already registered.");
            }

            System.out.println("Choose your gender");
            Gender gender;
            while (true) {
                System.out.print("""
                        1 - Male
                        2 - Female
                        Choose:""");
                String choice1 = new Scanner(System.in).next();
                if (choice1.equals("1")) {
                    gender = Gender.MALE;
                    break;
                } else if (choice1.equals("2")) {
                    gender = Gender.FEMALE;
                    break;
                } else {
                    System.out.println("Wrong command!");
                }
            }
            System.out.print("Top up your balance to continue: ");
            BigDecimal balance = new Scanner(System.in).nextBigDecimal();
            while (true) {
                System.out.print("""
                        Create book -
                        1 to create new book
                        2 to quit creating
                        press:""");
                String press = new Scanner(System.in).next();
                if (press.equalsIgnoreCase("1")) bookService.createBooks(books);
                else if (press.equalsIgnoreCase("2")) {
                    System.out.println("You quit");
                    break;
                } else System.out.println("Wrong command!");
            }


            users.add(new User(id, name, surname, email, phoneNumber, gender, balance, books));
            isCreated = true;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isCreated ? "User Successfully Created!" : "Something went wrong. User creation failed. Try again!";
    }

    @Override
    public List<User> findAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found");
        }
        {
            return users;
        }
    }

    @Override
    public User findUserById(Long id) {
        if (users.isEmpty()) {
            return null;
        } else {
            for (User user : users) {
                if (user.getId().equals(id)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public String removeUserByName(String name) {
        boolean isRemoved = false;
        if (!users.isEmpty()) {
            System.out.print("Enter a name: ");
            name = new Scanner(System.in).next();
            for (User user : users) {
                if (user.getName().equalsIgnoreCase(name)) {
                    users.remove(user);
                    isRemoved = true;
                }
            }
        } else {
            System.out.println("No users found!");
        }
        return isRemoved ? "User removed successfully!" : "Something went wrong. User removing failed!";
    }

    @Override
    public void updateUser(Long id) {
        try {
            if (users.isEmpty()) {
                System.out.println("No users found!");
            } else {
                System.out.print("Enter ID: ");
                id = new Scanner(System.in).nextLong();
                for (User user : users) {
                    if (user.getId().equals(id)) {
                        System.out.println("Please provide new information!");
                        System.out.print("Set ID: ");
                        Long newId = new Scanner(System.in).nextLong();
                        if (user.getId().equals(id))
                            throw new UniqueConstraintException("This ID is already taken. Try another ID!");
                        else user.setId(newId);
                        System.out.print("Enter your name: ");
                        String name = new Scanner(System.in).next();
                        if (name.matches("[0-9]*")) throw new Exception("Name can not contain numbers!");
                        else user.setName(name);
                        System.out.print("Surname: ");
                        String surname = new Scanner(System.in).next();
                        if (surname.matches("[0-9]*")) throw new Exception("Name can not contain numbers!");
                        else user.setSurname(surname);
                        System.out.print("Set email: ");
                        String email = new Scanner(System.in).next();
                        for (User user1 : users) {
                            if (user1.getEmail().equals(email))
                                throw new UniqueConstraintException("This email is already taken. Try another email!");
                        }
                        user.setEmail(email);
                        System.out.print("Phone number: ");
                        String phoneNumber = new Scanner(System.in).next();
                        if (phoneNumber.matches("[a-zA-Z]*"))
                            throw new Exception("Phone number cannot contain letters!");
                        else if (phoneNumber.length() != 9) throw new Exception("Phone number must be 9 digits!");
                        for (User user1 : users) {
                            if (user1.getPhoneNumber().equals(phoneNumber))
                                throw new UniqueConstraintException("Phone number you provided is already registered.");
                        }
                        user.setPhoneNumber(phoneNumber);
                        System.out.println("Choose your gender");
                        Gender gender;
                        while (true) {
                            System.out.print("""
                                    1 - Male
                                    2 - Female
                                    Choose:""");
                            String choice1 = new Scanner(System.in).next();
                            if (choice1.equals("1")) {
                                gender = Gender.MALE;
                                break;
                            } else if (choice1.equals("2")) {
                                gender = Gender.FEMALE;
                                break;
                            } else {
                                System.out.println("Wrong command!");
                            }
                        }
                        user.setGender(gender);
                        System.out.print("Top up your balance to continue: ");
                        BigDecimal balance = new Scanner(System.in).nextBigDecimal();
                        user.setMoney(balance);
                        List<Book> bookList = new ArrayList<>();
                        while (true) {
                            System.out.println("""
                                    Do you want to create new books?
                                    yes(create new book)
                                    no(leave old ones)
                                    enter:""");
                            String enter = new Scanner(System.in).next();
                            if (enter.equalsIgnoreCase("yes")) {
                                while (true) {
                                    System.out.print("""
                                            Create book -
                                            1 to create new book
                                            2 to quit creating
                                            press:""");
                                    String press = new Scanner(System.in).next();
                                    if (press.equalsIgnoreCase("1")) bookService.createBooks(bookList);
                                    else if (press.equalsIgnoreCase("2")) {
                                        System.out.println("You quit");
                                        break;
                                    } else System.out.println("Wrong command!");
                                }
                            } else if (enter.equalsIgnoreCase("no")) {
                                System.out.println("Books remained same!");
                                break;
                            } else System.out.println("Wrong command!");
                        }
                        user.setBooks(bookList);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void groupUsersByGender() {
        if (users.isEmpty()) System.out.println("No users found!");
        else {
            List<User> maleUsers = users.stream().filter(user -> user.getGender().equals(Gender.MALE)).toList();
            List<User> femaleUsers = users.stream().filter(user -> user.getGender().equals(Gender.FEMALE)).toList();
            System.out.println("Male users");
            maleUsers.forEach(System.out::println);
            System.out.println("Female users");
            femaleUsers.forEach(System.out::println);
        }

    }

    @Override
    public String buyBooks(String name, List<Book> books) {
        return null;
    }
}
