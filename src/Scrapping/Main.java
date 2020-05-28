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
    public static void main(String args[]) throws IOException, SQLException, InterruptedException, GeoIp2Exception {


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
        Thread [] threads= new Thread[N];
        if(N < 12) {
            int firstThread = 13 - N;
            for (int i = 0; i < N; i++) {

                if(i==0)
                {
                    List<Content> url = new ArrayList<>();
                    for(int j =0; j < firstThread;j++)
                    {
                        url.add(new Content(seedSet.get(j).getLink()));
                    }
                    threads[i]=new Thread(new Crawler(url));
                    threads[i].start();
                }
                else {
                    if(firstThread>seedSet.size())
                        break;
                    List<Content> url2 = new ArrayList<>();
                    url2.add(new Content(seedSet.get(firstThread).getLink()));
                    firstThread=firstThread+1;
                    new Thread(new Crawler(url2)).start();
                }
            }
        }
        else {
            for (int i = 0; i < N; i++) {
                List<Content> url = new ArrayList<>();
                url.add(new Content(seedSet.get(i).getLink()));
                threads[i]=new Thread(new Crawler(url));
                threads[i].start();
            }
        }
        for(int i =0;i < N;i++)
        {
            threads[i].join();
        }
        System.out.println("All threads finished");
        //Crawler C = new Crawler(seedSet);

/*
        String loc="";
        InetAddress address = InetAddress.getByName(new URL("https://fr.fifa.com").getHost());
        String ip = address.getHostAddress();
        System.out.println(ip);
        File dbFile = new File("GeoLiteCity.dat");
        LookupService lookupService = new LookupService(dbFile, LookupService.GEOIP_MEMORY_CACHE);

        Location location = lookupService.getLocation(ip);
        if (location != null) {
            location.region = regionName.regionNameByCode(location.countryCode, location.region);
            loc = location.countryName;
        }
        System.out.println(loc);*/







    }
}

