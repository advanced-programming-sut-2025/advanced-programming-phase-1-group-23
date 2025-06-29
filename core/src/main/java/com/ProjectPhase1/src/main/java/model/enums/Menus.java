package model.enums;

import view.*;

public enum Menus {
    LoginMenu(new RegisterMenu()),
    GameMenu(new GameMenu()),
    MainMenu(new MainMenu()),
    ProfileMenu(new ProfileMenu()),
    AvatarMenu(new AvatarMenu()),
    HomeMenu(new HomeMenu()),
    ExitMenu(new ExitMenu());

    private final AppMenu menu;

    Menus(AppMenu menu) {
        this.menu = menu;
    }

    public AppMenu getMenu() {
        return menu;
    }

    public String string() {
        if (this == Menus.LoginMenu) {
            return "Login Menu";
        } else if (this == Menus.GameMenu) {
            return "Game Menu";
        } else if (this == Menus.MainMenu) {
            return "Main Menu";
        } else if (this == Menus.ProfileMenu) {
            return "Profile Menu";
        } else if (this == Menus.AvatarMenu) {
            return "Avatar Menu";
        } else if (this == Menus.HomeMenu) {
            return "Home Menu";
        }else{
            return "";
        }
    }


}
