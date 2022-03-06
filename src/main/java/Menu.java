import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;

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

}
