package Scrapping;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Crawler {

    private List<Content> pivotList;
    DBconnection connect = new DBconnection();

    public Crawler(List <Content> pivotList) {
        this.pivotList=pivotList;


    }


    public void Crawling() throws IOException, SQLException {

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


                searchContent(link.attr("href"),pivotList);

            }
           // pivotList.addAll(New);
        }
        pivotList.clear();
        pivotList.addAll(connect.getUrls());
        Crawling();

    }


    public void searchContent(String URL,List<Content> urls) throws IOException {



            Document doc;
            // Connecting to a URL
            try {
                int index = urls.size()-1;
                if(isVisited(connect.getUrls(),URL)==false)
                {
                    doc = Jsoup.connect(URL).get();
                   // pivotList.add(new Content(URL));
                    String title = doc.title();
                    String content = doc.body().text();
                    String h1 = doc.getElementsByTag("h1").text();
                    String h2 = doc.getElementsByTag("h2").text();
                    String h3 = doc.getElementsByTag("h3").text();
                    String h4 = doc.getElementsByTag("h4").text();
                    String h5 = doc.getElementsByTag("h5").text();
                    String h6 = doc.getElementsByTag("h6").text();
                    //String p = doc.getElementsByTag("p").text();
                    String list = doc.getElementsByTag("li").text();
                    String OrderedList = doc.getElementsByTag("ol").text();
                    String UnorderedList = doc.getElementsByTag("ul").text();
                    String td = doc.getElementsByTag("td").text();
                    String th = doc.getElementsByTag("th").text();
                    String description = doc.getElementsByTag("td").text();
                    Elements metatags= doc.getElementsByTag("meta");
                    for( Element metatag:metatags){
                        String name= metatag.attr("name");
                        content= metatag.attr("content");
                        String  Description;
                        if (name.equals("description")){
                            Description=content;
                        }
                    }


                    /*urls.get(index).setTitle(title);
                    urls.get(index).setContent(content);
                    urls.get(index).setH1(h1);
                    urls.get(index).setH2(h2);
                    urls.get(index).setH3(h3);
                    urls.get(index).setH4(h4);
                    urls.get(index).setH5(h5);
                    urls.get(index).setH6(h6);
                    urls.get(index).setList(list);
                    urls.get(index).setOrderedList(OrderedList);
                    urls.get(index).setUnorderedList(UnorderedList);
                    urls.get(index).setTd(td);
                    urls.get(index).setTh(th);
                    urls.get(index).setDescription(description);*/



                  //  connect.setURLcontent(urls.get(index).getLink(), urls.get(index).getTitle(), urls.get(index).getContent(),urls.get(index).getH1(),urls.get(index).getH2(),urls.get(index).getH3(),urls.get(index).getH4(),urls.get(index).getH5(),urls.get(index).getH6());
                    connect.setURLcontent(URL,title, content,h1,h2,h3,h4,h5,h6);
                }

            } catch (IllegalArgumentException | SQLException e )
            {

            }
            catch ( HttpStatusException  |ConnectException  | SocketTimeoutException | MalformedURLException | SSLHandshakeException connectException)
            {

            }
            catch ( UnknownHostException | UnsupportedMimeTypeException ex)
            {

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
