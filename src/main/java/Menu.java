import entity.Student;
import entity.UserAccount;
import repository.GenericRepositoryImpel;
import service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private int command;
    private String userName, password;
    private final StudentService studentService = new StudentService();
    private final ProfessorService professorService = new ProfessorService();
    private final ClerkService clerkService = new ClerkService();
    private final LoginService loginService = new LoginService();
    private final OfferLessonService offerLessonService = new OfferLessonService();
    private final LessonService lessonService = new LessonService();

    public int mainMenu() {
        System.out.println("\n**********WELCOME**********");
        System.out.println("1-Enter.");
        System.out.println("2-Exit.");
        System.out.print("Please enter your command:");
        command = giveInput();
        switch (command) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 3;
        }
    }

    public void publicLogin() {
        System.out.print("Please enter your username:");
        userName = input.nextLine();
        System.out.print("Please enter your password:");
        password = input.nextLine();
        if (userName.equals("admin") && password.equals("admin")) {
            UserAccount user = new UserAccount(0, "admin", "admin", "admin");
            clerkMenu(user);
        } else {
            UserAccount user = loginService.findByUserName(userName);
            if (user == null)
                System.out.println("You enter a wrong username");
            else if (user.getDiscriminatorValue().equals("Clerk") && user.getPassword().equals(password))
                clerkMenu(user);
            else if (user.getDiscriminatorValue().equals("Professor") && user.getPassword().equals(password))
                professorMenu(user);
            else if (user.getDiscriminatorValue().equals("Student") && user.getPassword().equals(password))
                studentMenu(user);
            else
                System.out.println("You enter wrong password!");
        }
    }

    public void clerkMenu(UserAccount user) {
        boolean finalWhile = true;
        while (finalWhile) {
            System.out.println("\n****** Hi " + user.getFullName() + "******");
            System.out.println("1-Student Tools.");
            System.out.println("2-Professor Tools.");
            System.out.println("3-Clerk Tools.");
            System.out.println("4-Unit Tools.");
            System.out.println("5-View Salary bill.");
            System.out.println("6-Exit.");
            System.out.print("Please select:");
            command = giveInput();
            switch (command) {
                case 1:
                    System.out.println("\n1-Register Student.");
                    System.out.println("2-Delete Student.");
                    System.out.println("3-Edit Student.");
                    System.out.print("Please select a number:");
                    command = giveInput();
                    switch (command) {
                        case 1:
                            studentService.add();
                            break;

                        case 2:
                            studentService.delete();
                            break;

                        case 3:
                            studentService.update();
                            break;

                        default:
                            System.out.println("you enter a wrong number!");
                    }
                    break;

                case 2:
                    System.out.println("\n1-Register Professor.");
                    System.out.println("2-Delete Professor.");
                    System.out.println("3-Edit Professor.");
                    System.out.print("Please select a number:");
                    command = giveInput();
                    switch (command) {
                        case 1:
                            professorService.add();
                            break;

                        case 2:
                            professorService.delete();
                            break;

                        case 3:
                            professorService.update();
                            break;

                        default:
                            System.out.println("You enter a wrong number!");
                    }
                    break;

                case 3:
                    System.out.println("\n1-Register Clerk.");
                    System.out.println("2-Delete Clerk.");
                    System.out.println("3-Edit Clerk.");
                    System.out.print("Please select a number:");
                    command = giveInput();
                    switch (command) {
                        case 1:
                            clerkService.add();
                            break;

                        case 2:
                            clerkService.delete();
                            break;

                        case 3:
                            clerkService.update();
                            break;
                    }
                    break;

                case 4:
                    System.out.println("\n1-Register unit.");
                    System.out.println("2-Delete unit.");
                    System.out.println("3-Edit unit.");
                    System.out.print("Please slect a number:");
                    command = giveInput();
                    switch (command) {
                        case 1:
                            offerLessonService.add();
                            break;

                        case 2:
                            offerLessonService.delete();
                            break;

                        case 3:
                            offerLessonService.update();
                            break;
                    }
                    break;

                case 5:
                    if (user.getId() == 0)
                        System.out.println("Your salary is:100000");
                    else
                        clerkService.showSalary(user);
                    break;

                case 6:
                    System.out.println("Good Luck!");
                    finalWhile = false;
                    break;

                default:
                    System.out.println("You enter a wrong number!");

            }
        }
    }

    public void studentMenu(UserAccount user) {
        boolean finalWhile = true;
        while (finalWhile) {
            System.out.println("\n****** Hi! " + user.getFullName() + " ******");
            System.out.println("1-view Offer lesson List.");
            System.out.println("2-Unit select.");
            System.out.println("3-view lesson and grade's.");
            System.out.println("4-Exit.");
            System.out.print("Please select a number:");
            command = giveInput();
            switch (command) {
                case 1:
                    studentService.showAllUnit();
                    break;

                case 2:
                    lessonService.add(user);
                    break;

                case 3:
                    studentService.showMyLesson(user);
                    break;

                case 4:
                    System.out.println("Good luck!");
                    finalWhile = false;
                    break;

                default:
                    System.out.println("you enter a wrong number!");
            }
        }
    }

    public void professorMenu(UserAccount user) {
        boolean falseWhile = true;
        while (falseWhile) {
            System.out.println("\n****** Hi! " + user.getFullName() + " ******");
            System.out.println("1-Register grad's Student.");
            System.out.println("2-view salary bill.");
            System.out.println("3-Exit.");
            System.out.print("please select a number:");
            command = giveInput();
            switch (command) {
                case 1:
                    professorService.registerGrade(user);
                    break;

                case 2:
                    professorService.calcSalary(user);
                    break;

                case 3:
                    System.out.println("Good luck!");
                    falseWhile = false;
                    break;

                default:
                    System.out.println("you enter a wrong number");
            }
        }
    }

    private Integer giveInput() {
        Integer i;
        while (true) {
            try {
                i = input.nextInt();
                input.nextLine();
                return i;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Just enter number please!");
            }
        }
    }
}
