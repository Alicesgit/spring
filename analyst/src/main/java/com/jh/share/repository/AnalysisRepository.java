package com.jh.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jh.share.model.Analysis;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

	Analysis findByFileId(String fileid);

}
