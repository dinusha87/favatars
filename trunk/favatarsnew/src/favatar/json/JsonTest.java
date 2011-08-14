package favatar.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class JsonTest {
	String serverUrl="http://rdeshapriya.com/favatars/webService.php";
	
	public Object encodeData(HashMap Data)
	{
		 Gson gson = new Gson();
		  
		  //convert java object to JSON format
		  String json = gson.toJson(Data);
	 
		  System.out.println(json);
		  
	
		return json;
		
	}
	// with only the action
	public HashMap postRequest(String action)
	{
		HashMap data1= new HashMap();
		try {
		 String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode(action, "UTF-8");
		 
		    URL url = new URL(serverUrl);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();
		    StringBuffer answer = new StringBuffer();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line="";
		    while ((line = rd.readLine()) != null) {
		       answer.append(line);   
		    }
		    
		    wr.close();
		    rd.close();
		    
		    Gson gson = new Gson();
		    Type type =new TypeToken<Map<String, String>>(){}.getType();

		    	 
		    data1=gson.fromJson(answer.toString(),type);
		    return data1;
		    
		}catch (Exception e) {
			data1.put("message", "Error");
			return data1;
		}
	}
	// with action and data
	public HashMap postRequest(String action,Object obj)
	{
		HashMap data1= new HashMap();
		try {
			
		    // Construct data
		    String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode(action, "UTF-8");
		    data += "&" + URLEncoder.encode("data", "UTF-8") + "=" + URLEncoder.encode(obj.toString(), "UTF-8");
            
		    
		    // Send data
		    URL url = new URL(serverUrl);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();
		    StringBuffer answer = new StringBuffer();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line="";
		    while ((line = rd.readLine()) != null) {
		       answer.append(line);   
		    }
		    
		    
		    
		    wr.close();
		    rd.close();
		   
		    Gson gson = new Gson();
		    Type type =new TypeToken<Map<String, String>>(){}.getType();

		    	 
		   data1=gson.fromJson(answer.toString(),type);
		   return data1;
		    
		    
		} catch (Exception e) {
			data1.put("message", "Error");
			return data1;
		}
	}

}
