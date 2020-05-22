package Scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Crawler {

    private List<Content> pivotList;

    DBconnection connect = new DBconnection();
    public Crawler(List <Content> pivotList) {
        this.pivotList=pivotList;


    }


    public void Crawling() throws IOException {

        //List<Content> New = new ArrayList<>();
        for (int i=0; i < pivotList.size();i++) {
            if(pivotList.get(i).getVisited())
            {
                continue;
            }
            pivotList.get(i).setVisited(true);
            Document D;
            // Connecting to a URL
            D = Jsoup.connect(pivotList.get(i).getLink()).header("Accept-Language", "en").get();

            // Getting the links in it
            Elements links = D.select("a[href]");


            for (Element link : links) {


                pivotList.add(new Content(link.attr("href")));
                searchContent(link.attr("href"),pivotList);

            }
           // pivotList.addAll(New);
        }

    }


    public void searchContent(String URL,List<Content> urls) throws IOException {
        List<Content> pages= new ArrayList<>();
        //List<Pivot> pivots= this.Crawling();


            Document doc;
            // Connecting to a URL
            try {
                doc = Jsoup.connect(URL).get();
                String title = doc.title();
                String content = doc.body().text();
                int index = urls.size()-1;
                urls.get(index).setTitle(title);
                urls.get(index).setContent(content);

             // System.out.println(urls.get(index).getLink());
              //System.out.println(urls.get(index).getContent());
             // System.out.println(urls.get(index).getTitle());
                if(isVisited(connect.getUrls(),urls.get(index).getLink())==false) {
                    connect.setURLcontent(urls.get(index).getLink(), urls.get(index).getTitle(), urls.get(index).getContent());
                }

               // connect.setContent(title,content);
                /*String h1 = doc.getElementsByTag("h1").text();
                String h2 = doc.getElementsByTag("h2").text();
                String h3 = doc.getElementsByTag("h3").text();
                String h4 = doc.getElementsByTag("h4").text();
                String h5 = doc.getElementsByTag("h5").text();
                String h6 = doc.getElementsByTag("h6").text();
                String p = doc.getElementsByTag("p").text();
                String list = doc.getElementsByTag("li").text();
                String OrderedList = doc.getElementsByTag("ol").text();
                String UnorderedList = doc.getElementsByTag("ul").text();
                String td = doc.getElementsByTag("td").text();
                String th = doc.getElementsByTag("th").text();
                String description = doc.getElementsByTag("td").text();
                Elements metatags= doc.getElementsByTag("meta");
                for( Element metatag:metatags){
                    String name= metatag.attr("name");
                    String content= metatag.attr("content");
                    String  Description;
                    if (name.equals("description")){
                        Description=content;
                    }
                }*/


            } catch (IllegalArgumentException | SQLException e) {

            }

    }

   public int getURLslistSize()
    {
        return this.pivotList.size();
    }
    public void printURLslist()
    {
        for (int i= 0 ; i < pivotList.size() ; i ++)
        {
            System.out.println(pivotList.get(i).getLink());
            System.out.println(pivotList.get(i).getContent());
            System.out.println(pivotList.get(i).getTitle());
            System.out.println("----------------------------");
        }
    }
   public boolean isVisited(List<Content>urls,String URL)
   {
        for(int i=0;i<urls.size();i++)
        {
            if (URL.equals(urls.get(i).getLink()))
            {
                return true;
            }
        }
        return false;
   }
}
