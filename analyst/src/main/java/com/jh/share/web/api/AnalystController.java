package com.jh.share.web.api;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jh.share.model.Analysis;
import com.jh.share.service.AnalysisService;

@Controller
public class AnalystController {
	
	 // http://www.studytrails.com/frameworks/spring/spring-mvc-file-upload/
	private static String UPLOAD_LOCATION = "C:/opt/files/";
	
	
	@Autowired
    private AnalysisService analysisService;
    
    static String fileID= UUID.randomUUID().toString();

    @RequestMapping(value = {"/analyst/overview/index"},
            method = RequestMethod.GET)
    public String index(ModelMap map) {
        Collection<Analysis> analysiss = analysisService.findAll();
         // 加入一个属性，用来在模板中读取
        map.addAttribute("analysiss", analysiss);
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";  
    }

    @RequestMapping(value = "/pages", method=RequestMethod.GET)
    public String getAnalysisPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size, ModelMap map) {
        Sort sort = new Sort(Direction.DESC, "Id");
        Pageable pageable = new PageRequest(page, size, sort);
    	Page<Analysis> resultPage = analysisService.findAll(pageable);

    	// 加入一个属性，用来在模板中读取
        int current = resultPage.getNumber() + 1;
        int begin = Math.max(1, current - 2);
        int end = Math.min(begin + 4, resultPage.getTotalPages());

        int[] range = getRange(begin, end);
		map.addAttribute("range", range);
		map.addAttribute("currentIndex", current);
		map.addAttribute("analysiss", resultPage);
		// return模板文件的名称，对应src/main/resources/templates/index.html
        return "indexByPage";  
    }

    private int[] getRange(int begin, int end) {
    	int[] range = new int[end-begin+1];
    	int j=0;
    	for (int i= begin; i<= end; i++) {
    		range[j++] = i;
    	}
    	return range;
    }
    
    @RequestMapping(value = "/analyst/addObject",
            method = RequestMethod.GET)
    public String addObject(Model model,HttpServletRequest request) {
    	Analysis analysis = new Analysis();
    	
    	
    	
    	analysis.setFileId(fileID);
    	Analysis savedAnalysis = analysisService.create(analysis);
    	
    	model.addAttribute("analysis", analysis);
    	
        // return模板文件的名称，对应src/main/resources/templates/login.html
        
        return "/addObject";
    }

    @RequestMapping(value = "/analyst/addObject", method = RequestMethod.POST)
    public String  addObjectPost(@ModelAttribute Analysis analysis, MultipartFile image,HttpServletRequest request) throws IOException {
    	// UUID fileid=UUID.randomUUID();
    	
    	 String filename=fileID+image.getOriginalFilename();
    	 System.out.println("analysis.getFileId: "+analysis.getFileId());
    	
    	 System.out.println("original file name is: "+filename);
    	if (!image.isEmpty()) {
    		try {
    		    validateImage(image);
    		} catch (RuntimeException re) {
    		    return "redirect:/person?new";
    		}
    		 System.out.println("analysis is +"+analysis.getIntValue());
    		 saveImage(fileID + ".jpg", image);
    		}
    	//if(fileID!=null&&!fileID.equals("")){
    	//analysis.setFileId(fileid.toString());
    	analysis.setImagePath(fileID+".jpg");
    	System.out.println(fileID+".jpg");
    
    	
    	Analysis savedAnalysis = analysisService.update(analysis);
        // return模板文件的名称，对应src/main/resources/templates/index.html
    	//return index(map);
    	return "analyst/addObject";
    }

	private void saveImage(String filename, MultipartFile image) throws IOException {
		try {
			File file = new File(UPLOAD_LOCATION 
			+ filename);
			FileCopyUtils.copy( image.getBytes(),file);
			
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
