package com.P.common.model.Maps;

import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Objects.ShippingBin;
import com.P.common.model.Objects.Shop;
import com.P.common.model.enums.CropName;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.enums.ShopName;
import com.P.common.model.enums.TreeName;
import com.P.common.model.item.TileDescriptionId;
import dev.morphia.annotations.Embedded;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Embedded
public class Farm {
    private static final int ROWS = 50;
    private static final int COLS = 75;
    private static TileDescriptionId[][] tiles = new TileDescriptionId[ROWS][COLS];
    private static TileDescriptionId[][] villageTiles = new TileDescriptionId[ROWS][COLS];

    private List<Tile> cells;
    private List<Building> buildings;
    private List<ShippingBin> shippingBins;
    private int num;
    private static int lastNum = 0;

    public Farm() {
        this.cells = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.shippingBins = new ArrayList<>();
    }

    public Farm(List<Tile> cells, List<Building> buildings) {
        this.cells = new ArrayList<>(cells);
        this.buildings = new ArrayList<>(buildings);
        this.shippingBins = new ArrayList<>();
    }

    public void showFarm(int x, int y, int size, Game game) {
        Player owner = game.getCurrentPlayer();
        Point ownerPos = (!owner.isInVillage() && owner.getCurrentFarm(game) == this)
                ? new Point(owner.getPosition().getX(), owner.getPosition().getY())
                : new Point(-1, -1);

        cells.stream()
                .filter(cell -> Math.abs(x - cell.getCoordinate().getX()) <= size / 2 &&
                                Math.abs(y - cell.getCoordinate().getY()) <= size / 2)
                .forEach(cell -> {
                    printCellWithColor(cell, ownerPos);
                    if (cell.getCoordinate().getX() - x == size / 2) System.out.println();
                });
    }

    public void showEntireFarm() {
        Point playerPos = new Point(
                App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getX(),
                App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getPosition().getY()
        );

        printHorizontalBorder();
        for (int i = 0; i < cells.size(); i++) {
            if (i % COLS == 0) System.out.print("|");
            printCellWithColor(cells.get(i), playerPos);
            if ((i + 1) % COLS == 0) System.out.println("|");
        }
        printHorizontalBorder();
    }

    private void printHorizontalBorder() {
        System.out.println("_".repeat(COLS * 2 + 2));
    }

    private void printCellWithColor(Tile cell, Point playerPos) {
        Position pos = cell.getCoordinate();
        if (pos.getX() == playerPos.x && pos.getY() == playerPos.y) {
            System.out.print(colorize("P", "blue"));
        } else {
            System.out.print(colorize(cell.getObjectOnCell().string(), cell.getObjectOnCell().color));
        }
    }

    private String colorize(String text, String color) {
        String code = switch (color.toLowerCase()) {
            case "blue" -> "\u001B[34m";
            case "red" -> "\u001B[31m";
            case "green" -> "\u001B[32m";
            case "yellow" -> "\u001B[33m";
            case "black" -> "\u001B[90m";
            case "gray" -> "\u001B[37m";
            case "purple" -> "\u001B[35m";
            case "cyan" -> "\u001B[36m";
            case "bright purple" -> "\u001B[95m";
            default -> "";
        };
        return code + " " + text + "\033[0m";
    }

    public static Farm makeFarm(int lakeModifier) {
        List<Tile> farmCells = new ArrayList<>();
        List<Building> farmBuildings = new ArrayList<>();

        makeEmptyCells(farmCells);
        addBuildings(farmBuildings, farmCells);

        if (lakeModifier == 1) addLake(farmCells, 10, 10, 4, 4);
        else if (lakeModifier == 2) {
            addLake(farmCells, 10, 11, 6, 5);
            addLake(farmCells, 5, 25, 4, 5);
        }

        populateRandomObjects(farmCells, tiles);
        return new Farm(farmCells, farmBuildings);
    }

