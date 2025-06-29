package controller;

import model.Basics.App;
import model.Basics.Game;
import model.Basics.Player;
import model.Command;
import model.Maps.NothingInTile;
import model.Maps.Tile;
import model.Maps.Water;
import model.Objects.Tool;
import model.Repo.GameRepo;
import model.Maps.Farm;
import model.Objects.Inventory;
import model.Resualt;
import model.enums.Ingredients;
import model.enums.Weather;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameController extends ControllersController {
    public static Resualt handleTimeQuery(Command request) {
        Resualt response = new Resualt();
        response.setAccept(true);

        try {
            String timeString = App.getLoggedInUser()
                    .getCurrentGame()
                    .getDate()
                    .toLocalTime()
                    .toString();
            response.setAnswer(timeString);
        } catch (NullPointerException e) {
            response.setAccept(false);
            response.setAnswer("Error: Could not determine current time");
        }

        return response;
    }

    public static Resualt handleDateQuery(Command request) {
        Resualt response = new Resualt();

        try {
            String dateString = App.getLoggedInUser()
                    .getCurrentGame()
                    .getDate()
                    .toLocalDate()
                    .toString();
            response.setAccept(true);
            response.setAnswer(dateString);
        } catch (NullPointerException e) {
            response.setAccept(false);
            response.setAnswer("Error: Could not determine current date");
        }

        return response;
    }

    public static Resualt handleDatetimeQuery(Command request) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
            String formattedDate = App.getLoggedInUser()
                    .getCurrentGame()
                    .getDate()
                    .format(formatter);

            return new Resualt(true, formattedDate);
        } catch (NullPointerException | DateTimeException e) {
            return new Resualt(false, "Error: Could not format datetime");
        }
    }

    public static Resualt handleDayOfWeekQuery(Command request) {
        try {
            LocalDateTime currentDateTime = App.getLoggedInUser()
                    .getCurrentGame()
                    .getDate();
            String dayName = currentDateTime.getDayOfWeek()
                    .toString()
                    .toLowerCase();

            return new Resualt(true, dayName);
        } catch (NullPointerException e) {
            return new Resualt(false, "Error: Could not determine day of week");
        }
    }

    public static Resualt handleCheatAdvanceTime(Command request) {
        int amountOfHours = Integer.parseInt(request.body.get("X"));
        LocalDateTime currentDateTime = App.getLoggedInUser().getCurrentGame().getDate();
        LocalDateTime nextDateTime;
        Game currentGame = App.getLoggedInUser().getCurrentGame();

        int howManyDays = amountOfHours / 24;
        int howManyHours = amountOfHours % 24;
        int howManyMonths = howManyDays / 28;
        howManyDays %= 28;

        int currentHour = currentDateTime.getHour();
        int currentDay = currentDateTime.getDayOfMonth();

        if (howManyHours + currentHour > 22) {
            howManyHours = 9 - currentHour;
            if (howManyHours < 0) {
                howManyDays++;
                howManyHours += 24;
            }
        }

        if (howManyDays + currentDay > 28) {
            howManyMonths++;
            howManyDays -= 28;
        }

        nextDateTime = currentDateTime.plusDays(howManyDays);
        nextDateTime = nextDateTime.plusHours(howManyHours);
        nextDateTime = nextDateTime.plusMonths(howManyMonths);

        boolean check = nextDateTime.getMonthValue() - currentDateTime.getMonthValue() > 0
                || nextDateTime.getDayOfMonth() - currentDateTime.getDayOfMonth() > 0;

        currentGame.setDate(nextDateTime);
        currentGame.checkSeasonChange();
        if (check) {
            for (int i = 0; i <= howManyDays; i++)
                currentGame.newDayBackgroundChecks();
        }

        return new Resualt(true, "Date and time set successfully.");
    }


    public static Resualt handleCheatAdvanceDate(Command request) {
        int amountOfDays = Integer.parseInt(request.body.get("X"));
        LocalDateTime currentDateTime = App.getLoggedInUser().getCurrentGame().getDate();
        LocalDateTime nextDateTime;
        Game currentGame = App.getLoggedInUser().getCurrentGame();
        int howManyDays = amountOfDays % 28;
        int howManyMonths = amountOfDays / 28;
        int currentDay = currentDateTime.getDayOfMonth();
        if (howManyDays + currentDay > 28) {
            howManyMonths++;
            howManyDays -= 28;
        }
        nextDateTime = currentDateTime.plusDays(howManyDays);
        nextDateTime = nextDateTime.plusMonths(howManyMonths);
        boolean check = (nextDateTime.getMonthValue() - currentDateTime.getMonthValue() > 0)
                || (nextDateTime.getDayOfMonth() - currentDateTime.getDayOfMonth() > 0);
        currentGame.setDate(nextDateTime);
        if (check) {
            currentGame.newDayBackgroundChecks();
        }
        currentGame.checkSeasonChange();
        // GameRepo.saveGame(currentGame);
        return new Resualt(true, "Date set successfully.");
    }


    public static Resualt handleSeasonQuery(Command request) {
        return new Resualt(true, App.getLoggedInUser().getCurrentGame().getSeason().toString());
    }

    //New Jasmin
    public static Resualt handleCheatThor(Command request) {
        int targetX = Integer.parseInt(request.body.get("x"));
        int targetY = Integer.parseInt(request.body.get("y"));

        if (targetX >= 75 || targetY >= 50 || targetX < 0 || targetY < 0) {
            return new Resualt(false, "Coordinates out of bounds.");
        }

        Game currentGame = App.getLoggedInUser().getCurrentGame();
        currentGame.getCurrentPlayer().getCurrentFarm(currentGame).strikeLightning(targetX, targetY, currentGame.getDate());
        //GameRepo.saveGame(currentGame);
        return new Resualt(true, "Lightning summoned at target coordinates.");
    }

    public static Resualt handleWeatherQuery(Command request) {
        return new Resualt(true, App.getLoggedInUser().getCurrentGame().getWeatherToday().toString());
    }

    public static Resualt handleWeatherForecastQuery(Command request) {
        return new Resualt(true, "Tomorrow's weather forecast is: "
                + App.getLoggedInUser().getCurrentGame().getWeatherTomorrow().toString());
    }

    public static Resualt handleSetWeatherCheat(Command request) {
        String type = request.body.get("Type");
        Weather weather = Weather.getWeatherByName(type);
        Game game = App.getLoggedInUser().getCurrentGame();
        if (weather == null) {
            return new Resualt(false, "Weather type is invalid.");
        } else {
            game.setWeatherTomorrow(weather);
            // GameRepo.saveGame(game);
        }
        return new Resualt(true, "Tomorrow's weather set successfully.");
    }

    public static Resualt handleGreenhouseBuilding(Command request) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        Farm farm = player.getFarm();
        Inventory backpack = player.getInventory();

        Tile testCell = Farm.getCellByCoordinate(25, 4, farm.getCells());

        if (testCell.getObjectOnCell() instanceof Water) {
            return new Resualt(false, "Greenhouse already built.");
        }

        if (player.getMoney() < 1000) {
            return new Resualt(false, "You don't have enough money.");
        }

        if (player.getInventory().getIngredients().containsKey(Ingredients.WOOD)) {
            if (player.getInventory().getIngredients().get(Ingredients.WOOD) < 500) {
                return new Resualt(false, "You don't have enough wood.");
            }
        }

        for (int i = 23; i < 28; i++) {
            for (int j = 4; j < 10; j++) {
                Tile cell = Farm.getCellByCoordinate(i, j, farm.getCells());
                cell.setObjectOnCell(new NothingInTile());
            }
        }

        Tile cell = Farm.getCellByCoordinate(25, 10, farm.getCells());
        cell.setObjectOnCell(new NothingInTile());

        Tile cell1 = Farm.getCellByCoordinate(25, 4, farm.getCells());
        cell1.setObjectOnCell(new Water());

        //  GameRepo.saveGame(game);

        return new Resualt(true, "Greenhouse built successfully.");
    }


    private static int[] getXAndYIncrement(String direction) {
        if (direction.compareToIgnoreCase("up") == 0) {
            return new int[]{0, -1};
        } else if (direction.compareToIgnoreCase("down") == 0) {
            return new int[]{0, 1};
        } else if (direction.compareToIgnoreCase("right") == 0) {
            return new int[]{1, 0};
        } else if (direction.compareToIgnoreCase("left") == 0) {
            return new int[]{-1, 0};
        } else if (direction.compareToIgnoreCase("up_right") == 0) {
            return new int[]{1, -1};
        } else if (direction.compareToIgnoreCase("up_left") == 0) {
            return new int[]{-1, -1};
        } else if (direction.compareToIgnoreCase("down_right") == 0) {
            return new int[]{1, 1};
        } else if (direction.compareToIgnoreCase("down_left") == 0) {
            return new int[]{-1, 1};
        } else {
            return new int[]{10000, 10000};
        }
    }

}
