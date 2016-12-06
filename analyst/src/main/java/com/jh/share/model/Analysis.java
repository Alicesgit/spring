package com.jh.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Analysis {
	@Id
	@GeneratedValue
	@Column(name = "analysis_id")
	private Long id;
	
	@Column(name="file_id")
	private String fileId;
	
	@Column(name="currentPrice")
	private int currentPrice;
	
	private int intValue;

	private String stringValue1;

	private String stringValue2;

	private String stringValue3;

	private String stringValue4;

	private String stringValue5;

	private String stringValue6;

	private String stringValue7;
	
	private String imagePath;
	

	public Analysis() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getStringValue1() {
		return stringValue1;
	}

	public void setStringValue1(String stringValue1) {
		this.stringValue1 = stringValue1;
	}

	public String getStringValue2() {
		return stringValue2;
	}

	public void setStringValue2(String stringValue2) {
		this.stringValue2 = stringValue2;
	}

	public String getStringValue3() {
		return stringValue3;
	}

	public void setStringValue3(String stringValue3) {
		this.stringValue3 = stringValue3;
	}

	public String getStringValue4() {
		return stringValue4;
	}

	public void setStringValue4(String stringValue4) {
		this.stringValue4 = stringValue4;
	}

	public String getStringValue5() {
		return stringValue5;
	}

	public void setStringValue5(String stringValue5) {
		this.stringValue5 = stringValue5;
	}

	public String getStringValue6() {
		return stringValue6;
	}

	public void setStringValue6(String stringValue6) {
		this.stringValue6 = stringValue6;
	}

	public String getStringValue7() {
		return stringValue7;
	}

	public void setStringValue7(String stringValue7) {
		this.stringValue7 = stringValue7;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice =currentPrice;
	}
	
	
}
