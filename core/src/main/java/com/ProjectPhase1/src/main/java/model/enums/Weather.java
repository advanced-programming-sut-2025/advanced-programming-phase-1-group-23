package model.enums;

public enum Weather {
    SUNNY("Sunny", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN, Season.WINTER}),
    RAIN("Rain", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN}),
    STORM("Storm", new Season[]{Season.SPRING, Season.SUMMER, Season.AUTUMN}),
    SNOW("Snow", new Season[]{Season.WINTER});

    public Season[] possibleSeasons;
    private String name;

    Weather(String name, Season[] seasons) {
        this.name = name;
        this.possibleSeasons = seasons;
    }

    public String string() {
        return name;
    }

    public boolean isWeatherPossible(Season season) {
        for (Season possibleSeason : possibleSeasons) {
            if (possibleSeason == season) {
                return true;
            }
        }
        return false;
    }

    public static Weather getWeatherByName(String input) {
        for (Weather weather : Weather.values()) {
            if (weather.name.compareToIgnoreCase(input) == 0) {
                return weather;
            }
        }
        return null;
    }

}
