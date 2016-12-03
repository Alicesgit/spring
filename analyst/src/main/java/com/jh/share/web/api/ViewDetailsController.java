package com.jh.share.web.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jh.share.model.Analysis;
import com.jh.share.service.AnalysisService;

@Controller
public class ViewDetailsController {
	@Autowired
    private AnalysisService analysisService;
	
	 @RequestMapping(value = "analysis/details",
	            method = RequestMethod.GET)
	    public String addObject( @RequestParam(value = "fid")String fid, ModelMap map) {
	    	
	    	
	        // return模板文件的名称，对应src/main/resources/templates/login.html
	        
	        return "/analystDetails";
	    }
	
}
