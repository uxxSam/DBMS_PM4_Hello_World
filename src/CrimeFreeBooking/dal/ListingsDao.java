package CrimeFreeBooking.dal;

import CrimeFreeBooking.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListingsDao {
	protected ConnectionManager connectionManager;

	private static ListingsDao instance = null;
	
	protected ListingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ListingsDao getInstance() {
		if(instance == null) {
			instance = new ListingsDao();
		}
		return instance;
	}

	public Listings create(Listings listing) throws SQLException {
		String insertListing =
			"INSERT INTO Listings(ListingId,HostId,URL,Title,Price,Description, " +
			"ImageURL,Street1,Street2,City,State,ZipCode,Latitude,Longitude,PropertyType, " +
			"RoomType,Accomodates,Bathrooms,Bedrooms,Beds) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertListing);
			insertStmt.setInt(1, listing.getListingId());
			insertStmt.setInt(2, listing.getHostProfile().getHostId());
			insertStmt.setString(3, listing.getUrl());
			insertStmt.setString(4, listing.getTitle());
			insertStmt.setInt(5, listing.getPrice());
			insertStmt.setString(6, listing.getDescription());
			insertStmt.setString(7, listing.getImageUrl());
			insertStmt.setString(8, listing.getStreet1());
			insertStmt.setString(9, listing.getStreet2());
			insertStmt.setString(10, listing.getCity());
			insertStmt.setString(11, listing.getState());
			insertStmt.setString(12, listing.getZipCode());
			insertStmt.setDouble(13, listing.getLatitude());
			insertStmt.setDouble(14, listing.getLongitude());
			insertStmt.setString(15, listing.getPropertyType());
			insertStmt.setString(16, listing.getRoomType());
			insertStmt.setInt(17, listing.getAccomodates());
			insertStmt.setInt(18, listing.getBathrooms());
			insertStmt.setInt(19, listing.getBedrooms());
			insertStmt.setInt(20, listing.getBeds());
			insertStmt.executeUpdate();
			
			return listing;
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
		}
	}

	public Listings updatePrice(Listings listing, int newPrice) throws SQLException {
		String updateListing = "UPDATE Listings SET Price=? WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateListing);
			updateStmt.setInt(1, newPrice);
			updateStmt.setInt(2, listing.getListingId());
			updateStmt.executeUpdate();

			listing.setPrice(newPrice);
			return listing;
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

	public Listings delete(Listings listing) throws SQLException {
		String deleteListing = "DELETE FROM Listings WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListing);
			deleteStmt.setInt(1, listing.getListingId());
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

	public Listings getListingById(int listingId) throws SQLException {
		String selectListing =
			"SELECT ListingId,HostId,URL,Title,Price,Description,ImageURL,Street1, " +
			"Street2,City,State,ZipCode,Latitude,Longitude,PropertyType,RoomType, " +
			"Accomodates,Bathrooms,Bedrooms,Beds " +
			"FROM Listings " +
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListing);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();

			HostProfilesDao hostProfilesDao = HostProfilesDao.getInstance();
			if(results.next()) {
				int resultListingId = results.getInt("ListingId");
				int hostId = results.getInt("HostId");
				String url = results.getString("URL");
				String title = results.getString("Title");
				int price = results.getInt("Price");
				String description = results.getString("Description");
				String imageURL = results.getString("ImageURL");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipCode = results.getString("ZipCode");
				double latitude = results.getDouble("Latitude");
				double longitude = results.getDouble("Longitude");
				String propertyType = results.getString("PropertyType");
				String roomType = results.getString("RoomType");
				int accomodates = results.getInt("Accomodates");
				int bathrooms = results.getInt("Bathrooms");
				int bedrooms = results.getInt("Bedrooms");
				int beds = results.getInt("Beds");
				
				HostProfiles hostProfile = hostProfilesDao.getHostProfileById(hostId);
				Listings listing = new Listings(resultListingId,hostProfile,url,title,
						price,description,imageURL,street1,street2,city,state,zipCode,
						latitude,longitude,propertyType,roomType,accomodates,bathrooms,
						bedrooms,beds);
				return listing;
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
	
	public List<Listings> getListingsForHostProfile(HostProfiles hostProfile) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
			"SELECT ListingId,HostId,URL,Title,Price,Description,ImageURL,Street1, " +
			"Street2,City,State,ZipCode,Latitude,Longitude,PropertyType,RoomType, " +
			"Accomodates,Bathrooms,Bedrooms,Beds " +
			"FROM Listings " +
			"WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setInt(1, hostProfile.getHostId());
			results = selectStmt.executeQuery();
			HostProfilesDao hostProfilesDao = HostProfilesDao.getInstance();
			getListing(listings, results, hostProfilesDao);
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
		return listings;
	}

	public List<Listings> getListingsBasedOnCoordinates(double latitude, double longitude) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
				"SELECT ListingId,HostId,URL,Title,Price,Description,ImageURL,Street1, " +
						"Street2,City,State,ZipCode,Latitude,Longitude,PropertyType,RoomType, " +
						"Accomodates,Bathrooms,Bedrooms,Beds " +
						"FROM Listings " +
						"WHERE Latitude=? AND Longitude=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setDouble(1, latitude);
			selectStmt.setDouble(2, longitude);
			results = selectStmt.executeQuery();
			HostProfilesDao hostProfilesDao = HostProfilesDao.getInstance();
			getListing(listings, results, hostProfilesDao);
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
		return listings;
	}

	public List<Listings> getListingsBasedOnCity(String city) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
				"SELECT ListingId,HostId,URL,Title,Price,Description,ImageURL,Street1, " +
						"Street2,City,State,ZipCode,Latitude,Longitude,PropertyType,RoomType, " +
						"Accomodates,Bathrooms,Bedrooms,Beds " +
						"FROM Listings " +
						"WHERE City=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setString(1, city);
			results = selectStmt.executeQuery();
			HostProfilesDao hostProfilesDao = HostProfilesDao.getInstance();
			getListing(listings, results, hostProfilesDao);
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
		return listings;
	}

	public List<Listings> getListingsBasedOnBedsAndBaths(int bedrooms, int bathrooms) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
				"SELECT ListingId,HostId,URL,Title,Price,Description,ImageURL,Street1, " +
						"Street2,City,State,ZipCode,Latitude,Longitude,PropertyType,RoomType, " +
						"Accomodates,Bathrooms,Bedrooms,Beds " +
						"FROM Listings " +
						"WHERE Bedrooms=? AND Bathrooms=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setInt(1, bedrooms);
			selectStmt.setInt(2, bathrooms);
			results = selectStmt.executeQuery();
			HostProfilesDao hostProfilesDao = HostProfilesDao.getInstance();
			getListing(listings, results, hostProfilesDao);
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
		return listings;
	}

	public List<Listings> getListingsForPrice(int price) throws SQLException {
		List<Listings> listings = new ArrayList<Listings>();
		String selectListings =
				"SELECT ListingId,HostId,URL,Title,Price,Description,ImageURL,Street1, " +
						"Street2,City,State,ZipCode,Latitude,Longitude,PropertyType,RoomType, " +
						"Accomodates,Bathrooms,Bedrooms,Beds " +
						"FROM Listings " +
						"WHERE Price <=? ORDER BY Price DESC LIMIT 50;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectListings);
			selectStmt.setInt(1, price);
			results = selectStmt.executeQuery();
			HostProfilesDao hostProfilesDao = HostProfilesDao.getInstance();
			getListing(listings, results, hostProfilesDao);
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
		return listings;
	}

	private void getListing(List<Listings> listings, ResultSet results,
			HostProfilesDao hostProfilesDao) throws SQLException {
		while(results.next()) {
			int listingId = results.getInt("ListingId");
			int hostId = results.getInt("HostId");
			String url = results.getString("URL");
			String title = results.getString("Title");
			int price = results.getInt("Price");
			String description = results.getString("Description");
			String imageURL = results.getString("ImageURL");
			String street1 = results.getString("Street1");
			String street2 = results.getString("Street2");
			String resultCity = results.getString("City");
			String state = results.getString("State");
			String zipCode = results.getString("ZipCode");
			double resultLatitude = results.getDouble("Latitude");
			double resultLongitude = results.getDouble("Longitude");
			String propertyType = results.getString("PropertyType");
			String roomType = results.getString("RoomType");
			int accomodates = results.getInt("Accomodates");
			int bathrooms = results.getInt("Bathrooms");
			int bedrooms = results.getInt("Bedrooms");
			int beds = results.getInt("Beds");

			HostProfiles resultHostProfile = hostProfilesDao.getHostProfileById(hostId);
			Listings listing = new Listings(listingId,resultHostProfile,url,title,
					price,description,imageURL,street1,street2,resultCity,state,zipCode,
					resultLatitude,resultLongitude,propertyType,roomType,accomodates,bathrooms,
					bedrooms,beds);
			listings.add(listing);
		}
	}

}
