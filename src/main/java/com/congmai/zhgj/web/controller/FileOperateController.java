package com.congmai.zhgj.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.congmai.zhgj.core.util.ApplicationUtils;
/**
 * 
 * @ClassName FileOperateController
 * @Description TODO(文件的上传下载控制器)
 * @author likt
 * @Date 2017年8月10日 下午5:08:12
 * @version 1.0.0
 */
@Controller
@RequestMapping("fileOperate")
@PropertySource("classpath:/application.properties")
public class FileOperateController {
	
	@Autowired  
	Environment env;
	
	/**
	 * 上传单个文件
	 * @Description (单个文件上传)
	 * @param request
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("uploadSingleFile")
	@ResponseBody
	public String uploadSingleFile(HttpServletRequest request,@RequestParam("file") MultipartFile uploadFile) {
		String filename = "";
		
		try {
			String path = env.getProperty("upload_path");
			String fileName = uploadFile.getOriginalFilename();
			String prefix = "." + fileName.substring(fileName.lastIndexOf(".") + 1);
			filename = ApplicationUtils.random32UUID() + prefix;
			File dst = null;
			File uploadDir = new File(path); // 创建上传目录
			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // 如果不存在则创建upload目录
			}
			dst = new File(uploadDir,filename); // 创建一个指向upload目录下的文件对象，文件名随机生成
			uploadFile.transferTo(dst); // 创建文件并将上传文件复制过去
			System.out.println("上传文件----------"+filename);
		} catch (Exception e) {
			System.out.println("文件上传失败----------"+filename+"-------Exception:"+e.getMessage());
			filename="";
		}
		return filename;
	}

	/**
	 * 下载文件
	 * @Description (文件下载)
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	@RequestMapping("downloadFile")
	@ResponseBody
	public String downloadFile(HttpServletRequest request,HttpServletResponse response,String fileName) {
		try {
			String path = env.getProperty("upload_path");
			String destFilePath = path + "/" + fileName;
			String agent = request.getHeader("USER-AGENT");
        	if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
        		destFilePath = java.net.URLEncoder.encode(destFilePath, "UTF8");  
        	} else {// others
        		destFilePath = new String(destFilePath.getBytes("utf-8"), "iso-8859-1");
        	}
        	response.setContentType("application/file;charset=UTF-8");
        	//设置响应头和下载保存的文件名      用关键字命名        
            response.setHeader("content-disposition","attachment;filename="+fileName);
			byte[] buffer = new byte[1024];
			int k = 0;
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(destFilePath));
			while ((k = fis.read(buffer)) > 0) {
				response.getOutputStream().write(buffer, 0, k);
			}
			System.out.println("下载文件------"+fileName);
			fis.close();
		} catch (Exception e) {
			System.out.println("文件下载失败------"+fileName+"------Exception:"+e.getMessage());
		}
		
		return null;
	}
	
}
