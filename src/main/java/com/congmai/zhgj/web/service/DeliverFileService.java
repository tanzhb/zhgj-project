package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DeliverFile;
import com.congmai.zhgj.web.model.DeliverFileExample;

/**
 * 
 * @ClassName DeliverFileService
 * @Description 订单附件Service
 * @author qfzhao
 * @Date 2018年4月24日 下午3:04:17
 * @version 1.0.0
 */
public interface DeliverFileService extends GenericService<DeliverFile, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<DeliverFile> selectList(DeliverFileExample m);

	public void deleteDeliverFiles(String ids);

	void betchInsertDeliverFiles(List<DeliverFile> deliverFiles);


}
