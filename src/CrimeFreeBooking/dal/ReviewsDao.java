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

public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(ListingId,Date,ReviewerName,Content) " +
			"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, review.getListing().getListingId());
			insertStmt.setDate(2, (java.sql.Date) review.getReviewDate());
			insertStmt.setString(3, review.getReviewerName());
			insertStmt.setString(4, review.getContent());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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
	
	public Reviews updateContent(Reviews review, String newContent) throws SQLException {
		String updateReview = "UPDATE Reviews SET Content=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, newContent);
			updateStmt.setInt(2, review.getReviewId());
			updateStmt.executeUpdate();

			review.setContent(newContent);
			return review;
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
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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

	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectReview =
			"SELECT ReviewId,ListingId,Date,ReviewerName,Content " +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			ListingsDao listingsDao = ListingsDao.getInstance();
			
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				int listingId = results.getInt("ListingId");
				Date date = results.getDate("Date");
				String reviewerName = results.getString("ReviewerName");
				String content = results.getString("Content");
				
				Listings listing = listingsDao.getListingById(listingId);
				Reviews review = new Reviews(resultReviewId, listing, date, reviewerName, content);
				return review;
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
	
	public List<Reviews> getReviewsForListing(Listings listing) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
			"SELECT ReviewId,ListingId,Date,ReviewerName,Content " +
			"FROM Reviews " + 
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, listing.getListingId());
			results = selectStmt.executeQuery();
			ListingsDao listingsDao = ListingsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				int resultListingId = results.getInt("ListingId");
				Date date = results.getDate("Date");
				String reviewerName = results.getString("ReviewerName");
				String content = results.getString("Content");
				
				Listings resultListing = listingsDao.getListingById(resultListingId);
				Reviews review = new Reviews(reviewId, resultListing, date, reviewerName, content);
				reviews.add(review);
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
		return reviews;
	}
}
