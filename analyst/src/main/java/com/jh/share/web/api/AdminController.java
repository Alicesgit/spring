package com.jh.share.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jh.share.model.Analysis;
import com.jh.share.service.AnalysisService;

@Controller
public class AdminController {
	@Autowired
    private AnalysisService analysisService;
	
	 @RequestMapping(value = "/analysis/delete",
	            method = RequestMethod.GET)
	    public String deleteObject( @RequestParam(value = "fid")String fid, Model model) {
	    	 System.out.println("fid: "+fid);
	    	 if(!fid.equals(null)&&fid!="")
	    	analysisService.removeByFileId(fid);
	        return "/analyst/overview/index";
	    }
}
