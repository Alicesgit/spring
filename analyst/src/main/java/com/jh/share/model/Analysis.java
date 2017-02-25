package com.jh.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Analysis {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name="file_id")
	private String fileId;
	
	@Column(name="currentPrice")
	private int currentPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
    private java.util.Date insertDate;
	

	private int intValue;

	private String stringValue1;

	private String stringValue2;

	private String stringValue3;

	private String stringValue4;

	private String stringValue5;

	private String stringValue6;

	private String stringValue7;
	
	private String imagePath;
	private String imagePath1;
	private String imagePath2;
	private String imagePath3;
	private String imagePath4;
	

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
		this.intValue = 23;
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
	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice =currentPrice;
	}

	public java.util.Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(java.util.Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public String getImagePath3() {
		return imagePath3;
	}

	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}

	public String getImagePath4() {
		return imagePath4;
	}

	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}
	
	
}
