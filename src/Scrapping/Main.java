package Scrapping;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import Scrapping.*;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

public class Main {
    public static void backup() throws SQLException, InterruptedException {
        List<Content> seedSet = new ArrayList<>();
        DBconnection connect = new DBconnection();
        List<Content> backupUrls = new ArrayList<>();
        backupUrls = connect.getBackupUrls();
        for (int i = 0; i < backupUrls.size(); i++) {
            seedSet.add(new Content(backupUrls.get(i).getLink()));
        }
        System.out.println("Please enter the no. of threads from 1 to 12: ");
        Scanner myInput = new Scanner(System.in);
        int N = myInput.nextInt();
        Thread[] threads = new Thread[N];
        int startIndex = 0;
        // double a=(double)backupUrls.size()/N;
        int noOfURLS = backupUrls.size() / N;
        int stopIndex = noOfURLS;
        for (int i = 0; i < N; i++) {

            if (i == N - 1) {
                List<Content> urls = new ArrayList<>();
                urls = seedSet.subList(startIndex, backupUrls.size());
                System.out.println("URLS added are:");
                for (int j = 0; j < urls.size(); j++)
                    System.out.println(urls.get(j).getLink());
                threads[i] = new Thread(new Crawler(urls));
                threads[i].start();
                break;
            }
            List<Content> urls = new ArrayList<>();
            urls = seedSet.subList(startIndex, stopIndex);
            System.out.println("URLS added are:");
            for (int j = 0; j < urls.size(); j++)
                System.out.println(urls.get(j).getLink());
            threads[i] = new Thread(new Crawler(urls));
            threads[i].start();
            startIndex += noOfURLS;
            stopIndex = stopIndex + noOfURLS;


        }
        for (int i = 0; i < N; i++) {
            threads[i].join();
        }
        System.out.println("All threads finished");
    }
    public static void main(String args[]) throws IOException, SQLException, InterruptedException, GeoIp2Exception {
        List<Content> seedSet = new ArrayList<>();
        DBconnection connect = new DBconnection();
        boolean afterFL=false;
        //connect.deleteallTables();
       while (true) {
           if(afterFL)
           {
               System.out.println("All threads finished");
               System.out.println("Do you want to delete and restart ? 'y'or 'n'");
               char Choice = ' ';
               Scanner input = new Scanner(System.in);
               do{
                   Choice = input.next().charAt(0);
                   Choice = Character.toLowerCase(Choice);
               }
               while (Choice != 'y' && Choice != 'n');
               if(Choice=='y')
               {
                   connect.deleteallTables();
                   afterFL=false;
               }
               else
               {
                   backup();
               }
           }
           else if (connect.checkTable()) {
               backup();
               afterFL=true;
           }
           else {
               seedSet.add(new Content("https://sports.yahoo.com/"));
               seedSet.add(new Content("https://www.marca.com/en"));
               seedSet.add(new Content("https://www.nbcsports.com/"));
               seedSet.add(new Content("https://www.starva.com/"));
               seedSet.add(new Content("https://en.as.com/"));
               seedSet.add(new Content("https://www.espn.com/"));
               seedSet.add(new Content("https://www.bbc.com/"));
               seedSet.add(new Content("https://www.theguardian.com/"));
               seedSet.add(new Content("https://www.independent.co.uk/"));
               seedSet.add(new Content("https://www.nytimes.com/"));
               seedSet.add(new Content("https://news.yahoo.com/"));
               seedSet.add(new Content("https://www.foxnews.com/"));

               System.out.println("Please enter the no. of threads from 1 to 12: ");
               Scanner myInput = new Scanner(System.in);
               int N = myInput.nextInt();
               Thread[] threads = new Thread[N];
               if (N < 12) {
                   int firstThread = 13 - N;
                   for (int i = 0; i < N; i++) {

                       if (i == 0) {
                           List<Content> url = new ArrayList<>();
                           for (int j = 0; j < firstThread; j++) {
                               url.add(new Content(seedSet.get(j).getLink()));
                           }
                           threads[i] = new Thread(new Crawler(url));
                           threads[i].start();
                       } else {
                           if (firstThread > seedSet.size())
                               break;
                           List<Content> url2 = new ArrayList<>();
                           url2.add(new Content(seedSet.get(firstThread).getLink()));
                           firstThread = firstThread + 1;
                           new Thread(new Crawler(url2)).start();
                       }
                   }
               } else {
                   for (int i = 0; i < N; i++) {
                       List<Content> url = new ArrayList<>();
                       url.add(new Content(seedSet.get(i).getLink()));
                       threads[i] = new Thread(new Crawler(url));
                       threads[i].start();
                   }
               }
               for (int i = 0; i < N; i++) {
                   threads[i].join();
               }
               afterFL = true;

           }

        }
    }

}

