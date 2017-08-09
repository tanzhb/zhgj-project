package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.druid.util.StringUtils;
import com.congmai.zhgj.web.model.ContractVO;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.ContractService;
import com.congmai.zhgj.web.service.UserService;


/**
 * 合同管理controller
 * @author czw
 *
 */
@Controller
@RequestMapping("/contract")
public class ContractController {
	
	/**
	 * 合同管理service
	 */
	@Resource
	private ContractService contractService;
	
	/**
     * 
     * @Description 查找所有用户合同信息
     * @return
     */
	@RequestMapping(value = "/findAllUserContract", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllUserContract(HttpServletRequest request) {
		
		User user=(User) request.getSession().getAttribute("userInfo"); 
    	List<ContractVO> contractList=contractService.queryContractList("");
		
		if (contractList.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);// You many
			                                                             // HttpStatus.NOT_FOUND
		}
		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", contractList.size());
		pageMap.put("recordsFiltered", contractList.size());
		pageMap.put("data", contractList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}
	
	
	/**
	 * 保存用户合同
	 * @param contractVO（合同对象）
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveUserContract", method = RequestMethod.POST)
	   public ResponseEntity<Void> saveUserContract(@Valid ContractVO contractVO, HttpServletRequest request,
			   UriComponentsBuilder ucBuilder,@RequestParam(value = "files")MultipartFile files[]) {
		//如果id为空执行保存
		if(StringUtils.isEmpty(contractVO.getId())){
			  String serialNum=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
			  
			   contractVO.setId(serialNum);
			   String electronicContract=uploadFile(files[0]);
			   String signContract=uploadFile(files[1]);
			   
			   contractVO.setElectronicContract(electronicContract);
			   contractVO.setSignContract(signContract);
			   
			   User user=(User) request.getSession().getAttribute("userInfo");   
			   contractVO.setCreator("1");
			   contractService.insertContract(contractVO);
		  }else{
			  //如果id不为空执行更新
			  User user1=(User) request.getSession().getAttribute("userInfo");
			  contractVO.setUpdater("1");
			  contractService.updateContract(contractVO);
		  }
		    
		   
	    HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/userContract").buildAndExpand(contractVO.getId()).toUri());
					
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	   }
	
	
	public String uploadFile(MultipartFile file){
		String filePath = getClasspath()+"uploadAttachFiles/";
		String randomName=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
		String fileName = fileUp(file, filePath,randomName);
		System.out.println(fileName);
		return fileName;
	}
	
	
	public  String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {

			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}

			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");

		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	
	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private  String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir,realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	
	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public  File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}
	
	
	
	
	/**获取classpath1
	 * @return
	 */
	public  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
	
	
		 /**
		 * 删除用户合同
		 * @param contractVO
		 * @param request
		 * @return
		 */
		 @RequestMapping(value = "/deleteUserContractS", method = RequestMethod.POST)
		   public ResponseEntity<Void> deleteUserContractS(@RequestBody String ids) {
			 if ("".equals(ids) || ids == null) {
					return new ResponseEntity<Void>(HttpStatus.CONFLICT);
				}
			 contractService.deleteUserContractS(ids); 
			   return new ResponseEntity<Void>(HttpStatus.OK);
		   }
		 
		 
		 /**
		  * 通过id查询合同详情
		  * @param ids（前台所传递的id）
		  * @return
		  */
		 @RequestMapping(value = "/selectConbtractById", method = RequestMethod.GET)
	     public ResponseEntity<ContractVO> selectConbtractById(String ids) {
			 
			 ContractVO c=contractService.selectConbtractById(ids);
			 return new ResponseEntity<ContractVO>(c, HttpStatus.OK);
	     }
		 
		 /**
		  * 跳转到编辑页面
		  * @return
		  */
		 @RequestMapping(value = "/editUserContractPage")
	     public String editUserContractPage(String id,String view) {
			 return "contract/editUserContractPage";
	     }

		 
		 /**
		  * 跳转到编辑页面
		  * @return
		  */
		 @RequestMapping(value = "/upload")
	     public String upload(String id,String view) {
			 return "contract/upload";
	     }
}
