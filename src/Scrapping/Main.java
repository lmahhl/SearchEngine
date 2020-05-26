package Scrapping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Scrapping.*;
public class Main {
    public static void main(String args[]) throws IOException {

       // String StartURL = "https://www.google.com";
        List<Content> seedSet = new ArrayList<>();
        seedSet.add(new Content("https://www.fifa.com/worldcup/"));
        seedSet.add(new Content("https://en.wikipedia.org/wiki/FIFA_World_Cup"));
        seedSet.add(new Content("https://www.rugbyworldcup.com/2021"));
        seedSet.add(new Content("https://www.gutenberg.org/"));

        //seedSet.add(new Content("https://www.gutenberg.org"));
        Crawler C = new Crawler(seedSet);

        try {
            C.Crawling();

        } catch (IllegalArgumentException i) {
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
         //C.printURLslist();
       // System.out.println(C.getURLslistSize());



    }
}

