package CrimeFreeBooking.model;

import java.sql.SQLException;
import java.util.List;

import CrimeFreeBooking.dal.ListingsDao;
import CrimeFreeBooking.model.Listings;

public class Wishlists {
	protected int wishlistId;
	protected String user;
	protected int listing;
	
	public Wishlists(int wishlistId, String user, int listing) {
		this.wishlistId = wishlistId;
		this.user = user;
		this.listing = listing;
	}

	public Wishlists(int wishlistId) {
		this.wishlistId = wishlistId;
	}

	public Wishlists(String user, int listing) {
		this.user = user;
		this.listing = listing;
	}

	public int getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
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
			return listing.getTitle() + "<br><br>";
		} catch (SQLException e) {
			
		}

		return "";
	}
	
	@Override
	public String toString() {
		try {
			Listings listing = ListingsDao.getInstance().getListingById(this.listing);
			return "Wish Listing Description:      " + listing.getDescription();
		} catch (SQLException e) {
			
		}
		
		return "";
	}
}