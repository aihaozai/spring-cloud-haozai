package spring.cloud.base.fund.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * @author haozai
 * @date 2021/5/7 10:20
 */
public class DocumentUtil {
    /**
     * 设置连接超时时间，单位ms
     */
    private final static int TIME  = 10000;

    public static Document getDocument(String url) {
        try {
            Connection connection =  Jsoup.connect(url);
            connection.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.header("Accept-Encoding", "gzip, deflate, sdch");
            connection.header("Accept-Language", "zh-CN,zh;q=0.8");
            connection.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            connection.header("Cache-Control","no-cache");
            connection.header("Pragma","no-cache");
            connection.header("Content-Type","application/x-www-form-urlencoded");
            connection.header("Connection","keep-alive");
            return connection.timeout(TIME).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
