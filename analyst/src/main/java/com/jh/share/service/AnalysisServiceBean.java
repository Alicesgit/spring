package com.jh.share.service;


import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jh.share.model.Analysis;
import com.jh.share.repository.AnalysisRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AnalysisServiceBean implements AnalysisService {
	private static final int PAGE_SIZE = 50;
	
    @Autowired
    private AnalysisRepository analysisRepository;

    @Override
	public Collection<Analysis> findAll() {
        Collection<Analysis> analysiss = analysisRepository.findAll();
		return analysiss;
	}

	@Override
    @Cacheable(
            value = "analyses",
            key = "#id")
	public Analysis findOne(Long id) {
        Analysis analysis = analysisRepository.findOne(id);
		return analysis;
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Analysis create(Analysis analysis) {
        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (analysis.getId() != null) {
            // Cannot create Analysis with specified ID value
            return null;
        }
        //gererate uuid as file id, which is initialized as null but here is set a new value
       
        Analysis savedAnalysis = analysisRepository.save(analysis);
		return savedAnalysis;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    @CachePut(
            value = "analyses",
            key = "#analysis.id")
	public Analysis update(Analysis analysis) {
		Analysis analysisToUpdate = findOne(analysis.getId());
        if (analysisToUpdate == null) {
            // Cannot update Greeting that hasn't been persisted
            return null;
        }
        analysisToUpdate.setCurrentPrice(analysis.getCurrentPrice());
        analysisToUpdate.setImagePath(analysis.getImagePath());
        analysisToUpdate.setStringValue4(analysis.getStringValue4());
        Analysis updatedAnalysis = analysisRepository.save(analysisToUpdate);
		return updatedAnalysis;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    @CacheEvict(
            value = "analyses",
            key = "#id")
	public void delete(Long id) {
		analysisRepository.delete(id);
	}

	@Override
    @CacheEvict(
            value = "analyses",
            allEntries = true)
	public void evictCache() {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Analysis> findAll(Pageable pageable) {
		Page<Analysis> analysiss = analysisRepository.findAll(pageable);
		return analysiss;
	}

	@Override
	public Analysis fineByFileId(String fileid) {
		Analysis analysis=analysisRepository.findByFileId(fileid);
		return analysis;
	}

	@Override
	public Collection<Analysis> findAllByOrderByCurrentPriceAsc() {
		Collection<Analysis> analysiss = analysisRepository.findAllByOrderByCurrentPriceAsc();
		return analysiss;
	
	}
}
