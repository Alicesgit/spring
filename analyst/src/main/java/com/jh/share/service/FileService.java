package com.jh.share.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.jh.share.model.Analysis;
import com.jh.share.model.FileBucket;
import com.jh.share.repository.FileBucketRepository;
import com.jh.share.repository.UserRepository;


public interface FileService {
	  String findFileBucketPathById(int id);
	  
	  FileBucket create(FileBucket fileBucket);
}
