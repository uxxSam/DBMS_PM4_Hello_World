package CrimeFreeBooking.model;

public class Listings {
	protected int listingId;
	protected HostProfiles hostProfile;
	protected String url;
	protected String title;
	protected int price;
	protected String description;
	protected String imageUrl;
	protected String street1;
	protected String street2;
	protected String city;
	protected String state;
	protected String zipCode;
	protected double latitude;
	protected double longitude;
	protected String propertyType;
	protected String roomType;
	protected int accomodates;
	protected int bathrooms;
	protected int bedrooms;
	protected int beds;
	
	public Listings(int listingId, HostProfiles hostProfile, String url, String title, int price, String description,
			String imageUrl, String street1, String street2, String city, String state, String zipCode, double latitude,
			double longitude, String propertyType, String roomType, int accomodates, int bathrooms, int bedrooms,
			int beds) {
		this.listingId = listingId;
		this.hostProfile = hostProfile;
		this.url = url;
		this.title = title;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.propertyType = propertyType;
		this.roomType = roomType;
		this.accomodates = accomodates;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
		this.beds = beds;
	}
	
	public Listings(int listingId) {
		this.listingId = listingId;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public HostProfiles getHostProfile() {
		return hostProfile;
	}

	public void setHostProfile(HostProfiles hostProfile) {
		this.hostProfile = hostProfile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getAccomodates() {
		return accomodates;
	}

	public void setAccomodates(int accomodates) {
		this.accomodates = accomodates;
	}

	public int getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}
}