package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.MaterielFile;
import com.congmai.zhgj.web.model.MaterielFileExample;

/**
 * 
 * @ClassName MaterielFileService
 * @Description 物料附件Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface MaterielFileService extends GenericService<MaterielFile, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<MaterielFile> selectList(MaterielFileExample m);

	public void deleteMaterielFiles(String ids);

	void betchInsertMaterielFiles(List<MaterielFile> materielFiles);


}
