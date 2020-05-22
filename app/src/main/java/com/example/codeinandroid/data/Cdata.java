package com.example.codeinandroid.data;

import android.content.Context;

import com.example.codeinandroid.model.DataModel;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Cdata {
    private Multimap<String, Object> data;
    private Context context;
    private ArrayList<DataModel> mData;

    public Cdata(Context context) {
        this.context = context;
        data = ArrayListMultimap.create();
        mData = new ArrayList<>();
    }

    private void getData(String topic, String lang) {
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);
            JSONObject fullData = new JSONObject(json);
            JSONArray array = fullData.getJSONArray(lang);
            JSONObject obj = new JSONObject();
            JSONArray keys = new JSONArray();
            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);
                if (obj.getString("topic").equals(topic)) {
                    keys = obj.names();
                    break;
                }
            }

            assert keys != null;
            for (int i = 0; i < keys.length(); i++) {
                String type = String.valueOf(keys.get(i));
                String value = String.valueOf(obj.get(String.valueOf(keys.get(i))));
                data.put(type, value);

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    private void addTable(String tableName) {
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(tableName);
            ArrayList<String> column1 = new ArrayList<>();
            ArrayList<String> column2 = new ArrayList<>();
            ArrayList<String> column3 = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                JSONObject obj2;
                if (obj.has("column1")) {
                    obj2 = (JSONObject) obj.get("column1");
                    for (int j = 1; j < obj2.length() + 1; j++) {
                        column1.add(String.valueOf(obj2.get("value" + j)));
                    }
                }
                if (obj.has("column2")) {
                    obj2 = (JSONObject) obj.get("column2");
                    for (int j = 1; j < obj2.length() + 1; j++) {
                        column2.add(String.valueOf(obj2.get("value" + j)));
                    }
                }
                if (obj.has("column3")) {
                    obj2 = (JSONObject) obj.get("column3");
                    for (int j = 1; j < obj2.length() + 1; j++) {
                        column3.add(String.valueOf(obj2.get("value" + j)));
                    }
                }
            }
            for (int i = 0; i < column1.size(); i++) {
                mData.add(new DataModel("TABLE", column1.get(i), column2.get(i), column3.get(i)));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DataModel> create(String topic, String lang) {
        getData(topic, lang);
        int j = 1;
        for (String i : data.keySet()) {
            String title = "title" + j;
            String exp = "exp" + j;
            String code_exec = "code_exec" + j;
            String code_not_exec = "code_not_exec" + j;
            String image = "image" + j;
            String table = "table" + j;

            String str = String.valueOf(data.get(i));
            str = str.substring(1, str.length() - 1);

            if (i.equals(title)) {
                mData.add(new DataModel("TITLE", str));
            } else if (i.equals(exp)) {
                mData.add(new DataModel("EXP", str));
            } else if (i.equals(code_exec)) {
                mData.add(new DataModel("CODE_EXEC", str));
            } else if (i.equals(code_not_exec)) {
                mData.add(new DataModel("CODE_NOT_EXEC", str));
            } else if (i.equals(image)) {
                mData.add(new DataModel("IMAGE", Integer.parseInt(str)));
            } else if (i.equals(table)) {
                addTable(str);
            }
            j++;
        }
        return mData;
    }
}