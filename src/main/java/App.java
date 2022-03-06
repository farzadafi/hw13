public class App {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while (true) {
            menu.mainMenu();
            switch (menu.publicLogin()) {
                case 0:
                    break;

                case 1:
                    menu.clerkMenu();
                    break;

                case 2:
                    menu.studentMenu();
                    break;

                case 3:
                    menu.professorMenu();
            }
        }
    }
}

