package com.example.demo.all_modal;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Owner_Property_Data")
public class OwnerProperty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long propertyId;
	private String propertyName;
	private String city;
	private String location;
	private String address;
	private long pricerange;
	private String bhk;
	private String typeRoom;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	private String availability;
	private String facility;
	private String nearLocation;
	private String typeFamily;
	private String parking;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "property_image", joinColumns = { @JoinColumn(name = "property_id") }, inverseJoinColumns = {
			@JoinColumn(name = "image_id") })
	private List<ImageModel> propertyImage;

	private String ownerName;

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public OwnerProperty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OwnerProperty(String city, String location, String address, long pricerange, String bhk, String typeRoom,
			String availability, String facility, String nearLocation, String typeFamily, String parking,
			List<ImageModel> propertyImage, String ownerName, String propertyName) {
		super();
		this.city = city;
		this.location = location;
		this.address = address;
		this.pricerange = pricerange;
		this.bhk = bhk;
		this.typeRoom = typeRoom;
		this.availability = availability;
		this.facility = facility;
		this.nearLocation = nearLocation;
		this.typeFamily = typeFamily;
		this.parking = parking;
		this.propertyImage = propertyImage;
		this.ownerName = ownerName;
		this.propertyName = propertyName;
	}

	public long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPricerange() {
		return pricerange;
	}

	public void setPricerange(long pricerange) {
		this.pricerange = pricerange;
	}

	public String getBhk() {
		return bhk;
	}

	public void setBhk(String bhk) {
		this.bhk = bhk;
	}

	public String getTypeRoom() {
		return typeRoom;
	}

	public void setTypeRoom(String typeRoom) {
		this.typeRoom = typeRoom;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getNearLocation() {
		return nearLocation;
	}

	public void setNearLocation(String nearLocation) {
		this.nearLocation = nearLocation;
	}

	public String getTypeFamily() {
		return typeFamily;
	}

	public void setTypeFamily(String typeFamily) {
		this.typeFamily = typeFamily;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public List<ImageModel> getPropertyImage() {
		return propertyImage;
	}

	public void setPropertyImage(List<ImageModel> propertyImage) {
		this.propertyImage = propertyImage;
	}

}
