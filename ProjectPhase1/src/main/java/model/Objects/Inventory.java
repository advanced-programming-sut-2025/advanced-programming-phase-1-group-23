package model.Objects;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Tool> tools=new ArrayList<>();

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void setTools(ArrayList<Tool> tools) {
        this.tools = tools;
    }
}
