package Scrapping;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.regionName;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;
import com.maxmind.geoip.LookupService;
import javax.net.ssl.SSLHandshakeException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import org.w3c.dom.*;
public class Crawler implements Runnable{

    private List<Content> pivotList;
    private List<Content> tobeCrawled;
    long start = System.nanoTime();
    DBconnection connect = new DBconnection();
    boolean afterSS=false;
    boolean stopCrawling=false;
    public Crawler(List <Content> pivotList) {
        this.pivotList=pivotList;
        tobeCrawled=new ArrayList<>();
    }
    @Override
    public void run() {
        try {
            System.out.println("A thread has started with link");
            for(int i =0;i<pivotList.size();i++)
                System.out.println(pivotList.get(i).getLink());
                Crawling();
                return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (InterruptedException ie)
        {
            return;
        }
    }

    public void Crawling() throws IOException, SQLException,InterruptedException {

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

                if(stopCrawling)
                {
                    return;
                }
                searchContent(link.attr("href"),pivotList.get(i).getLink());

            }
           if(afterSS) {
               //pivotList.get(i).setCrawled(true);
               connect.setCrawled(pivotList.get(i).getLink());
               connect.removeFromBackup(pivotList.get(i).getLink());
           }
        }
        System.out.println("first loop done");

        pivotList.clear();
        afterSS=true;
        pivotList.addAll(tobeCrawled);
        Crawling();

    }


    public void searchContent(String URL,String pUrl) throws IOException, SQLException {

            if(System.nanoTime() - start > 3600E9)
            {
                stopCrawling=true;
                return;
            }

            Document doc;
            // Connecting to a URL
            try {
                //int index = urls.size()-1;
                if(isVisited(connect.getUrls(),URL)==false)
                {
                    doc = Jsoup.connect(URL).get();
                    System.out.println("last link entered with thread "+Thread.currentThread().getName()+" url : \n"+URL);
                   // pivotList.add(new Content(URL));
                   Robotstxt R= new Robotstxt(URL);
                    if(R.isAllowed()!=true)
                    {
                        System.out.println("A robot.txt does not allow you to enter this link : \n"+URL);
                        return;
                    }
                    String title = doc.title();
                    String h1 = doc.getElementsByTag("h1").text();
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
                    String date= doc.getElementsByAttribute("pubdate").text();
                    String x = doc.getElementsByTag("time").attr("itemprob").toString();
                    if(!x.isBlank())
                    {
                        date=x;
                    }

                    String loc="";
                    InetAddress address = InetAddress.getByName(new URL(URL).getHost());
                    String ip = address.getHostAddress();
                    File dbFile = new File("GeoLiteCity.dat");
                    LookupService lookupService = new LookupService(dbFile, LookupService.GEOIP_MEMORY_CACHE);

                    Location location = lookupService.getLocation(ip);
                    if (location != null) {
                        location.region = regionName.regionNameByCode(location.countryCode, location.region);
                        loc = location.countryName;
                    }
                    Element image = doc.select("img").first();
                    if(image!=null) {
                        String imageURL = image.absUrl("src");
                        String imgD = image.getElementsByAttribute("alt").text();
                        Element Title = doc.select("img[title]").first();
                        String imageDescription="";
                        if(Title!=null) {
                            imageDescription = Title.attr("title");
                            if (!imgD.isBlank()) {
                                imageDescription = imgD;
                            }
                        }

                        connect.setImage(imageURL,imageDescription,title);
                    }






                   //
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


                  connect.setURLcontent(URL,title,pUrl,h1,h2,h3,h4,h5,h6,p,list,OrderedList,UnorderedList,td,th,date,loc);
                  connect.setURLRelation(URL,pUrl);

                  tobeCrawled.add(new Content(URL));
                  connect.addtoBackup(URL);
                }

            } catch (IllegalArgumentException | SQLException e )
            {
               // e.printStackTrace();
            }
            catch ( HttpStatusException  |ConnectException  | SocketTimeoutException | MalformedURLException | SSLHandshakeException connectException)
            {
               // connectException.printStackTrace();
            }
            catch ( UnknownHostException | UnsupportedMimeTypeException ex)
            {
               // ex.printStackTrace();
            }
            catch (Exception E)
            {
               // E.printStackTrace();
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
   public synchronized boolean isVisited(List<Content>urls,String URL)
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
