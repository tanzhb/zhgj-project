package com.congmai.zhgj.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.congmai.zhgj.core.util.ExcelUtil;
import com.congmai.zhgj.web.model.DemandPlanMateriel;
import com.congmai.zhgj.web.model.User;
import com.congmai.zhgj.web.service.PurchaseForecastService;


/**
 * 采购预测controller
 * @author czw
 *
 */
@Component  
@Scope("prototype")  
@Controller
@RequestMapping("/purchaseForecast")
public class PurchaseForecastController {

	/**
	 * 采购预测service
	 */
	@Resource
	private PurchaseForecastService purchaseForecastService;

	/**
	 * 
	 * @Description 查找所有采购预测信息
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/findAllPurchaseForecast", method = RequestMethod.GET)
	public ResponseEntity<Map> findAllPurchaseForecast(HttpServletRequest request) throws ParseException {

		List<DemandPlanMateriel> purchaseForecastList=purchaseForecastService.queryPurchaseForecastList("");
		
		if(purchaseForecastList.size()>0){
			for(DemandPlanMateriel demandPlanMateriel:purchaseForecastList){
				Date deliveryDate=demandPlanMateriel.getDeliveryDate();
				Date now=null;
				now=new Date();
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				String nowString=sdf.format(now);
				now=sdf.parse(nowString);
				int daysBeforeDelivery=(int)((deliveryDate.getTime()-now.getTime())/1000/60/60/24);
				demandPlanMateriel.setDaysBeforeDelivery(daysBeforeDelivery+"");
			}
			
		}

		if (purchaseForecastList.isEmpty()) {
			return new ResponseEntity<Map>(HttpStatus.NO_CONTENT);// You many
			// HttpStatus.NOT_FOUND
		}
		//封装datatables数据返回到前台
		Map pageMap = new HashMap();
		pageMap.put("draw", 1);
		pageMap.put("recordsTotal",purchaseForecastList==null?0:purchaseForecastList.size());
		pageMap.put("recordsFiltered",purchaseForecastList==null?0:purchaseForecastList.size());
		pageMap.put("data", purchaseForecastList);
		return new ResponseEntity<Map>(pageMap, HttpStatus.OK);
	}


	/**
	 * 保存用户合同
	 * @param contractVO（合同对象）
	 * @param request（http 请求对象）
	 * @param ucBuilder
	 * @return 操作结果
	 */
	/*@RequestMapping(value = "/saveUserContract", method = RequestMethod.POST)
	public ResponseEntity<Void> saveUserContract(@Valid ContractVO contractVO, HttpServletRequest request,
			UriComponentsBuilder ucBuilder,@RequestParam(value = "files")MultipartFile files[]) {
		Subject currentUser = SecurityUtils.getSubject();
		String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名 


		String electronicContract=null;
		String signContract=null;
		//只有在文件组不为空时上传文件
		if(files.length>0){
			if(files[0]!=null){
				electronicContract=uploadFile(files[0]);   
			}


			if(files[1]!=null){
				signContract=uploadFile(files[1]); 
			}
		}
		//如果id为空执行保存
		if(StringUtils.isEmpty(contractVO.getId())){
			String serialNum=ApplicationUtils.random32UUID(); 

			contractVO.setId(serialNum);


			//给各自的文件字段赋值文件名
			contractVO.setElectronicContract(electronicContract);
			contractVO.setSignContract(signContract);

			contractVO.setCreator(currenLoginName);
			contractService.insertContract(contractVO);
		}else{
			//如果id不为空执行更新
			User user1=(User) request.getSession().getAttribute("userInfo");
			contractVO.setUpdater(currenLoginName);

			//给各自的文件字段赋值文件名
			contractVO.setElectronicContract(electronicContract);
			contractVO.setSignContract(signContract);

			contractService.updateContract(contractVO);
		}


		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/userContract").buildAndExpand(contractVO.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}*/

