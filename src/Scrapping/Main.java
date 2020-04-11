package Scrapping;

import java.io.IOException;
import java.util.List;
import Scrapping.*;
public class Main {
    public static void main(String args[]) {

        String StartURL = "http://tankoedward.wordpress.com/";
        URLS SeedSet =  new URLS (StartURL);
        System.out.println(SeedSet.getLink());
        Crawler C = new Crawler(SeedSet);
        try {
            C.Crawling();

        }
        catch (IllegalArgumentException i)
        {}
        catch (IOException e) {
            e.printStackTrace();
        }
        C.printURLslist();
        System.out.println(C.getURLslist().size());
    }

}
