package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.web.dao.ContractFileMapper;
import com.congmai.zhgj.web.model.ContractFile;
import com.congmai.zhgj.web.model.ContractFileExample;
import com.congmai.zhgj.web.model.ContractFileExample.Criteria;
import com.congmai.zhgj.web.service.ContractFileService;

/**
 * 
 * @ClassName ContractFileServiceImpl
 * @Description 合同附件ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class ContractFileServiceImpl implements ContractFileService {
    @Resource
    private ContractFileMapper ContractFileMapper;
    
	@Override
	public int insert(ContractFile model) {
		return ContractFileMapper.insert(model);
	}

	@Override
	public int update(ContractFile model) {
		return ContractFileMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return ContractFileMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public ContractFile selectById(String serialNum) {
		return ContractFileMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public ContractFile selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractFile> selectList() {
		return ContractFileMapper.selectByExample(null);
	}

	@Override
	public List<ContractFile> selectList(ContractFileExample m) {
		return ContractFileMapper.selectByExample(m);
	}

	@Override
	public void deleteContractFiles(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertContractFiles(List<ContractFile> Files) {
		if(!CollectionUtils.isEmpty(Files)){
			//删除之前的附件
			ContractFile m = new ContractFile();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(Files.get(0).getCreator());
			
			ContractFileExample ex =new ContractFileExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andContractSerialEqualTo(Files.get(0).getContractSerial());
			
			ContractFileMapper.updateByExampleSelective(m, ex);
			
			for(ContractFile b:Files){
				ContractFileMapper.insert(b);
			}
		}
	}

}
