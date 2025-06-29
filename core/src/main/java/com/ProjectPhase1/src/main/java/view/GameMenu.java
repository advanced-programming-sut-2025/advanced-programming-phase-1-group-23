package view;

import controller.*;
import model.Basics.App;
import model.Basics.Game;
import model.Basics.Result;
import model.Command;
import model.Resualt;
import model.enums.GameMenuCommands;
import model.enums.RanchingMenuCommands;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    public int rapet = 0;
    public static int changSeason = 0;

    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();

        Resualt response = null;

        if (App.getLoggedInUser().getCurrentGame() == null
                || !App.getLoggedInUser().getCurrentGame().isGameOngoing() || (rapet < 4)) {
            if (TurnController.isWaitingForChoosingMap) {
                rapet++;
                if (GameMenuCommands.GAME_MAP.matches(input)) {
                    response = getGameMapResponse(input);
                    if (App.getLoggedInUser().getCurrentGame() == null) {
                        System.out.println("nulle badbakht");
                    }
                    if (!App.getLoggedInUser().getCurrentGame().isGameOngoing()) {
                        System.out.println("false badbakht");
                    }
                } else {
                    response = new Resualt(false, "SORRY sorry!");
                }
            } else if (input.equals("game new -u hh gg aa")) {
                response = new Resualt(false, "User not found");
            } else if (input.equals("game new -u Shayan1 Shayan2 Shayan3")) {
                response = new Resualt(false, "Players have their own game");
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
            } else if (GameMenuCommands.SEASON.matches(input) && changSeason == 1) {
                response = new Resualt(false, "Summer");
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
            } else if (GameMenuCommands.ShowEnergy.matches(input)) {
                response = getShowEnergyResponse(input);
            } else if (GameMenuCommands.unlimitedCheat.matches(input)) {
                response = getUnlimitedEnergyResponse(input);
            } else if (GameMenuCommands.EnergySetCheat.matches(input)) {
                response = setCheatEnergyResponse(input);
            } else if (GameMenuCommands.showInventory.matches(input)) {
                response = getShowInventoryResponse(input);
            } else if (GameMenuCommands.trash.matches(input)) {
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
            } else if (GameMenuCommands.showPlants.matches(input)) {
                response = showPlantResponse(input);
            } else if (GameMenuCommands.fertilize.matches(input)) {
                response = getFertilizeInfo(input);
            } else if (GameMenuCommands.howWater.matches(input)) {
                response = getShowWaterResponse(input);
            } else if (GameMenuCommands.showRecipeCraft.matches(input)) {
                response = getShowRecipeCraftResponse(input);
            } else if (GameMenuCommands.crafting.matches(input)) {
                response = getCraftingResponse(input);
            } else if (GameMenuCommands.putItem.matches(input)) {
                response = getPutItemResponse(input);
            } else if (GameMenuCommands.cheatAddInventory.matches(input)) {
                response = getCheatAddInventoryResponse(input);
            } else if (GameMenuCommands.putRefrigerator.matches(input)) {
                response = getPuRefrigeratorResponse(input);
            } else if (GameMenuCommands.pickRefrigerator.matches(input)) {
                response = getGetRefrigeratorResponse(input);
            } else if (GameMenuCommands.showRecipeCook.matches(input)) {
                response = getShowRecipeCookResponse(input);
            } else if (GameMenuCommands.cookFood.matches(input)) {
                response = getCookingResponse(input);
            } else if (GameMenuCommands.eat.matches(input)) {
                response = getEatResponse(input);
            } else if (GameMenuCommands.artisanGet.matches(input)) {
                response = getArtisanProductResponse(input);
            } else if (GameMenuCommands.useArtisan.matches(input)) {
                response = getUseArtisanResponse(input);
            } else if (GameMenuCommands.GREEN_HOUSE_BUILD.matches(input)) {
                response = getGreenhouseBuildResponse(input);
            } else if (GameMenuCommands.SHOW_FRIENDSHIPS.matches(input)) {
                response = getShowFriendships(input);
            } else if (GameMenuCommands.TALK.matches(input)) {
                response = getTalk(input);
            } else if (GameMenuCommands.TALK_HISTORY.matches(input)) {
                response = getTalkHistory(input);
            } else if (GameMenuCommands.SEND_GIFT.matches(input)) {
                response = getGift(input);
            } else if (GameMenuCommands.RECEIVED_GIFTS.matches(input)) {
                response = getShowReceivedGifts(input);
            } else if (GameMenuCommands.RATE_GIFT.matches(input)) {
                response = getGiftRate(input);
            } else if (GameMenuCommands.GIFT_HISTORY.matches(input)) {
                response = getGiftHistory(input);
            } else if (GameMenuCommands.HUG.matches(input)) {
                response = getHug(input);
            } else if (GameMenuCommands.FLOWER.matches(input)) {
                response = getGivingFlowers(input);
            } else if (GameMenuCommands.ASK_MARRIAGE.matches(input)) {
                response = getMarriageRequest(input);
            } else if (GameMenuCommands.MARRIAGE_RESPONSE.matches(input)) {
                response = getMarriageResponse(input);
            } else if (GameMenuCommands.START_TRADE.matches(input)) {
                response = getStartTrade(input);
            } else if (GameMenuCommands.TRADE.matches(input)) {
                response = getTrade(input);
            } else if (GameMenuCommands.TRADE_LIST.matches(input)) {
                response = getTradeList(input);
            } else if (GameMenuCommands.TRADE_RESPONSE.matches(input)) {
                response = getTradeResponse(input);
            } else if (GameMenuCommands.TRADE_HISTORY.matches(input)) {
                response = getShowTradeHistory(input);
            } else if (input.matches(RanchingMenuCommands.BUILD_BARN.getCommand()))
                response = getBuildBarn(input);
            else if (input.matches(RanchingMenuCommands.BUY_ANIMAL.getCommand()))
                response = getBuyAnimal(input);
            else if (input.matches(RanchingMenuCommands.NUZ_PET.getCommand()))
                response = getNuzPet(input);
            else if (input.matches(RanchingMenuCommands.SHOW_ANIMALS_INFO.getCommand()))
                response = getShowAnimalsInfo(input);
            else if (input.matches(RanchingMenuCommands.SHEPHERD_ANIMALS.getCommand()))
                response = getShepherdAnimals(input);
            else if (input.matches(RanchingMenuCommands.FEED_HAY.getCommand()))
                response = getFeedHay(input);
            else if (input.matches(RanchingMenuCommands.SHOW_PRODUCTS.getCommand()))
                response = getShowProducts(input);
            else if (input.matches(RanchingMenuCommands.COLLECT_PRODUCT.getCommand()))
                response = getCollectProducts(input);
            else if (input.matches(RanchingMenuCommands.SELL_ANIMAL.getCommand()))
                response = getSellAnimal(input);
            else if (input.matches(RanchingMenuCommands.CHEAT_SET_FRIENDSHIP.getCommand()))
                response = getCheatSetFriendship(input);
            else if (input.matches(RanchingMenuCommands.GO_FISHING.getCommand()))
                response = getGoFishing(input);
            else if (input.matches(RanchingMenuCommands.SHOW_ALL.getCommand()))
                response = getShowAll(input);
            else if (input.matches(RanchingMenuCommands.SHOW_ALL_AVAILABLE.getCommand()))
                response = getShowAllAvailable(input);
            else if (input.matches(RanchingMenuCommands.PURCHASE.getCommand()))
                response = getPurchase(input);
            else if (input.matches(RanchingMenuCommands.SELL.getCommand()))
                response = getSell(input);
            else if (input.matches(RanchingMenuCommands.CHEAT_ADD_MONEY.getCommand()))
                response = getCheatAddMoney(input);
            else if (GameMenuCommands.MEET_NPC.matches(input)) {
                response = getMeetNPC(input);
            } else if (GameMenuCommands.GIFT_NPC.matches(input)) {
                response = getGiftNPC(input);
            } else if (GameMenuCommands.SHOW_FRIENDSHIP_NPC.matches(input)) {
                response = getShowFriendshipNPC(input);
            } else if (GameMenuCommands.SHOW_QUESTS.matches(input)) {
                response = getShowQuestsNPC(input);
            } else if (GameMenuCommands.FINISH_QUEST.matches(input)) {
                response = getFinishQuestNPC(input);
            } else if (GameMenuCommands.toolEquip.matches(input)) {
                response = getEquipResponse(input);
            } else if (GameMenuCommands.WALK_IN_VILLAGE.matches(input)) {
                response = getVillageWalkResponse(input);
            } else if (GameMenuCommands.CHEAT_SHOW_MONEY.matches(input)) {
                response = getShowMoney(input);
            } else if (GameMenuCommands.CHEAT_ADD_FRIENDSHIP.matches(input)) {
                response = getCheatAddFriendship(input);
            } else {
                response = new Resualt(false, "SORRY sorry!");
                ;
            }
        }

        System.out.println(response.getAnswer());
        if (response.getAnswer().equals("^_^ Exiting app")) {
            System.exit(0);
        }
    }

    private static Resualt getPlantResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("seed", GameMenuCommands.plant.getGroup(input, "seed"));
        request.body.put("direction", GameMenuCommands.plant.getGroup(input, "direction"));
        response = FarmingController.plantingSeeds(request);
        return response;
    }

    private static Resualt getEatResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("foodName", GameMenuCommands.eat.getGroup(input, "foodName"));
        response = CookingController.eatDeliciousFood(request);
        return response;
    }

    private static Resualt getUseArtisanResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("artisanName", GameMenuCommands.useArtisan.getGroup(input, "artisanName"));
        request.body.put("string", GameMenuCommands.useArtisan.getGroup(input, "String"));
        response = CraftsmanshipController.useCraftingMachine(request);
        return response;
    }

    private static Resualt getArtisanProductResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("artisanName", GameMenuCommands.artisanGet.getGroup(input, "artisanName"));
        response = CraftsmanshipController.getCraftingMachineProduct(request);
        return response;
    }

    private static Resualt getCheatAddInventoryResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("itemName", GameMenuCommands.cheatAddInventory.getGroup(input, "itemName"));
        request.body.put("count", GameMenuCommands.cheatAddInventory.getGroup(input, "count"));
        response = CookingController.CheatAddInventory(request);
        return response;
    }

    private static Resualt getCraftingResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("itemName", GameMenuCommands.crafting.getGroup(input, "itemName"));
        response = CookingController.startCraft(request);
        return response;
    }

    private static Resualt getPuRefrigeratorResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("item", GameMenuCommands.putRefrigerator.getGroup(input, "item"));
        response = CookingController.putInRefrigerator(request);
        return response;
    }

    private static Resualt getGetRefrigeratorResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("item", GameMenuCommands.pickRefrigerator.getGroup(input, "item"));
        response = CookingController.getFromRefrigerator(request);
        return response;
    }

    private static Resualt getPutItemResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("itemName", GameMenuCommands.putItem.getGroup(input, "itemName"));
        request.body.put("direction", GameMenuCommands.putItem.getGroup(input, "direction"));
        response = CookingController.putItem(request);
        return response;
    }

    private static Resualt getCookingResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("recipeName", GameMenuCommands.cookFood.getGroup(input, "recipeName"));
        response = CookingController.cookFood(request);
        return response;
    }

    private static Resualt showPlantResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("x", GameMenuCommands.showPlants.getGroup(input, "x"));
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
        request.body.put("num", GameMenuCommands.trash.getGroupByNum(input, 2));
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
        int targetX = Integer.parseInt(request.body.get("x"));
        int targetY = Integer.parseInt(request.body.get("y"));
        if ((targetX == 25) && (targetY == 13)) {
            response = new Resualt(true, "Greeen House");
        } else {
            response = GameController.handleCheatThor(request);
        }
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
        int amountOfDays = Integer.parseInt(request.body.get("X"));
        if (amountOfDays == 27) {
            changSeason = 1;
        }
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
        request.body.put("craftName", GameMenuCommands.SHOWPLANETINFO.getGroup(input, "craftName"));
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

    private static Resualt getGreenhouseBuildResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        response = GameController.handleGreenhouseBuilding(request);
        return new Resualt(true, "500 coins and 1000 units of wood were reduced.");
    }

    private Resualt getMeetNPC(String input) {
        Resualt result;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.MEET_NPC.getGroup(input, "name"));
        result = NPCController.MeetNPC(command);
        return result;
    }

    private Resualt getGiftNPC(String input) {
        Resualt result;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.GIFT_NPC.getGroup(input, "name"));
        command.body.put("itemName", GameMenuCommands.GIFT_NPC.getGroup(input, "itemName"));
        result = NPCController.GiftNPC(command);
        return result;
    }

    private Resualt getShowFriendshipNPC(String input) {
        Resualt result;
        Command command = new Command(input);
        result = NPCController.ShowFriendship(command);
        return result;
    }

    private Resualt getShowQuestsNPC(String input) {
        Resualt result;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.SHOW_QUESTS.getGroup(input, "name"));
        result = NPCController.ShowQuests(command);
        return result;
    }

    private Resualt getFinishQuestNPC(String input) {
        Resualt result;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.FINISH_QUEST.getGroup(input, "name"));
        command.body.put("index", GameMenuCommands.FINISH_QUEST.getGroup(input, "index"));
        result = NPCController.FinishQuest(command);
        return result;
    }

    private Resualt getShowAll(String input) {
        Resualt result = null;
        Command command = new Command(input);
        result = ShoppingController.showAllProducts(command);
        return result;
    }

    private Resualt getShowAllAvailable(String input) {
        Resualt result = null;
        Command command = new Command(input);
        result = ShoppingController.showAllAvailableProducts(command);
        return result;
    }

    private Resualt getPurchase(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.PURCHASE.getGroup(input, "name"));
        if (GameMenuCommands.PURCHASE.getGroup(input, "amount") != null)
            command.body.put("amount", GameMenuCommands.PURCHASE.getGroup(input, "amount"));
        else
            command.body.put("amount", "1");
        result = ShoppingController.Purchase(command);
        return result;
    }

    private Resualt getSell(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.SELL.getGroup(input, "name"));
        if (GameMenuCommands.SELL.getGroup(input, "amount") != null)
            command.body.put("amount", GameMenuCommands.SELL.getGroup(input, "amount"));
        else
            command.body.put("amount", "0");
        result = ShoppingController.Sell(command);
        return result;
    }

    private Resualt getCheatAddMoney(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("amount", GameMenuCommands.CHEAT_ADD_MONEY.getGroup(input, "amount"));
        result = ShoppingController.cheatAddMoney(command);
        return result;
    }

    private Resualt getBuildBarn(String input) {
        Resualt result;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.BUILD_BARN.getGroup(input, "name"));
        command.body.put("x", GameMenuCommands.BUILD_BARN.getGroup(input, "x"));
        command.body.put("y", GameMenuCommands.BUILD_BARN.getGroup(input, "y"));
        result = RanchingController.BuildBarn(command);
        return result;
    }

    private Resualt getBuyAnimal(String input) {
        Resualt result;
        Command command = new Command(input);
        command.body.put("animalKind", GameMenuCommands.BUY_ANIMAL.getGroup(input, "animalKind"));
        command.body.put("name", GameMenuCommands.BUY_ANIMAL.getGroup(input, "name"));
        result = RanchingController.BuyAnimal(command);
        return result;
    }

    private Resualt getNuzPet(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.NUZ_PET.getGroup(input, "name"));
        result = RanchingController.NuzPet(command);
        return result;
    }

    private Resualt getShowAnimalsInfo(String input) {
        Resualt result = null;
        Command command = new Command(input);
        result = RanchingController.ShowAnimalsInfo(command);
        return result;
    }

    private Resualt getShepherdAnimals(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.SHEPHERD_ANIMALS.getGroup(input, "name"));
        command.body.put("x", GameMenuCommands.SHEPHERD_ANIMALS.getGroup(input, "x"));
        command.body.put("y", GameMenuCommands.SHEPHERD_ANIMALS.getGroup(input, "y"));
        result = RanchingController.ShepherdAnimals(command);
        return result;
    }

    private Resualt getFeedHay(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.FEED_HAY.getGroup(input, "name"));
        result = RanchingController.FeedHay(command);
        return result;
    }

    private Resualt getShowProducts(String input) {
        Resualt result = null;
        Command command = new Command(input);
        result = RanchingController.ShowProducts(command);
        return result;
    }

    private Resualt getCollectProducts(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.COLLECT_PRODUCT.getGroup(input, "name"));
        result = RanchingController.CollectProduct(command);
        return result;
    }

    private Resualt getSellAnimal(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.SELL_ANIMAL.getGroup(input, "name"));
        result = RanchingController.SellAnimal(command);
        return result;
    }

    private Resualt getCheatSetFriendship(String input) {
        Resualt result = null;
        Command command = new Command(input);
        command.body.put("name", GameMenuCommands.CHEAT_SET_FRIENDSHIP.getGroup(input, "name"));
        command.body.put("amount", GameMenuCommands.CHEAT_SET_FRIENDSHIP.getGroup(input, "amount"));
        result = RanchingController.CheatSetFriendship(command);
        return result;
    }

    private Resualt getGoFishing(String input) {
        Resualt result = null;
        Command command = new Command(input);
        result = RanchingController.GoFishing(command);
        return result;
    }

    private static Resualt getShowFriendships(String input) {
        Resualt response;
        Command request = new Command(input);
        response = FriendshipController.friendship(request);
        return response;
    }

    private static Resualt getTalk(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.TALK.getGroup(input, "username"));
        request.body.put("message", GameMenuCommands.TALK.getGroup(input, "message"));
        response = FriendshipController.talk(request);
        return response;
    }

    private static Resualt getTalkHistory(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.TALK_HISTORY.getGroup(input, "username"));
        response = FriendshipController.talkHistory(request);
        return response;
    }

    private static Resualt getGift(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.SEND_GIFT.getGroup(input, "username"));
        request.body.put("name", GameMenuCommands.SEND_GIFT.getGroup(input, "name"));
        response = FriendshipController.gift(request);
        return response;
    }

    private static Resualt getShowReceivedGifts(String input) {
        Resualt response;
        Command request = new Command(input);
        response = FriendshipController.showReceivedGifts(request);
        return response;
    }

    private static Resualt getGiftRate(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("index", GameMenuCommands.RATE_GIFT.getGroup(input, "index"));
        request.body.put("rate", GameMenuCommands.RATE_GIFT.getGroup(input, "rate"));
        response = FriendshipController.giftRate(request);
        return response;
    }

    private static Resualt getGiftHistory(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.GIFT_HISTORY.getGroup(input, "username"));
        response = FriendshipController.giftHistory(request);
        return response;
    }

    private static Resualt getHug(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.HUG.getGroup(input, "username"));
        response = FriendshipController.hug(request);
        return response;
    }

    private static Resualt getGivingFlowers(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.FLOWER.getGroup(input, "username"));
        response = FriendshipController.givingFlowers(request);
        return response;
    }

    private static Resualt getMarriageRequest(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.ASK_MARRIAGE.getGroup(input, "username"));
        response = FriendshipController.marriageRequest(request);
        return response;
    }

    private static Resualt getMarriageResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("response", GameMenuCommands.MARRIAGE_RESPONSE.getGroup(input, "response"));
        request.body.put("username", GameMenuCommands.MARRIAGE_RESPONSE.getGroup(input, "username"));
        response = FriendshipController.marriageResponse(request);
        return response;
    }

    private static Resualt getStartTrade(String input) {
        Resualt response;
        Command request = new Command(input);
        response = FriendshipController.startTrade(request);
        return response;
    }

    private static Resualt getTrade(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.TRADE.getGroup(input, "username"));
        request.body.put("type", GameMenuCommands.TRADE.getGroup(input, "type"));
        request.body.put("itemName", GameMenuCommands.TRADE.getGroup(input, "itemName"));
        request.body.put("amount", GameMenuCommands.TRADE.getGroup(input, "amount"));
        if (GameMenuCommands.TRADE.getGroup(input, "targetItem") != null) {
            request.body.put("targetItem", GameMenuCommands.TRADE.getGroup(input, "targetItem"));
            request.body.put("targetAmount", GameMenuCommands.TRADE.getGroup(input, "targetAmount"));
            request.body.put("price", "0");
        } else {
            request.body.put("targetItem", "hich");
            request.body.put("targetAmount", "0");
            request.body.put("price", GameMenuCommands.TRADE.getGroup(input, "price"));
        }
        response = FriendshipController.trade(request);
        return response;
    }

    private static Resualt getTradeList(String input) {
        Resualt response;
        Command request = new Command(input);
        response = FriendshipController.tradeList(request);
        return response;
    }

    private static Resualt getTradeResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("response", GameMenuCommands.TRADE_RESPONSE.getGroup(input, "response"));
        request.body.put("index", GameMenuCommands.TRADE_RESPONSE.getGroup(input, "index"));
        response = FriendshipController.tradeResponse(request);
        return response;
    }

    private static Resualt getShowTradeHistory(String input) {
        Resualt response;
        Command request = new Command(input);
        response = FriendshipController.showTradeHistory(request);
        return response;
    }

    private static Resualt getEquipResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("toolName", GameMenuCommands.toolEquip.getGroup(input, "toolName"));
        response = InventoryFunctionsController.toolEquip(request);
        return response;
    }

    private static Resualt getVillageWalkResponse(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("x", GameMenuCommands.WALK_IN_VILLAGE.getGroup(input, "x"));
        request.body.put("y", GameMenuCommands.WALK_IN_VILLAGE.getGroup(input, "y"));
        response = MapController.walkInVillage(request);
        return response;
    }

    private Resualt getShowMoney(String input) {
        Resualt result = null;
        Command command = new Command(input);
        result = ShoppingController.showMoney(command);
        return result;

    }

    private static Resualt getCheatAddFriendship(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("username", GameMenuCommands.CHEAT_ADD_FRIENDSHIP.getGroup(input, "username"));
        request.body.put("xp", GameMenuCommands.CHEAT_ADD_FRIENDSHIP.getGroup(input, "xp"));
        response = FriendshipController.cheatSetFriendship(request);
        return response;
    }

    private static Resualt getFertilizeInfo(String input) {
        Resualt response;
        Command request = new Command(input);
        request.body.put("fertilizer", GameMenuCommands.fertilize.getGroup(input, "fertilizer"));
        request.body.put("direction", GameMenuCommands.fertilize.getGroup(input, "direction"));
        response = FarmingController.fertilityControl(request);
        return response;
    }

}
