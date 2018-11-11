package CrimeFreeBooking.dal;

import CrimeFreeBooking.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WishlistsDao {
	protected ConnectionManager connectionManager;

	private static WishlistsDao instance = null;
	protected WishlistsDao() {
		connectionManager = new ConnectionManager();
	}
	public static WishlistsDao getInstance() {
		if(instance == null) {
			instance = new WishlistsDao();
		}
		return instance;
	}
	
	public Wishlists create(Wishlists wishlist) throws SQLException {
		String insertWishlist =
			"INSERT INTO Wishlists(UserName,ListingId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWishlist,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, wishlist.getUser().getUserName());
			insertStmt.setInt(2, wishlist.getListing().getListingId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int wishlistId = -1;
			if(resultKey.next()) {
				wishlistId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			wishlist.setWishlistId(wishlistId);
			return wishlist;
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
	
	public Wishlists delete(Wishlists wishlist) throws SQLException {
		String deleteWishlist = "DELETE FROM Wishlists WHERE WishlistId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWishlist);
			deleteStmt.setInt(1, wishlist.getWishlistId());
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

	public Wishlists getWishlistById(int wishlistId) throws SQLException {
		String selectWishlist =
			"SELECT WishlistId,UserName,ListingId " +
			"FROM Wishlists " +
			"WHERE WishlistId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWishlist);
			selectStmt.setInt(1, wishlistId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ListingsDao listingsDao = ListingsDao.getInstance();
			
			if(results.next()) {
				int resultWishlistId = results.getInt("WishlistId");
				String userName = results.getString("UserName");
				int listingId = results.getInt("ListingId");
				
				Users user = usersDao.getUserByUserName(userName);
				Listings listing = listingsDao.getListingById(listingId);
				Wishlists wishlist = new Wishlists(resultWishlistId, user, listing);
				return wishlist;
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
	
	public List<Wishlists> getWishlistsForUser(User user) throws SQLException {
		List<Wishlists> wishlists = new ArrayList<Wishlists>();
		String selectWishlists =
			"SELECT WishlistId,UserName,ListingId " +
			"FROM Wishlists " + 
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWishlists);
			selectStmt.setString(1, user.getUsername());
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			ListingsDao listingsDao = ListingsDao.getInstance();
			
			while(results.next()) {
				int wishlistId = results.getInt("WishlistId");
				String userName = results.getString("UserName");
				int listingId = results.getInt("ListingId");
				
				Users resultUser = usersDao.getUserByUserName(userName);
				Listings listing = listingsDao.getListingById(listingId);
				Wishlists wishlist = new Wishlists(wishlistId, resultUser, listing);
				wishlists.add(wishlist);
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
		return wishlists;
	}
}
