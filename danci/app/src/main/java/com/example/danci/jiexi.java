package com.example.danci;

import org.json.JSONArray;
import org.json.JSONObject;

public class jiexi {
    static String wang;
    static String word;
    static String query;
    public static void parsexml(String data){
        word="";
        wang="网络释义：\n";
        try{
            JSONArray jsonArray=new JSONArray("["+data+"]");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                query=jsonObject.getString("query");
                if(jsonObject.has("basic")){
                    JSONObject basic=jsonObject.getJSONObject("basic");
                    if(basic.has("explains")){
                        word="传统释义："+basic.getString("explains").trim();
                    }
                }
                if(jsonObject.has("web")){
                    String webString=jsonObject.getString("web");
                    JSONArray web=new JSONArray("["+webString+"]");
                    JSONArray webArray=web.getJSONArray(0);
                    int j=0;
                    while(!webArray.isNull(j)){
                        if(webArray.getJSONObject(j).has("key")){
                            wang=wang+webArray.getJSONObject(j).getString("key");

                        }
                        if(webArray.getJSONObject(j).has("value")){
                            wang=wang+webArray.getJSONObject(j).getString("value")+"\n";
                        }
                        j++;
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
