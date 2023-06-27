package controller;

import database.Database;
import utils.Convertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
//    private String get(HashMap<String ,String > data){
//        int count =Integer.valueOf(data.get("count"));
//        ArrayList<HashMap<String,String>> list=Database.getInstance().getTable("massages").get();
//        ArrayList<HashMap<String,String>> finalList=new ArrayList<>();
//        for (int i=list.size()-1;i>=Math.max(0,list.size()-count);i--){
//            finalList.add(list.get(i));
//        }
//        return Convertor.arrMapToString(finalList);
//    }
    private String getBy(){
        return "";
    }

    private String checkLogin(String data) throws FileNotFoundException {
        String[] emailAndPass = data.split(",,");
        System.out.println(emailAndPass[0]+emailAndPass[1]);
        Scanner scan = new Scanner(new File("src/data/massages.txt"));
        while(scan.hasNextLine()){
            String s=scan.nextLine();
            if (s.contains(emailAndPass[0]+",,"))
                if (!s.contains(emailAndPass[1]+",,"))
                    return "Password is incorrect!";
                else
                    return "Login successfully\n"+s;
        }
        return "User not found!";
    }
    public  String run (String command, String data) throws FileNotFoundException {
        //HashMap<String , String> dataMap= Convertor.stringToMap(data);
        switch (command){
//            case "get":
//                return get(dataMap);
            case "getBy":
                return getBy();
            case "checkLogin":
                return checkLogin(data);
        }
        return "eshteb zadi!!!";
    }
}
