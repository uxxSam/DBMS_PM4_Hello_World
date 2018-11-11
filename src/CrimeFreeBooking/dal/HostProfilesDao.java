package CrimeFreeBooking.dal;

import CrimeFreeBooking.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HostProfilesDao {
	protected ConnectionManager connectionManager;

	private static HostProfilesDao instance = null;
	
	protected HostProfilesDao() {
		connectionManager = new ConnectionManager();
	}
	public static HostProfilesDao getInstance() {
		if(instance == null) {
			instance = new HostProfilesDao();
		}
		return instance;
	}

	public HostProfiles create(HostProfiles hostProfile) throws SQLException {
		String insertHostProfile =
			"INSERT INTO HostProfiles(HostId,HostName,HostPictureUrl,HostLocation,HostReponseTime,HostTotalListing) " +
			"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHostProfile);
			insertStmt.setInt(1, hostProfile.getHostId());
			insertStmt.setString(2, hostProfile.getHostName());
			insertStmt.setString(3, hostProfile.getHostPictureUrl());
			insertStmt.setString(4, hostProfile.getHostLocation());
			insertStmt.setString(5, hostProfile.getHostResponseTime());
			insertStmt.setInt(6, hostProfile.getHostTotalListing());
			insertStmt.executeUpdate();
			
			return hostProfile;
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

	public HostProfiles updateLocation(HostProfiles hostProfile, String newLocation) throws SQLException {
		String updateHostProfile = "UPDATE HostProfiles SET HostLocation=? WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHostProfile);
			updateStmt.setString(1, newLocation);
			updateStmt.setInt(2, hostProfile.getHostId());
			updateStmt.executeUpdate();

			hostProfile.setHostLocation(newLocation);
			return hostProfile;
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

	public BlogComments delete(HostProfiles hostProfile) throws SQLException {
		String deleteHostProfile = "DELETE FROM HostProfiles WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHostProfile);
			deleteStmt.setInt(1, hostProfile.getHostId());
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

	public HostProfiles getHostProfileById(int hostId) throws SQLException {
		String selectHostProfile =
			"SELECT HostId,HostName,HostPictureURL,HostLocation,HostResponseTime,HostTotalListing " +
			"FROM HostProfiles " +
			"WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHostProfile);
			selectStmt.setInt(1, hostId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultHostId = results.getInt("HostId");
				String hostName = results.getString("HostName");
				String hostPictureURL = results.getString("HostPictureURL");
				String hostLocation = results.getString("HostLocation");
				String hostResponseTime = results.getString("HostResponseTime");
				int hostTotalListing = results.getInt("HostTotalListing");
				
				HostProfiles hostProfile = new HostProfiles(resultHostId,hostName,hostPictureURL,
						hostLocation,hostResponseTime,hostTotalListing);
				return hostProfile;
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
}



