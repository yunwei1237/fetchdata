package com.tcf.data.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Archer Tan
 */
public class JsoupUtil {
    private Map<String,String> cookies = new HashMap<>();
    private Map<String,String> headers = new HashMap<>();

    public JsoupUtil() {
    }

    public JsoupUtil(Map<String,String> headers, Map<String,String> cookies) {
        this.cookies = cookies;
        this.headers = headers;
    }

    /**
     * 获得连接
     * @param url
     * @return
     */
    public Connection getConnection(String url){
        Connection conn = Jsoup.connect(url);
        conn.headers(this.headers);
        conn.cookies(this.cookies);
        return conn;
    }

    /**
     * 通过get方式获得文档对象
     * @param url
     * @return
     * @throws IOException
     */
    public Document getDocument(String url) throws IOException {
        return getConnection(url).get();
    }

    /**
     * 通过post方式获得文档对象
     * @param url
     * @return
     * @throws IOException
     */
    public Document getDocumentByPost(String url) throws IOException {
        return getConnection(url).post();
    }

    public void addHeader(String key,String val){
        this.headers.put(key, val);
    }
    public void addHeaders(Map<String,String> headers){
        this.headers.putAll(headers);
    }
    public void addCookie(String key,String val){
        this.cookies.put(key, val);
    }
    public void cookies(Map<String,String> cookies){
        this.cookies.putAll(cookies);
    }

    public static JsoupUtil getJsoupUtil(){
        JsoupUtil util = new JsoupUtil();
//        util.addHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
//        util.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//
//        util.addCookie("PHPSESSID", "2001d4sprsg0da65c0gt86cur1");
//        util.addCookie("_csrf", "2b88f2c9e858eb55c23bfc7256305c3dfec386bee6d0fec8865055f9e5125966a%3A2%3A%7Bi%3A0%3Bs%3A5%3A%22_csrf%22%3Bi%3A1%3Bs%3A32%3A%22xgTeBfmp8Kogc4VX2pnvr9ZYGdNo3a5I%22%3B%7D");
//        util.addCookie("_identity", "ee9faa83034ca1ca6354cda0e55c0f50a40593f330efa73911eaf4355e56d700a%3A2%3A%7Bi%3A0%3Bs%3A9%3A%22_identity%22%3Bi%3A1%3Bs%3A50%3A%22%5B1425860%2C%22d471a077b38dafe8798753b94ad70342%22%2C86400%5D%22%3B%7D");
//        util.addCookie("_sync_login_identity", "e1ceff1a6272fc119b23648e5ad92c7e5e52c0fc67593b26351bcaed977af3e2a%3A2%3A%7Bi%3A0%3Bs%3A20%3A%22_sync_login_identity%22%3Bi%3A1%3Bs%3A50%3A%22%5B1425860%2C%22-PnCdd1uKW_mAYs--5YSTCPjUvVLZKpT%22%2C86400%5D%22%3B%7D");
//        util.addCookie("Hm_lvt_6de0a5b2c05e49d1c850edca0c13051f", "1531976771,1532066721,1532315728,1532392680");
//        util.addCookie("Hm_lpvt_6de0a5b2c05e49d1c850edca0c13051f", "1532392686");
//        util.addCookie("_ga", "GA1.2.1413200553.1531735142");
//        util.addCookie("_gid", "GA1.2.1034370946.1532315728");
//        util.addCookie("_gat_gtag_UA_112991577_1", "1");
//        util.addCookie("chid", "753411127a26c0bf4f88c5bb0c64e771512616316bae9de43cb9a9038d6b13ffa%3A2%3A%7Bi%3A0%3Bs%3A4%3A%22chid%22%3Bi%3A1%3Bs%3A1%3A%223%22%3B%7D");
//        util.addCookie("xd", "302c76d9e27c6fb0e1f815bdf637ae7f9ec27997dd7c18c9fcf7c68da09ff5c8a%3A2%3A%7Bi%3A0%3Bs%3A2%3A%22xd%22%3Bi%3A1%3Bs%3A1%3A%222%22%3B%7D");
        return util;
    }
}
