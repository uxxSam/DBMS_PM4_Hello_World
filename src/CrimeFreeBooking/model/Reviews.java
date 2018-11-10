package CrimeFreeBooking.model;

import java.util.Date;

public class Reviews {
	protected int reviewId;
	protected Listings listing;
	protected Date reviewDate;
	protected String reviewerName;
	protected String content;
	
	public Reviews(int reviewId, Listings listing, Date reviewDate, String reviewerName, String content) {
		this.reviewId = reviewId;
		this.listing = listing;
		this.reviewDate = reviewDate;
		this.reviewerName = reviewerName;
		this.content = content;
	}

	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public Reviews(Listings listing, Date reviewDate, String reviewerName, String content) {
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

	public Listings getListing() {
		return listing;
	}

	public void setListing(Listings listing) {
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
}


