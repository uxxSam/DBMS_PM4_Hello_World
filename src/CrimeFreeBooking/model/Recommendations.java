package CrimeFreeBooking.model;

import java.sql.SQLException;

import CrimeFreeBooking.dal.ListingsDao;

public class Recommendations {
	protected int recommendationId;
	protected String user;
	protected int listing;
	
	public Recommendations(int recommendationId, String user, int listing) {
		this.recommendationId = recommendationId;
		this.user = user;
		this.listing = listing;
	}

	public Recommendations(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Recommendations(String user, int listing) {
		this.user = user;
		this.listing = listing;
	}

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getListing() {
		return listing;
	}

	public void setListing(int listing) {
		this.listing = listing;
	}
	
	public String toStringTitle() {
		try {
			Listings listing = ListingsDao.getInstance().getListingById(this.listing);
			return listing.getTitle();
		} catch (SQLException e) {
			
		}
		
		return "";
	}

	@Override
	public String toString() {
		try {
			Listings listing = ListingsDao.getInstance().getListingById(this.listing);
			return "Recommended Listing Description: " + listing.getDescription() + "<br>";
		} catch (SQLException e) {
			
		}
		
		return "";
	}
	
	
}