package com.turath.control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Similarite {
	
    private float synSim;
    private float semSim;

    public Similarite(float synSim, float semSim)
    {
    	this.semSim= semSim;
    	this.synSim= synSim;
    }
    
	public float getSynSim() {
		return synSim;
	}

	public void setSynSim(float synSim) {
		this.synSim = synSim;
	}

	public float getSemSim() {
		return semSim;
	}


	public void setSemSim(float semSim) {
		this.semSim = semSim;
	}


	public void synSimilarity(String motCle, String mot)
	{
        HttpURLConnection conn = null;
        DataOutputStream os = null;
        try{
        	
            URL url = new URL("http://127.0.0.1:5000/syn/"); //important to add the trailing slash after add
            String[] inputData = {"{\"mot\":\""+motCle+"\", \"motCle\":\""+mot+"\"}"};
            for(String input: inputData){
                byte[] postData = input.getBytes(StandardCharsets.UTF_8);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty( "charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
                os = new DataOutputStream(conn.getOutputStream());
                os.write(postData);
                os.flush();

                if (conn.getResponseCode() != 200) 
                {
                    throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                
                String output;
                String json="";
                while ((output = br.readLine() )!= null) {	              	
                    json= json+output;                 
                }
                System.out.println(mot+": "+json); 
               
                try {
 
				JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(json);
                if (element.isJsonObject()) {
                    JsonObject sim = element.getAsJsonObject();
                    this.synSim= sim.get("syn").getAsFloat();
                }
               
               } catch(Exception e) {e.printStackTrace();}

                conn.disconnect();
            }
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }catch (IOException e){
        e.printStackTrace();
    }finally
        {
            if(conn != null)
            {
                conn.disconnect();
            }
        }
        
	}    
        public void semSimilarity(String motCle, String mot)
    	{
            HttpURLConnection conn = null;
            DataOutputStream os = null;
            try{
            	
                URL url = new URL("http://127.0.0.1:5000/sem/"); //important to add the trailing slash after add
                String[] inputData = {"{\"mot\":\""+motCle+"\", \"motCle\":\""+mot+"\"}"};
                for(String input: inputData){
                    byte[] postData = input.getBytes(StandardCharsets.UTF_8);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty( "charset", "utf-8");
                    conn.setRequestProperty("Content-Length", Integer.toString(input.length()));
                    os = new DataOutputStream(conn.getOutputStream());
                    os.write(postData);
                    os.flush();

                    if (conn.getResponseCode() != 200) 
                    {
                        throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                    
                    String output;
                    String json="";
                    while ((output = br.readLine() )!= null) {	              	
                        json= json+output;                 
                    }
                    System.out.println(mot+": "+json); 
                   
                    try {
     
    				JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(json);
                    if (element.isJsonObject()) {
                        JsonObject sim = element.getAsJsonObject();
                        this.semSim= sim.get("sem").getAsFloat();
                    }
                   
                   } catch(Exception e) {e.printStackTrace();}

                    conn.disconnect();
                }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally
            {
                if(conn != null)
                {
                    conn.disconnect();
                }
            }
    
	}

}
