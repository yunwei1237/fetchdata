package com.tcf.data.test;

import com.tcf.data.util.GetAnswerThread;
import com.tcf.data.util.JsoupUtil;
import com.tcf.data.util.QuestionUtil;
import org.junit.Test;

/**
 * @author Archer Tan
 */
public class TestUtil {
    @Test
    public void test(){
        QuestionUtil qu = new QuestionUtil();
        qu.setUtil(JsoupUtil.getJsoupUtil());
         /* List list = new ArrayList();
      for(int i = 1;i<=10;i++){
            list.addAll(qu.koolearn("http://www.koolearn.com","/shiti/list-1-3-0-"+i+".html","初中","英语"));
        }
        System.out.println(list.size());
        String json = JSONArray.toJSONString(list);
        System.out.println(json);*/




       //qu.zujuan("https://www.zujuan.com", "/question/list?knowledges=&question_channel_type=&difficult_index=&exam_type=&kid_num=&grade_id[0]=0&grade_id[1]=1&grade_id[2]=2&grade_id[3]=3&grade_id[4]=4&grade_id[5]=5&grade_id[6]&grade_id[7]&grade_id[8]&grade_id[9]&grade_id[10]&grade_id[11]&grade_id[12]&grade_id[13]=6&sortField=time&page=2&_=1531993608089&per-page=10");

//        try {
//            String answer = qu.zujuanAnswer(806115);
//            System.out.println(answer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        /*try {
            qu.getAllIds(100);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {

        int size = 10;
        int count = 2100;
        int page = count/size;
        for(int i = 1;i<=page;i++){
            new GetAnswerThread((i-1)*size,(i-1)*size+size,JsoupUtil.getJsoupUtil()).start();
        }
        if(page*size<count){
            new GetAnswerThread(page*size,count,JsoupUtil.getJsoupUtil()).start();
        }
    }
}
