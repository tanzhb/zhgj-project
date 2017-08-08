package com.congmai.zhgj.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 
 * @ClassName ExcelUtil
 * @Description TODO(Excel导出工具类)
 * @author likt
 * @Date 2017年8月8日 下午12:08:23
 * @version 1.0.0
 */
public class ExcelUtil {
	
	
	
	/**
	 * 
	 * @Description (Excel导出)
	 * @param request
	 * @param response
	 * @param map 待打印的数据
	 * @param tempName 模板名称
	 * @param fileName 导出文件名
	 */
	public static void export(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map,String tempName,String fileName){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
        try
        {
        	String uid = UUID.randomUUID().toString().replace("-", "");
            String exportFilePath = "/temp/exportTemp/"+tempName+".xls";
            String filePath = request.getSession().getServletContext().getRealPath("/temp/exportTemp/temp/");
            File destDir = new File(filePath);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            //Map<String, Object> companyMap = new HashMap<String, Object>();
            String destFilePath = filePath + "/" + uid + ".xls";
            String exportFileName = fileName + "-" + fmt.format(new Date())+".xls";
            String agent = request.getHeader("USER-AGENT");
        	if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
        		exportFileName = java.net.URLEncoder.encode(exportFileName, "UTF8");  
        	} else {// others
        		exportFileName = new String(exportFileName.getBytes("utf-8"), "iso-8859-1");
        	}
        	response.setContentType("application/file;charset=UTF-8");
            
            //设置响应头和下载保存的文件名      用关键字命名        
            response.setHeader("content-disposition","attachment;filename="+exportFileName);
            //List<Company> companyList = companyService.selectByPage(new Company()).getResult();
            //companyMap.put("companyList",companyList);
            XLSTransformer transformer = new XLSTransformer();
            transformer.transformXLS(request.getSession().getServletContext().getRealPath(exportFilePath), map, destFilePath);
            byte[] buffer = new byte[1024];
            int k = 0;
            // 以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(destFilePath));
            while ((k = fis.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, k);
            }
            fis.close();
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
	}
	
	/**
	 * 
	 * @Description (导入模板下载)
	 * @param request
	 * @param response
	 * @param map
	 * @param tempName
	 */
	public static void importTempDownLoad(HttpServletRequest request,HttpServletResponse response,String tempName){
		try
		{
			String filePath = request.getSession().getServletContext().getRealPath("/temp/importTemp/");
			String destFilePath = filePath + "/" + tempName + ".xls";
			String agent = request.getHeader("USER-AGENT");
        	if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
        		destFilePath = java.net.URLEncoder.encode(destFilePath, "UTF8");  
        	} else {// others
        		destFilePath = new String(destFilePath.getBytes("utf-8"), "iso-8859-1");
        	}
        	response.setContentType("application/file;charset=UTF-8");
        	//设置响应头和下载保存的文件名      用关键字命名        
            response.setHeader("content-disposition","attachment;filename="+tempName+".xls");
			byte[] buffer = new byte[1024];
			int k = 0;
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(destFilePath));
			while ((k = fis.read(buffer)) > 0) {
				response.getOutputStream().write(buffer, 0, k);
			}
			fis.close();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
