package com.cvut.fit.biopj.portniagin.semestralka.DBHandlers;

import com.cvut.fit.biopj.portniagin.semestralka.items.Inventory;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.items.ItemSpecialEffect;
import com.cvut.fit.biopj.portniagin.semestralka.player.ActiveInventory;
import com.cvut.fit.biopj.portniagin.semestralka.player.User;
import com.cvut.fit.biopj.portniagin.semestralka.session.DaySnapshot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class DBUploader {
    private final Connection connection;

    public DBUploader(DBconnector connector) {
        this.connection = connector.getConnection();
    }

    public User registerUser(String username, String password) throws SQLException {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int newUserID = keys.getInt(1);
                    createPlayerStats(newUserID);
                    return new User(newUserID, username, password);
                }
            }
        }
        throw new SQLException("Failed to retrieve generated user ID after insert");
    }

    private void createPlayerStats(int userID) throws SQLException {
        String sql = "INSERT INTO PLAYER_STATS (USER_ID, RATING) VALUES (?, 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            stmt.executeUpdate();
        }
    }

    public void updateRating(int userID, int rating) throws SQLException {
        String sql = "UPDATE PLAYER_STATS SET RATING = ? WHERE USER_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rating);
            stmt.setInt(2, userID);
            stmt.executeUpdate();
        }
    }

    public void saveDaySnapshot(int userID, DaySnapshot snapshot) throws SQLException {
        String sql =
            "INSERT INTO DAY_SNAPSHOTS " +
            "(USER_ID, DAY, CURRENT_HEALTH, CURRENT_WINS, PLAYER_RATING, PLAYER_USERNAME, PLAYER_MAX_HP, PLAYER_LVL) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, snapshot.getSession().getDay());
            stmt.setInt(3, snapshot.getSession().getCurrentHealth());
            stmt.setInt(4, snapshot.getSession().getCurrentWins());
            stmt.setInt(5, snapshot.getPlayer().getRating());
            stmt.setString(6, snapshot.getPlayer().getUsername());
            stmt.setInt(7, snapshot.getPlayer().getPlayerDummy().getMaxHP());
            stmt.setInt(8, snapshot.getPlayer().getPlayerDummy().getPlayerLVL());
            stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    saveActiveInventory(keys.getInt(1), snapshot.getPlayer().getPlayerDummy().getActiveInventory());
                }
            }
        }
    }

    private void saveActiveInventory(int snapshotId, ActiveInventory inventory) throws SQLException {
        String sql =
            "INSERT INTO SNAPSHOT_ACTIVE_INVENTORY " +
            "(SNAPSHOT_ID, SLOT_POSITION, ITEM_ID, ITEM_RARITY, ITEM_COST, ITEM_DAMAGE, ITEM_LEVEL, " +
            "ITEM_MULTICAST, ITEM_CRIT_CHANCE, ITEM_COOLDOWN, ITEM_NAME, SE_TRIGGER, SE_TARGET, SE_EFFECT, SE_AMOUNT) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Item [] items = inventory.getItems();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < inventory.getMAX_ITEMS(); i++) {
                Item item = items[i];
                if (item == null) continue;
                stmt.setInt(1, snapshotId);
                stmt.setInt(2, i);
                stmt.setInt(3, item.getItemID());
                stmt.setString(4, item.getItemRarity().name());
                stmt.setInt(5, item.getItemCost());
                stmt.setInt(6, item.getItemDamage());
                stmt.setInt(7, item.getItemLevel());
                stmt.setInt(8, item.getItemMulticast());
                stmt.setFloat(9, item.getItemCritChance());
                stmt.setFloat(10, item.getItemCooldown());
                stmt.setString(11, item.getItemName());
                ItemSpecialEffect se = item.getSpecialEffect();
                if (se != null) {
                    stmt.setString(12, se.getTriggerCondition().name());
                    stmt.setString(13, se.getTarget().name());
                    stmt.setString(14, se.getEffect().name());
                    stmt.setFloat(15, se.getAmount());
                } else {
                    stmt.setNull(12, Types.VARCHAR);
                    stmt.setNull(13, Types.VARCHAR);
                    stmt.setNull(14, Types.VARCHAR);
                    stmt.setNull(15, Types.FLOAT);
                }
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}
