package CrimeFreeBooking.model;

public class Complaints {
	protected int complaintId;
	protected Listings listing;
	protected Users user;
	protected String content;
	protected String complaintType;
	
	public Complaints(int complaintId, Listings listing, Users user, String content, String complaintType) {
		this.complaintId = complaintId;
		this.listing = listing;
		this.user = user;
		this.content = content;
		this.complaintType = complaintType;
	}

	public Complaints(int complaintId) {
		this.complaintId = complaintId;
	}

	public Complaints(Listings listing, Users user, String content, String complaintType) {
		this.listing = listing;
		this.user = user;
		this.content = content;
		this.complaintType = complaintType;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public Listings getListing() {
		return listing;
	}

	public void setListing(Listings listing) {
		this.listing = listing;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
}