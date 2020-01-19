package com.jeffrey.learn.base.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jeffrey
 * @date 1/17/20 9:51 AM
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String, Object> sub = new HashMap<>(2);
        sub.put("key1", "value1");
        sub.put("key2", "value2");

        Map<String, Object> parent = new HashMap<>(1);
        parent.put("sub", sub);

        System.out.println(parent.toString());
        System.out.println(JSONObject.toJSONString(parent));
        System.out.println(JSON.toJSONString(parent));

        JSONObject sub_json = new JSONObject();
        sub_json.put("key1", "value1");
        sub_json.put("key2", "value2");

        JSONObject parent_json = new JSONObject();
        parent_json.put("sub", sub_json);
        System.out.println(parent_json.toJSONString());

        JSONObject object1 = new JSONObject();
        List<String> list1 = new ArrayList<>();
        list1.add("value1");
        list1.add("value2");
        object1.put("key1", list1);
        System.out.println(object1.toJSONString());

        JSONObject object2 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("value1");
        jsonArray.add("value2");
        object2.put("key1", jsonArray);
        System.out.println(object2.toJSONString());
    }
}
