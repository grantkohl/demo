package demo.json;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import net.sf.json.util.JSONTokener;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonDemo {

    public static void main(String[] args) {
        //String json = "{\"a\":\"1\",\"b\":\{\"abc\"}";

        String json = "{\"a\":\"1\",\"b\":{\"c\":\"2\",\"d\":\"[3,4]\"}}";

        JsonDemo demo = new JsonDemo();
        JSONObject jsonObject=demo.transform(json);

    }

    private JSONObject transform(String json){

        JSONObject jsonObject=new JSONObject();

        JSONObject jsonObjectAfter=new JSONObject();

        try{
            jsonObject = JSONObject.parseObject(json,JSONObject.class, Feature.OrderedField);
            //Object json = new JSONTokener(jsonResponse).nextValue();
            //Set<String> keys=jsonObject.keySet();
            return recursion(jsonObject,jsonObjectAfter,new String(""));

        }catch (JSONException e){
            jsonObjectAfter=new JSONObject();
            jsonObjectAfter.put("code", 0);
            jsonObjectAfter.put("msg", "json格式有误,转换失败");
            return jsonObjectAfter;
        } catch (Exception e){
            jsonObjectAfter=new JSONObject();
            jsonObjectAfter.put("code", 0);
            jsonObjectAfter.put("msg", "json转换失败");
            e.printStackTrace();
            return jsonObjectAfter;

        }
    }

    /**
     * 递归 将多层嵌套json 转化为 一层json
     * 例如 {"a":"1","b":{"c":"2","d":"[3,4]"}}==>  {"a":"1","b.c":"2","b.d":"[3,4]"}
     * @param jsonObject dest
     * @param jsonObjectAfter target
     * @param str path
     */
    private JSONObject recursion(JSONObject jsonObject,JSONObject jsonObjectAfter,String str){

        Set<Map.Entry<String, Object>> jsonEntrys=jsonObject.entrySet();

        Iterator<Map.Entry<String, Object>> iterator = jsonEntrys.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();

            if(entry.getValue() instanceof JSONObject){
                JSONObject jsonObj = (JSONObject)entry.getValue();
                if(str.length()==0){
                    str+=entry.getKey();
                }else{
                    str+='.'+entry.getKey();

                }
                recursion(jsonObj,jsonObjectAfter,str);
            }else{
                String sb=str;
                if(sb.length()==0){
                    sb+=entry.getKey();
                }else{
                    sb+="."+entry.getKey();

                }
                jsonObjectAfter.put(sb.toString(),entry.getValue());
                //3.set集合元素的删除
                iterator.remove();
            }
        }
        return jsonObjectAfter;
    }
}
