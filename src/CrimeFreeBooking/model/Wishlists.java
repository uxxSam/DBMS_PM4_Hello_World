package CrimeFreeBooking.model;

public class Wishlists {
	protected int wishlistId;
	protected Users user;
	protected Listings listing;
	
	public Wishlists(int wishlistId, Users user, Listings listing) {
		this.wishlistId = wishlistId;
		this.user = user;
		this.listing = listing;
	}

	public Wishlists(int wishlistId) {
		this.wishlistId = wishlistId;
	}

	public Wishlists(Users user, Listings listing) {
		this.user = user;
		this.listing = listing;
	}

	public int getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
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
	
	@Override
	public String toString() {
		return "wishlistId: " + this.wishlistId + "\n"
				+ this.listing.toString() + "\n";
	}
}