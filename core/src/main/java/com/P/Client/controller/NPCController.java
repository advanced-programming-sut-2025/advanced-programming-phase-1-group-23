package com.P.Client.controller;

import java.time.Duration;
import java.util.Random;

import com.P.common.model.Resualt;
import com.P.Client.model.Command;
import com.P.common.model.NPC.NPC;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.enums.Ingredients;
import com.P.common.model.NPC.Quest;
import org.json.JSONArray;
import org.json.JSONObject;  // برای کار با JSON

import java.net.URI;         // برای کار با URL
import java.net.http.HttpClient;   // برای ارسال درخواست HTTP
import java.net.http.HttpRequest;  // برای ساختن درخواست HTTP
import java.net.http.HttpResponse; // برای دریافت پاسخ از API
import java.io.IOException;    // برای مدیریت خطاها
import java.net.http.HttpHeaders; // برای هدرهای HTTP
import java.util.concurrent.ExecutionException;  // برای مدیریت خطاهای اجرایی
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

import static java.lang.Integer.parseInt;

public class NPCController extends ControllersController {
    public static Resualt MeetNPC(Command request) {
        String name = request.body.get("name");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        NPC npc = getNPCbyName(name);
        if (npc == null)
            return new Resualt(false, "No NPC found.");
        if (!player.getPosition().isNextTo(npc.getPosition()))
            return new Resualt(false, name + " can't hear you! You are so far.");
        int i = game.getPlayers().indexOf(player);
        int previous = npc.getFriendships().get(i).getFriendshipLevel();
        if (!npc.getFriendships().get(i).isHaveTalked()) {
            npc.getFriendships().get(i).changeXp(20);
            npc.getFriendships().get(i).setHaveTalked(true);
        }
        if (npc.getFriendships().get(i).getFriendshipLevel() == 1 && previous == 0 &&
            !npc.getQuests().get(1).isCompleted())
            npc.getActiveQuests().get(i).add(npc.getQuests().get(1));
        return new Resualt(true, name + ": " + getDialogue(npc));
    }

    public static String getGPT(NPC npc) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");

        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int i = game.getPlayers().indexOf(player);
        String apiKey = "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";

        String prompt = String.format(
            "Generate a short dialogue line for an NPC, one sentence only. " +
                "The weather today is %s. The season is %s. Friendship level is %d.",
            game.getWeatherToday(),
            game.getSeason(),
            npc.getFriendships().get(i).getFriendshipLevel()
        );

        JSONObject message = new JSONObject()
            .put("role", "user")
            .put("content", prompt);

        JSONArray messages = new JSONArray().put(message);

