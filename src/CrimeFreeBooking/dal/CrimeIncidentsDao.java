package CrimeFreeBooking.dal;

import CrimeFreeBooking.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrimeIncidentsDao {
	protected ConnectionManager connectionManager;

	private static CrimeIncidentsDao instance = null;
	protected CrimeIncidentsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CrimeIncidentsDao getInstance() {
		if(instance == null) {
			instance = new CrimeIncidentsDao();
		}
		return instance;
	}
	
	public CrimeIncidents create(CrimeIncidents crimeIncident) throws SQLException {
		String insertCrimeIncident =
			"INSERT INTO CrimeIncidents(Latitude,Longitude,CrimeDate,CrimeType) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCrimeIncident,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setDouble(1, crimeIncident.getLatitude());
			insertStmt.setDouble(2, crimeIncident.getLongitude());
			insertStmt.setTimestamp(3, new Timestamp(crimeIncident.getCrimeDate().getTime()));
			insertStmt.setString(4, crimeIncident.getCrimeType());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int crimeId = -1;
			if(resultKey.next()) {
				crimeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			crimeIncident.setCrimeId(crimeId);
			return crimeIncident;
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
	
	public CrimeIncidents updateCrimeDate(CrimeIncidents crimeIncident, Date newCrimeDate) throws SQLException {
		String updateCrimeIncident = "UPDATE CrimeIncidents SET CrimeDate=? WHERE CrimeId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCrimeIncident);
			updateStmt.setTimestamp(1, new Timestamp(newCrimeDate.getTime()));
			updateStmt.setInt(2, crimeIncident.getCrimeId());
			updateStmt.executeUpdate();

			crimeIncident.setCrimeDate(new Timestamp(newCrimeDate.getTime()));
			return crimeIncident;
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
	
	public CrimeIncidents delete(CrimeIncidents crimeIncident) throws SQLException {
		String deleteCrimeIncident = "DELETE FROM CrimeIncidents WHERE CrimeIncidentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCrimeIncident);
			deleteStmt.setInt(1, crimeIncident.getCrimeId());
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

	public CrimeIncidents getCrimeIncidentById(int crimeId) throws SQLException {
		String selectCrimeIncident =
			"SELECT CrimeId,Latitude,Longitude,CrimeDate,CrimeType " +
			"FROM CrimeIncidents " +
			"WHERE CrimeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCrimeIncident);
			selectStmt.setInt(1, crimeId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultCrimeId = results.getInt("CrimeId");
				double latitude = results.getDouble("Latitude");
				double longitude = results.getDouble("Longitude");
				Date crimeDate = new Date(results.getTimestamp("CrimeDate").getTime());
				String crimeType = results.getString("CrimeType");
		
				CrimeIncidents crimeIncident = new CrimeIncidents(resultCrimeId, latitude, longitude, crimeDate, crimeType);
				return crimeIncident;
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
	
	public List<CrimeIncidents> getCrimeIncidentsForCrimeType(String crimeType) throws SQLException {
		List<CrimeIncidents> crimeIncidents = new ArrayList<CrimeIncidents>();
		String selectCrimeIncidents =
			"SELECT CrimeId,Latitude,Longitude,CrimeDate,CrimeType " +
			"FROM CrimeIncidents " +
			"WHERE CrimeType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCrimeIncidents);
			selectStmt.setString(1, crimeType);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int crimeId = results.getInt("CrimeId");
				double latitude = results.getDouble("Latitude");
				double longitude = results.getDouble("Longitude");
				Date crimeDate = new Date(results.getTimestamp("CrimeDate").getTime());
				String resultCrimeType = results.getString("CrimeType");
		
				CrimeIncidents crimeIncident = new CrimeIncidents(crimeId, latitude, longitude, crimeDate, resultCrimeType);
				crimeIncidents.add(crimeIncident);
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
		return crimeIncidents;
	}
}
