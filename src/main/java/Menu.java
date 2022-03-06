import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;
    private String userName,password;

    public void mainMenu(){
        System.out.println("\n**********WELCOME**********");
        System.out.println("1-Enter.");
        System.out.println("2-Exit.");
        System.out.print("Please enter your command:");
        command = input.nextInt();
        switch(command) {
            case 1:
                //
                break;
            case 2:
                System.out.println("Have a nice day!");
                System.exit(0);
            default:
                System.out.println("you enter a wrong number!");
        }
    }

    public int publicLogin(){
        System.out.print("Please enter your username:");
        userName = input.nextLine();
        System.out.print("Please enter your password:");
        password = input.nextLine();
        if( userName.equals("admin" ) && password.equals("admin"))
            return 1;
        else
            return 0;
    }

    public void clerkMenu(){

    }

    public void studentMenu(){

    }

    public void professorMenu(){

    }



















}
