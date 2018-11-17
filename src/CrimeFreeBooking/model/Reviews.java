package CrimeFreeBooking.model;

import java.sql.SQLException;
import java.util.Date;

import CrimeFreeBooking.dal.ListingsDao;

public class Reviews {
	protected int reviewId;
	protected int listing;
	protected Date reviewDate;
	protected String reviewerName;
	protected String content;
	
	public Reviews(int reviewId, int listing, Date reviewDate, String reviewerName, String content) {
		this.reviewId = reviewId;
		this.listing = listing;
		this.reviewDate = reviewDate;
		this.reviewerName = reviewerName;
		this.content = content;
	}

	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public Reviews(int listing, Date reviewDate, String reviewerName, String content) {
		this.listing = listing;
		this.reviewDate = reviewDate;
		this.reviewerName = reviewerName;
		this.content = content;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getListing() {
		return listing;
	}

	public void setListing(int listing) {
		this.listing = listing;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		try {
			Listings listing = ListingsDao.getInstance().getListingById(this.listing);
			return "Reviewed Listing Name: " + listing.getTitle() + "<br>" +
					"Reviewed Listing Description: " + listing.getDescription() + "<br>" +
					"Reviewed Date: "+ this.reviewDate + "<br>" +
					"Review Content: " + this.content + "<br>";
		} catch (SQLException e) {
			
		}
		
		return "";
	}

}


