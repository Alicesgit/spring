package com.jh.share.web.api;

import java.io.BufferedOutputStream;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.jh.share.model.FileBucket;
import com.jh.share.service.AnalysisService;
import com.jh.share.service.FileService;

@Controller
public class FileUploadController {

	private static String UPLOAD_LOCATION = "C:/opt/files/";

	@Autowired
	private FileService fileService;
	// 访问路径为：http://127.0.0.1:8080/file

	@RequestMapping("/file")
	public String file() {

		return "/file";

	}

	/**
	 * 
	 * 文件上传具体实现方法;
	 * 
	 * @param file
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public RedirectView handleFileUpload(FileBucket fileBucket, ModelMap model, RedirectAttributes redirectAttrs) {

		// String currentPath=System.getProperty("user.dir").replaceAll("\\",
		// "/")+"/src/main/resources/static/images/";;
		System.out.println("Fetching file");
		MultipartFile multipartFile = fileBucket.getFile();
		// gererate uuid and insert into db and the same time redirect the uuid
		// to analyst controller
		UUID fileid = UUID.randomUUID();

		fileBucket.setFile_id(fileid.toString());
		fileBucket.setFilePath(fileBucket.getFile().getOriginalFilename());
		FileBucket file = fileService.create(fileBucket);

		// Now do something with file...
		try {
			FileCopyUtils.copy(fileBucket.getFile().getBytes(),
					new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fileName = multipartFile.getOriginalFilename();
		model.addAttribute("fileName", fileName);

		redirectAttrs.addFlashAttribute("fileID", fileid);
		RedirectView redirectView = new RedirectView();

		redirectView.setUrl("/analyst/addObject/");
		return redirectView;

	}
}
