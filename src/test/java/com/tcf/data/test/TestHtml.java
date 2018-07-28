package com.tcf.data.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Archer Tan
 */
public class TestHtml {
   /* @Test*/
    public void test(){
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        Elements list = doc.select("p");
        for(Element element:list){
            System.out.println(element.html());
        }
    }
    @Test
    public void test2(){
        Connection conn = null;
        Document doc = null;
        try {
            conn =Jsoup.connect("http://www.koolearn.com/shiti/list-1-3-0-1.html");

//            conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//            conn.header("Accept-Encoding", "gzip, deflate");
//            conn.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
//            conn.header("Cache-Control", "max-age=0");
//            conn.header("Connection", "keep-alive");
//            conn.header("Host", "www.koolearn.com");
//            conn.header("Referer", "http://www.koolearn.com/shiti/list-1-3-0-1.html");
//            conn.header("Upgrade-Insecure-Requests", "1");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

            conn.cookie("__jsluid", "9e0ba71c91e5e82fa8ee652c7ea1f844");
//            conn.cookie("_ga", "GA1.2.895785597.1530605617");
            conn.cookie("__jsl_clearance", "1531718304.374|0|pxQBRLMLuMjVhx1ecN5mbFpATvo%3D");
            //conn.cookie("_csrf", "659338db1b3c033c71f89e858bef7964368b0ac2e1afef971a6809ff1888a318a%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%220-tcav-KxSmZ41KGwykt1HNX_9unGy4Y%22%3B%7D");
//            conn.cookie("php-webapp-flow", "6aa52c435ffaccf916c2fe3ff575a180");
//            conn.cookie("Hm_lvt_5023f5fc98cfb5712c364bb50b12e50e", "1530756650,1531388371,1531470923,1531718302");
//            conn.cookie("Hm_lpvt_5023f5fc98cfb5712c364bb50b12e50e", "1531718302");
//            conn.cookie("JSESSIONID", "D1CE0BC4E097D488D2A9FB1A87A15FDC");
//            conn.cookie("class-webapp-lmsclass", "31a35df57b0c1c3a8bfebd03cc0bd239");
//            conn.cookie("_gid", "GA1.2.1405026343.1531718302");
//            conn.cookie("_gat", "1");
//            conn.cookie("wwwad", "true");
            doc = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements list = doc.select(".p-results .i-timu");
        for(Element element : list){
            String content = element.select(".content>div").html();
            String type = element.select(".footer>label:eq(0)").text().split("：")[1];
            String diff = element.select(".footer>label:eq(1)").text().split("：")[1];
            System.out.println("content:"+content);
            System.out.println("type"+type);
            System.out.println("diff"+diff);
            System.out.println("----------------------------------------------------------");
        }
    }
}
