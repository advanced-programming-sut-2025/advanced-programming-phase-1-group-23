package model.enums;

import view.*;

public enum Menus {
    LoginMenu(new LoginMenu()),
    GameMenu(new GameMenu()),
    MainMenu(new MainMenu()),
    ProfileMenu(new ProfileMenu()),

    ;

    private final AppMenu menu;

    Menus(AppMenu menu) {
        this.menu=menu;
    }
}
