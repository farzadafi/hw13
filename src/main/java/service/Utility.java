package service;

import exception.InvalidName;

import java.util.Scanner;

public class Utility {
    private Scanner input = new Scanner(System.in);
    private String fullName;
    private final InvalidName invalidName = new InvalidName();

    public String setFullName(){
        while(true){
            System.out.print("Enter name(just alpha):");
            try {
                fullName = input.nextLine();
                checkName(fullName);
                break;
            }catch (InvalidName except){
                System.out.println(except.getMessage());
            }
        }
        return fullName;
    }











    public void checkName(String name){
        if(name.length() < 3 )
            throw new InvalidName("name should be more than 2 character!");
        for (Character ch:name.toCharArray()
        ) {
            if(Character.isDigit(ch))
                throw new InvalidName("name can not have number!");
        }
        for (Character ch:name.toCharArray()
        ) {
            if(!Character.isAlphabetic(ch))
                throw new InvalidName("name can't have Sign(!,@,#,%,...)");
        }
    }



}
