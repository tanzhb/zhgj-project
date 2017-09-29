package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.util.UserUtil;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.OperateLog;
import com.congmai.zhgj.web.model.OperateLogExample;
import com.congmai.zhgj.web.model.OperateLogExample.Criteria;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.OperateLogService;


/**
 * 
 * @ClassName OperateLogController
 * @Description 操作日志
 * @author qfzhao
 * @Date 2017年9月29日 下午2:01:39
 * @version 1.0.0
 */

@Controller
@RequestMapping("/operateLog")
public class OperateLogController {
	private static final Logger logger = Logger.getLogger(OperateLogController.class);

    @Resource
    private OperateLogService operateLogService;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
   
    /**
     * 
     * @Description 查询操作日志列表
     * @param params
     * @return
     */
    @RequestMapping("/findOperateLogList")
    @ResponseBody
    public ResponseEntity<Map> findOperateLogList(@RequestBody String params) {
    	try {
			params = URLDecoder.decode(params, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	List<OperateLog> operateLogList = new ArrayList<OperateLog>();

    	User user = UserUtil.getUserFromSession();
    	OperateLogExample m =new OperateLogExample();
    	//and 条件1
    	Criteria criteria =  m.createCriteria();
    	criteria.andCreatorEqualTo(user.getUserId().toString());
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	Map  map = null;
    	try {
			map = objectMapper.readValue(params.substring(7),Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(map!=null&&map.get("startTime")!=null&&!"".equals(map.get("startTime"))){
    		try {
				criteria.andOperationTimeGreaterThanOrEqualTo(sdf.parse((String) map.get("startTime")));
				if(map!=null&&map.get("endTime")!=null&&!"".equals(map.get("endTime"))){
		    		Calendar cal = Calendar.getInstance();
		        	cal.setTime(sdf.parse((String) map.get("endTime")));
		        	cal.add(Calendar.DATE, 1);
		        	criteria.andOperationTimeLessThan(cal.getTime());
		    	}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	operateLogList = operateLogService.selectList(m);
    	
    	//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal", operateLogList==null?0:operateLogList.size());
		pageMap.put("recordsFiltered", operateLogList==null?0:operateLogList.size());
		pageMap.put("data", operateLogList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);

    }

    
}
