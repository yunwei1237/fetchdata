package com.tcf.data;

import com.tcf.data.util.Executable;
import com.tcf.data.util.FileReaderUtil;
import com.tcf.data.util.QuestionUtil;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author Archer Tan
 */
public class FetchAnswerData implements Executable {
    private File from;
    private File to;
    private QuestionUtil util;
    @Override
    public boolean execute() {
        return false;
    }

    /*@Override
    public void setData(Object obj) {
        if(obj == null){
            throw new IllegalArgumentException("data is null");
        }
        if(obj instanceof  URL){
            this.url = (URL)obj;
        }else if(obj instanceof  String){
            try {
                this.url = new URL((String)obj);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException("data is wrong format");
    }*/
    @Override
    public void setData(Object obj) {
        if(obj == null){
            throw new IllegalArgumentException("data is null");
        }
        if(obj instanceof  File){
            this.from = (File) obj;
        }else if(obj instanceof  String){
            this.from = new File((String)obj);
        }
        throw new IllegalArgumentException("data is wrong format");
    }

    public boolean fetch(){
        if(util == null){
            throw new IllegalArgumentException("util is null");
        }
        if(from == null)
            throw new IllegalArgumentException("from is null");
        if(to == null)
            throw new IllegalArgumentException("to is null");
        if(!to.isDirectory()){
            throw new IllegalArgumentException("to is'nt a directory");
        }
        if(!to.exists()){
            to.mkdirs();
        }
        /*int id = Integer.parseInt(line);
        String answer = util.zujuanAnswer(id);
        if(answer != null){
            FileReaderUtil.appendText(to.getPath()+"\\"+line+".json", answer);
            System.out.println("完成数据："+line+".json");
            return true;
        }*/
        return false;
    }
}
