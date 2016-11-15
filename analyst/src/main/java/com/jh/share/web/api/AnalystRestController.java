package com.jh.share.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jh.share.model.Analysis;
import com.jh.share.service.AnalysisService;

@RestController
public class AnalystRestController {

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(
            value = "/api/analyses",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Analysis>> getAnalyses() {

        Collection<Analysis> analysiss = analysisService.findAll();

        return new ResponseEntity<Collection<Analysis>>(analysiss,
                HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/analyses/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Analysis> getAnalysis(
            @PathVariable("id") Long id) {

        Analysis analysis = analysisService.findOne(id);
        if (analysis == null) {
            return new ResponseEntity<Analysis>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Analysis>(analysis, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/analyses",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Analysis> createAnalysis(
            @RequestBody Analysis analysis) {

        Analysis savedAnalysis = analysisService.create(analysis);

        return new ResponseEntity<Analysis>(savedAnalysis, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/analyses/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Analysis> updateAnalysis(
            @RequestBody Analysis analysis) {

        Analysis updatedAnalysis = analysisService.update(analysis);
        if (updatedAnalysis == null) {
            return new ResponseEntity<Analysis>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Analysis>(updatedAnalysis, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/analyses/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Analysis> deleteAnalysis(
            @PathVariable("id") Long id, @RequestBody Analysis analysis) {

        analysisService.delete(id);
        return new ResponseEntity<Analysis>(HttpStatus.NO_CONTENT);
    }
}
