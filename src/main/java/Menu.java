import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;
    private String userName,password;

    public int mainMenu(){
        System.out.println("\n**********WELCOME**********");
        System.out.println("1-Enter.");
        System.out.println("2-Exit.");
        System.out.print("Please enter your command:");
        command = input.nextInt();
        switch(command) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 3;
        }
    }

    public void publicLogin(){
        input.nextLine();
        System.out.print("Please enter your username:");
        userName = input.nextLine();
        System.out.print("Please enter your password:");
        password = input.nextLine();
        if( userName.equals("admin" ) && password.equals("admin"))
            clerkMenu();
        else
            System.out.println("farzad");
    }

    public void clerkMenu(){

    }

    public void studentMenu(){

    }

    public void professorMenu(){

    }



















}
