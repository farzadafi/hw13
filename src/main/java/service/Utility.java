package service;

import service.exception.InvalidName;
import service.exception.InvalidNationalId;
import service.exception.InvalidPassword;

import java.util.Scanner;

public class Utility {
    private final Scanner input = new Scanner(System.in);
    private String fullName,nationalId,password;

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

    public String setNationalId(){
        while(true){
            System.out.print("Enter National Id(just number):");
            try {
                nationalId = input.nextLine();
                nationalIdChecker(nationalId);
                break;
            }catch (InvalidNationalId except){
                System.out.println(except.getMessage());
            }
        }
        return nationalId;
    }

    public String setPassword(){
        while(true) {
            System.out.print("Enter your password:");
            try {
                password = input.nextLine();
                passwordCheck(password);
                break;
            } catch (InvalidPassword except) {
                System.out.println(except.getMessage());
            }
        }
        return password;
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

    public void nationalIdChecker(String nationalId){
        if(nationalId.length() > 10 )
            throw new InvalidNationalId("national Id can't more than ten number!");
        if(nationalId.equals(""))
            throw new InvalidNationalId("dont enter space!");
        for (Character ch:nationalId.toCharArray()) {
            if(!Character.isDigit(ch))
                throw new InvalidNationalId("national Id should be just number!");
        }
    }

    public void passwordCheck(String password){
        if(password.length() < 3 )
            throw new InvalidPassword("password should be more than 2 ");
        char[] passwordArray = password.toCharArray();
        char[] signArray =  new char[] {'!','@','#','$','%','^','&','*','(',')','-','+','=','.',',','>','<','?','/','|',':',';'};
        int space = 0,lowerCase = 0,upperCase = 0,sign = 0,digit = 0;
        for(int i=0;i<passwordArray.length;i++)
            if(Character.isSpaceChar(passwordArray[i]))
                ++space;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isUpperCase(passwordArray[i]))
                ++upperCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isLowerCase(passwordArray[i]))
                ++lowerCase;
        for(int i = 0;i<passwordArray.length;i++)
            if(Character.isDigit(passwordArray[i]))
                ++digit;
        for(int i=0;i<signArray.length;i++)
            for(int j=0;j<passwordArray.length;j++)
                if(signArray[i] == passwordArray[j])
                    ++sign;
        if( (space == 0) || (lowerCase == 0) || (upperCase == 0) || (sign == 0) || (digit == 0) )
            throw new InvalidPassword("password should have space+lowerCase+upperCase+sign+digit!");
    }




}
