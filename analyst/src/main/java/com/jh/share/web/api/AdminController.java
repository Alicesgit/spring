package com.jh.share.web.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jh.share.model.Analysis;
import com.jh.share.service.AnalysisService;

@Controller
public class AdminController {
	@Autowired
    private AnalysisService analysisService;
	
	 @RequestMapping(value = "/analysis/delete",method = RequestMethod.GET)
	    public String deleteObject( @RequestParam(value = "fid")String fid, Model model) {
	    	 System.out.println("fid: "+fid);
	    	 if(!fid.equals(null)&&fid!="")
	    	analysisService.removeByFileId(fid);
	        return "/analyst/overview/index";
	    }
	 @RequestMapping(value = "/analysis/update",method = RequestMethod.GET)
	    public String updateObject( @RequestParam(value = "fid")String fid, ModelMap map, HttpServletRequest request) {
	    	
	    	     map.addAttribute("analysis", analysisService.fineByFileId(fid));
	    	     request.getSession().setAttribute("fileId", fid);
	    	     
	    		 
	    	 return "/updateObject3";
	    }
	 
	 @RequestMapping(value = "/analyst/update",method = RequestMethod.POST)
	    public String update(@ModelAttribute Analysis analysis,HttpServletRequest request) {
		 String fid=(String)request.getSession().getAttribute("fileId");
		
	     System.out.println("analysi getCurrentPrice: "+analysis.getCurrentPrice());
	     Analysis newanalysis= analysisService.fineByFileId(fid);
	     System.out.println("analysi getid: "+newanalysis.getId());
	     newanalysis.setId(newanalysis.getId());
	     newanalysis.setCurrentPrice(analysis.getCurrentPrice());
	     newanalysis.setStringValue1(analysis.getStringValue1());
	     
	     analysisService.update(newanalysis);
	    		 
	    	 return "/updateObject3";
	    }
	 
}
