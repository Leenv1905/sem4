package com.t2406e.player.model;

public class PlayerView {
    private int id;
    private String playerName;
    private String age;
    private String indexName;
    private float value;

    // getter & setter
    public PlayerView () {}

    public PlayerView(int id, String playerName, String age, String indexName, float value) {
        this.id = id;
        this.playerName = playerName;
        this.age = age;
        this.indexName = indexName;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
