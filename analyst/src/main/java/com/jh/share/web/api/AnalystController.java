package com.jh.share.web.api;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
	@RequestMapping(value = { "/analyst" }, method = RequestMethod.GET)
	public String firstPage(ModelMap map) {
		
		
		return "welcome";
	}
	
	@RequestMapping(value = { "/analyst/overview/index" }, method = RequestMethod.GET)
	public String index(ModelMap map) {
		Collection<Analysis> analysiss = analysisService.findAll();
		// 加入一个属性，用来在模板中读取
		map.addAttribute("analysiss", analysiss);
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index2";
	}

	@RequestMapping(value = "/sortingByPrice")
	public String sortByPrice(ModelMap map) {
		Collection<Analysis> analysiss = analysisService.findAllByOrderByCurrentPriceAsc();

		map.addAttribute("analysiss", analysiss);
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";

	}
	
	@RequestMapping(value = "/sortingByDate")
	public String sortByDate(ModelMap map) {
		Collection<Analysis> analysiss = analysisService.findAllByOrderByInsertDateDesc();

		map.addAttribute("analysiss", analysiss);
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";

	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
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
		//return "indexByPage";
		return "index2";
	}

	private int[] getRange(int begin, int end) {
		int[] range = new int[end - begin + 1];
		int j = 0;
		for (int i = begin; i <= end; i++) {
			range[j++] = i;
		}
		return range;
	}

	@RequestMapping(value = "/addAdvertisement", method = RequestMethod.GET)
	public String addObject(@ModelAttribute Analysis analysis, HttpServletRequest request) {
		
		
		//analysis.setIntValue(0);
		//analysis.setCurrentPrice(0);
		
		//Analysis savedAnalysis = analysisService.create(analysis);
	//	Analysis savedAnalysis = null;
		
		
		//request.getSession().setAttribute("analysisid", analysis.getId());
       // System.out.println("in get id:"+analysis.getId());
		// return模板文件的名称，对应src/main/resources/templates/login.html
       
		return "/addObject";
	}

	@RequestMapping(value = "/analyst/addObject", method = RequestMethod.POST)
	public String addObjectPost(@ModelAttribute Analysis analysis,
			@RequestParam(value = "image", required = false) MultipartFile[] files, HttpServletRequest request)
			throws IOException, ParseException {
		
		System.out.println("request content type: "+request.getContentType());
		System.out.println("request content type: "+request.getContentLength());
		System.out.println(analysis.getIntValue());
		System.out.println("request content type: "+request.getContentType());
		// UUID fileid=UUID.randomUUID();
		// System.out.println("image path"+image.getOriginalFilename());
		// String filename=fileID+image.getOriginalFilename();
		String fileID = UUID.randomUUID().toString();
		System.out.println("get method: " + fileID);
		//System.out.println("get id from post method: "+request.getSession().getAttribute("analysisid"));
		analysis.setFileId(fileID);
		//analysis.setId((Long)(request.getSession().getAttribute("analysisid")));
		
		System.out.println("post object: "+analysis.getId());
		DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		Calendar calendar=Calendar.getInstance();
		String formattedDate = df2.format(calendar.getTime());
		Date result=df2.parse(formattedDate);
		
				
		
		

		System.out.println("analysis.getId: " + analysis.getId());
		System.out.println("analysis.getCurrentPrice(): " + analysis.getCurrentPrice());
		System.out.println("analysis.analysis.getStringValue3(): " + analysis.getStringValue3());
		analysis.setInsertDate(result);
		// System.out.println("original file name is: "+filename);
		List<String> imageNames=new ArrayList<String>() ;
		String message = "";
		for (int i = 0; i < files.length; i++) {
			 MultipartFile file = files[i];
			if (!file.isEmpty()) {
					try {
						System.out.println("file is not empty");
						String filename = analysis.getFileId() + file.getOriginalFilename();
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
	     analysis.setImagePath(imageNames.get(0));}
	     
	     if(listSize>1&&imageNames.get(1)!=null&&!imageNames.get(1).equals(null)){
	     analysis.setImagePath1(imageNames.get(1));}
	     if(listSize>2&&imageNames.get(2)!=null&&!imageNames.get(2).equals(null)){
	    analysis.setImagePath2(imageNames.get(2));}
	     if(listSize>3&&imageNames.get(3)!=null&&!imageNames.get(3).equals(null)){
	     analysis.setImagePath3(imageNames.get(3));}
	     if(listSize>4&&imageNames.get(4)!=null&&!imageNames.get(4).equals(null)){
	    analysis.setImagePath4(imageNames.get(4));}
		
		// if(fileID!=null&&!fileID.equals("")){
		// analysis.setFileId(fileid.toString());
	
		System.out.println(analysis + ".jpg");

		//Analysis savedAnalysis = analysisService.update(analysis);
		Analysis savedAnalysis = analysisService.create(analysis);
		// return模板文件的名称，对应src/main/resources/templates/index.html
		// return index(map);
		return "/addObject";
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
		System.out.println("image contentType"+image.getContentType());
		System.out.println("image size"+image.getSize());
		if (!image.getContentType().equals("image/jpeg")) {
			throw new RuntimeException("Only JPG images are accepted");
		}

	}

}
