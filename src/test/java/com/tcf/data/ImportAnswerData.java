package com.tcf.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcf.data.dao.BaseDao;
import com.tcf.data.util.Executable;
import com.tcf.data.util.FileReaderUtil;

import java.io.File;

/**
 * @author Archer Tan
 */
public class ImportAnswerData implements Executable {
    private File file;
    @Override
    public boolean execute() {
        return insert();
    }

    @Override
    public void setData(Object obj) {
        if(obj == null){
            throw new IllegalArgumentException("data is null");
        }
        if(obj instanceof  File){
            this.file = (File) obj;
        }else if(obj instanceof  String){
            this.file = new File((String)obj);
        }
        throw new IllegalArgumentException(String.format("data [%s] is wrong format",obj));
    }

    public JSONObject getJsonObject(String path){
        String text = FileReaderUtil.readText(path);
        JSONArray arr = JSON.parseArray(text);
        return (JSONObject) arr.getJSONObject(0).getJSONArray("questions").get(0);
    }
    public boolean insert(){
        BaseDao dao = new BaseDao();
        JSONObject obj = getJsonObject(this.file.getPath());
        String question_id = obj.getString("question_id");
        String answer = obj.getString("answer");
        String explanation = obj.getString("explanation");
        String sql = "insert into zujuanAnswer(question_id,answer,explanation) values(?,?,?)";
        return dao.executeNonQuery(sql,question_id,answer,explanation)>0;
    }
}
