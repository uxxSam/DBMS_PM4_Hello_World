package CrimeFreeBooking.model;

public class HostProfiles {
	protected int hostId;
	protected String hostName;
	protected String hostPictureUrl;
	protected String hostLocation;
	protected String hostResponseTime;
	protected int hostTotalListing;
	
	public HostProfiles(int hostId, String hostName, String hostPictureUrl, String hostLocation,
			String hostResponseTime, int hostTotalListing) {
		this.hostId = hostId;
		this.hostName = hostName;
		this.hostPictureUrl = hostPictureUrl;
		this.hostLocation = hostLocation;
		this.hostResponseTime = hostResponseTime;
		this.hostTotalListing = hostTotalListing;
	}

	public HostProfiles(int hostId) {
		this.hostId = hostId;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostPictureUrl() {
		return hostPictureUrl;
	}

	public void setHostPictureUrl(String hostPictureUrl) {
		this.hostPictureUrl = hostPictureUrl;
	}

	public String getHostLocation() {
		return hostLocation;
	}

	public void setHostLocation(String hostLocation) {
		this.hostLocation = hostLocation;
	}

	public String getHostResponseTime() {
		return hostResponseTime;
	}

	public void setHostResponseTime(String hostResponseTime) {
		this.hostResponseTime = hostResponseTime;
	}

	public int getHostTotalListing() {
		return hostTotalListing;
	}

	public void setHostTotalListing(int hostTotalListing) {
		this.hostTotalListing = hostTotalListing;
	}
}



