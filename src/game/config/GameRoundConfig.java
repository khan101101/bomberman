package game.config;

import java.util.ArrayList;

public class GameRoundConfig
{
    private MapConfig mapConfig;
    private ArrayList<PlayerConfig> currentPlayerConfigs;
    private ArrayList<InputConfiguration> currentInputConfigs;
    private int timeLimit;

    public MapConfig getMapConfig() {
        return mapConfig;
    }

    public void setMapConfig(MapConfig mapConfig) {
        this.mapConfig = mapConfig;
    }

    public ArrayList<PlayerConfig> getCurrentPlayerConfigs() {
        return currentPlayerConfigs;
    }

    public void setCurrentPlayerConfigs(ArrayList<PlayerConfig> currentPlayerConfigs) {
        this.currentPlayerConfigs = currentPlayerConfigs;
    }

    public ArrayList<InputConfiguration> getCurrentInputConfigs() {
        return currentInputConfigs;
    }

    public void setCurrentInputConfigs(ArrayList<InputConfiguration> currentInputConfigs) {
        this.currentInputConfigs = currentInputConfigs;
    }

    /**
     * time limit in millisecs
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}
