package com.jh.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jh.share.model.Analysis;
import com.jh.share.model.FileBucket;

public interface FileBucketRepository extends JpaRepository<FileBucket, Long>{

}
