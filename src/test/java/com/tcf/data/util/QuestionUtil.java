package com.tcf.data.util;

import com.tcf.data.dao.BaseDao;
import com.tcf.data.entity.Question;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Archer Tan
 */
public class QuestionUtil {

    private JsoupUtil util;

    public void setUtil(JsoupUtil util) {
        this.util = util;
    }

    public List<Question> koolearn(String host, String resource, String grade, String subject){
        List<Question> list = new ArrayList<>();
        try {
            Document doc = util.getDocument(host+resource);
            Elements elements = doc.select(".p-results .i-timu");
            for(Element element : elements){
                String content = element.select(".content>div").html();
                String type = element.select(".footer>label:eq(0)").text().split("：")[1];
                String diff = element.select(".footer>label:eq(1)").text().split("：")[1];
                String a = element.select(".divider+a").attr("href");
                String[] contents = content.split("<br>");
                Question question = new Question();
                Map<String,String> map = new HashMap<>();
                for(int i = 0;i<contents.length;i++){
                    String[] items = contents[i].split("\\.");
                    if(i == 0){
                        question.setQuestionTitle(contents[i]);
                    }else if(items.length>1){
                        map.put(items[0],items[1]);
                    }
                }
                question.setOptions(map);
                question.setQuestionType(type);
                question.setDifficulty(diff);
                question.setAuthor("koolearn");
                question.setGrade(grade);
                question.setSubject(subject);
                Document doc2 = util.getDocument(host+a);
                String answer = doc2.select("#i-tab-content div").text();
                question.setAnswer(answer);
                String answerDetail = doc2.select(".i-card .content").text();
                question.setAnswerDetail(answerDetail.replace("[详细]", ""));
                list.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将数据填充到文件中
     * @return
     */
    public void zujuan(){
        /* Map<Integer,String> question_channel_types = new HashMap<>();
        question_channel_types.put(null,"全部");
        question_channel_types.put(1,"单选题");
        question_channel_types.put(3,"判断题");
        question_channel_types.put(4,"填空题");
        question_channel_types.put(10,"语言表达");
        question_channel_types.put(9,"问答题");
        question_channel_types.put(13,"翻译");
        question_channel_types.put(16,"文言文阅读");
        question_channel_types.put(17,"现代文阅读");
        question_channel_types.put(18,"连词成句");
        question_channel_types.put(19,"默写");
        question_channel_types.put(20,"诗歌鉴赏");
        question_channel_types.put(28,"综合题");
        question_channel_types.put(30,"连线题");
        question_channel_types.put(34,"书写");
        question_channel_types.put(32,"写作题");

        Map<Integer,String> difficult_indexs = new HashMap<>();
        difficult_indexs.put(null,"全部");
        difficult_indexs.put(1,"容易");
        difficult_indexs.put(3,"普通");
        difficult_indexs.put(5,"困难");

        Map<Integer,String> kid_nums = new HashMap<>();
        kid_nums.put(null,"全部");
        kid_nums.put(1,"1个知识点");
        kid_nums.put(2,"2个知识点");
        kid_nums.put(3,"多个知识点");

        Map<Integer,String> exam_types = new HashMap<>();
        exam_types.put(null,"全部");
        exam_types.put(1,"小升初真题");
        exam_types.put(2,"常考题");
        exam_types.put(7,"模拟题");

        Map<Integer,String> grade_ids = new HashMap<>();
        grade_ids.put(1, "一年级");
        grade_ids.put(2, "二年级");
        grade_ids.put(3, "三年级");
        grade_ids.put(4, "四年级");
        grade_ids.put(5, "五年级");
        grade_ids.put(6, "六年级");
        grade_ids.put(7, "七年级");
        grade_ids.put(8, "八年级");
        grade_ids.put(9, "九年级");
        grade_ids.put(10, "高一");
        grade_ids.put(11, "高二");
        grade_ids.put(12, "高三");
        grade_ids.put(13, "选修");*/
        String text = "";
        for(int i = 21060;text != null;i++){
            if(text != null){
                String url = "https://www.zujuan.com/question/list?knowledges=&question_channel_type=&difficult_index=&exam_type=&kid_num=&grade_id[0]=0&grade_id[1]=1&grade_id[2]=2&grade_id[3]=3&grade_id[4]=4&grade_id[5]=5&grade_id[6]=6&grade_id[7]=7&grade_id[8]=8&grade_id[9]=9&grade_id[10]=10&grade_id[11]=11&grade_id[12]=12&grade_id[13]=13&sortField=time&page="+i+"&_=1531993608089&per-page=10";
                System.out.println(i);
                FileWriter fw = null;
                try{
                    text = HttpUtil.doGet(url);
                    fw = new FileWriter("E:\\temp\\json\\question-"+i+".json");
                    fw.write(text);
                }catch (Exception e){
                    e.printStackTrace();
                    i--;
                }finally {
                    try {
                        if(fw != null) fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 获得结果，重复Integer.MAX_VALUE次，直到获得结果
     * @param id
     * @return
     * @throws IOException
     */
    public String zujuanAnswer(int id) throws IOException {
        return zujuanAnswer(id, Integer.MAX_VALUE);
    }

    /**
     * 获得结果
     * @param id 题目id
     * @param times 重复次数
     * @return
     * @throws IOException
     */
    public String zujuanAnswer(int id,int times) throws IOException {
        String answer = null;
        int cycle = 0;
        do{
            try{
                Document doc = util.getDocument("https://www.zujuan.com/question/detail-"+id+".shtml");
                //System.out.println(doc);
                Elements list = doc.select("script");
                for(int i = 0;i<list.size();i++){
                    String text = list.get(i).html();
                    //System.out.printf("%s:%s",i,text);
                    if(text.indexOf("MockDataTestPaper") != -1){
                        String line = text.split("\n")[1];
                        answer = line.substring(line.indexOf("=")+1,line.lastIndexOf(";"));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            cycle++;
        }while(cycle<=times && answer == null);
        return answer;
    }

    /**
     * 将id保存到文本文件中
     * @param size
     * @throws SQLException
     */
    public void getAllIds(int size) throws SQLException {
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.executeQuery("select question_id from zujuanquestion");
        int i = 1;
        int index = 0;
        while(rs.next()){
            if(i%size == 0){
                index++;
            }
            String id = rs.getString("question_id");
            FileReaderUtil.appendText("E:\\temp\\ids\\ids-"+index+".txt", id+"\r");
            i++;
        }
    }
}
