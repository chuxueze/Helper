package crs.tq.wl.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	private static final String REQ_METHOD_POST = "POST";
	private static final String REQ_METHOD_PUT = "PUT";
	private static final String REQ_METHOD_DELETE = "DELETE";
	private static final String ENCODE="utf-8";
	/** 
     * 向指定URL发送GET方法的请求 
     * @param url 发送请求的URL 
     * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。 
     * @return URL所代表远程资源的响应 
     */  
    public static String sendGet(String url, String param) {  
		StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url ;
            if(param != null && param != ""){
            	urlNameString = url + (url.indexOf("?") > 0 ? "&" : "?") + param;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),ENCODE));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            result.append("发送GET请求出现异常");
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }
    
    /**  
     * 向指定URL发送POST方法的请求  
     *   
     * @param url  
     *            发送请求的URL  
     * @param param  
     *            请求参数，请求参数应该是name1=value1&name2=value2的形式。  
     * @return URL所代表远程资源的响应  
     */  
    public static String sendPost(String url, String param) {  
    	StringBuilder result = new StringBuilder();
        PrintWriter out = null;  
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应 
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),ENCODE));  
            String line;  
            while ((line = in.readLine()) != null) {  
            	result.append(line);
            }  
        } catch (Exception e) {  
            System.out.println("发送POST请求出现异常！" + e);  
            result = new StringBuilder("");
            e.printStackTrace();
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
            	result = new StringBuilder("");
                ex.printStackTrace();  
            }  
        }  
        return result.toString();  
    }
    
    public static String sendPostJson(String surl, String json, String reqMethod){
		URL url;
		StringBuilder sb;
		DataOutputStream out;
		BufferedReader reader;
		HttpURLConnection conn;
		try {
			url = new URL(surl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestMethod(reqMethod);// 提交模式
			conn.setRequestProperty("Content-Length", json.getBytes().length + "");
			conn.setConnectTimeout(100000);// 连接超时单位毫秒 //
			conn.setReadTimeout(200000);// 读取超时 单位毫秒
			conn.setDoOutput(true);// 是否输入参数
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			
			out = new DataOutputStream(conn.getOutputStream());
			out.write(json.getBytes());
			out.flush();
			out.close();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),ENCODE));
			sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			conn.disconnect();
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    public static String sendPostByJson(String surl, String json){
    	return sendPostJson(surl, json, REQ_METHOD_POST);
    }

    public static String sendPutByJson(String surl, String json){
    	return sendPostJson(surl, json, REQ_METHOD_PUT);
    }
    
    public static String sendDelete(String surl){
    	return sendPostJson(surl, null, REQ_METHOD_DELETE);
    }
}
