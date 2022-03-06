public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while (true) {
            switch (menu.mainMenu()) {
                case 1:
                    menu.publicLogin();
                    break;

                case 2:
                    System.out.println("Have a nice day!");
                    System.exit(0);

                case 3:
                    System.out.println("You enter a wrong number");
                    break;
            }
        }
    }
}

