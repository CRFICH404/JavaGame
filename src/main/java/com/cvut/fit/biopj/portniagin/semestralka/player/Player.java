package com.cvut.fit.biopj.portniagin.semestralka.player;

public class Player{
    private int rating;
    private String username;
    private final boolean isPlayer;
    private PlayerDummy playerDummy;

    public Player(){
        this.rating = 0;
        this.username = "";
        this.isPlayer = true;
        this.playerDummy = null;
    }
    public Player(int rating, String username, boolean isPlayer){
        this.rating = rating;
        this.username = username;
        this.isPlayer = isPlayer;
        this.playerDummy = new PlayerDummy(this);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerDummy getPlayerDummy() {
        return playerDummy;
    }

    public void setPlayerDummy(PlayerDummy playerDummy) {
        this.playerDummy = playerDummy;
    }

    public boolean isPlayer() {return isPlayer;}

    public void deregister() {
        if (playerDummy != null) {
            playerDummy.deregister();
        }
    }
}
