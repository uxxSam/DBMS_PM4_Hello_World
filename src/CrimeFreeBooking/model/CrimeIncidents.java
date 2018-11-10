package CrimeFreeBooking.model;

import java.util.Date;

public class CrimeIncidents {
	protected int crimeId;
	protected double latitude;
	protected double longitude;
	protected Date crimeDate;
	protected String crimeType;
	
	public CrimeIncidents(int crimeId, double latitude, double longitude, Date crimeDate, String crimeType) {
		this.crimeId = crimeId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.crimeDate = crimeDate;
		this.crimeType = crimeType;
	}

	public CrimeIncidents(int crimeId) {
		this.crimeId = crimeId;
	}

	public CrimeIncidents(double latitude, double longitude, Date crimeDate, String crimeType) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.crimeDate = crimeDate;
		this.crimeType = crimeType;
	}

	public int getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(int crimeId) {
		this.crimeId = crimeId;
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

	public Date getCrimeDate() {
		return crimeDate;
	}

	public void setCrimeDate(Date crimeDate) {
		this.crimeDate = crimeDate;
	}

	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}
}