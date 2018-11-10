package CrimeFreeBooking.model;

public class Preferences {
	protected int preferenceId;
	protected Users user;
	protected int bathrooms;
	protected int bedrooms;
	
	public Preferences(int preferenceId, Users user, int bathrooms, int bedrooms) {
		this.preferenceId = preferenceId;
		this.user = user;
		this.bathrooms = bathrooms;
		this.bedrooms = bedrooms;
	}

	public Preferences(int preferenceId) {
		this.preferenceId = preferenceId;
	}

	public Preferences(Users user, int bathrooms, int bedrooms) {
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
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
}