package CrimeFreeBooking.dal;

import CrimeFreeBooking.model.Listings;
import CrimeFreeBooking.model.Recommendations;
import CrimeFreeBooking.model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * RecommendationsDao is an object containing.
 */
public class RecommendationsDao {

  protected ConnectionManager connectionManager;

  private static RecommendationsDao instance = null;
  protected RecommendationsDao () {
    connectionManager = new ConnectionManager();
  }
  public static RecommendationsDao getInstance() {
    if(instance == null) {
      instance = new RecommendationsDao();
    }
    return instance;
  }

  public Recommendations create(Recommendations recommendations) throws SQLException {
    String insertRecommendation =
        "INSERT INTO Recommendations(UserName,ListingId) " +
            "VALUES(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertRecommendation,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, recommendations.getUser());
      insertStmt.setInt(2, recommendations.getListing());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int recommendationId = -1;
      if(resultKey.next()) {
        recommendationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      recommendations.setRecommendationId(recommendationId);
      return recommendations;
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

  public Recommendations updateRecommendation(Recommendations recommendations, Listings listing )throws SQLException {
    String updateRecommendation = "UPDATE Recommendations SET ListingId=? WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateRecommendation);
      updateStmt.setInt(1, listing.getListingId());
      updateStmt.setInt(2, recommendations.getRecommendationId());
      updateStmt.executeUpdate();

      recommendations.setListing(listing.getListingId());
      return recommendations;
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

  public Recommendations delete(Recommendations recommendations) throws SQLException {
    String deleteRecommendation = "DELETE FROM Recommendations WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteRecommendation);
      deleteStmt.setInt(1, recommendations.getRecommendationId());
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
  public Recommendations getRecommendationById(int recommendationId) throws SQLException {
    String selectRecommendation =
        "SELECT RecommendationId,UserName,ListingId " +
            "FROM Recommendations " +
            "WHERE RecommendationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      selectStmt.setInt(1, recommendationId);
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      ListingsDao listingsDao = ListingsDao.getInstance();

      if(results.next()) {
        int resultRecommendationId = results.getInt("RecommendationId");
        String userName = results.getString("UserName");
        int listingId = results.getInt("ListingId");

        Users user = usersDao.getUserByUserName(userName);
        Listings listing = listingsDao.getListingById(listingId);
        Recommendations recommendations = new Recommendations(resultRecommendationId, user.getUserName(), listing.getListingId());
        return recommendations;
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

  public List<Recommendations> getRecommendationsByUser(Users user) throws SQLException {
    List<Recommendations> recommendationList = new ArrayList<Recommendations>();
    String selectRecommendation =
        "SELECT RecommendationId,UserName,ListingId " +
            "FROM Recommendations " +
            "WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      selectStmt.setString(1, user.getUserName());
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      ListingsDao listingsDao = ListingsDao.getInstance();

      getRecommendationList(recommendationList, results, usersDao, listingsDao);
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
    return recommendationList;
  }

  public List<Recommendations> getRecommendationsForListing(Listings listings) throws SQLException {
    List<Recommendations> recommendationList = new ArrayList<Recommendations>();
    String selectRecommendation =
        "SELECT RecommendationId,UserName,ListingId " +
            "FROM Recommendations " +
            "WHERE ListingId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      selectStmt.setInt(1, listings.getListingId());
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      ListingsDao listingsDao = ListingsDao.getInstance();
      getRecommendationList(recommendationList, results, usersDao, listingsDao);
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
    return recommendationList;
  }

  public List<Recommendations> getRecommendationsForCity(String city) throws SQLException {

    List<Recommendations> recommendationList = new ArrayList<Recommendations>();
    String selectRecommendation =
        "SELECT RecommendationId,UserName,ListingId " +
            "FROM Recommendations " +
            "WHERE ListingId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      ListingsDao listingsDao = ListingsDao.getInstance();
      List<Listings> recommendedListings =listingsDao.getListingsBasedOnCity(city);
      for(Listings listings : recommendedListings) {
        selectStmt.setInt(1, listings.getListingId());
        results = selectStmt.executeQuery();
        UsersDao usersDao = UsersDao.getInstance();
        getRecommendationList(recommendationList, results, usersDao, listingsDao);
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
    return recommendationList;
  }

  public List<Recommendations> getRecommendationsForPrice(int price) throws SQLException {

    List<Recommendations> recommendationList = new ArrayList<Recommendations>();
    String selectRecommendation =
        "SELECT RecommendationId,UserName,ListingId " +
            "FROM Recommendations " +
            "WHERE ListingId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      ListingsDao listingsDao = ListingsDao.getInstance();
      List<Listings> recommendedListings =listingsDao.getListingsForPrice(price);
      for(Listings listings : recommendedListings) {
        selectStmt.setInt(1, listings.getListingId());
        results = selectStmt.executeQuery();
        UsersDao usersDao = UsersDao.getInstance();
        getRecommendationList(recommendationList, results, usersDao, listingsDao);
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
    return recommendationList;
  }

  public List<Recommendations> getRecommendationsForBedsAndBaths(int bedrooms , int bathrooms) throws SQLException {

    List<Recommendations> recommendationList = new ArrayList<Recommendations>();
    String selectRecommendation =
        "SELECT RecommendationId,UserName,ListingId " +
            "FROM Recommendations " +
            "WHERE ListingId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectRecommendation);
      ListingsDao listingsDao = ListingsDao.getInstance();
      List<Listings> recommendedListings =listingsDao.getListingsBasedOnBedsAndBaths(bedrooms,bathrooms);
      for(Listings listings : recommendedListings) {
        selectStmt.setInt(1, listings.getListingId());
        results = selectStmt.executeQuery();
        UsersDao usersDao = UsersDao.getInstance();
        getRecommendationList(recommendationList, results, usersDao, listingsDao);
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
    return recommendationList;
  }
  private void getRecommendationList(List<Recommendations> recommendationList, ResultSet results,
      UsersDao usersDao, ListingsDao listingsDao) throws SQLException {
    while(results.next()) {
      int resultRecommendationId = results.getInt("RecommendationId");
      String userName = results.getString("UserName");
      int listingId = results.getInt("ListingId");

      Users resultUser = usersDao.getUserByUserName(userName);
      Listings listing = listingsDao.getListingById(listingId);
      Recommendations recommendations = new Recommendations(resultRecommendationId,resultUser.getUserName(), listing.getListingId());
      recommendationList.add(recommendations);
    }
  }
}




