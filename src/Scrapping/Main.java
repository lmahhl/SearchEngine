package Scrapping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Scrapping.*;
public class Main {
    public static void main(String args[]) throws IOException {


        List<Content> seedSet = new ArrayList<>();
        seedSet.add(new Content("https://sports.yahoo.com/"));
        seedSet.add(new Content("https://www.espn.com/"));
        seedSet.add(new Content("https://www.bleacherreport.com/"));
        seedSet.add(new Content("https://www.cbssports.com/"));
        seedSet.add(new Content("https://www.si.com/"));
        seedSet.add(new Content("https://www.foxsports.com/"));
        seedSet.add(new Content("https://www.fifa.com/"));
        seedSet.add(new Content("https://www.marca.com/"));
        seedSet.add(new Content("https://www.hupu.com/"));
        seedSet.add(new Content("https://www.starva.com/"));
        seedSet.add(new Content("https://www.nbcsports.com/"));
        seedSet.add(new Content("https://en.as.com/"));

        System.out.println("Please enter the no. of threads from 1 to 12: ");
        Scanner myInput = new Scanner( System.in );
        int N = myInput.nextInt();

        for (int i =0 ;i < N ;i ++)
        {
            List <Content>url=new ArrayList<>();
            url.add(new Content(seedSet.get(i).getLink()));
            new Thread(new Crawler(url)).start();
        }

        //Crawler C = new Crawler(seedSet);





    }
}

