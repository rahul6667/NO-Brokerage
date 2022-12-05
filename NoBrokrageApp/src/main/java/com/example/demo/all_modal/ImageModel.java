package com.example.demo.all_modal;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

@Entity
@Table
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String imageName;
	private String type;
	@Column(length = 50000000)
	private byte[] picByte;

	@Override
	public String toString() {
		return "ImageModel [id=" + id + ", imageName=" + imageName + ", type=" + type + ", picByte="
				+ Arrays.toString(picByte) + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public ImageModel(String imageName, String type, byte[] picByte) {
		// super();
		this.imageName = imageName;
		this.type = type;
		this.picByte = picByte;
	}

	public ImageModel() {
		// super();
		// TODO Auto-generated constructor stub
	}

}
