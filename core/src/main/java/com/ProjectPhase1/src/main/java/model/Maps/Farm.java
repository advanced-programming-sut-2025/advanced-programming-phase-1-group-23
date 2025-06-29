package model.Maps;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Naturals.Crop;
import model.Naturals.Tree;
import model.Objects.Shop;
import model.enums.CropName;
import model.enums.Ingredients;
import model.enums.ShopName;
import model.enums.TreeName;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dev.morphia.annotations.Embedded;
import model.Objects.ShippingBin;

@Embedded
public class Farm {
    private ArrayList<Tile> cells;
    private ArrayList<Building> buildings;
    private ArrayList<ShippingBin> shippingBins;
    private int num;
    private static int lastNum;

    static {
        lastNum = 0;
    }

    public Farm() {

    }

    public Farm(ArrayList<Tile> cells, ArrayList<Building> buildings) {
        this.cells = cells;
        this.buildings = buildings;
        this.shippingBins = new ArrayList<>();
    }

    public void showFarm(int x, int y, int size, Game game) {
        Player owner = game.getCurrentPlayer();
        int ownerX = -1;
        int ownerY = -1;
        int partnerX = -1;
        int partnerY = -1;
        if (owner.getCurrentFarm(game) == this && !owner.isInVillage()) {
            ownerX = owner.getPosition().getX();
            ownerY = owner.getPosition().getY();
        }

        for (Tile cell : cells) {
            Position coordinate = cell.getCoordinate();

            int xOfCell = coordinate.getX();
            int yOfCell = coordinate.getY();

            if (Math.abs(x - xOfCell) <= size / 2 && Math.abs(y - yOfCell) <= size / 2) {
                if (xOfCell == ownerX && yOfCell == ownerY)
                    System.out.print("\u001B[34m " + "O" + "\033[0m");
                else if (xOfCell == partnerX && yOfCell == partnerY)
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
                else if (cell.getObjectOnCell().color.equals("purple"))
                    System.out.print("\u001B[35m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("cyan"))
                    System.out.print("\u001B[36m " + cell.getObjectOnCell().string() + "\033[0m");
                else if (cell.getObjectOnCell().color.equals("bright purple"))
                    System.out.print("\u001B[95m " + cell.getObjectOnCell().string() + "\033[0m");
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

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<ShippingBin> getShippingBins() {
        return shippingBins;
    }

    public static Farm makeFarm(int lakeModifier) {
        ArrayList<Tile> farmCells = new ArrayList<>();
        ArrayList<Building> farmBuildings = new ArrayList<>();
        makeEmptyCells(farmCells);
        addBuildings(farmBuildings, farmCells);
        if (lakeModifier == 1)
            addOneLake(farmCells);
        else
            addTwoLakes(farmCells);

        for (Tile cell : farmCells) {
            int randomNumber = (int) (Math.random() * 5);

            if (cell.getObjectOnCell().type.equals(".") && randomNumber == 3) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                cell.setObjectOnCell(new Plant(TreeName.AppleTree));
            } else if (cell.getObjectOnCell().type.equals(".") && randomNumber == 2) {
                cell.setObjectOnCell(new Stone(Ingredients.STONE, "gray", "stone"));
            } else if (cell.getObjectOnCell().type.equals(".") && randomNumber == 1) {
                cell.setObjectOnCell(randomForagingCrop());
            } else if (cell.getObjectOnCell().type.equals("Mine") && (randomNumber == 4 || randomNumber == 3) && isMineCell(cell)) {
                cell.setObjectOnCell(randomForagingMineral());
            }
        }


        return new Farm(farmCells, farmBuildings);
    }

    private static WildSeeds randomForagingCrop() {
        CropName[] allValues = CropName.values();
        ArrayList<CropName> validValues = new ArrayList<>();

        for (int i = 0; i < allValues.length; i++) {
            if (allValues[i].getSeason().length > 1 || allValues[i].getSeason()[0] == App.getLoggedInUser().getCurrentGame().getSeason()) {
                validValues.add(allValues[i]);
            }
        }

        int randomNumber = (int) (Math.random() * validValues.size());
        return new WildSeeds(validValues.get(randomNumber));
    }

    private static FodderCrop randomForagingMineral() {
        Ingredients[] values = Ingredients.values();
        int randomNumber = (int) (Math.random() * values.length);
        return new FodderCrop();
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
        return cell.getCoordinate().getX() <= 9 && cell.getCoordinate().getX() >= 0 && cell.getCoordinate().getY() <= 11
                && cell.getCoordinate().getY() >= 0;
    }

    private static void addBuildings(ArrayList<Building> farmBuildings, ArrayList<Tile> farmCells) {
        ArrayList<Tile> cottageCells = new ArrayList<>();
        ArrayList<Tile> greenHouseCells = new ArrayList<>();
        ArrayList<Tile> mineCells = new ArrayList<>();
        for (int i = 61; i < 65; i++) {
            for (int j = 4; j < 8; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(false, "Home"));
                cottageCells.add(cell);
            }
        }

        for (int i = 22; i < 29; i++) {
            for (int j = 3; j < 11; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);

                if (i != 22 && i != 28 && j != 3 && j != 10) {
                    cell.setObjectOnCell(new BuildingsForPaint(false, "Greenhouse"));
                } else
                    cell.setObjectOnCell(new BuildingsForPaint(false, "Wall"));

                greenHouseCells.add(cell);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "Mine"));
                mineCells.add(cell);
            }
        }
        farmBuildings.add(new Cottage(cottageCells));
        farmBuildings.add(new GreenHouse(greenHouseCells));
        farmBuildings.add(new Mine(mineCells));
    }


    public Tile findCellByCoordinate(int x, int y) {
        for (Tile cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public static Tile getCellByCoordinate(int x, int y, ArrayList<Tile> cells) {
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

    //New Jasmin

    public void setCells(ArrayList<Tile> cells) {
        this.cells = cells;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static int getLastNum() {
        return lastNum;
    }

    public static void setLastNum(int lastNum) {
        Farm.lastNum = lastNum;
    }


    //New Jasmin
    public void strikeLightning(int targetX, int targetY, LocalDateTime source) {
        Tile targetCell = findCellByCoordinate(targetX, targetY);
        if (targetCell != null) {
            if (targetCell.getObjectOnCell() instanceof Plant) {
                TreeName burntTree = TreeName.BurntTree;

                targetCell.setObject(new Tree(burntTree));
            }
            if (targetCell.getObjectOnCell() instanceof FodderCrop) {
                targetCell.setObjectOnCell(new NothingInTile());
            }
            if (targetCell.getObjectOnCell() instanceof FodderCrop) {
                targetCell.setObjectOnCell(new NothingInTile());
            }
        }
        System.out.println("Lightning has struck coordinates: " + targetX + ", " + targetY);
    }

    //new Jasmin
    // private static void addShop(ArrayList<Building> farmBuildings, ArrayList<Tile> farmCells){
    //     ArrayList<Tile> shopAnimal = new ArrayList<>();
    //     ArrayList<Tile> shopBarn = new ArrayList<>();
    //     ArrayList<Tile> shopIngredient = new ArrayList<>();
    //     ArrayList<Tile> shopItem = new ArrayList<>();
    //     ArrayList<Tile> shopRecipe = new ArrayList<>();
    //     ArrayList<Tile> shopSeed = new ArrayList<>();
    //     ArrayList<Tile> shopTool = new ArrayList<>();

    //     for (int i = 1; i < 6; i++) {
    //         for (int j = 1; j < 6; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopAnimal"));
    //             shopAnimal.add(cell);
    //         }
    //     }

    //     for (int i = 9; i < 14; i++) {
    //         for (int j = 9; j < 14; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopBarn"));
    //             shopBarn.add(cell);
    //         }
    //     }

    //     for (int i = 16; i < 21; i++) {
    //         for (int j = 16; j < 21; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopIngredient"));
    //             shopIngredient.add(cell);
    //         }
    //     }

    //     for (int i = 25; i < 30; i++) {
    //         for (int j = 25; j < 30; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopItem"));
    //             shopItem.add(cell);
    //         }
    //     }

    //     for (int i = 35; i < 40; i++) {
    //         for (int j = 35; j < 43; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopRecipe"));
    //             shopRecipe.add(cell);
    //         }
    //     }

    //     for (int i = 45; i < 50; i++) {
    //         for (int j = 35; j < 42; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopSeed"));
    //             shopSeed.add(cell);
    //         }
    //     }

    //     for (int i = 45; i < 50; i++) {
    //         for (int j = 43; j < 48; j++) {
    //             Tile cell = getCellByCoordinate(i, j, farmCells);
    //             cell.setObjectOnCell(new BuildingsForPaint(false, "ShopTool"));
    //             shopTool.add(cell);
    //         }
    //     }

    //     farmBuildings.add(new Shop(shopAnimal,));

    // }

    // public static Farm makeNPCFarm() {
    //     ArrayList<Tile> farmCells = new ArrayList<>();
    //     ArrayList<Building> farmBuildings = new ArrayList<>();

    //     makeEmptyCells(farmCells);
    // }

    private static void addShop(ArrayList<Building> farmBuildings, ArrayList<Tile> farmCells) {
        ArrayList<Tile> blacksmithFloor = new ArrayList<>();
        ArrayList<Tile> marnieRanchFloor = new ArrayList<>();
        ArrayList<Tile> stardropSaloonFloor = new ArrayList<>();
        ArrayList<Tile> carpenterShopFloor = new ArrayList<>();
        ArrayList<Tile> jojaMartFloor = new ArrayList<>();
        ArrayList<Tile> pierreGeneralFloor = new ArrayList<>();
        ArrayList<Tile> fishShopFloor = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "blackSmith"));
                blacksmithFloor.add(cell);
            }
        }

        for (int i = 9; i < 14; i++) {
            for (int j = 9; j < 14; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "marnieRanch"));
                marnieRanchFloor.add(cell);
            }
        }

        for (int i = 16; i < 21; i++) {
            for (int j = 16; j < 21; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "stardropSaloon"));
                stardropSaloonFloor.add(cell);
            }
        }

        for (int i = 25; i < 30; i++) {
            for (int j = 25; j < 30; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "carpenterShop"));
                carpenterShopFloor.add(cell);
            }
        }

        for (int i = 35; i < 40; i++) {
            for (int j = 35; j < 43; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "jojaMart"));
                jojaMartFloor.add(cell);
            }
        }

        for (int i = 45; i < 50; i++) {
            for (int j = 35; j < 42; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "pierreGeneralStore"));
                pierreGeneralFloor.add(cell);
            }
        }

        for (int i = 45; i < 50; i++) {
            for (int j = 43; j < 48; j++) {
                Tile cell = getCellByCoordinate(i, j, farmCells);
                cell.setObjectOnCell(new BuildingsForPaint(true, "fishShop"));
                fishShopFloor.add(cell);
            }
        }
        Shop blacksmith = new Shop(blacksmithFloor, ShopName.BlackSmith, 9, 4);
        Shop marnieRanch = new Shop(marnieRanchFloor, ShopName.MarnieRanch, 9, 16);
        Shop stardropSaloon = new Shop(stardropSaloonFloor, ShopName.StardropSaloon, 12, 12);
        Shop carpenterShop = new Shop(carpenterShopFloor, ShopName.CarpenterShop, 9, 20);
        Shop jojaMart = new Shop(jojaMartFloor, ShopName.JojaMart, 9, 23);
        Shop pierreGeneral = new Shop(pierreGeneralFloor, ShopName.PierreGeneralStore, 9, 17);
        Shop fishShop = new Shop(fishShopFloor, ShopName.FishShop, 9, 17);
        blacksmith.ChangeSeasonSpring();
        marnieRanch.ChangeSeasonSpring();
        stardropSaloon.ChangeSeasonSpring();
        carpenterShop.ChangeSeasonSpring();
        jojaMart.ChangeSeasonSpring();
        pierreGeneral.ChangeSeasonSpring();
        fishShop.ChangeSeasonSpring();
        farmBuildings.add(blacksmith);
        farmBuildings.add(marnieRanch);
        farmBuildings.add(stardropSaloon);
        farmBuildings.add(carpenterShop);
        farmBuildings.add(jojaMart);
        farmBuildings.add(pierreGeneral);
        farmBuildings.add(fishShop);
    }

    public static Farm makeNPCFarm() {
        ArrayList<Tile> farmCells = new ArrayList<>();
        ArrayList<Building> farmBuildings = new ArrayList<>();

        makeEmptyCells(farmCells);

        addShop(farmBuildings, farmCells);
        return new Farm(farmCells, farmBuildings);

    }
}
