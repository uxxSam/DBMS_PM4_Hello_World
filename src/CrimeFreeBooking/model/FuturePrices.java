package CrimeFreeBooking.model;

import java.util.Date;

public class FuturePrices {
	protected int futurePriceId;
	protected Listings listing;
	protected Date priceDate;
	protected boolean available;
	protected int price;
	
	public FuturePrices(int futurePriceId, Listings listing, Date priceDate, boolean available, int price) {
		this.futurePriceId = futurePriceId;
		this.listing = listing;
		this.priceDate = priceDate;
		this.available = available;
		this.price = price;
	}

	public FuturePrices(int futurePriceId) {
		this.futurePriceId = futurePriceId;
	}

	public FuturePrices(Listings listing, Date priceDate, boolean available, int price) {
		this.listing = listing;
		this.priceDate = priceDate;
		this.available = available;
		this.price = price;
	}

	public int getFuturePriceId() {
		return futurePriceId;
	}

	public void setFuturePriceId(int futurePriceId) {
		this.futurePriceId = futurePriceId;
	}

	public Listings getListing() {
		return listing;
	}

	public void setListing(Listings listing) {
		this.listing = listing;
	}

	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}