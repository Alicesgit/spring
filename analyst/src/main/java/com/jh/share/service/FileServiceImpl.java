package com.jh.share.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.jh.share.model.FileBucket;
import com.jh.share.repository.FileBucketRepository;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	 private FileBucketRepository fileBucketRepository;
	
	@Override
	public String findFileBucketPathById(int id) {
		String filePath = fileBucketRepository.findOne((long) id).getFilePath();
		return filePath;
	}

	@Override
	public FileBucket create(FileBucket fileBucket) {
		FileBucket file = fileBucketRepository.saveAndFlush(fileBucket);
		return file;
	}

}
