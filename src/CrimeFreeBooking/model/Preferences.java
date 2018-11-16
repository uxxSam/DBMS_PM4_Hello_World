package CrimeFreeBooking.model;

public class Preferences {
	protected int preferenceId;
	protected String user;
	protected int bathrooms;
	protected int bedrooms;
	
	public Preferences(int preferenceId, String user, int bathrooms, int bedrooms) {
		this.preferenceId = preferenceId;
		this.user = user;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
	}

	public Preferences(int preferenceId) {
		this.preferenceId = preferenceId;
	}

	public Preferences(String user, int bathrooms, int bedrooms) {
		this.user = user;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
	}

	public int getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(int preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "Preference: bathrooms = " + bathrooms + "\n" + "bedrooms = " + bedrooms + "\n";
	}
	
	
}