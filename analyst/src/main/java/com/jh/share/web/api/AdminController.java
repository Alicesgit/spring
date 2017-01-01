package com.jh.share.web.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jh.share.model.Analysis;
import com.jh.share.service.AnalysisService;

@Controller
public class AdminController {
	
	// http://www.studytrails.com/frameworks/spring/spring-mvc-file-upload/
   private static String UPLOAD_LOCATION = "C:/opt/files/";
	
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
	    public String update(@ModelAttribute Analysis analysis,@RequestParam(value = "image", required = false) MultipartFile[] files,HttpServletRequest request) throws IOException {
		 String fid=(String)request.getSession().getAttribute("fileId");
		
	     System.out.println("analysi getCurrentPrice: "+analysis.getCurrentPrice());
	     Analysis newanalysis= analysisService.fineByFileId(fid);
	     System.out.println("analysi getid: "+newanalysis.getId());
	     newanalysis.setId(newanalysis.getId());
	     newanalysis.setCurrentPrice(analysis.getCurrentPrice());
	     newanalysis.setStringValue1(analysis.getStringValue1());
	     newanalysis.setStringValue2(analysis.getStringValue2());
	     newanalysis.setStringValue3(analysis.getStringValue3());
	     newanalysis.setStringValue4(analysis.getStringValue4());
	     newanalysis.setStringValue5(analysis.getStringValue5());
	     newanalysis.setStringValue6(analysis.getStringValue6());
	     newanalysis.setStringValue7(analysis.getStringValue7());
	     newanalysis.setIntValue(analysis.getIntValue());
	     newanalysis.setCurrentPrice(analysis.getCurrentPrice());
	    
	       // System.out.println("original file name is: "+filename);
			List<String> imageNames=new ArrayList<String>() ;
			String message = "";
			for (int i = 0; i < files.length; i++) {
				 MultipartFile file = files[i];
				if (!file.isEmpty()) {
						try {
							System.out.println("file is not empty");
							String filename = fid + file.getOriginalFilename();
							validateImage(file);
							saveImage(filename, file);
							imageNames.add(filename);
						} catch (RuntimeException re) {
							return "redirect:/person?new";
						}
						System.out.println("analysis is +" + analysis.getIntValue());
						
					}
			}
			
			int listSize=imageNames.size();
	     if(imageNames.get(0)!=null&&!imageNames.get(0).equals(null)){
	     newanalysis.setImagePath(imageNames.get(0));}
	     
	     if(listSize>1&&imageNames.get(1)!=null&&!imageNames.get(1).equals(null)){
	     newanalysis.setImagePath1(imageNames.get(1));}
	     if(listSize>2&&imageNames.get(2)!=null&&!imageNames.get(2).equals(null)){
	     newanalysis.setImagePath2(imageNames.get(2));}
	     if(listSize>3&&imageNames.get(3)!=null&&!imageNames.get(3).equals(null)){
	     newanalysis.setImagePath3(imageNames.get(3));}
	     if(listSize>4&&imageNames.get(4)!=null&&!imageNames.get(4).equals(null)){
	     newanalysis.setImagePath4(imageNames.get(4));}
	     analysisService.update(newanalysis);
	    		 
	     return "/index";
	    }
	 
	 private void saveImage(String filename, MultipartFile image) throws IOException {
			try {
				File file = new File(UPLOAD_LOCATION + filename);
				FileCopyUtils.copy(image.getBytes(), file);

				System.out.println("Go to the location:  " + file.toString()
						+ " on your computer and verify that the image has been stored.");
			} catch (IOException e) {
				throw e;
			}
		}

		private void validateImage(MultipartFile image) {
			if (!image.getContentType().equals("image/jpeg")) {
				throw new RuntimeException("Only JPG images are accepted");
			}

		}
	 
}
