package com.congmai.zhgj.core.util;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/wmsAPI.properties")
public class HttpClientUtil  implements EnvironmentAware{
	
	@Autowired
	static   
	Environment env;
	
	/**
	 * 发送 post请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
	public static String sendHttpPost(String httpUrl, String body) {
		HttpPost httpPost = new HttpPost(env.getProperty("apiUrl")+httpUrl);// 创建httpPost
		httpPost.setHeader("X-Token", env.getProperty("X-Token"));
		httpPost.setHeader("Content-Type", "application/json");
//		// 创建参数队列
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		for (String key : maps.keySet()) {
//			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
//		}
		try {
			httpPost.setEntity(new StringEntity(body,"UTF-8"));
//			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	
	/**
	 * 发送 get请求
	 * 
	 * @param httpUrl
	 *            地址
	 * @param maps
	 *            参数
	 */
	public static String sendHttpGet(String httpUrl) {
		HttpGet httpGet = new HttpGet(env.getProperty("apiUrl")+httpUrl);// 创建httpGet
		httpGet.setHeader("X-Token", env.getProperty("X-Token"));
		return sendHttpGet(httpGet);
	}
	
	private static RequestConfig requestConfig = RequestConfig.custom()
			.setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();

	public static String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);

			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}


	public static String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	@Override
	public void setEnvironment(Environment environment) {
		HttpClientUtil.env=environment;
	}

}
