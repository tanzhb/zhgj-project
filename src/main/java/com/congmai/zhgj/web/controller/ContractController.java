package com.congmai.zhgj.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	   public ResponseEntity<Void> saveUserContract(@Valid ContractVO contractVO, HttpServletRequest request,UriComponentsBuilder ucBuilder) {
		//如果id为空执行保存
		if(StringUtils.isEmpty(contractVO.getId())){
			  String serialNum=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
			   contractVO.setId(serialNum);
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
		 @RequestMapping(value = "/selectConbtractById", method = RequestMethod.POST)
	     public ResponseEntity<ContractVO> selectConbtractById(@RequestBody String ids) {
			 
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

}
