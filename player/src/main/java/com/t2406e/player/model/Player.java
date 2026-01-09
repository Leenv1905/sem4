package com.t2406e.player.model;

public class Player {
    private int playerId;
    private String name;
    private String fullName;
    private String age;

    // getter & setter
    public Player (){}

    public Player(int playerId, String name, String fullName, String age) {
        this.playerId = playerId;
        this.name = name;
        this.fullName = fullName;
        this.age = age;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

