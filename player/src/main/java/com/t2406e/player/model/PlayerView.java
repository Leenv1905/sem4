package com.t2406e.player.model;

public class PlayerView {
    private int playerIndexId;   // id của player_index
    private int playerId;        // id của player
    private String playerName;
    private String fullName;
    private String age;
    private int indexId;
    private String indexName;
    private float value;

    // getter & setter
    public PlayerView () {}

    public PlayerView(int playerIndexId, int playerId, String playerName, String fullName , String age, int indexId, String indexName, float value) {
        this.playerIndexId = playerIndexId;
        this.playerId = playerId;
        this.playerName = playerName;
        this.fullName = fullName;
        this.age = age;
        this.indexId = indexId;
        this.indexName = indexName;
        this.value = value;
    }

    public int getPlayerIndexId() {
        return playerIndexId;
    }

    public void setPlayerIndexId(int playerIndexId) {
        this.playerIndexId = playerIndexId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getIndexId() {
        return indexId;
    }

    public void setIndexId(int indexId) {
        this.indexId = indexId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
