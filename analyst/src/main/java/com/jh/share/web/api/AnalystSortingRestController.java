package com.jh.share.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jh.share.model.Analysis;
import com.jh.share.model.AnalysisList;
import com.jh.share.service.AnalysisService;

@RestController
public class AnalystSortingRestController {
	
	 @Autowired
	 private AnalysisService analysisService;
	 
	 @RequestMapping(value="/sortingByPrice", produces = "application/json")
	  public ResponseEntity<Collection<Analysis>> sortByPrice() {
		 Collection<Analysis> analysiss = analysisService.findAllByOrderByStringValue3Asc();

	        return new ResponseEntity<Collection<Analysis>>(analysiss, HttpStatus.OK);
		 
	    }
	 
	 @RequestMapping("/sortingByDate")
	  public  Collection<Analysis> sortByDate() {
	        return null;
	    }
	 /**
	     * Returns all resources (podcasts) from the database
	     *
	     * @return
	     * @throws IOException
	     * @throws JsonMappingException
	     * @throws JsonGenerationException
	     * @throws AppException
	     */
	    
	  
}
