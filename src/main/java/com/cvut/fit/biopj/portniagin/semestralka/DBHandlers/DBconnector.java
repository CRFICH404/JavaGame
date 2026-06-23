package com.cvut.fit.biopj.portniagin.semestralka.DBHandlers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnector {
    private static final String DB_PATH = resolveDbPath();
    private static final String JDBC_URL = "jdbc:derby:" + DB_PATH + ";create=true";

    private static String resolveDbPath() {
        try {
            Path codeLocation = Paths.get(
                DBconnector.class.getProtectionDomain().getCodeSource().getLocation().toURI()
            );
            if (codeLocation.toString().endsWith(".jar")) {
                // packaged: place database next to the JAR
                return codeLocation.getParent()
                    .resolve("database/users-db")
                    .toString().replace("\\", "/");
            }
            // IDE (target/classes): navigate back to project root then into resources
            return codeLocation.getParent().getParent()
                .resolve("src/main/resources/com/cvut/fit/biopj/portniagin/semestralka/database/users-db")
                .toString().replace("\\", "/");
        } catch (Exception e) {
            return System.getProperty("user.home") + "/TowerOfGod/users-db";
        }
    }

    private Connection connection;

    public DBconnector() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        createTablesIfNotExist();
    }

    private void createTablesIfNotExist() throws SQLException {
        String createUsersTable =
            "CREATE TABLE USERS (" +
            "    USER_ID   INT          NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "    USERNAME  VARCHAR(50)  NOT NULL UNIQUE," +
            "    PASSWORD  VARCHAR(255) NOT NULL" +
            ")";

        String createPlayerStatsTable =
            "CREATE TABLE PLAYER_STATS (" +
            "    STAT_ID   INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "    USER_ID   INT NOT NULL REFERENCES USERS(USER_ID)," +
            "    RATING    INT NOT NULL DEFAULT 0" +
            ")";

        String createDaySnapshotsTable =
            "CREATE TABLE DAY_SNAPSHOTS (" +
            "    SNAPSHOT_ID      INT         NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "    USER_ID          INT         NOT NULL REFERENCES USERS(USER_ID)," +
            "    DAY              INT         NOT NULL," +
            "    CURRENT_HEALTH   INT         NOT NULL," +
            "    CURRENT_WINS     INT         NOT NULL," +
            "    PLAYER_RATING    INT         NOT NULL," +
            "    PLAYER_USERNAME  VARCHAR(50) NOT NULL," +
            "    PLAYER_MAX_HP    INT         NOT NULL DEFAULT 500," +
            "    PLAYER_LVL       INT         NOT NULL DEFAULT 1" +
            ")";

        String createSnapshotInventoryTable =
            "CREATE TABLE SNAPSHOT_ACTIVE_INVENTORY (" +
            "    INV_ITEM_ID      INT          NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "    SNAPSHOT_ID      INT          NOT NULL REFERENCES DAY_SNAPSHOTS(SNAPSHOT_ID)," +
            "    SLOT_POSITION    INT          NOT NULL," +
            "    ITEM_ID          INT          NOT NULL," +
            "    ITEM_RARITY      VARCHAR(20)  NOT NULL," +
            "    ITEM_COST        INT          NOT NULL," +
            "    ITEM_DAMAGE      INT          NOT NULL," +
            "    ITEM_LEVEL       INT          NOT NULL," +
            "    ITEM_MULTICAST   INT          NOT NULL," +
            "    ITEM_CRIT_CHANCE FLOAT        NOT NULL," +
            "    ITEM_COOLDOWN    FLOAT        NOT NULL," +
            "    ITEM_NAME        VARCHAR(100) NOT NULL," +
            "    SE_TRIGGER       VARCHAR(30)," +
            "    SE_TARGET        VARCHAR(30)," +
            "    SE_EFFECT        VARCHAR(30)," +
            "    SE_AMOUNT        FLOAT" +
            ")";

        try (Statement stmt = connection.createStatement()) {
            createIfNotExists(stmt, createUsersTable);
            createIfNotExists(stmt, createPlayerStatsTable);
            createIfNotExists(stmt, createDaySnapshotsTable);
            addColumnIfNotExists(stmt, "DAY_SNAPSHOTS", "PLAYER_MAX_HP", "ALTER TABLE DAY_SNAPSHOTS ADD COLUMN PLAYER_MAX_HP INT NOT NULL DEFAULT 500");
            addColumnIfNotExists(stmt, "DAY_SNAPSHOTS", "PLAYER_LVL",    "ALTER TABLE DAY_SNAPSHOTS ADD COLUMN PLAYER_LVL INT NOT NULL DEFAULT 1");
            createIfNotExists(stmt, createSnapshotInventoryTable);
        }
    }

    private void createIfNotExists(Statement stmt, String ddl) throws SQLException {
        try {
            stmt.execute(ddl);
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) {
                System.out.println(e.getSQLState());
            }
        }
    }

    private void addColumnIfNotExists(Statement stmt, String tableName, String columnName, String ddl) throws SQLException {
        String check =
            "SELECT 1 FROM SYS.SYSCOLUMNS c " +
            "JOIN SYS.SYSTABLES t ON c.REFERENCEID = t.TABLEID " +
            "WHERE t.TABLENAME = '" + tableName + "' AND c.COLUMNNAME = '" + columnName + "'";
        try (ResultSet rs = stmt.getConnection().createStatement().executeQuery(check)) {
            if (!rs.next()) {
                stmt.execute(ddl);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
