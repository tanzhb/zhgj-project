package com.congmai.zhgj.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.feature.orm.mybatis.Page;
import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.core.util.DateUtil;
import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.Notice;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.NoticeService;
import com.congmai.zhgj.web.service.UserCompanyService;

@Controller
@RequestMapping("notice")
public class NoticeContoller {
	
	private Log logger = LogFactory.getLog(NoticeContoller.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UserCompanyService userCompanyService;
	
	@RequestMapping("noticeManage")
	public String noticeManage() {
		
		return "notice/noticeManage";
	}
	
    /**
     * @Description (获取列表数据)
     * @param request
     * @return
     */
    @RequestMapping(value="noticeList",method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> noticeList(Map<String, Object> map,HttpServletRequest request,@RequestBody Notice notice) {
    	//远程分页代码
    	/*try {
    		params = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 ObjectMapper objectMapper = new ObjectMapper();
    	 objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  	   		DataTablesParams  dataTablesParams = null;
		   try {
			   JSONObject a = JSONObject.fromObject(params);
			   dataTablesParams = objectMapper.readValue(params,DataTablesParams.class);
			} catch (JsonParseException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (JsonMappingException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (IOException e) {
				System.out.println(this.getClass()+"---------"+ e.getMessage());
			} catch (Exception e) {
		    	System.out.println(this.getClass()+"---------"+ e.getMessage());
			}
		 company.setPageIndex(dataTablesParams.getStart());
		 company.setPageSize(dataTablesParams.getLength());*/
    	
    	notice.setPageIndex(0);
    	notice.setPageSize(-1);
		 //获取session中的user
		//User user = UserUtil.getUserFromSession();
		
		Page<Notice> notices = new Page<Notice>();
		//if(user !=null){
			
			//notice.setComIds(userCompanyService.getComIdsByUserId(String.valueOf(user.getUserId())));//获取用户的企业ID
		notices = noticeService.selectByPage(notice);
	    	//List<Company> companys = companyService.selectByPage(company).getResult();
			// 封装datatables数据返回到前台
		//}
    	
		Map<String,Object> pageMap = new HashMap<String,Object>();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", notice==null?0:notices.getTotalCount());
		pageMap.put("recordsFiltered", notice==null?0:notices.getTotalCount());
		pageMap.put("data", notices.getResult());
		return new ResponseEntity<Map<String,Object>>(pageMap, HttpStatus.OK);
    }
    
    @RequestMapping(value="myNoticeList",method=RequestMethod.POST)
    @ResponseBody
    public Page<Notice> myNoticeList(@RequestBody Notice notice){
    	Page<Notice> page = new Page<Notice>();
    	try{
    		User user = UserUtil.getUserFromSession();
    		String companyType = userCompanyService.getUserComType(user.getUserId().toString());
    		page = this.noticeService.selectMyNoticeByPage(notice, user.getUserId().toString(), companyType);
    		if(CollectionUtils.isNotEmpty(page.getResult())){
    			for(Notice n : page.getResult()){
    				n.setRelaseDate(DateUtil.getInterval(DateUtil.format("yyyy-MM-dd HH:mm:ss",n.getUpdateTime())));
    			}
    		}
    	}catch(Exception e){
    		logger.warn(e.getMessage(), e);
    	}
    	return page;
    }
    
	  /**
     * @Description (公告新增页面)
     * @param request
     * @return
     */
    @RequestMapping("noticeAdd")
    public String noticeAdd(HttpServletRequest request) {
        return "notice/noticeAdd";
    }

    
    /**
     * @Description (保存)
     * @param request
     * @return
     */
    @RequestMapping(value="saveNotice",method=RequestMethod.POST)
    @ResponseBody
    public String saveNotice(Map<String, Object> map,@RequestBody Notice notice,HttpServletRequest request) {
    	String flag ="0"; //默认失败

        	try{
        		Subject currentUser = SecurityUtils.getSubject();
        		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
        		if(StringUtils.isEmpty(notice.getSerialNum())){
        			notice.setSerialNum(ApplicationUtils.random32UUID());
        			notice.setCreateTime(new Date());
        			notice.setCreator(currenLoginName);
        			notice.setUpdateTime(new Date());
        			notice.setUpdater(currenLoginName);
        			noticeService.insert(notice);
        		}else{
        			notice.setUpdateTime(new Date());
        			notice.setUpdater(currenLoginName);
        			noticeService.update(notice);
        		}
        		
        		flag = "1";
        	}catch(Exception e){
        		logger.warn(e.getMessage(), e);
        	}
    	return flag;
    }
    
    
    /**
     * @Description (跳转至查看公告页面)
     * @param request
     * @return
     */
    @RequestMapping(value="noticeView")
    public String noticeView(Map<String, Object> map,String serialNum,HttpServletRequest request) {
    	
    	return "notice/noticeView";
    }
    
    /**
     * @Description (跳转至个人公告页面)
     * @param request
     * @return
     */
    @RequestMapping(value="myNotice")
    public String myNotice(Map<String, Object> map,HttpServletRequest request) {
    	return "notice/myNotice";
    }

    
    /**
     * @Description (查看公告)
     * @param request
     * @return
     */
    @RequestMapping(value="getNoticeInfo")
    @ResponseBody
    public Notice getNoticeInfo(String serialNum,HttpServletRequest request) {
    	Notice notice = null;
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			notice = noticeService.selectById(serialNum);
    			if(notice!=null){
    				notice.setRelaseDate(DateUtil.getInterval(DateUtil.format("yyyy-MM-dd HH:mm:ss",notice.getUpdateTime())));
    			}
    		}
    		
    	}catch(Exception e){
    		logger.warn(e.getMessage(),e);
    		return null;
    	}
    	return notice;
    }

    /**
     * @Description (删除公告信息)
     * @param request
     * @return
     */
    @RequestMapping(value="deleteNoticeBatch",method=RequestMethod.POST)
    @ResponseBody
    public String deleteNoticeBatch(Map<String, Object> map,@RequestBody String serialNums,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNums)){
    			List<String> serialNumArray  = Arrays.asList(serialNums.split(","));
    			noticeService.deleteBatch(serialNumArray,UserUtil.getUserFromSession().getUserName());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
    
    /**
     * @Description (删除公告信息)
     * @param request
     * @return
     */
    @RequestMapping(value="readMyNotice",method=RequestMethod.POST)
    @ResponseBody
    public String readMyNotice(Map<String, Object> map,@RequestBody String serialNum,HttpServletRequest request) {
    	String flag = "0"; //默认失败
    	try{
    		if(StringUtils.isNotEmpty(serialNum)){
    			noticeService.readNoticeShare(serialNum,UserUtil.getUserFromSession().getUserId().toString());
    		}
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		flag = "1";
    	}
    	return flag;
    }
}