        JSONObject jsonBody = new JSONObject()
            .put("model", "gpt-4o-mini")
            .put("messages", messages);

        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openai.com/v1/chat/completions"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + apiKey)
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody.toString()))
            .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject responseJson = new JSONObject(response.body());
                String reply = responseJson
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .optString("content", "No response");
                return reply.trim();
            } else {
                System.err.println("Error: " + response.statusCode() + " - " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Resualt GiftNPC(NPC npc, String itemName) {
//        String name = request.body.get("name");
//        String itemName = request.body.get("itemName");
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
//        NPC npc = getNPCbyName(name);

        Ingredients gift = null;
        for (Ingredients ingredients : player.getInventory().getIngredients().keySet())
            if (ingredients.getName().equals(itemName))
                gift = ingredients;
        if (gift == null)
            return new Resualt(false, "You can't give " + itemName + " to " + npc.getName());

        Integer amount = player.getInventory().getIngredients().get(gift);
        if (amount == 1)
            player.getInventory().getIngredients().remove(gift);
        else
            player.getInventory().getIngredients().put(gift, amount - 1);
        int i = game.getPlayers().indexOf(player);
        int previous = npc.getFriendships().get(i).getFriendshipLevel();
        if (!npc.getFriendships().get(i).isHaveGifted()) {
            if (npc.getFavorites().contains(gift))
                npc.getFriendships().get(i).changeXp(200);
            else
                npc.getFriendships().get(i).changeXp(50);
            npc.getFriendships().get(i).setHaveGifted(true);
        }
        if (npc.getFriendships().get(i).getFriendshipLevel() == 1 && previous == 0 &&
            !npc.getQuests().get(1).isCompleted())
            npc.getActiveQuests().get(i).add(npc.getQuests().get(1));
        return new Resualt(true, "Gift sent successfully!");
    }

    public static Resualt ShowFriendship(NPC npc) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int i = game.getPlayers().indexOf(player);
        StringBuilder response = new StringBuilder();
        // for(NPC npc : game.getNpcs())
        response.append(npc.getName()).append(": ").append(npc.getFriendships().get(i).getXp())
            .append(", level ").append(npc.getFriendships().get(i).getFriendshipLevel()).append("\n");
        return new Resualt(true, response.toString());
    }

    public static Resualt ShowQuests(NPC npc) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder response = new StringBuilder();
        boolean completed = false;
        int i = game.getPlayers().indexOf(player);
        for (int j = 0; j < npc.getActiveQuests().get(i).size(); j++) {
            Quest quest = npc.getActiveQuests().get(i).get(j);
            response.append("Quest ").append(j + 1).append(": ");
            response.append(quest.getRequirement().getIngredient().getName()).append(", ");
            response.append(quest.getRequirement().getNumber()).append(", Status: ");
            if (quest.isCompleted()) {
                response.append("completed\n");
                completed = true;
            } else
                response.append("ongoing\n");
        }
        return new Resualt(completed, response.toString());
    }

    public static Resualt ShowAllQuests(NPC npc) {
        StringBuilder response = new StringBuilder();
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int ind = game.getPlayers().indexOf(player);
        for (Quest quest : npc.getQuests()) {
            response.append("Required: ").append(quest.getRequirement().getIngredient().getName()).append(" ").append(quest.getRequirement().getNumber()).append("   ");
            response.append("Reward: ").append(quest.getReward().getIngredient().getName()).append(" ").append(quest.getReward().getNumber()).append("   ");
            String status;
            if (!npc.getActiveQuests().get(ind).contains(quest))
                status = "not taken";
            else if (quest.isCompleted())
                status = "completed";
            else
                status = "in progress";
            response.append("Status: ").append(status).append("\n");
        }
        return new Resualt(true, response.toString());
    }

    public static Resualt FinishQuest(Command request) {
        String name = request.body.get("name");
        int index = parseInt(request.body.get("index")) - 1;
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        NPC npc = getNPCbyName(name);
        if (npc == null)
            return new Resualt(false, "No NPC found.");
        if (!player.getPosition().isNextTo(npc.getPosition()))
            return new Resualt(false, "You are so far.");
        int i = game.getPlayers().indexOf(player);
        if (npc.getActiveQuests().get(i).size() <= index)
            return new Resualt(false, "No quest found.");
        Quest quest = npc.getActiveQuests().get(i).get(index);
        if (quest.isCompleted())
            return new Resualt(false, "This quest has been already completed.");
        Ingredients ingredients = quest.getRequirement().getIngredient();
        Integer amount = quest.getRequirement().getNumber();
        Integer number = player.getInventory().getIngredients().get(ingredients);
        if (number == null || number < amount)
            return new Resualt(false, "Not enough items to complete the quest.");
        if (amount.equals(number))
            player.getInventory().getIngredients().remove(ingredients);
        else
            player.getInventory().getIngredients().put(ingredients, number - amount);
        ingredients = quest.getReward().getIngredient();
        amount = quest.getReward().getNumber();
        number = player.getInventory().getIngredients().get(ingredients);
        if (number == null)
            number = 0;
        if (npc.getFriendships().get(i).getFriendshipLevel() >= 2)
            amount *= 2;
        player.getInventory().getIngredients().put(ingredients, number + amount);
        quest.setCompleted(true);
        player.addCompletedQuests();
        return new Resualt(true, "Quest completed successfully!");
    }

    private static NPC getNPCbyName(String name) {
        Game game = App.getLoggedInUser().getCurrentGame();
        for (NPC npc : game.getNpcs())
            if (npc.getName().equals(name))
                return npc;
        return null;
    }

    public static String getDialogue(NPC npc) {
        Game game = App.getLoggedInUser().getCurrentGame();
        Player player = game.getCurrentPlayer();
        int i = game.getPlayers().indexOf(player);

        Random random = new Random();
        int r = random.nextInt(3);
        if (r == 0) {
            // Weather dialogues
            String[] sunnyMessages = {
                "What a beautiful day it is! I love sunny days.",
                "The sun is shining brightly today.",
                "A perfect day for a picnic, don't you think?",
                "Sunny skies always lift my mood.",
                "Time to wear my favorite sunglasses!",
                "The flowers are blooming beautifully in this sun.",
                "A great day for a long walk outside.",
                "The warmth of the sun feels amazing.",
                "Perfect weather for some outdoor games.",
                "I hope you brought your sunhat today!",
                "The sunlight is reflecting off the lake beautifully.",
                "I love seeing the shadows of trees in the sun.",
                "Birds are singing more joyfully on sunny days.",
                "It's so bright, I can barely open my eyes!",
                "Nothing beats a sunny morning with fresh air.",
                "The sky is a clear, endless blue today.",
                "Time to enjoy some ice cream under the sun!",
                "A perfect day to plant some flowers in the garden.",
                "Sunshine makes everything look more colorful.",
                "I feel energized just being out in the sun."
            };

            String[] rainMessages = {
                "Can't wait to see the rainbow when the rain ends!",
                "I love the sound of raindrops on the roof.",
                "Rainy days make me want to drink hot cocoa.",
                "The garden is so fresh after the rain.",
                "Perfect weather to curl up with a book.",
                "I enjoy walking with an umbrella on rainy days.",
                "The smell of rain is so refreshing.",
                "It's cozy inside while the rain pours outside.",
                "Rain gives life to the flowers and trees.",
                "Listening to rain is surprisingly calming.",
                "The streets shine under the wet rain.",
                "Time to jump in puddles!",
                "Rainy evenings make the town so peaceful.",
                "I love watching raindrops slide down windows.",
                "The world looks cleaner after rain.",
                "Rainy weather is perfect for painting.",
                "The sound of rain is music to my ears.",
                "It’s raining cats and dogs today!",
                "I enjoy the smell of wet earth.",
                "Raindrops make everything sparkle."
            };

            String[] stormMessages = {
                "We'd better go home as fast as possible; the weather is getting worse.",
                "The wind is howling; stay safe!",
                "Thunder is roaring, keep away from trees.",
                "Lightning is scary; better stay indoors.",
                "Storms always make me nervous.",
                "The rain is heavy; hold onto your hat!",
                "Winds are strong today, watch out!",
                "It’s dangerous outside during a storm.",
                "Storm clouds are gathering quickly.",
                "I hope the storm passes soon.",
                "Don't forget to close the windows!",
                "The sky looks menacing and dark.",
                "Stay close to a safe shelter.",
                "The storm will soon be over, stay patient.",
                "I love storms from the safety of home.",
                "Thunder makes me jump every time!",
                "Heavy rain and strong winds today.",
                "Be careful on the roads during this storm.",
                "The weather is wild today.",
                "Storms remind me of old tales and legends."
            };

            String[] snowMessages = {
                "A great day for making snowmen, isn't it?",
                "The snow is sparkling beautifully in the sun.",
                "Time for a snowball fight!",
                "I love the crisp winter air.",
                "The ground is covered in a thick blanket of snow.",
                "Perfect weather for sledding.",
                "Snow makes everything look magical.",
                "I enjoy watching snowflakes fall.",
                "It's freezing, but beautiful outside.",
                "The forest looks enchanting with snow.",
                "Hot chocolate is perfect for a snowy day.",
                "Walking on snow makes a satisfying crunch.",
                "Snowy nights are peaceful and quiet.",
                "I love building snow forts!",
                "The world looks like a winter wonderland.",
                "Snow makes the town feel festive.",
                "Time to wear warm coats and mittens.",
                "The snow glistens under the moonlight.",
                "I love watching children play in the snow.",
                "Snow makes everything feel fresh and clean."
            };

            switch (game.getWeatherToday()) {
                case SUNNY -> {
                    return sunnyMessages[random.nextInt(sunnyMessages.length)];
                }
                case RAIN -> {
                    return rainMessages[random.nextInt(rainMessages.length)];
                }
                case STORM -> {
                    return stormMessages[random.nextInt(stormMessages.length)];
                }
                case SNOW -> {
                    return snowMessages[random.nextInt(snowMessages.length)];
                }
                default -> {
                    return "I'm never gonna tell you this.";
                }
            }

        }
        else if (r == 1) {
            // Season dialogues
            String[] springMessages = {
                "The migrating swallows are coming back. Have you seen the nests?!",
                "Spring brings new life and flowers.",
                "The weather is perfect for planting seeds.",
                "Birds are chirping cheerfully everywhere.",
                "I love the fresh scent of spring air.",
                "Trees are blossoming beautifully this season.",
                "Spring mornings are so refreshing.",
                "The garden looks alive and vibrant.",
                "It's time to clean up the winter mess.",
                "Flowers bloom in all colors in spring.",
                "Spring showers bring lush greenery.",
                "The world seems full of hope this season.",
                "Walking in the park is delightful now.",
                "I love seeing butterflies in spring.",
                "The days are getting longer and warmer.",
                "Spring makes me feel energized.",
                "Fresh fruits are coming into season.",
                "The town looks colorful with blooming flowers.",
                "Spring festivals are a joy to attend.",
                "Nature wakes up beautifully in spring."
            };

            String[] summerMessages = {
                "Farmer Robinson has the best peach trees this summer. How are you doing in the garden?",
                "Summer days are perfect for swimming.",
                "The sun is hot but I love it.",
                "Ice cream tastes best in summer.",
                "Time for outdoor festivals and fairs.",
                "Summer evenings are wonderful for strolls.",
                "The nights are warm and pleasant now.",
                "I love the sound of crickets in summer.",
                "Gardens are full of ripe fruits.",
                "It's perfect weather for barbecues.",
                "Long summer days feel endless.",
                "Time to enjoy the beach and sun.",
                "Summer storms are dramatic and beautiful.",
                "The heat makes lemonade irresistible.",
                "Camping trips are best in summer.",
                "Fireflies light up the summer nights.",
                "Farmers work hard during summer harvest.",
                "Summer brings a lively, vibrant energy.",
                "Time for fresh berries and fruits.",
                "Summer is perfect for adventures outdoors."
            };

            String[] autumnMessages = {
                "Come visit us sometime. It's good to have a companion on these long evenings.",
                "The leaves are turning golden and red.",
                "Autumn winds are cool and refreshing.",
                "Pumpkin spice is everywhere this season!",
                "The harvest is in full swing now.",
                "It's sweater weather finally.",
                "I love watching leaves fall slowly.",
                "The sunsets look incredible in autumn.",
                "Crisp mornings make the air delightful.",
                "Autumn festivals are so fun to attend.",
                "The town feels cozy and peaceful.",
                "Apple picking season is here!",
                "Time to enjoy warm drinks by the fire.",
                "Animals are preparing for winter.",
                "The forest is a palette of colors.",
                "Autumn evenings are calm and quiet.",
                "The air smells of earth and leaves.",
                "It's perfect weather for hiking.",
                "Autumn brings reflection and calm.",
                "The harvest moon shines bright at night."
            };

            String[] winterMessages = {
                "Guess it's the coldest winter we've had in past two decades!",
                "Snow blankets the town beautifully.",
                "Winter winds are sharp and chilly.",
                "Time to wear thick coats and scarves.",
                "I love hot drinks in winter.",
                "The nights are long and quiet.",
                "Winter mornings are crisp and fresh.",
                "The town looks magical in snow.",
                "Firesides make winter evenings cozy.",
                "Ice skating is fun in this weather.",
                "Snow brings joy to children everywhere.",
                "The stars are bright in clear winter nights.",
                "Winter festivals are a delight.",
                "I love the sparkle of frost on trees.",
                "The cold makes everything feel fresh.",
                "Time to enjoy hearty winter meals.",
                "Winter nights are perfect for stories.",
                "The air is clean and invigorating.",
                "Snowy walks are peaceful and serene.",
                "Winter brings a calm, reflective mood."
            };

            switch (game.getSeason()) {
                case SPRING -> {
                    return springMessages[random.nextInt(springMessages.length)];
                }
                case SUMMER -> {
                    return summerMessages[random.nextInt(summerMessages.length)];
                }
                case AUTUMN -> {
                    return autumnMessages[random.nextInt(autumnMessages.length)];
                }
                case WINTER -> {
                    return winterMessages[random.nextInt(winterMessages.length)];
                }
                default -> {
                    return "I'm never gonna tell you this.";
                }
            }

        }
        else {
            // Friendship dialogues
            String[] friendship0 = {
                "Hey there! Good to see you.",
                "Hello! How's your day going?",
                "Hi! Nice running into you.",
                "Good to have a chat with you.",
                "Hey! Hope you're doing well.",
                "Hi there! How's everything?",
                "Hello! It's nice to meet you.",
                "Hey! What have you been up to?",
                "Good day! How are you?",
                "Hi! Long time no see.",
                "Hello! I hope you're happy today.",
                "Hey! How's life treating you?",
                "Hi there! It's a lovely day, isn't it?",
                "Hello! Glad to see you around.",
                "Hey! How's your work going?",
                "Hi! Everything okay on your side?",
                "Hello! Lovely weather we're having.",
                "Hey there! What's new?",
                "Hi! How have you been?",
                "Hello! Good to catch up a bit."
            };

            String[] friendship1 = {
                "Long time no see! How are you doing?",
                "Hey! It's been a while.",
                "Good to see you again.",
                "Hi! How's everything since we last met?",
                "Hello! I missed our chats.",
                "Hey! How's life going for you?",
                "Hi there! It's nice to see you again.",
                "Hello! How have you been keeping?",
                "Hey! What have you been up to lately?",
                "Hi! It's good to reconnect.",
                "Hello! Long time indeed.",
                "Hey! It's been ages, hasn't it?",
                "Hi! I hope all is well with you.",
                "Hello! Nice to meet again.",
                "Hey! Feels good to see you.",
                "Hi! How's your family doing?",
                "Hello! How's your work going?",
                "Hey! Been a while, hasn't it?",
                "Hi there! Good to see a friendly face.",
                "Hello! How's your health these days?"
            };

            String[] friendship2 = {
                "What a pleasant accident! I was just heading to your farm.",
                "Hey! Fancy meeting you here.",
                "I was thinking of visiting you today.",
                "Hello! Great timing to see you.",
                "Hey! What a surprise meeting you.",
                "I was just about to call you.",
                "Hello! How coincidental to meet here.",
                "Hey! I hope your day is going well.",
                "What a nice surprise to see you!",
                "Hi! Just the person I wanted to meet.",
                "Hello! We seem to be on the same path.",
                "Hey! What luck to meet today.",
                "Hi! The stars aligned for us to meet.",
                "Hello! Nice bumping into you.",
                "Hey! You made my day by showing up.",
                "Hi there! Perfect timing, as always.",
                "Hello! I was hoping to see you soon.",
                "Hey! What a delightful coincidence.",
                "Hi! Good to see you around.",
                "Hello! It's nice catching up like this."
            };

            String[] friendship3 = {
                "Dani ke chist dolat? Didar e yar didan!",
                "My dear friend! How wonderful to meet you.",
                "Hey! It's always a joy to see you.",
                "Long-lost friend! How have you been?",
                "I treasure our friendship, as always.",
                "Hello! Your presence brightens the day.",
                "Friendship like ours is rare.",
                "Hey! I missed seeing your face.",
                "Hi! My friend, how are you feeling?",
                "Hello! It feels like ages since our last talk.",
                "Hey! You always bring joy when around.",
                "My cherished friend! How lovely to see you.",
                "Hello! Together again at last.",
                "Friendship grows stronger with every meeting.",
                "Hi! Your visit is a delight.",
                "Hey! It's wonderful to share this moment.",
                "Hello! I am so happy to see you.",
                "My friend! It's great to be together.",
                "Hi! We've shared many memorable times.",
                "Hello! What a pleasure your company is."
            };

            int level = npc.getFriendships().get(i).getFriendshipLevel();
            switch (level) {
                case 0 -> {
                    return friendship0[random.nextInt(friendship0.length)];
                }
                case 1 -> {
                    return friendship1[random.nextInt(friendship1.length)];
                }
                case 2 -> {
                    return friendship2[random.nextInt(friendship2.length)];
                }
                case 3 -> {
                    return friendship3[random.nextInt(friendship3.length)];
                }
                default -> {
                    return "I'm never gonna tell you this.";
                }
            }
        }
    }
}
