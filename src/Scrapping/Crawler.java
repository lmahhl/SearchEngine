package Scrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Crawler {

    private List<URLS> URLslist;

    public Crawler(URLS SeedSet) {
        this.URLslist = new ArrayList<>();
        this.URLslist.add(SeedSet);
    }


    public void Crawling() throws IOException {

        List<URLS> New = new ArrayList<>();
        for (int i = 0; i < URLslist.size(); i++) {
            Document D;
            // Connecting to a URL
            D = Jsoup.connect(URLslist.get(i).getLink()).get();

            // Getting the links in it
            Elements links = D.select("a[href]");
            //String Title = D.title();
           // String Content = D.body().text();

            for (Element link : links) {

              // String Title = link.getElementsByTag("title").text() ;
                New.add(new URLS());
                int LastURL = New.size() - 1 ;
                New.get(LastURL).setLink(link.attr("href"));
                //URLslist.get(LastURL).setTitle(link.tagName());

            }

        }
        URLslist.addAll(New);



    }
    public List<URLS> getURLslist()
    {
        return this.URLslist;
    }
    public void printURLslist()
    {
        for (int i= 0 ; i < URLslist.size() ; i ++)
        {
            System.out.println(URLslist.get(i).getLink());
           // System.out.println(URLslist.get(i).getContent());
           // System.out.println(URLslist.get(i).getTitle());
            System.out.println("----------------------------");
        }
    }
   /* public void ContentSetter() throws IOException {

        for (int i = 0 ; i < URLslist.size(); i ++)
        {
            Document D2;
            // Connecting to a URL
            D2 = Jsoup.connect(URLslist.get(i).getLink()).get();
            // Getting the links in it
            //URLslist.get(i).setTitle(D2.title()) ;
            //URLslist.get(i).setContent(D2.body().text());
            System.out.println(D2.title());

        }
    }*/
}
