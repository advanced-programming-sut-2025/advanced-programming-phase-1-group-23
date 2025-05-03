package model.Maps;

import model.Basics.App;

import java.util.ArrayList;

public class Farm {
    private ArrayList<Tile> cells;
    private ArrayList<Buildings> buildings;

    public Farm() {

    }

    public Farm(ArrayList<Tile> cells, ArrayList<Buildings> buildings) {
        this.cells = cells;
        this.buildings = buildings;
    }

    public void showFarm(int x, int y, int size) {
        int playerX = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getX();
        int playerY = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getY();

        for (Tile cell : cells) {
            Position coordinate = cell.getCoordinate();

            int xOfCell = coordinate.getX();
            int yOfCell = coordinate.getY();

            if (Math.abs(x - xOfCell) <= size / 2 && Math.abs(y - yOfCell) <= size / 2) {
                if (xOfCell == playerX && yOfCell == playerY)
                    System.out.print("\u001B[34m " + "P" + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("blue"))
                    System.out.print("\u001B[34m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("red"))
                    System.out.print("\u001B[31m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("green"))
                    System.out.print("\u001B[32m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("yellow"))
                    System.out.print("\u001B[33m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("black"))
                    System.out.print("\u001B[90m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("gray"))
                    System.out.print("\u001B[37m " + cell.getObjectOnCell().string() + "\033[0m");
                if (xOfCell - x == size / 2) {
                    System.out.print("\n");
                }
            }
        }
    }

    public void showEntireFarm() {
        int playerX = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getX();
        int playerY = App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getY();

        for (int i = 0; i < 152; i++) {
            System.out.print("_");
        }
        System.out.println();

        int cellIndex = 0;

        for (Tile cell : cells) {
            if (cellIndex % 75 == 0)
                System.out.print("|");

            if (cell.getCoordinate().getX() == playerX && cell.getCoordinate().getY() == playerY)
                System.out.print("\u001B[34m " + "P" + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("blue"))
                System.out.print("\u001B[34m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("red"))
                System.out.print("\u001B[31m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("green"))
                System.out.print("\u001B[32m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("yellow"))
                System.out.print("\u001B[33m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("black"))
                System.out.print("\u001B[90m " + cell.getObjectOnCell().string() + "\033[0m");
            else if (cell.getObjectOnCell().color.equals("gray"))
                System.out.print("\u001B[37m " + cell.getObjectOnCell().string() + "\033[0m");

            cellIndex++;
            if (cellIndex % 75 == 0)
                System.out.println("|");
        }
        for (int i = 0; i < 152; i++) {
            System.out.print("_");
        }
    }

    public ArrayList<Tile> getCells() {
        return cells;
    }

    public ArrayList<Buildings> getBuildings() {
        return buildings;
    }

    public static Farm makeFarm(int lakeModifier) {
        ArrayList<Tile> farmCells = new ArrayList<>();
        ArrayList<Buildings> farmBuildings = new ArrayList<>();
        makeEmptyCells(farmCells);
        // add buildings
        if (lakeModifier == 1)
            addOneLake(farmCells);
        else
            addTwoLakes(farmCells);

        addRandomItems(farmCells);
        return new Farm(farmCells, farmBuildings);
    }

    private static void addRandomItems(ArrayList<Tile> farmCells) {
        for (Tile cell : farmCells) {
            // TODO parameters
            int randomNumber = (int) (Math.random() * 8);
            if (cell.getObjectOnCell().type.equals("empty") && randomNumber == 3) {
                cell.setObjectOnCell(new Plant());
            } else if (cell.getObjectOnCell().type.equals("empty") && randomNumber == 2) {
                cell.setObjectOnCell(new Stone());
            } else if (cell.getObjectOnCell().type.equals("empty") && randomNumber == 1) {
                cell.setObjectOnCell(new FodderCrop());
            } else if (cell.getObjectOnCell().type.equals("empty") && randomNumber == 4) {
                cell.setObjectOnCell(new WildSeeds());
            }
        }
    }

    public void foragingRefresh(){
        for(Tile cell : cells){
            int randomNumber = (int) (Math.random() * 100);
            if(cell.getObjectOnCell().type.equals("empty") && cell.isTilled() && randomNumber == 3){
                cell.setObjectOnCell(new WildSeeds());
            }
            else if(cell.getObjectOnCell().type.equals("empty") && randomNumber == 3){
                cell.setObjectOnCell(new FodderCrop());
            }
            int randomNumber2 = (int) (Math.random() * 20);
            if(cell.getObjectOnCell().type.equals("empty") && randomNumber2 == 2 ){}
        }
    }

    private static void addOneLake(ArrayList<Tile> farmCells) {
        for (int j = 37; j < 46; j++) {
            for (int i = 33; i < 41; i++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new Water());
            }
        }
    }

    private static void addTwoLakes(ArrayList<Tile> farmCells) {
        for (int j = 37; j < 46; j++) {
            for (int i = 33; i < 41; i++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new Water());
            }
        }
        for (int j = 34; j < 40; j++) {
            for (int i = 42; i < 48; i++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new Water());
            }
        }
    }


    private static void makeEmptyCells(ArrayList<Tile> farmCells) {
        for (int j = 0; j < 50; j++) {
            for (int i = 0; i < 75; i++) {
                Position coordinate = new Position(i, j);
                farmCells.add(new Tile(new NothingInTile(), coordinate));
            }
        }
    }

    private static boolean isMineCell(Tile cell) {
        return cell.getCoordinate().getX() <=9 && cell.getCoordinate().getX() >=0 && cell.getCoordinate().getY() <=11 && cell.getCoordinate().getY() >=0;
    }

    private static Tile getCellByCoordinate(int x, int y, ArrayList<Tile> cells) {
        for (Tile cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public Tile findCellByCoordinate(int x, int y) {
        for (Tile cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public void initialCells() {
        for (Tile cell : cells) {
            cell.energy = 0;
            cell.distance = 0;
            cell.turns = 0;
            cell.prev = null;
        }
    }
}
