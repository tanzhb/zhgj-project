package com.congmai.zhgj.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.congmai.zhgj.web.dao.DeliverFileMapper;
import com.congmai.zhgj.web.model.DeliverFile;
import com.congmai.zhgj.web.model.DeliverFileExample;
import com.congmai.zhgj.web.model.DeliverFileExample.Criteria;
import com.congmai.zhgj.web.service.DeliverFileService;

/**
 * 
 * @ClassName DeliverFileServiceImpl
 * @Description 订单附件ServiceImpl
 * @author qfzhao
 * @Date 2017年8月11日 下午4:52:15
 * @version 1.0.0
 */
@Service
public class DeliverFileServiceImpl implements DeliverFileService {
    @Resource
    private DeliverFileMapper DeliverFileMapper;
    
	@Override
	public int insert(DeliverFile model) {
		return DeliverFileMapper.insert(model);
	}

	@Override
	public int update(DeliverFile model) {
		return DeliverFileMapper.updateByPrimaryKeySelective(model);
	}

	
	@Override
	public int delete(String serialNum) {
		return DeliverFileMapper.deleteByPrimaryKey(serialNum);
	}

	@Override
	public DeliverFile selectById(String serialNum) {
		return DeliverFileMapper.selectByPrimaryKey(serialNum);
	}

	@Override
	public DeliverFile selectOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeliverFile> selectList() {
		return DeliverFileMapper.selectByExample(null);
	}

	@Override
	public List<DeliverFile> selectList(DeliverFileExample m) {
		return DeliverFileMapper.selectByExample(m);
	}

	@Override
	public void deleteDeliverFiles(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void betchInsertDeliverFiles(List<DeliverFile> Files) {
		if(!CollectionUtils.isEmpty(Files)){
			//删除之前的附件
			DeliverFile m = new DeliverFile();
			m.setDelFlg("1");
			m.setUpdateTime(new Date());
			m.setUpdater(Files.get(0).getCreator());
			
			DeliverFileExample ex =new DeliverFileExample();
	    	Criteria criteria =  ex.createCriteria();
	    	criteria.andDeliverSerialEqualTo(Files.get(0).getDeliverSerial());
			
			DeliverFileMapper.updateByExampleSelective(m, ex);
			
			for(DeliverFile b:Files){
				DeliverFileMapper.insert(b);
			}
		}
	}

}
