package CrimeFreeBooking.model;

public class Recommendations {
	protected int recommendationId;
	protected Users user;
	protected Listings listing;
	
	public Recommendations(int recommendationId, Users user, Listings listing) {
		this.recommendationId = recommendationId;
		this.user = user;
		this.listing = listing;
	}

	public Recommendations(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Recommendations(Users user, Listings listing) {
		this.user = user;
		this.listing = listing;
	}

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Listings getListing() {
		return listing;
	}

	public void setListing(Listings listing) {
		this.listing = listing;
	}
}