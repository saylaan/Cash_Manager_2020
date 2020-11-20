package server;

import java.sql.*;
import org.json.JSONObject;

class Worker {
  public static void main(String[] args) {
    try {
        Connection dbConn = connectToDB("127.0.0.1");

        System.err.println("Watching vote queue");

        // while (true) {
        //     String userId = voteData.getString("user_id");
        //     String email = voteData.getString("email");
        //     String pwd = voteData.getString("pwd");

        //     System.err.printf("Processing vote for '%s' by '%s'\n", email, userId);
        //     updateVote(dbConn, userId, email, password);
        // }
    } catch (SQLException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  static void updateUser(Connection dbConn, String userId, String email, String pwd) throws SQLException {
    PreparedStatement insert = dbConn.prepareStatement(
      "INSERT INTO users (id, email, pwd) VALUES (?, ?, ?)");
    insert.setString(1, userId);
    insert.setString(2, email);
    insert.setString(3, pwd);

    try {
      insert.executeUpdate();
    } catch (SQLException e) {
      PreparedStatement update = dbConn.prepareStatement(
        "UPDATE users SET email = ? WHERE id = ?");
      update.setString(1, email);
      update.setString(2, pwd);
      update.setString(3, userId);
      update.executeUpdate();
    }
  }

  static Connection connectToDB(String host) throws SQLException {
    Connection conn = null;

    try {

      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://" + host + "/postgres";

      while (conn == null) {
        try {
            conn = DriverManager.getConnection(url, "postgres", "password");
        } catch (SQLException e) {
          System.err.println("Waiting for db");
          sleep(1000);
        }
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.err.println("Connected to db");
    return conn;
  }

  static void sleep(long duration) {
    try {
      Thread.sleep(duration);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }
}
