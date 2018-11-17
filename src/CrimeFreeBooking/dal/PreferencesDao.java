package CrimeFreeBooking.dal;

import CrimeFreeBooking.model.Listings;
import CrimeFreeBooking.model.Preferences;
import CrimeFreeBooking.model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * PreferencesDao is an object containing.
 */
public class PreferencesDao {

  protected ConnectionManager connectionManager;

  private static PreferencesDao instance = null;
  protected PreferencesDao () {
    connectionManager = new ConnectionManager();
  }
  public static PreferencesDao getInstance() {
    if(instance == null) {
      instance = new PreferencesDao();
    }
    return instance;
  }

  public Preferences create(Preferences preferences) throws SQLException {
    String insertPreferences =
        "INSERT INTO Preferences(UserName,Bathrooms,Bedrooms) " +
            "VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertPreferences,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, preferences.getUser());
      insertStmt.setInt(2, preferences.getBathrooms());
      insertStmt.setInt(3, preferences.getBedrooms());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int preferenceId = -1;
      if(resultKey.next()) {
        preferenceId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      preferences.setPreferenceId(preferenceId);
      return preferences;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Preferences updatePreference(int preferences, int bedrooms, int bathrooms )throws SQLException {
    String updatePreferences = "UPDATE Preferences SET Bathrooms= ? , Bedrooms= ?  WHERE PreferenceId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePreferences);
      updateStmt.setInt(1, bathrooms);
      updateStmt.setInt(2, bedrooms);
      updateStmt.setInt(3, preferences);
      updateStmt.executeUpdate();
      Preferences pref = PreferencesDao.getInstance().getPreferenceById(preferences);
      pref.setBathrooms(bathrooms);
      pref.setBedrooms(bedrooms);
      return pref;
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
    return null;
  }

  public Preferences delete(int preferences) throws SQLException {
    String deletePrefernce = "DELETE FROM Preferences WHERE PreferenceId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deletePrefernce);
      deleteStmt.setInt(1, preferences);
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  public Preferences getPreferenceById(int preferenceId) throws SQLException {
    String selectPreference =
        "SELECT PreferenceId,UserName,Bathrooms,Bedrooms " +
            "FROM Preferences " +
            "WHERE PreferenceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPreference);
      selectStmt.setInt(1, preferenceId);
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();

      if(results.next()) {
        int resultPreferenceId = results.getInt("PreferenceId");
        String userName = results.getString("UserName");
        int bathrooms = results.getInt("Bathrooms");
        int bedrooms = results.getInt("Bedrooms");

        Users user = usersDao.getUserByUserName(userName);
        Preferences preferences = new Preferences(resultPreferenceId, user.getUserName(), bathrooms,bedrooms);
        return preferences;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return null;
  }

  public List<Preferences> getPreferenceOfUser(Users user) throws SQLException {
    List<Preferences> preferencesList = new ArrayList<Preferences>();
    String selectWishlists =
        "SELECT PreferenceId,UserName,Bathrooms,Bedrooms " +
            "FROM Preferences " +
            "WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectWishlists);
      selectStmt.setString(1, user.getUserName());
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      ListingsDao listingsDao = ListingsDao.getInstance();

      while(results.next()) {
        int resultPreferenceId = results.getInt("PreferenceId");
        String userName = results.getString("UserName");
        int bathrooms = results.getInt("Bathrooms");
        int bedrooms = results.getInt("Bedrooms");

        Users resultUser = usersDao.getUserByUserName(userName);
        Preferences preferences = new Preferences(resultPreferenceId, resultUser.getUserName(), bathrooms,bedrooms);
        preferencesList.add(preferences);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return preferencesList;
  }
}
