package view;

import java.util.Scanner;
import model.Basics.App;
import model.enums.Menus;

public class AppView {
    public final static Scanner scanner = new Scanner(System.in);

    public void run() {
        while (App.getCurrentMenu() != Menus.ExitMenu) {
            String input = scanner.nextLine().trim();
            App.getCurrentMenu().getMenu().check(scanner);
        }
    }
}
