package view;

import controller.*;
import model.Basics.App;
import model.Basics.Game;
import model.Basics.Result;
import model.Command;
import model.Resualt;
import model.enums.GameMenuCommands;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();

        Resualt response = null;

        if (App.getLoggedInUser().getCurrentGame() == null
                || !App.getLoggedInUser().getCurrentGame().isGameOngoing() || (rapet < 4)) {
            if (TurnController.isWaitingForChoosingMap) {
                rapet++;
                if (GameMenuCommands.GAME_MAP.matches(input)) {
                    response = getGameMapResponse(input);
                } else {
                    response = new Resualt(false, "SORRY sorry!");
                }
            } else if (GameMenuCommands.GAME_NEW.matches(input)) {
                response = getNewGameResponse(input);
            } else if (GameMenuCommands.LOAD_GAME.matches(input)) {
                response = getLoadGameResponse(input);
            } else {
                response = new Resualt(false, "SORRY sorry!");
            }
        } else {
            Game game = App.getLoggedInUser().getCurrentGame();
            if (game.getGameThread() == null) {
                game.setGameThread(new PlayGame(game));
                game.getGameThread().keepRunning = true;
                game.getGameThread().start();
            }
            if (GameMenuCommands.SHOW_FARM.matches(input)) {
                response = getShowFullFarmResponse(input);
            } else if (GameMenuCommands.EXIT_GAME.matches(input)) {
                response = getExitGameResponse(input);
            } else if (GameMenuCommands.SHOW_MENU.matches(input)) {
                response = getShowMenuResponse(input);
            } else if (GameMenuCommands.NEXT_TURN.matches(input)) {
                response = getNextTurnResponse(input);
            } else if (GameMenuCommands.FORCE_DELETE_GAME.matches(input)) {
                response = getForceDeleteGameResponse(input);
            } else if (GameMenuCommands.TIME.matches(input)) {
                response = getTimeResponse(input);
            } else if (GameMenuCommands.DATE.matches(input)) {
                response = getDateResponse(input);
            } else if (GameMenuCommands.DATETIME.matches(input)) {
                response = getDateTimeResponse(input);
            } else if (GameMenuCommands.DAY_OF_WEEK.matches(input)) {
                response = getDayOfWeekResponse(input);
            } else if (GameMenuCommands.CHEAT_ADVANCE_TIME.matches(input)) {
                response = getCheatAdvanceTimeResponse(input);
            } else if (GameMenuCommands.CHEAT_ADVANCE_DATE.matches(input)) {
                response = getCheatAdvanceDateResponse(input);
            } else if (GameMenuCommands.SEASON.matches(input)) {
                response = getSeasonResponse(input);
            } else if (GameMenuCommands.CHEAT_THOR.matches(input)) {
                response = getCheatThorResponse(input);
            } else if (GameMenuCommands.WEATHER.matches(input)) {
                response = getWeatherResponse(input);
            } else if (GameMenuCommands.WEATHER_FORECAST.matches(input)) {
                response = getWeatherForecastResponse(input);
            } else if (GameMenuCommands.CHEAT_WEATHER_SET.matches(input)) {
                response = getWeatherSetResponse(input);
            } else if (GameMenuCommands.WALK.matches(input)) {
                response = getWalkResponse(input);
            } else if (GameMenuCommands.PRINT_MAP.matches(input)) {
                response = getShowFarmResponse(input);
            } else if (GameMenuCommands.FORCE_DELETE_GAME.matches(input)) {
                response = getForceDeleteGameResponse(input);
            } else if (GameMenuCommands.HELP_READING_MAP.matches(input)) {
                response = getMapHelpResponse(input);
            } else if (GameMenuCommands.WALK_HOME.matches(input)) {
                response = walkHome(input);
            } else if (GameMenuCommands.GO_TO_VILLAGE.matches(input)) {
                response = goToVillage(input);
            } else if (GameMenuCommands.WALK_ADD_COORDS.matches(input)) {
                response = getWalkAddCoordsResponse(input);
            } else if (GameMenuCommands.SHOWPLANETINFO.matches(input)) {
                response = getShowPlanetInfoResponse(input);
            }else if (GameMenuCommands.ShowEnergy.matches(input)) {
                response = getShowEnergyResponse(input);
            } else if (GameMenuCommands.unlimitedCheat.matches(input)) {
                response = getUnlimitedEnergyResponse(input);
            } else if (GameMenuCommands.EnergySetCheat.matches(input)) {
                response = setCheatEnergyResponse(input);
            } else if (GameMenuCommands.showInventory.matches(input)) {
                response = getShowInventoryResponse(input);
            }else if (GameMenuCommands.toolEquip.matches(input)) {
                response = getEquipResponse(input);
            }
            else if (GameMenuCommands.trash.matches(input)) {
                response = getTrashResponse(input);
            } else if (GameMenuCommands.showInHand.matches(input)) {
                response = getInHandResponse(input);
            } else if (GameMenuCommands.showTools.matches(input)) {
                response = getShowTOOlResponse(input);
            } else if (GameMenuCommands.upgradeTool.matches(input)) {
                response = getUpgradeToolResponse(input);
            } else if (GameMenuCommands.useTool.matches(input)) {
                response = getUseToolResponse(input);
            } else if (GameMenuCommands.plant.matches(input)) {
                response = getPlantResponse(input);
            }else if (GameMenuCommands.showPlants.matches(input)) {
                response = showPlantResponse(input);
            }else if (GameMenuCommands.fertilize.matches(input)) {//TODO: make fertility func
                response = getFertilizeInfo(input);
            }else if (GameMenuCommands.howWater.matches(input)) {
                response = getShowWaterResponse(input);
            }else if (GameMenuCommands.showRecipeCraft.matches(input)) {
                response = getShowRecipeCraftResponse(input);
            }else if (GameMenuCommands.crafting.matches(input)) {
                response = getCraftingResponse(input);
            }else if (GameMenuCommands.putItem.matches(input)) {
                response = getPutItemResponse(input);
            }else if (GameMenuCommands.cheatAddInventory.matches(input)) {
                response = getCheatAddInventoryResponse(input);
            }else if (GameMenuCommands.putRefrigerator.matches(input)) {
                response = getPuRefrigeratorResponse(input);
            }else if (GameMenuCommands.pickRefrigerator.matches(input)) {
                response = getGetRefrigeratorResponse(input);
            }else if (GameMenuCommands.showRecipeCook.matches(input)) {
                response = getShowRecipeCookResponse(input);
            }else if (GameMenuCommands.cookFood.matches(input)) {
                response = getCookingResponse(input);
            }else if (GameMenuCommands.eat.matches(input)) {
                response = getEatResponse(input);
            }else if (GameMenuCommands.artisanGet.matches(input)) {
                response = getArtisanProductResponse(input);
            }else if (GameMenuCommands.useArtisan.matches(input)) {
                response = getUseArtisanResponse(input);
            } else {
                response = new Resualt(false, "SORRY sorry!");
                ;
            }
        }

        System.out.println(response.getAnswer());
    }

    private static Resualt getPlantResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("seed", GameMenuCommands.plant.getGroup(input,"seed"));
        request.body.put("direction", GameMenuCommands.plant.getGroup(input, "direction"));
        response = FarmingController.plantingSeeds(request);
        return response;
    }

    private static Resualt getFertilizeInfo(String input){
        Resualt response;
        Command request=new Command(input);
        request.body.put("fertilizer", GameMenuCommands.toolEquip.getGroup(input,"fertilizer"));
        request.body.put("direction", GameMenuCommands.toolEquip.getGroup(input,"direction"));
        response = FarmingController.fertilityControl(request);
        return response;
    }
    private static Resualt getEquipResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("toolName", GameMenuCommands.toolEquip.getGroup(input,"toolName"));
        response = InventoryFunctionsController.toolEquip(request);
        return response;
    }
    private static Resualt getEatResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("foodName", GameMenuCommands.eat.getGroup(input,"foodName"));
        response = CookingController.eatDeliciousFood(request);
        return response;
    }

    private static Resualt getUseArtisanResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("artisanName", GameMenuCommands.useArtisan.getGroup(input,"artisanName"));
        request.body.put("string", GameMenuCommands.useArtisan.getGroup(input,"String"));
        response = CraftsmanshipController.useCraftingMachine(request);
        return response;
    }

    private static Resualt getArtisanProductResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("artisanName", GameMenuCommands.artisanGet.getGroup(input,"artisanName"));
        response = CraftsmanshipController.getCraftingMachineProduct(request);
        return response;
    }
    private static Resualt getCheatAddInventoryResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("itemName", GameMenuCommands.cheatAddInventory.getGroup(input,"itemName"));
        request.body.put("count", GameMenuCommands.cheatAddInventory.getGroup(input,"count"));
        response = CookingController.CheatAddInventory(request);
        return response;
    }

    private static Resualt getCraftingResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("itemName", GameMenuCommands.crafting.getGroup(input,"itemName"));
        response = CookingController.startCraft(request);
        return response;
    }
    private static Resualt getPuRefrigeratorResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("item", GameMenuCommands.putRefrigerator.getGroup(input,"item"));
        response = CookingController.putInRefrigerator(request);
        return response;
    }
    private static Resualt getGetRefrigeratorResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("item", GameMenuCommands.pickRefrigerator.getGroup(input,"item"));
        response = CookingController.getFromRefrigerator(request);
        return response;
    }
    private static Resualt getPutItemResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("itemName", GameMenuCommands.putItem.getGroup(input,"itemName"));
        request.body.put("direction", GameMenuCommands.putItem.getGroup(input,"direction"));
        response = CookingController.putItem(request);
        return response;
    }

    private static Resualt getCookingResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("recipeName", GameMenuCommands.cookFood.getGroup(input,"recipeName"));
        response = CookingController.cookFood(request);
        return response;
    }

    private static Resualt showPlantResponse(String input) {
        Resualt response;
        Command request=new Command(input);
        request.body.put("x", GameMenuCommands.showPlants.getGroup(input,"x"));
        request.body.put("y", GameMenuCommands.showPlants.getGroup(input, "y"));
        response = FarmingController.showPlantingInfo(request);
        return response;
    }

    private static Resualt getShowWaterResponse(String input) {
        Resualt response;
        response = FarmingController.seeWater();
        return response;
    }

    private static Resualt getShowRecipeCraftResponse(String input) {
        Resualt response;
        response = CookingController.showCraftingRecipes();
        return response;
    }

    private static Resualt getShowRecipeCookResponse(String input) {
        Resualt response;
        response = CookingController.showCookingRecipes();
        return response;
    }
    private static Resualt getShowTOOlResponse(String input) {
        Resualt response;
        response = InventoryFunctionsController.showAllTools();
        return response;
    }
    private static Resualt getUpgradeToolResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("toolName", GameMenuCommands.upgradeTool.getGroup(input, "toolName"));
        response = InventoryFunctionsController.toolUpgrade(request);
        return response;
    }
    private static Resualt getUseToolResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("direction", GameMenuCommands.useTool.getGroup(input, "direction"));
        response = InventoryFunctionsController.useTool(request);
        return response;
    }
    private static Resualt getShowInventoryResponse(String input) {
        Resualt response;
        response = InventoryFunctionsController.showAllInInventory();
        return response;
    }
    private static Resualt getTrashResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("toolName", GameMenuCommands.trash.getGroupByNum(input, 1));
        request.body.put("num", GameMenuCommands.trash.getGroupByNum(input,2));
        response = InventoryFunctionsController.useTrashCan(request);
        return response;
    }
    private static Resualt getInHandResponse(String input) {
        Resualt response;
        response = InventoryFunctionsController.showInHandTool();
        return response;
    }
    private static Resualt getShowFullFarmResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = MapController.showFullFarm(request);
        return response;
    }

    private static Resualt getShowFarmResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("x", GameMenuCommands.PRINT_MAP.getGroup(input, "x"));
        request.body.put("y", GameMenuCommands.PRINT_MAP.getGroup(input, "y"));
        request.body.put("size", GameMenuCommands.PRINT_MAP.getGroup(input, "size"));
        response = MapController.showFarm(request);
        return response;
    }

    private static Resualt getForceDeleteGameResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = TurnController.handleForceDeleteGame(request);
        return response;
    }

    private static Resualt getMapHelpResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = MapController.handleMapHelp(request);
        return response;
    }

    private static Resualt getWalkResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("x", GameMenuCommands.WALK.getGroup(input, "x"));
        request.body.put("y", GameMenuCommands.WALK.getGroup(input, "y"));
        response = MapController.handleWalking(request);
        return response;
    }

    private static Resualt getWeatherSetResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("Type", GameMenuCommands.CHEAT_WEATHER_SET.getGroup(input, "Type"));
        response = GameController.handleSetWeatherCheat(request);
        return response;
    }

    private static Resualt getWeatherForecastResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleWeatherForecastQuery(request);
        return response;
    }

    private static Resualt getWeatherResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleWeatherQuery(request);
        return response;
    }

    private static Resualt getCheatThorResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("x", GameMenuCommands.CHEAT_THOR.getGroup(input, "x"));
        request.body.put("y", GameMenuCommands.CHEAT_THOR.getGroup(input, "y"));
        response = GameController.handleCheatThor(request);
        return response;
    }

    private static Resualt getSeasonResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleSeasonQuery(request);
        return response;
    }

    private static Resualt getCheatAdvanceDateResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("X", GameMenuCommands.CHEAT_ADVANCE_DATE.getGroup(input, "X"));
        response = GameController.handleCheatAdvanceDate(request);
        return response;
    }

    private static Resualt getCheatAdvanceTimeResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("X", GameMenuCommands.CHEAT_ADVANCE_TIME.getGroup(input, "X"));
        response = GameController.handleCheatAdvanceTime(request);
        return response;
    }

    private static Resualt getShowPlanetInfoResponse(String input) {
        Resualt result;
        Command request = new Command(input);
        request.body.put("cropName", GameMenuCommands.SHOWPLANETINFO.getGroup(input, "craftName"));
        result = FarmingController.showPlanetsInfo(request);
        return result;
    }

    private static Resualt getDayOfWeekResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleDayOfWeekQuery(request);
        return response;
    }

    private static Resualt getDateTimeResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleDatetimeQuery(request);
        return response;
    }

    private static Resualt getDateResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleDateQuery(request);
        return response;
    }

    private static Resualt getShowEnergyResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = InventoryFunctionsController.showEnergy(request);
        return response;
    }

    private static Resualt getUnlimitedEnergyResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = InventoryFunctionsController.unlimitedEnergyCheat(request);
        return response;
    }

    private static Resualt getTimeResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleTimeQuery(request);
        return response;
    }

    private static Resualt getNextTurnResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = TurnController.handleNextTurn(request);
        return response;
    }

    private static Resualt getExitGameResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = TurnController.handleExitGame(request);
        return response;
    }

    private static Resualt getLoadGameResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = TurnController.handleLoadGame(request);
        return response;
    }

    private static Resualt getGameMapResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("mapNumber", GameMenuCommands.GAME_MAP.getGroup(input, "mapNumber"));
        response = TurnController.handleMapSelection(request);
        return response;
    }

    private static Resualt setCheatEnergyResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("value", GameMenuCommands.GAME_MAP.getGroup(input, "value"));
        response = InventoryFunctionsController.setEnergyCheat(request);
        return response;
    }



    private static Resualt getNewGameResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("users", GameMenuCommands.GAME_NEW.getGroup(input, "users"));
        response = TurnController.handleNewGame(request);
        return response;
    }

    private static Resualt walkHome(String input) {
        Resualt response;
        Command request = new Command(input);
        response = MapController.walkHome(request);
        return response;
    }

    private static Resualt goToVillage(String input) {
        Resualt response;
        Command request = new Command(input);
        response = MapController.goToVillage(request);
        return response;
    }

    private static Resualt getWalkAddCoordsResponse(String input) {
        Command request = new Command(input);
        request.body.put("x", GameMenuCommands.WALK_ADD_COORDS.getGroup(input, "x"));
        request.body.put("y", GameMenuCommands.WALK_ADD_COORDS.getGroup(input, "y"));
        return MapController.handleAddCoords(request);
    }

    private static Resualt getShowMenuResponse(String input) {
        return GameController.handleShowMenu(new Command(input));
    }


}