    private static void addLake(List<Tile> cells, int startX, int startY, int width, int height) {
        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                Tile cell = getCellByCoordinate(x, y, cells);
                cell.setObjectOnCell(new Water());
                tiles[y][x] = TileDescriptionId.WATER;
            }
        }
    }

    private static void populateRandomObjects(List<Tile> farmCells, TileDescriptionId[][] targetTiles) {
        Random random = new Random();
        for (Tile cell : farmCells) {
            int r1 = random.nextInt(8);
            int r2 = random.nextInt(3) + 1;
            if (cell.getObjectOnCell().type.equals(".")) {
                switch (r1) {
                    case 3 -> {
                        cell.setObjectOnCell(new Plant(TreeName.AppleTree));
                        targetTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] =
                                (r2 == 2) ? TileDescriptionId.TREE2 : TileDescriptionId.TREE1;
                    }
                    case 2 -> {
                        cell.setObjectOnCell(new Stone(Ingredients.STONE, "gray", "stone"));
                        targetTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.STONE;
                    }
                    case 1 -> {
                        cell.setObjectOnCell(randomForagingCrop());
                        targetTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.F;
                    }
                    case 4 -> {
                        cell.setObjectOnCell(randomForagingCrop());
                        targetTiles[cell.getCoordinate().getY()][cell.getCoordinate().getX()] = TileDescriptionId.WOOD;
                    }
                }
            } else if ("Mine".equals(cell.getObjectOnCell().type) && (r1 == 3 || r1 == 4) && isMineCell(cell)) {
                cell.setObjectOnCell(randomForagingMineral());
            }
        }
    }

    private static WildSeeds randomForagingCrop() {
        List<CropName> valid = Arrays.stream(CropName.values())
                .filter(c -> c.getSeason().length > 1 ||
                        c.getSeason()[0] == App.getLoggedInUser().getCurrentGame().getSeason())
                .collect(Collectors.toList());
        return new WildSeeds(valid.get(new Random().nextInt(valid.size())));
    }

    private static FodderCrop randomForagingMineral() {
        return new FodderCrop();
    }

    private static void makeEmptyCells(List<Tile> farmCells) {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                farmCells.add(new Tile(new NothingInTile(), new Position(x, y)));
                tiles[y][x] = TileDescriptionId.GRASS;
            }
        }
    }

    private static void makeEmptyCellsForVillage(List<Tile> villageCells) {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                villageCells.add(new Tile(new NothingInTile(), new Position(x, y)));
                villageTiles[y][x] = TileDescriptionId.floor;
            }
        }
    }

    private static boolean isMineCell(Tile cell) {
        return cell.getCoordinate().getX() <= 9 && cell.getCoordinate().getY() <= 11;
    }


    private static void addBuildings(List<Building> buildings, List<Tile> cells) {
        buildings.add(new Cottage(fillBuildingArea(cells, 3, 3, 10, 8, "Home", TileDescriptionId.GRASS)));
        buildings.add(new GreenHouse(fillGreenhouse(cells)));
        buildings.add(new Mine(fillBuildingArea(cells, 22, 10, 26, 17, "Mine", TileDescriptionId.MINE)));
    }

    private static List<Tile> fillBuildingArea(List<Tile> cells, int startX, int startY, int endX, int endY, String name, TileDescriptionId tileId) {
        List<Tile> area = new ArrayList<>();
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile cell = getCellByCoordinate(x, y, cells);
                cell.setObjectOnCell(new BuildingsForPaint(false, name));
                tiles[y][x] = tileId;
                area.add(cell);
            }
        }
        return area;
    }

    private static List<Tile> fillGreenhouse(List<Tile> cells) {
        List<Tile> area = new ArrayList<>();
        for (int x = 22; x < 26; x++) {
            for (int y = 3; y < 7; y++) {
                Tile cell = getCellByCoordinate(x, y, cells);
                cell.setObjectOnCell(new BuildingsForPaint(false, (x != 22 && y != 3) ? "Greenhouse" : "Wall"));
                area.add(cell);
            }
        }
        tiles[3][25] = TileDescriptionId.GREENHOUSE1;
        tiles[4][25] = TileDescriptionId.GREENHOUSE2;
        tiles[5][25] = TileDescriptionId.GREENHOUSE3;
        tiles[6][25] = TileDescriptionId.GREENHOUSE4;
        tiles[3][24] = TileDescriptionId.GREENHOUSE5;
        tiles[4][24] = TileDescriptionId.GREENHOUSE6;
        tiles[5][24] = TileDescriptionId.GREENHOUSE7;
        tiles[6][24] = TileDescriptionId.GREENHOUSE8;
        tiles[3][23] = TileDescriptionId.GREENHOUSE9;
        tiles[4][23] = TileDescriptionId.GREENHOUSE10;
        tiles[5][23] = TileDescriptionId.GREENHOUSE11;
        tiles[6][23] = TileDescriptionId.GREENHOUSE12;
        tiles[3][22] = TileDescriptionId.GREENHOUSE13;
        tiles[4][22] = TileDescriptionId.GREENHOUSE14;
        tiles[5][22] = TileDescriptionId.GREENHOUSE15;
        tiles[6][22] = TileDescriptionId.GREENHOUSE16;
        return area;
    }

    private static void addShop(List<Building> buildings, List<Tile> cells) {
        buildings.add(makeShop(cells, 3, 3, 9, 8, "blackSmith", ShopName.BlackSmith, 9, 4));
        buildings.add(makeShop(cells, 9, 9, 15, 14, "marnieRanch", ShopName.MarnieRanch, 9, 16));
        buildings.add(makeShop(cells, 16, 16, 20, 21, "stardropSaloon", ShopName.StardropSaloon, 12, 13));
        buildings.add(makeShop(cells, 25, 25, 31, 32, "carpenterShop", ShopName.CarpenterShop, 9, 20));
        buildings.add(makeShop(cells, 35, 35, 41, 38, "jojaMart", ShopName.JojaMart, 9, 23));
        buildings.add(makeShop(cells, 45, 35, 51, 38, "pierreGeneralStore", ShopName.PierreGeneralStore, 9, 17));
        buildings.add(makeShop(cells, 45, 43, 50, 48, "fishShop", ShopName.FishShop, 9, 17));
    }

    private static Shop makeShop(List<Tile> cells, int startX, int startY, int endX, int endY,
                                 String buildingName, ShopName shopName, int doorX, int doorY) {
        List<Tile> area = new ArrayList<>();
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Tile cell = getCellByCoordinate(x, y, cells);
                cell.setObjectOnCell(new BuildingsForPaint(true, buildingName));
                area.add(cell);
            }
        }
        Shop shop = new Shop(area, shopName, doorX, doorY);
        shop.ChangeSeasonSpring();
        return shop;
    }


    public Tile findCellByCoordinate(int x, int y) {
        return cells.stream().filter(c -> c.getCoordinate().getX() == x && c.getCoordinate().getY() == y).findFirst().orElse(null);
    }

    public static Tile getCellByCoordinate(int x, int y, List<Tile> cells) {
        return cells.stream().filter(c -> c.getCoordinate().getX() == x && c.getCoordinate().getY() == y).findFirst().orElse(null);
    }

    public void initialCells() {
        cells.forEach(c -> { c.energy = 0; c.distance = 0; c.turns = 0; c.prev = null; });
    }

    public void strikeLightning(int targetX, int targetY, LocalDateTime source) {
        Tile target = findCellByCoordinate(targetX, targetY);
        if (target != null && target.getObjectOnCell() instanceof FodderCrop) {
            target.setObjectOnCell(new NothingInTile());
        }
        System.out.println("Lightning struck at: " + targetX + ", " + targetY);
    }


    public List<Tile> getCells() { return cells; }
    public List<Building> getBuildings() { return buildings; }
    public List<ShippingBin> getShippingBins() { return shippingBins; }
    public void setCells(List<Tile> cells) { this.cells = cells; }
    public void setBuildings(List<Building> buildings) { this.buildings = buildings; }
    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }
    public static int getLastNum() { return lastNum; }
    public static void setLastNum(int lastNum) { Farm.lastNum = lastNum; }
    public TileDescriptionId getTile(Point point) { return tiles[point.x][point.y]; }
    public TileDescriptionId[][] getTiles() { return tiles; }
    public TileDescriptionId[][] getVillageTiles() { return villageTiles; }
}
