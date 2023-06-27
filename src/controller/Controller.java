package controller;

import database.Database;
import utils.Convertor;

import java.io.*;
import java.util.Scanner;

public class Controller {
    private String giveAllBooks() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/data/books.txt"));
        String result="";
        while(scan.hasNextLine()) {
            result += scan.nextLine()+",,";
        }
        scan.close();
        //System.out.println(result);
        return result;
    }
    private String checkSingUp(String data) throws IOException {
        String[] emailAndPassAndusername = data.split(",,");
        Scanner scan = new Scanner(new File("src/data/users.txt"));
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            if (s.contains(emailAndPassAndusername[2]+",,")) {
                scan.close();
                return "username is unavailable";
            }
        }
        FileWriter fileWriter=new FileWriter("src/data/users.txt" ,true);
        String s=emailAndPassAndusername[2]+",,"+emailAndPassAndusername[0]+",,"+emailAndPassAndusername[1]+",, ,, ,, ,, ,,0,,false,, ,,0,,0000/0/0";
        System.out.println(s.split(",,").length);
        fileWriter.write(s+"\n");
        fileWriter.close();
        scan.close();
        return "Sing up successfully\n"+s;
    }

    private String checkLogin(String data) throws FileNotFoundException {
        String[] emailAndPass = data.split(",,");
        Scanner scan = new Scanner(new File("src/data/users.txt"));
        while(scan.hasNextLine()){
            String s=scan.nextLine();
            if (s.contains(emailAndPass[0]+",,"))
                if (!s.contains(emailAndPass[1]+",,")) {
                    scan.close();
                    return "Password is incorrect!";
                }
                else {
                    scan.close();
                    return "Login successfully\n" + s;
                }
        }
        scan.close();
        return "User not found!";
    }
    public  String run (String command, String data) throws IOException {
        switch (command){
            case "checkSingUp":
                return checkSingUp(data);
            case "checkLogin":
                return checkLogin(data);
            case "giveAllBooks":
                return giveAllBooks();
        }
        return "eshteb zadi!!!";
    }
}
