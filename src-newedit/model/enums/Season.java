package model.enums;

import model.Seasons.*;
import model.Weather;

public enum Season {
    Spring(new Spring()),
    Summer(new Summer()),
    Autumn(new Autumn()),
    Winter(new Winter());

    private final AppSeason season;

    Season(AppSeason season) {
        this.season = season;
    }

    public void WeatherForecast() {
        this.season.WeatherForecast();
    }

    public void Fishing() {
        this.season.Fishing();
    }
}
