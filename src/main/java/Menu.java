import entity.Student;
import entity.UserAccount;
import repository.GenericRepositoryImpel;
import service.LoginService;
import service.StudentService;

import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;
    private String userName,password;
    private final StudentService studentService = new StudentService();
    private final LoginService loginService = new LoginService();

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

    public void clerkMenu() {
        boolean finalWhile = true;
        while (finalWhile) {
            System.out.println("\n****** Hi!******");
            System.out.println("1-Student Tools.");
            System.out.println("2-Professor Tools.");
            System.out.println("3-Clerk Tools.");
            System.out.println("4-Unit Tools.");
            System.out.println("5-View Salary bill.");
            System.out.println("6-Exit.");
            System.out.print("Please select:");
            command = input.nextInt();
            input.nextLine();
            switch (command) {
                case 1:
                    System.out.println("\n1-Register Student.");
                    System.out.println("2-Delete Student.");
                    System.out.println("3-Edit Student.");
                    System.out.print("Please select a number:");
                    command = input.nextInt();
                    input.nextLine();
                    switch (command) {
                        case 1:
                            studentService.add();
                            break;

                        case 2:
                            break;

                        case 3:
                            break;

                        default:
                            System.out.println("you enter a wrong number!");
                    }
                    break;


            }//switch
        }//while
    }//clerkMenu

    public void studentMenu(){

    }

    public void professorMenu(){

    }



















}
