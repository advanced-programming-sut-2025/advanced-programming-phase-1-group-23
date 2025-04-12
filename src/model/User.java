package model;

import java.util.ArrayList;

public class User {
    private String username;
    private String nickname;
    private String password;
    private String gender;
    private String email;
    private ArrayList<String> craftingRecipes;
    private boolean sleep;
    private int energy;

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public User(String username, String nickname, String password, String gender, String email) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.email = email;
        craftingRecipes = new ArrayList<>();
        sleep = false;
        energy = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getCraftingRecipes() {
        return craftingRecipes;
    }

    public void setCraftingRecipes(ArrayList<String> craftingRecipes) {
        this.craftingRecipes = craftingRecipes;
    }
}
