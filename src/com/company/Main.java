package com.company;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static ArrayList<String> books = new ArrayList<>();
    private static File logins = new File("userLogins.txt");

    public static void main(String[] args) {

        boolean quit = false;
        CreateFile();
        getLogInDetails();
        while (!quit) {
            if (anotherBook().equals("n")) {
                quit = true;
            } else {
                books.add(getBookDetails());
            }
        }

        getBookData(0);
    }

    public static String getBookDetails() {
        String bookTitle = getInput("Please enter the book title");
        int ISBN = getIsbn();
        String authorName = getInput("Please enter the author");
        String bookGenre = getInput("Please enter the book genre");
        return (bookTitle + "," + ISBN + "," + authorName + "," + bookGenre);
    }

    private static int getIsbn() {
        int ISBN;
        while (true) {
            try {
                ISBN = Integer.parseInt(getInput("Please enter the ISBN of the book"));
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
        }
        return ISBN;
    }

    public static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void getBookData(int dataPart) {
        for (String book : books) {
            String[] bookDetails = book.split(",");
            System.out.println(bookDetails[dataPart]);
        }
    }


    public static String anotherBook() {
        String enterBook;
        do {
            enterBook = getInput("Would you like to add another book? PLease enter y or n: ");
        } while (!enterBook.equals("y") && (!enterBook.equals("n")));
        return enterBook;
    }

    public static void CreateFile() {
        try {
            if (logins.createNewFile()) {
                System.out.println("File created: " + logins.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void getLogInDetails() {
        String getUsername = getInput("ryland has hiv");
        String getPassword = getInput("please enter ur password");
        String userData = getUsername + "," + getPassword;
        if (!checkLogin(userData)){
            registerUser();
        }

    }

    public static boolean checkLogin(String userData) {
        boolean registeredUser = false;
        try {
            Scanner myReader = new Scanner(logins);
            while (myReader.hasNextLine() || !registeredUser) {
                String data = myReader.nextLine();
                if (data.equals(userData)) {
                    registeredUser = true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return registeredUser;
    }

    public static void registerUser(){
        System.out.println("you are registering now as u did not have login details in our database");
        String getUsername = getInput("please enter ur name");
        String getPassword = getInput("please enter ur password");
        String userData = getUsername + "," + getPassword;
        WriteToFile(userData);
    }

    public static void WriteToFile(String userData) {
        try {
            FileWriter myWriter = new FileWriter(logins.getName(), true);
            myWriter.write(userData+"\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}