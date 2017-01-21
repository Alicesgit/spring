package com.jh.share.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

/*
 * This is only used
 *  for uploading the file and have no
 *  direct connection with object 
 *  Analysis
 *  
 *  Xiaojia He
 *  2016/11/3
 * 
 * */
@Entity
@Table(name = "file_table")
public class FileBucket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "file_id")
	private String file_id;

	@Column(name = "filepath")
	private String filePath;

	@Transient
	MultipartFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}