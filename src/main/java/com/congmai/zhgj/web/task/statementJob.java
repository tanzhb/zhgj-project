package com.congmai.zhgj.web.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.core.util.ApplicationUtils;
import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.model.Statement;
import com.congmai.zhgj.web.service.CompanyService;
import com.congmai.zhgj.web.service.StatementService;

@Component 
public class statementJob {

	@Autowired
	private StatementService statementService;
	
	  /**  
     * 自动生成对账单
     */    
    //@Scheduled(cron = "0 52 8 * * ?")   
    public void creatStatement(){
    	Statement statement = new Statement();
    	statement.setSerialNum(ApplicationUtils.random32UUID());
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
		statement.setCreator(currenLoginName);
		statement.setUpdater(currenLoginName);
		statement.setCreateTime(new Date());
		statement.setUpdateTime(new Date());
		statement.setStatus("1");
		statement.setDelFlg("0");
		
		statementService.insert(statement);
    	
    }  
}
