package com.cvut.fit.biopj.portniagin.semestralka.DBHandlers;

import com.cvut.fit.biopj.portniagin.semestralka.enums.RarityEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTargetEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTriggerConditionEnum;
import com.cvut.fit.biopj.portniagin.semestralka.items.Inventory;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.items.ItemSpecialEffect;
import com.cvut.fit.biopj.portniagin.semestralka.player.ActiveInventory;
import com.cvut.fit.biopj.portniagin.semestralka.player.Player;
import com.cvut.fit.biopj.portniagin.semestralka.player.PlayerDummy;
import com.cvut.fit.biopj.portniagin.semestralka.player.User;
import com.cvut.fit.biopj.portniagin.semestralka.session.DaySnapshot;
import com.cvut.fit.biopj.portniagin.semestralka.session.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBLoader {
    private final Connection connection;

    public DBLoader(DBconnector connector) {
        this.connection = connector.getConnection();
    }

    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT USER_ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                    user.setLoginStatus(true);
                    return user;
                }
            }
        }
        return null;
    }

    public User findByUserID(int userID) throws SQLException {
        String sql = "SELECT USER_ID, USERNAME, PASSWORD FROM USERS WHERE USER_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                }
            }
        }
        return null;
    }

    public boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT 1 FROM USERS WHERE USERNAME = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public int getRatingByUsername(String username) throws SQLException {
        String sql =
            "SELECT ps.RATING FROM PLAYER_STATS ps " +
            "JOIN USERS u ON u.USER_ID = ps.USER_ID " +
            "WHERE u.USERNAME = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("RATING");
                }
            }
        }
        return 0;
    }

    public int getRatingByUserID(int userID) throws SQLException {
        String sql = "SELECT RATING FROM PLAYER_STATS WHERE USER_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("RATING");
                }
            }
        }
        return 0;
    }

    public DaySnapshot loadRandomDaySnapshotForDay(int day) throws SQLException {
        String sql =
            "SELECT SNAPSHOT_ID, DAY, CURRENT_HEALTH, CURRENT_WINS, PLAYER_RATING, PLAYER_USERNAME, PLAYER_MAX_HP, PLAYER_LVL " +
            "FROM DAY_SNAPSHOTS WHERE DAY = ? ORDER BY rand() FETCH FIRST 1 ROWS ONLY";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, day);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int snapshotId = rs.getInt("SNAPSHOT_ID");
                    Session session = new Session(
                        rs.getInt("DAY"),
                        rs.getInt("CURRENT_HEALTH"),
                        rs.getInt("CURRENT_WINS")
                    );
                    Player player = new Player(
                                rs.getInt("PLAYER_RATING"),
                                rs.getString("PLAYER_USERNAME"),
                        false
                    );
                    PlayerDummy dummy = player.getPlayerDummy();
                    dummy.setMaxHP(rs.getInt("PLAYER_MAX_HP"));
                    dummy.setPlayerLVL(rs.getInt("PLAYER_LVL"));
                    dummy.setActiveInventory(loadActiveInventory(snapshotId, dummy));
                    return new DaySnapshot(session, player);
                }
            }
        }
        return null;
    }

    private ActiveInventory loadActiveInventory(int snapshotId, PlayerDummy dummy) throws SQLException {
        String sql =
            "SELECT SLOT_POSITION, ITEM_ID, ITEM_RARITY, ITEM_COST, ITEM_DAMAGE, ITEM_LEVEL, ITEM_MULTICAST, " +
            "ITEM_CRIT_CHANCE, ITEM_COOLDOWN, ITEM_NAME, SE_TRIGGER, SE_TARGET, SE_EFFECT, SE_AMOUNT " +
            "FROM SNAPSHOT_ACTIVE_INVENTORY WHERE SNAPSHOT_ID = ? ORDER BY SLOT_POSITION";
        ActiveInventory activeInventory = new ActiveInventory();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, snapshotId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int slot = rs.getInt("SLOT_POSITION");
                    Item item = new Item(
                        rs.getInt("ITEM_ID"),
                        RarityEnum.valueOf(rs.getString("ITEM_RARITY")),
                        rs.getInt("ITEM_COST"),
                        rs.getInt("ITEM_DAMAGE"),
                        rs.getInt("ITEM_LEVEL"),
                        rs.getInt("ITEM_MULTICAST"),
                        rs.getFloat("ITEM_CRIT_CHANCE"),
                        rs.getFloat("ITEM_COOLDOWN"),
                        rs.getString("ITEM_NAME")
                    );
                    String seTrigger = rs.getString("SE_TRIGGER");
                    if (seTrigger != null) {
                        ItemSpecialEffect se = new ItemSpecialEffect(
                            SpecialEffectTriggerConditionEnum.valueOf(seTrigger),
                            SpecialEffectTargetEnum.valueOf(rs.getString("SE_TARGET")),
                            SpecialEffectEnum.valueOf(rs.getString("SE_EFFECT")),
                            rs.getFloat("SE_AMOUNT")
                        );
                        se.setEffectHolder(item);
                        item.setItemSpecialEffect(se);
                    }
                    activeInventory.addItem(slot, item);
                }
            }
        }
        activeInventory.setHolder(dummy);
        return activeInventory;
    }
}
