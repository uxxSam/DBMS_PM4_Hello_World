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

public class ComplaintsDao {
	protected ConnectionManager connectionManager;

	private static ComplaintsDao instance = null;
	protected ComplaintsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ComplaintsDao getInstance() {
		if(instance == null) {
			instance = new ComplaintsDao();
		}
		return instance;
	}
	
	public Complaints create(Complaints complaint) throws SQLException {
		String insertComplaint =
			"INSERT INTO Complaints(ListingId,UserName,Content,ComplaintType) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertComplaint,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, complaint.getListing().getListingId());
			insertStmt.setString(2, complaint.getUser().getUserName());
			insertStmt.setString(3, complaint.getContent());
			insertStmt.setString(4, complaint.getComplaintType());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int complaintId = -1;
			if(resultKey.next()) {
				complaintId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			complaint.setComplaintId(complaintId);
			return complaint;
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
	
	public Complaints updateContent(Complaints complaint, String newContent) throws SQLException {
		String updateComplaint = "UPDATE Complaints SET Content=? WHERE ComplaintId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateComplaint);
			updateStmt.setString(1, newContent);
			updateStmt.setInt(2, complaint.getComplaintId());
			updateStmt.executeUpdate();

			complaint.setContent(newContent);
			return complaint;
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
	
	public Complaints delete(Complaints complaint) throws SQLException {
		String deleteComplaint = "DELETE FROM Complaints WHERE ComplaintId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteComplaint);
			deleteStmt.setInt(1, complaint.getComplaintId());
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

	public Complaints getComplaintById(int complaintId) throws SQLException {
		String selectComplaint =
			"SELECT ComplaintId,ListingId,UserName,Content,ComplaintType " +
			"FROM Complaints " +
			"WHERE ComplaintId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComplaint);
			selectStmt.setInt(1, complaintId);
			results = selectStmt.executeQuery();
			ListingsDao listingsDao = ListingsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();

			if(results.next()) {
				int resultComplaintId = results.getInt("ComplaintId");
				int listingId = results.getInt("ListingId");
				String userName = results.getString("UserName");
				String content = results.getString("Content");
				String complaintType = results.getString("ComplaintType");
				
				Listings listing = listingsDao.getListingById(listingId);
				Users user = usersDao.getUserByUserName(userName);
				Complaints complaint = new Complaints(resultComplaintId, listing, user, content, complaintType);
				return complaint;
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
	
	public List<Complaints> getComplaintsForListing(Listings listing) throws SQLException {
		List<Complaints> complaints = new ArrayList<Complaints>();
		String selectComplaints =
			"SELECT ComplaintId,ListingId,UserName,Content,ComplaintType " +
			"FROM Complaints " +
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectComplaints);
			selectStmt.setInt(1, listing.getListingId());
			results = selectStmt.executeQuery();
			ListingsDao listingsDao = ListingsDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			
			while(results.next()) {
				int complaintId = results.getInt("ComplaintId");
				int listingId = results.getInt("ListingId");
				String userName = results.getString("UserName");
				String content = results.getString("Content");
				String complaintType = results.getString("ComplaintType");
				
				Listings resultListing = listingsDao.getListingById(listingId);
				Users user = usersDao.getUserByUserName(userName);
				Complaints complaint = new Complaints(complaintId, resultListing, user, content, complaintType);
				complaints.add(complaint);
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
		return complaints;
	}
}
