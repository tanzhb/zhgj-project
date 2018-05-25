package com.congmai.zhgj.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.congmai.zhgj.core.util.JsonUtil;
import com.congmai.zhgj.core.util.SolrUtils;

/**
 * @author tanzb
 * @version 2017年9月25日 下午7:09:21
 */
@RequestMapping("/search")
@Controller
public class SolrSearchController {
	private static final Logger logger = Logger
			.getLogger(SolrSearchController.class);

	/**
     * 搜索关键字
     * @param mQueryStr
     * @return query result
     */
	@RequestMapping(value="searchKey",method=RequestMethod.POST)
    public void query(HttpServletRequest request,HttpServletResponse response) {
		//获取前台传入的参数  
        String queryStr = request.getParameter("queryStr");
        //查询类型
        String searchType = request.getParameter("searchType");
		
		if(queryStr != null && !queryStr.trim().equals("")){
			List retuList = new ArrayList();
			try {
	            HttpSolrClient httpSolrClient = SolrUtils.connect();
	            
//	            query.setQuery("search_fields:上海");
	            
//	            // 分类信息分为：企业名称，物料名称
//	            query.addFacetField(new String[] {"comName","materielName"});//设置需要facet的字段
//	            query.setFacetLimit(10);// 限制facet返回的数量
//	            query.setFacetMissing(false);//不统计null的值
//	            query.setFacetMinCount(1);// 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
//	            List<FacetField> facets = qResponse.getFacetFields();//返回的facet列表
//	            //20180110 qhzhao System.out.println();
	            
	            
	            
	            
	            
	            
	            
	            /*queryStr = queryStr.replaceAll("\\s{1,}", " ");// 去除前后所有空格，将中间多个空格过滤成一个空格
				String s[] = queryStr.split(" ");
				String p = "";
				if (s.length > 0) {
					for (int i = 0; i < s.length; i++) {
						if (i == 0 && s.length == 1)
							p = "search_fields" + ":" + s[i];
						else if (i == (s.length - 1))
							p = p + "search_fields" + ":" + s[i];
						else
							p = p + "search_fields" + ":" + s[i] + " AND ";
					}

				}*/
	            
//				String qs = "title:this";
				
				SolrQuery query = new SolrQuery();
	            
				query.set("q","search_fields:"+queryStr);
	            //设定查询字段
	            //query.setQuery(mQueryStr);
//	            query.set("q",p);
	            //指定返回结果字段
	            //query.set("fl","id,name");
	            //覆盖schema.xml的defaultOperator（有空格时用"AND"还是用"OR"操作逻辑），一般默认指定。必须大写
	            //query.set("q.op","AND");
	            //设定返回记录数，默认为10条
	            query.setRows(1000);
	            
	            QueryResponse qResponse = httpSolrClient.query(query);
	            
	            SolrDocumentList list = qResponse.getResults();
	            
				for (int i = 0; i < list.size(); ++i) {
					SolrDocument st = list.get(i);
					HashMap map = new HashMap();
					Object o = st.getFieldValue("search_fields");
					
					//buyComId为null，则为采购订单；supplyComId为null，则为销售订单
					String buyComId = (String) st.getFieldValue("buyComId");
					String supplyComId = (String) st.getFieldValue("supplyComId");
					
					String orderNum = (String) st.getFieldValue("orderNum");
					String comNum = (String) st.getFieldValue("comNum");
					String materielNum = (String) st.getFieldValue("materielNum");
					String priceNum = (String) st.getFieldValue("priceNum");
					String contractNum = (String) st.getFieldValue("contractNum");
					String warehouseNum = (String) st.getFieldValue("warehouseNum");
					String stockNum = (String) st.getFieldValue("stockNum");
					String paymentNum = (String) st.getFieldValue("paymentNum");
					String invoiceNum = (String) st.getFieldValue("invoiceNum");
					String statementNum = (String) st.getFieldValue("statementNum");
					
					if(searchType.equals("purchaseOrder") && buyComId == null && orderNum != null){//汇总采购订单关键字
					}else if(searchType.equals("saleOrder") && supplyComId == null && orderNum != null){//汇总销售订单关键字
					}else if(searchType.equals("company") && comNum != null){
					}else if(searchType.equals("materiel") && materielNum != null){
					}else if(searchType.equals("priceList") && priceNum != null){
					}else if(searchType.equals("contract") && contractNum != null){
					}else if(searchType.equals("warehouse") && warehouseNum != null){
					}else if(searchType.equals("stock") && stockNum != null){
					}else if(searchType.equals("paymentRecord") && paymentNum != null){
					}else if(searchType.equals("invoice") && invoiceNum != null){
					}else if(searchType.equals("statement") && statementNum != null){
					}else if("all".equals(searchType)){
					}else{//类型和数据不匹配时，关键字不加人汇总
						continue;
					}
					
					if(o instanceof List){//一个表中有多个字段检索时，将其分开放入下拉列表中
						for(int j=0; j<((List)o).size(); j++){
							String s = (String)((List)o).get(j);
							if(s.contains(queryStr)){//字段中包含queryStr字符串的放入下拉列表中
								map.put("search_fields", s);
							}
						}
						
					}else map.put("search_fields", st.getFieldValue("search_fields"));
					
					
					//map.put("id", st.getFieldValue("id"));
					
					retuList.add(map);
				}
	           /* Map m = new HashMap();
	            for (int i = 0; i < facets.size(); ++i) {
	            	
	            	FacetField ff = facets.get(i);
	            	List<Count> counts = ff.getValues();
	            	List lt = new ArrayList();
	            	 for (Count count : counts) {
	            	        //20180110 qhzhao System.out.println(count.getName() + ":" + count.getCount());
	            	        lt.add(count.getName() + ":" + count.getCount());
	            	    }
	            	
	            	m.put(ff.getName(), lt);
	            	
	            }*/
				
				Map<String, Map> msp = new HashMap<String, Map>();
				List<Map<String, String>> listMap = new ArrayList<Map<String,String>>();
				 
				//把retuList中的数据转换成msp,去掉同一search_fields值多余数据，保留查找到第一个search_fields值对应的数据
		        for(int i = retuList.size()-1 ; i>=0; i--){
		            Map map = (Map) retuList.get(i);
		            String pm = (String)map.get("search_fields");
		            map.remove("search_fields");
		            msp.put(pm, map);
		        }
		         //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
		        Set<String> mspKey = msp.keySet();
		        for(String key: mspKey){
		            Map newMap = msp.get(key);
		            newMap.put("search_fields", key);
		            listMap.add(newMap);
		        }
	            
				
				String data = JsonUtil.disposeListJson(listMap);
				
				response.setContentType("text/xml;charset=utf-8");
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
					pw.print(data);
					pw.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (pw != null) {
						pw.close();
					}
				}
				
		
        
			
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
	
	
	/**
     * 结果列表
     * @param mQueryStr
     * @return query result
     */
	@RequestMapping(value="searchList",method=RequestMethod.POST)
    public void queryList(HttpServletRequest request,HttpServletResponse response) {
		//获取前台传入的参数  
        String queryStr = request.getParameter("queryStr");
		
		if(queryStr != null && !queryStr.trim().equals("")){
			List retuList = new ArrayList();
			try {
	            HttpSolrClient httpSolrClient = SolrUtils.connect();
				
				SolrQuery query = new SolrQuery();
	            
				query.set("q","search_fields:"+queryStr);
				query.setRows(1000);
				
				 /** 分组查询统计每个searchType数量开始 */
//	            query.addFacetField(new String[] {"buyComId","supplyComId"});//设置需要facet的字段
//	            query.setFacetLimit(10);// 限制facet返回的数量
//	            query.setFacet(true);
//	            query.setFacetMissing(false);//不统计null的值
//	            query.setFacetMinCount(1);// 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示
	            QueryResponse qResponse = httpSolrClient.query(query);
//	            List<FacetField> facets = qResponse.getFacetFields();//返回的facet列表            
	            /** 分组查询统计每个searchType数量结束 */
				
				

	            
	            
	            
	            
	            SolrDocumentList list = qResponse.getResults();
	            
				for (int i = 0; i < list.size(); ++i) {
					SolrDocument st = list.get(i);
					HashMap map = new HashMap();
					
					map.put("id", st.getFieldValue("id"));
					map.put("search_fields", st.getFieldValue("search_fields"));
					retuList.add(map);
				}

				
				String data = JsonUtil.disposeListJson(retuList);
				
				response.setContentType("text/xml;charset=utf-8");
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
					pw.print(data);
					pw.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (pw != null) {
						pw.close();
					}
				}
				
		
        
			
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

    /**
     * 分页查询、高亮显示
     * @param queryStr
     * @param start
     * @param rows
     * @return SolrDocumentList
     * @throws ClassNotFoundException 
     */
	@RequestMapping(value="queryPage",method=RequestMethod.POST)
	@ResponseBody
    public Object queryPage(@RequestBody Map<String, String> queryParams, HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException{
        try {
            HttpSolrClient httpSolrClient = SolrUtils.connect();
            SolrQuery query = new SolrQuery();
            String queryStr = queryParams.get("queryStr");
            String searchType = queryParams.get("searchType");
            Integer start = Integer.parseInt(queryParams.get("start"));
            Integer rows = Integer.parseInt(queryParams.get("rows"));
            if(searchType == null || "".equals(searchType)){
            	searchType = "all";
            }
            //设定查询字段
//            query.setQuery(queryStr);
            query.set("q",queryStr);            
            //指定返回结果字段
            query.setIncludeScore(true);
            // query.set("fl","id,name");
            //覆盖schema.xml的defaultOperator（有空格时用"AND"还是用"OR"操作逻辑），一般默认指定。必须大写
            query.set("q.op","AND");
            //分页开始页数
            if(start == 1)
            	query.setStart(start-1);
            else query.setStart(start);
            //设定返回记录数，默认为10条
            query.setRows(rows);
            //设定对查询结果是否高亮
            query.setHighlight(true);
            //设定高亮字段前置标签
            query.setHighlightSimplePre("<span style=\"color:red\">");
            //设定高亮字段后置标签
            query.setHighlightSimplePost("</span>");
            //设定高亮字段
            query.setParam("hl.fl", "search_fields");
            //一个表中多个字段高亮
            query.setParam("hl.preserveMulti", true);
            
            //根据检索类型添加过滤器，汇总总数据，方便分页
            if(!"all".equals(searchType)){
            	if(searchType.equals("purchaseOrder")){
            		query.addFilterQuery("orderNum:*");  
            		query.addFilterQuery("supplyComId:*");
            	}else if(searchType.equals("saleOrder")){
            		query.addFilterQuery("orderNum:*");  
            		query.addFilterQuery("buyComId:*");
            	}else if(searchType.equals("company")){
            		query.addFilterQuery("comNum:*");
				}else if(searchType.equals("materiel")){
					query.addFilterQuery("materielNum:*");
				}else if(searchType.equals("priceList")){
					query.addFilterQuery("priceNum:*");
				}else if(searchType.equals("contract")){
					query.addFilterQuery("contractNum:*");
				}else if(searchType.equals("warehouse")){
					query.addFilterQuery("warehouseNum:*");
				}else if(searchType.equals("stock")){
					query.addFilterQuery("stockNum:*");
				}else if(searchType.equals("paymentRecord")){
					query.addFilterQuery("paymentNum:*");
				}else if(searchType.equals("invoice")){
					query.addFilterQuery("invoiceNum:*");
				}else if(searchType.equals("statement")){
					query.addFilterQuery("statementNum:*");
				}
            }
            
            
            //设置显示字段
//            query.setParam("fl", "search_fields,orderNum,orderType,rate,tradeType");
            
            QueryResponse res = httpSolrClient.query(query);
            Map<String, Map<String, List<String>>> maplist=res.getHighlighting();
            
            
           //获取bean
           //  List<Object> bean = response.getBeans(Object.class);
            SolrDocumentList list = res.getResults();
            
            
            for(SolrDocument solrDocument:list){
            	String string = "";
            	String goType = "";//前台页面点击titel跳转，根据该类型跳转到不同查看页面
            	String title = "";//每行结果名称，用于页面显示
            	
                Object id=solrDocument.get("id");//企业或物料id
                Object orderNum = solrDocument.get("orderNum");//采购/销售订单号      
                Object comNum = solrDocument.getFieldValue("comNum");
                Object materielNum = solrDocument.getFieldValue("materielNum");
                Object priceNum = solrDocument.getFieldValue("priceNum");
                Object contractNum = solrDocument.getFieldValue("contractNum");
                Object warehouseNum = solrDocument.getFieldValue("warehouseNum");
                Object stockNum = solrDocument.getFieldValue("stockNum");
                Object paymentNum = solrDocument.getFieldValue("paymentNum");
                Object invoiceNum = solrDocument.getFieldValue("invoiceNum");
                Object statementNum = solrDocument.getFieldValue("statementNum");
//                Object orderType = solrDocument.get("orderType");//采购/销售类型
//                Object tradeType = solrDocument.get("tradeType");//贸易类型
//                Object seller = solrDocument.get("seller");//采购商/供应商                
//                Object currency = solrDocument.get("currency");//币种
//                Object rate = solrDocument.get("rate");//税率（%）
//                Object maker = solrDocument.get("maker");//制单人
//                Object makeDate = solrDocument.get("makeDate");//制单日期
//                Object orderDate = solrDocument.get("orderDate");//采购/销售日期
//                Object entrustParty = solrDocument.get("entrustParty");//委托方
//                Object serviceModel = solrDocument.get("serviceModel");//服务模式
//                Object createTime = solrDocument.get("createTime");//数据创建时间
//                Object updateTime = solrDocument.get("updateTime");//数据更新时间
//                Object comName = solrDocument.get("comName");//供应商/采购商
                
                Object supplyComId = solrDocument.get("supplyComId");//供应商id
                Object buyComId = solrDocument.get("buyComId");//采购商id
                
                
                Map<String, List<String>>  fieldMap=maplist.get(id);//高亮文字
                List<String> stringlist=fieldMap.get("search_fields");
                
                if(searchType.equals("purchaseOrder") && supplyComId != null && orderNum != null){//采购订单
                	title = "采购订单;单号：" + orderNum;
                	goType = "buyOrder";
//                	string = /*"id号：" + id + ";" +*/ StringUtils.join(stringlist.toArray(),",") ;
                }else if(searchType.equals("saleOrder") && buyComId != null && orderNum != null){//销售订单
                	title = "销售订单;单号：" + orderNum;
                	goType = "saleOrder";
//                	string = /*"id号：" + id + ";" +*/ StringUtils.join(stringlist.toArray(),",") ;
                }else if(searchType.equals("company") && comNum != null){
                	title = "企业信息;单号：" + comNum;
                	goType = "company";
				}else if(searchType.equals("materiel") && materielNum != null){
					title = "物料信息;单号：" + materielNum;
                	goType = "materiel";
				}else if(searchType.equals("priceList") && supplyComId != null && priceNum != null){
					title = "采购价格目录;单号：" + priceNum;
                	goType = "buyPriceList";
				}else if(searchType.equals("priceList") && buyComId != null && priceNum != null){
					title = "销售价格目录;单号：" + priceNum;
                	goType = "lePriceList";
				}else if(searchType.equals("contract") && contractNum != null){
					title = "合同信息;单号：" + contractNum;
                	goType = "contract";
				}else if(searchType.equals("warehouse") && warehouseNum != null){
					title = "仓库信息;单号：" + warehouseNum;
                	goType = "warehouse";
				}else if(searchType.equals("stock") && stockNum != null){
					title = "库存信息;单号：" + stockNum;
                	goType = "stock";
				}else if(searchType.equals("paymentRecord") && supplyComId != null && paymentNum != null){
					title = "应付款;单号：" + paymentNum;
                	goType = "buyPaymentRecord";
				}else if(searchType.equals("paymentRecord") && buyComId != null && paymentNum != null){
					title = "应收款;单号：" + paymentNum;
                	goType = "salePaymentRecord";
				}else if(searchType.equals("invoice") && supplyComId != null && invoiceNum != null){
					title = "进项票;单号：" + invoiceNum;
                	goType = "buyInvoice";
				}else if(searchType.equals("invoice") && buyComId != null && invoiceNum != null){
					title = "销项票;单号：" + invoiceNum;
                	goType = "saleInvoice";
				}else if(searchType.equals("statement") && supplyComId != null && statementNum != null){
					title = "供应商对账单;单号：" + statementNum;
                	goType = "supplyStatement";
				}else if(searchType.equals("statement") && buyComId != null && statementNum != null){
					title = "采购商对账单;单号：" + statementNum;
                	goType = "buyStatement";
				}else if(searchType.equals("all")){//全部
                	if(supplyComId != null && orderNum != null){
                		title = "采购订单;单号：" + orderNum;
                		goType = "buyOrder";
                	}else if(buyComId != null && orderNum != null){
                		title = "销售订单;单号：" + orderNum;
                		goType = "saleOrder";
                	}else if(comNum != null){
                    	title = "企业信息;单号：" + comNum;
                    	goType = "company";
    				}else if(stockNum != null){//在物料之前做判断，因为库存信息也有materielNum
    					title = "库存信息;单号：" + stockNum;
                    	goType = "stock";
    				}else if(materielNum != null){
    					title = "物料信息;单号：" + materielNum;
                    	goType = "materiel";
    				}else if(supplyComId != null && priceNum != null){
    					title = "采购价格目录;单号：" + priceNum;
                    	goType = "buyPriceList";
    				}else if(buyComId != null && priceNum != null){
    					title = "销售价格目录;单号：" + priceNum;
                    	goType = "salePriceList";
    				}else if(contractNum != null){
    					title = "合同信息;单号：" + contractNum;
                    	goType = "contract";
    				}else if(warehouseNum != null){
    					title = "仓库信息;单号：" + warehouseNum;
                    	goType = "warehouse";
    				}else if(supplyComId != null && paymentNum != null){
    					title = "应付款;单号：" + paymentNum;
                    	goType = "buyPaymentRecord";
    				}else if(buyComId != null && paymentNum != null){
    					title = "应收款;单号：" + paymentNum;
                    	goType = "salePaymentRecord";
    				}else if(supplyComId != null && invoiceNum != null){
    					title = "进项票;单号：" + invoiceNum;
                    	goType = "buyInvoice";
    				}else if(buyComId != null && invoiceNum != null){
    					title = "销项票;单号：" + invoiceNum;
                    	goType = "saleInvoice";
    				}else if(supplyComId != null && statementNum != null){
    					title = "供应商对账单;单号：" + statementNum;
                    	goType = "supplyStatement";
    				}else if(buyComId != null && statementNum != null){
    					title = "采购商对账单;单号：" + statementNum;
                    	goType = "buyStatement";
    				}
                	
                }
                
                string = /*"id号：" + id + ";" +*/ StringUtils.join(stringlist.toArray(),",") ;
                
                solrDocument.put("id", id);
                solrDocument.put("goType", goType);
                solrDocument.put("title", title);
                solrDocument.put("search_fields", string);
            }
            
            
            
           
            //结果总数，用于翻页
            long num = list.getNumFound();
            
            Map mp = new HashMap();
            mp.put("total", num);
            mp.put("datas", list);
            
            //转化为json返到前端显示
            String data = JsonUtil.disposeListJson(mp);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.print(data);
				pw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
            

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 添加一个实体
     *
     * @param object
     */
    public void addBean(Object object) {
        try {
            HttpSolrClient httpSolrClient = SolrUtils.connect();
            httpSolrClient.addBean(object);
            httpSolrClient.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 添加简单索引
     *
     * @param map
     */
    public void addDoc(Map<String, Object> map) {
        try {
            HttpSolrClient httpSolrClient = SolrUtils.connect();
            SolrInputDocument document = new SolrInputDocument();
            document = SolrUtils.addFileds(map,document);
            UpdateResponse response = httpSolrClient.add(document);
            httpSolrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除索引
     *
     * @param id
     */
    public void deleteById(String id) {
        try {
            HttpSolrClient httpSolrClient = SolrUtils.connect();
            httpSolrClient.deleteById(id);
            httpSolrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
