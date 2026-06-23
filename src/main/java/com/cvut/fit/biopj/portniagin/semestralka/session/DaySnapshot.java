package com.cvut.fit.biopj.portniagin.semestralka.session;

import com.cvut.fit.biopj.portniagin.semestralka.player.Player;

public class DaySnapshot {
    private Session session;
    private Player player;

    public DaySnapshot(Session session, Player player) {
        this.session = session;
        this.player = player;
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
