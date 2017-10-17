package com.congmai.zhgj.web.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.congmai.zhgj.web.model.Company;
import com.congmai.zhgj.web.model.CompanyQualification;
import com.congmai.zhgj.web.service.CompanyService;

@Component 
public class CompanyJob {

	Logger logger = LoggerFactory.getLogger(CompanyJob.class);
	
	@Autowired
	private CompanyService companyService;
	
	  /**  
     * 企业资质过期检查定时任务  
     */    
    @Scheduled(cron = "0 52 8 * * ?")   
    public void checkCompanyQualification(){  
    	
    	List<Company> list = companyService.selectAll();
    	if(CollectionUtils.isNotEmpty(list)){
    		for(Company c : list){
    			List<CompanyQualification> companyQualifications = c.getCompanyQualifications();
    			if(CollectionUtils.isNotEmpty(companyQualifications)){
    				for(CompanyQualification qualification : companyQualifications){
    					logger.info(qualification.getValidityDate().toGMTString());;
    				}
    			}
    		}
    	}
    	
    }  
}
