package tool;
/**
 * 用于爬取豆瓣网站中关于计算机类的书名，书id，书封面图（链接），书页面链接，书简介
 *
 */
import database.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BookCrawler {
    //爬取的网址
   //https://read.douban.com/category?page=1&kind=105
    /**爬取书籍的信息
     * @param type  爬取网站的分类
     * @param pages 爬取网站的页数
     */
    public ArrayList<Book> getBooks(String type,int pages) throws IOException {
        ArrayList<Book> books = new ArrayList<>();
        for(int index = 0;index < pages;index++){
            String url = jointUrl(type,index);
            Document doc = Jsoup.connect(url).get();
            doc.outputSettings().charset("UTF-8");
            Elements elements = doc.select(".clearfix .article #subject_list .subject-list");
            if(elements.size()==0){
                continue;
            }
            System.out.println("elements length: "+elements.size());
            for(Element element: elements){
                String bookUrl = element.select(".subject-item .pic .nbg").attr("href");
                if(bookUrl==null){break;}
                String id = getId(bookUrl);
                String name = element.select(".subject-item .info h2>a").attr("title");
                String coverUrl = element.select(".subject-item .pic .nbg img").attr("src");
                String author = element.select(".subject-item .info .pub").text();
                String info = element.select(".subject-item .info>p").text();
                Book book = new Book(id,name,author,bookUrl,info,coverUrl);
                System.out.println(new StringBuilder("book: ").append(book.toString()));
                books.add(book);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return books;
    }

    private String jointUrl(String type,int index){
        StringBuffer buffer = new StringBuffer("https://book.douban.com/tag/");
        buffer.append(type).append("?start=").append(index).append("&type=T");
        return new String(buffer);

    }
    //从bookUrl中提取出书的id
    //https://book.douban.com/subject/25985021/ 截取数字为书的id
    private String getId(String bookUrl){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(bookUrl);
        return m.replaceAll("").trim();
    }



}
