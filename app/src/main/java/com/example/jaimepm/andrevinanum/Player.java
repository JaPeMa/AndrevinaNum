package com.example.jaimepm.andrevinanum;

public class Player implements Comparable<Player> {
    private String name;
    private int attempts;

    public Player(String name, int attempts) {
        this.name = name;
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }

    public int getAttempts() {
        return attempts;
    }

    @Override
    public int compareTo(Player player){
        return this.attempts - player.attempts;
    }

}