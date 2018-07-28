package com.tcf.data.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author Archer Tan
 */
public class GetAnswerThread extends Thread {
    private QuestionUtil util;
    private int start;
    private int end;

    public GetAnswerThread(int start, int end,JsoupUtil util) {
        this.start = start;
        this.end = end;
        this.util = new QuestionUtil();
        this.util.setUtil(util);
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            String path = "E:\\temp\\ids\\ids-"+i+".txt";
            new File("E:\\temp\\answers\\"+i).mkdir();
            try {
                List<String> lines = IOUtils.readLines(new FileReader(path));
                for(String line:lines){
                    try {
                        int id = Integer.parseInt(line);
                        String answer = util.zujuanAnswer(id);
                        FileReaderUtil.appendText("E:\\temp\\answers\\"+i+"\\"+line+".json", answer);
                        System.out.println("完成数据："+line+".json");
                    }catch (Exception e){
                        e.printStackTrace();
                        FileReaderUtil.appendText("E:\\temp\\idsError.txt", line+"\r");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                FileReaderUtil.appendText("E:\\temp\\fileError.txt", path+"\r");
            }
        }
    }
}