	/**
	 * @Description (导出采购预测信息)
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/exportPurchaseForecast")
	public void exportPurchaseForecast(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) throws ParseException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
	  List<DemandPlanMateriel> purchaseForecastList=purchaseForecastService.queryPurchaseForecastList("");
		
		if(purchaseForecastList.size()>0){
			for(DemandPlanMateriel demandPlanMateriel:purchaseForecastList){
				Date deliveryDate=demandPlanMateriel.getDeliveryDate();
				Date now=null;
				now=new Date();
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				String nowString=sdf.format(now);
				now=sdf.parse(nowString);
				int daysBeforeDelivery=(int)((deliveryDate.getTime()-now.getTime())/1000/60/60/24);
				demandPlanMateriel.setDaysBeforeDelivery(daysBeforeDelivery+"");
			}
		}
		dataMap.put("purchaseForecastList",purchaseForecastList);
		ExcelUtil.export(request, response, dataMap, "purchaseForecast", "采购预测信息");
	}


	/**
	 * 上传执行
	 * @param file（上传的文件）
	 * @return
	 */
	public String uploadFile(MultipartFile file){
		String filePath = getClasspath()+"uploadAttachFiles/";
		String randomName=UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""); 
		String fileName = fileUp(file, filePath,randomName);
		System.out.println(fileName);
		return fileName;
	}


	/**
	 * 复制文件
	 * @param file (文件对象）
	 * @param filePath （文件路径）
	 * @param fileName   （文件名）
	 * @return
	 */
	public  String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {

			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}

			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");

		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}


	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private  String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = mkdirsmy(dir,realName);
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}


	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public  File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}




	/**获取classpath1
	 * @return
	 */
	public  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}


	/**
	 * 删除采购预测
	 * @param 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deletePurchaseForecast", method = RequestMethod.POST)
	public ResponseEntity<Void> deletePurchaseForecast(@RequestBody String ids) {
		if ("".equals(ids) || ids == null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		purchaseForecastService.deletePurchaseForecast(ids); 
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	/**
	 * 通过id查询合同详情
	 * @param ids（前台所传递的id）
	 * @return
	 */
	/*@RequestMapping(value = "/selectConbtractById", method = RequestMethod.GET)
	public ResponseEntity<ContractVO> selectConbtractById(String ids) {

		ContractVO c=contractService.selectConbtractById(ids);
		return new ResponseEntity<ContractVO>(c, HttpStatus.OK);
	}*/

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	/*@RequestMapping(value = "/editUserContractPage")
	public String editUserContractPage(String id,String view) {
		return "contract/editUserContractPage";
	}*/
	
	
	 /**
     * @Description (下载导入模板)
     * @param request
     * @return
     */
    @RequestMapping("downloadImportTemp")
    public void downloadContractTemp(Map<String, Object> map,HttpServletRequest request,HttpServletResponse response) {
    	ExcelUtil.importTempDownLoad(request, response, "contractImport");
    }
    
    
    /**
     * @Description (合同信息导入)
     * @param request
     * @return
     */
    /*@RequestMapping("contractImport")
    @ResponseBody
    public Map<String,String> contractImport(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest request,HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String, String>();
    	 try {
		     
			ExcelReader excelReader = new ExcelReader(excelFile.getInputStream());
			excelReader.readExcelContent(new RowHandler() {
				@Override
				public void handle(List<Object> row,int i) throws Exception {
					if(!CollectionUtils.isEmpty(row)){
						try{
							ContractVO contract = new ContractVO();
							contract.setId(ApplicationUtils.random32UUID());
							contract.setContractNum(row.get(0).toString());
							contract.setComId(row.get(1).toString());
							contract.setContractType(row.get(2).toString());
							contract.setServiceModel(row.get(3).toString());
							
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
							contract.setSignDate(sdf.parse(row.get(4).toString()));
							contract.setSigner(row.get(5).toString());
							contract.setStartDate(sdf.parse(row.get(6).toString()));
							contract.setEndDate(sdf.parse(row.get(7).toString()));
							contract.setSettlementClause(row.get(8).toString());
							Subject currentUser = SecurityUtils.getSubject();
							String currenLoginName = currentUser.getPrincipal().toString();//获取当前登录用户名
							contract.setCreator(currenLoginName);
							contractService.insertContract(contract);
						}catch(Exception  e){
							throw new Exception("第"+i+"行数据异常请检查，数据内容："+row.toString());
						}
						
					}
					
				}
			}, 2);
			map.put("data", "success");
		} catch (Exception e1) {
			map.put("data", e1.getMessage());
		}
    	
         return map;
    }*/


	/**
	 * 附件下载
	 * @return
	 * @throws IOException 
	 */
	/*@RequestMapping(value = "/resourceDownload", method = RequestMethod.GET)
	     public String upload(HttpSession session, HttpServletRequest request,String name,HttpServletResponse response) {
			 if(session==null)
			  {
			  return "redirect:/";
			  }
			  String dataDirectory = request.getServletContext().getRealPath("/uploadAttachFiles");
			  File file = new File(dataDirectory,"D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif");
			  if(file.exists())
			  {
			   response.setContentType("application/octet-stream");
			   response.addHeader("Content-Disposition","attachment;filename=D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif");
			   byte[] buffer = new byte[1024];
			   FileInputStream fis =null;
			   BufferedInputStream bis =null;
			   try {
			    fis = new FileInputStream(file);
			    bis = new BufferedInputStream(fis);
			    OutputStream os =response.getOutputStream();
			    int i =bis.read(buffer);
			    while (i!=-1) {
			     os.write(buffer, 0, i);
			     i=bis.read(buffer);
			    }
			   } catch (FileNotFoundException e) {
			    e.printStackTrace();
			   } catch (IOException e) {
			    e.printStackTrace();
			   }finally {
			    try {
			     bis.close();
			    } catch (IOException e) {
			     e.printStackTrace();
			    }
			    try {
			     fis.close();
			    } catch (IOException e) {
			     e.printStackTrace();
			    }
			   }

			  }
			  return null;
			 }*/

	@RequestMapping(value="/resourceDownload",method=RequestMethod.POST) //匹配的是href中的download请求
	public void download(@RequestParam("project_id") Integer projectId, HttpServletResponse response) throws IOException {
		/* response.setCharacterEncoding("utf-8");
		        response.setContentType("multipart/form-data");
		        response.setHeader("Content-Disposition", "attachment;fileName=" + "D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif");

		        try {
		            String path = Thread.currentThread().getContextClassLoader()
		                    .getResource("").getPath()//获得项目编译路径（MyEclipse的项目编译路径）
		                    + "download";
		            //D:\My Documents\GitHub\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\zhgj-project\WEB-INF\classes\download
		            InputStream inputStream = new FileInputStream(new File("D:\\userUploadFile\\Files\\" + "D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif"));//path是在本项目webinfo下的classes下的download文件夹下下载文件
		            FileInputStream in=new FileInputStream("D:\\userUploadFile\\Files\\" + "D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif");
		                                                  //若想在本机下载，把path去掉即可

		            OutputStream os = response.getOutputStream();
		            byte[] b = new byte[2048];
		            int length;
		            while ((length = inputStream.read(b)) > 0) {
		                os.write(b, 0, length);
		            }

		             // 这里主要关闭。
		            os.close();

		            inputStream.close();
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		            //  返回值要注意，要不然就出现下面这句错误！
		            //java+getOutputStream() has already been called for this response
		        return null;*/
		/*  try {  
			        // 清空response  
			        response.reset();  
			        //设置输出的格式  
			        response.setContentType("application/x-download");// 设置为下载application/x-download   
			        response.addHeader("content-type ","application/x-msdownload");   
			        response.setContentType("application/octet-stream");  
			        response.setHeader("content-disposition", "attachment; filename="+ "aaaa");//设定输出文件头  
			        response.addHeader("Content-Length", "1024");  
			        // 以流的形式下载文件。  
			        InputStream fis = new BufferedInputStream(new FileInputStream("D:\\userUploadFile\\Files\\" + "D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif"));  
			        ServletOutputStream toClient = response.getOutputStream();  
			        byte[] buffer = new byte[1024];  
			        int n = 0;  
			        while ((n = fis.read(buffer))!=-1) {  
			            toClient.write(buffer,0,n);  
			            toClient.flush();  
			        }  
			        fis.close();  
			        //输出文件  
			        toClient.close();  
			    } catch (Exception ex) {  
			        ex.printStackTrace();  
			    }  */

		/* String path="D:\\userUploadFile\\Files\\" + "D2E2589B23CA4B0EA9035DA9FC7E4BB2.xlsx";  
		        File file=new File(path); 
		        System.out.println(file.length());
		        HttpHeaders headers = new HttpHeaders();    
		        String fileName=new String("你好.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
		        headers.setContentDispositionFormData("attachment", fileName); 
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
		                                          headers, HttpStatus.CREATED); */
		/* String filename = "rules_" + DateFormatUtils.ISO_DATE_FORMAT.format(new Date()) + ".txt";
		        response.setCharacterEncoding("utf-8");
		        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		        response.setHeader("Transfer-Encoding", "chunked");
		        IOUtils.copy(new StringReader(ruleService.export(projectId)), response.getOutputStream(), "utf-8");*/



	}
}
/*  public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename,
		            Model model) throws IOException{

		        String downloadFilePath="D:\\userUploadFile\\Files";//从我们的上传文件夹中去取
		        filename="D2E2589B23CA4B0EA9035DA9FC7E4BB2.gif";
		        File file = new File(downloadFilePath+File.separator+filename);//新建一个文件

		        HttpHeaders headers = new HttpHeaders();//http头信息

		        String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");//设置编码

		        headers.setContentDispositionFormData("attachment", downloadFileName);


		        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);

		    }*/