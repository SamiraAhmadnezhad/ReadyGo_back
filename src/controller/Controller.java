package controller;

import java.io.*;
import java.util.Scanner;

public class Controller {
    private String giveAllBooks() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/data/books.txt"));
        String result="";
        while(scan.hasNextLine()) {
            result += scan.nextLine()+"**";
        }
        scan.close();
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
        String s=emailAndPassAndusername[2]+",,"+emailAndPassAndusername[0]+",,"+emailAndPassAndusername[1]+",, ,,0 ,, ,, 0,,0,,false,, ,, ,, ,, ,,";
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
                    return "Password is incorrect!\n";
                }
                else {
                    scan.close();
                    return "Login successfully\n" + s;
                }
        }
        scan.close();
        return "User not found!\n";
    }
    private String changeUser(String data) throws IOException {
        String[] usernameAndUser = data.split("!!!");
        Scanner scan = new Scanner(new File("src/data/users.txt"));
        String result="";
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            if (s.contains(usernameAndUser[0]+",,")) {
                result+=usernameAndUser[1]+",,\n";
                continue;
            }
            result+=s+"\n";
        }
        FileWriter fileWriter=new FileWriter("src/data/users.txt" ,false);
        fileWriter.write(result);
        fileWriter.close();
        scan.close();
        return "chage user successfully!";
    }
    private String changeBook(String data) throws IOException {
        System.out.println("start");
        String[] usernameAndUser = data.split("!!!");
        Scanner scan = new Scanner(new File("src/data/books.txt"));
        String result="";
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            if (s.contains(usernameAndUser[0])) {
                result+=usernameAndUser[1]+"\n";
                continue;
            }
            result+=s+"\n";
        }
        System.out.println(result);
        FileWriter fileWriter=new FileWriter("src/data/books.txt" ,false);
        fileWriter.write(result);
        fileWriter.close();
        scan.close();
        return "chage book successfully!";
    }
    public  String run (String command, String data) throws IOException {
        switch (command){
            case "changeBook":
                System.out.println("go");
                return changeBook(data);
            case "changeUser":
                return changeUser(data);
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
