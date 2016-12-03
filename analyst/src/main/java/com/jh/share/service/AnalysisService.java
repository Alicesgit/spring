package com.jh.share.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jh.share.model.Analysis;

public interface AnalysisService {
    Collection<Analysis> findAll();

    Page<Analysis> findAll(Pageable pageable);

    Analysis findOne(Long id);
    Analysis fineByFileId(String fileId);

    Analysis create(Analysis analysis);

    Analysis update(Analysis analysis);

    void delete(Long id);

    void evictCache();
}
