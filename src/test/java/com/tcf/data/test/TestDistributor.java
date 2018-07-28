package com.tcf.data.test;

import com.tcf.data.ImportAnswerData;
import com.tcf.data.util.TaskDistributor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Archer Tan
 */
public class TestDistributor {
    public static void main(String[] args) {
        List<Object> datas = new ArrayList<>();
        getFiles(datas, new File("E:\\temp\\answers"));
        ImportAnswerData exe = new ImportAnswerData();
        TaskDistributor td = new TaskDistributor(exe,datas);
        td.setSize(30);
        td.distribute();
    }


    public static void getFiles(List datas,File file){
        if(file.isDirectory()){
            for(File subFile : file.listFiles()){
                getFiles(datas, subFile);
            }
        }else{
            datas.add(file);
        }
    }
}
