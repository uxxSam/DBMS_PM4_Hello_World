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

public class FuturePricesDao {
	protected ConnectionManager connectionManager;

	private static FuturePricesDao instance = null;
	protected FuturePricesDao() {
		connectionManager = new ConnectionManager();
	}
	public static FuturePricesDao getInstance() {
		if(instance == null) {
			instance = new FuturePricesDao();
		}
		return instance;
	}
	
	public FuturePrices create(FuturePrices futurePrice) throws SQLException {
		String insertFuturePrice =
			"INSERT INTO FuturePrices(ListingId,Date,Available,Price) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFuturePrice,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, futurePrice.getListing().getListingId());
			insertStmt.setDate(2, (java.sql.Date) futurePrice.getPriceDate());
			insertStmt.setBoolean(3, futurePrice.isAvailable());
			insertStmt.setInt(4, futurePrice.getPrice());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int futurePriceId = -1;
			if(resultKey.next()) {
				futurePriceId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			futurePrice.setFuturePriceId(futurePriceId);
			return futurePrice;
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
	
	public FuturePrices updateAvailability(FuturePrices futurePrice, boolean newAvailability) throws SQLException {
		String updateFuturePrice = "UPDATE FuturePrices SET Available=? WHERE FuturePriceId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateFuturePrice);
			updateStmt.setBoolean(1, newAvailability);
			updateStmt.setInt(2, futurePrice.getFuturePriceId());
			updateStmt.executeUpdate();

			futurePrice.setAvailable(newAvailability);
			return futurePrice;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public FuturePrices delete(FuturePrices futurePrice) throws SQLException {
		String deleteFuturePrice = "DELETE FROM FuturePrices WHERE FuturePriceId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFuturePrice);
			deleteStmt.setInt(1, futurePrice.getFuturePriceId());
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

	public FuturePrices getFuturePriceById(int futurePriceId) throws SQLException {
		String selectFuturePrice =
			"SELECT FuturePrices(FuturePriceId,ListingId,Date,Available,Price) " +
			"FROM FuturePrices " +
			"WHERE FuturePriceId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFuturePrice);
			selectStmt.setInt(1, futurePriceId);
			results = selectStmt.executeQuery();
			ListingsDao listingsDao = ListingsDao.getInstance();
			
			if(results.next()) {
				int resultFuturePriceId = results.getInt("FuturePriceId");
				int listingId = results.getInt("ListingId");
				Date date = results.getDate("Date");
				boolean available = results.getBoolean("Available");
				int price = results.getInt("Price");
				
				Listings listing = listingsDao.getListingById(listingId);
				FuturePrices futurePrice = new FuturePrices(resultFuturePriceId, listing, date, available, price);
				return futurePrice;
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
	
	public List<FuturePrices> getFuturePricesForListing(Listings listing) throws SQLException {
		List<FuturePrices> futurePrices = new ArrayList<FuturePrices>();
		String selectFuturePrices =
			"SELECT FuturePrices(FuturePriceId,ListingId,Date,Available,Price) " +
			"FROM FuturePrices " + 
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFuturePrices);
			selectStmt.setInt(1, listing.getListingId());
			results = selectStmt.executeQuery();
			ListingsDao listingsDao = ListingsDao.getInstance();
			while(results.next()) {
				int futurePriceId = results.getInt("FuturePriceId");
				int listingId = results.getInt("ListingId");
				Date date = results.getDate("Date");
				boolean available = results.getBoolean("Available");
				int price = results.getInt("Price");
				
				Listings resultListing = listingsDao.getListingById(listingId);
				FuturePrices futurePrice = new FuturePrices(futurePriceId, resultListing, date, available, price);
				futurePrices.add(futurePrice);
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
		return futurePrices;
	}
}
