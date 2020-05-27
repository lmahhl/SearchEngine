package Scrapping;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Robotstxt {

    String url = "";

    public Robotstxt(String URL) {
        this.url = URL;
    }

    public boolean isAllowed() {
        try {
            URL Url = new URL(this.url);
            String base = Url.getProtocol() + "://" + Url.getHost();

            URL url = new URL(base + "/robots.txt");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

           // huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD");
            huc.setConnectTimeout(5000);
            huc.setReadTimeout(5000);//This is to prevent url connection hangs
            huc.setRequestProperty("Connection", "close");
            huc.connect () ;
            //int responseCode = huc.getResponseCode() ;

            //if (HttpURLConnection.HTTP_OK == responseCode) {
                List<String> disAllowed = new ArrayList<String>();
                // read text returned by server
                BufferedReader in = new BufferedReader(new InputStreamReader(huc.getInputStream()));

                String line;
                String text = "";
                String userAgent = "User-agent: *";
                String userAgent2 = "User-Agent: *";
                boolean startTrimming = false;
                while ((line = in.readLine()) != null) {
                    if (startTrimming) {
                        if (line.contains("Disallow") || line.contains("Allow"))
                            text = text + line + "\n";
                        else if (line.isBlank()) {
                            continue;
                        } else {
                            startTrimming = false;
                            break;
                        }
                    }
                    if (line.contains(userAgent) || line.contains(userAgent2)) {
                        startTrimming = true;
                    }

                }
                // System.out.println(text);
                in.close();

                //String newText=text.substring(text.lastIndexOf(userAgent) + 14);
                // System.out.println(newText);


                BufferedReader bufReader = new BufferedReader(new StringReader(text));


                String line2 = null;
                String disallow = "Disallow: ";
                while ((line2 = bufReader.readLine()) != null) {
                    if (line2.contains("Disallow") || line2.contains("Allow")) {
                        if (line2.contains(disallow))// zawd allow w check lw 5lsooo
                        {
                            String tobeAdded = line2.substring(line2.lastIndexOf(disallow) + 10);

                            if (tobeAdded.endsWith("*")) {
                                tobeAdded = tobeAdded.substring(0, tobeAdded.lastIndexOf("*"));

                            }
                            if (tobeAdded.charAt(0) == '*') {
                                tobeAdded = tobeAdded.substring(1);
                            }
                            if (tobeAdded.contains("*")) {
                                continue;
                            }
                            if (tobeAdded.contains("#")) {
                                int index = tobeAdded.indexOf("#");
                                String word = tobeAdded.substring(0, index);
                                if(tobeAdded.contains(" "))
                                {
                                    word=word.replaceAll("\\s+","");
                                }
                                disAllowed.add(word);
                            } else {
                                disAllowed.add(tobeAdded);
                            }
                        }
                    } else {
                        break;
                    }
                }

                // System.out.println("size = "+disAllowed.size());
               for (int i = 0; i < disAllowed.size(); i++) {
                   // System.out.println(disAllowed.get(i));
                   if(this.url.endsWith("/")&& disAllowed.get(i).endsWith("/"))
                   {

                       if (this.url.contains(disAllowed.get(i))) {
                           return false;
                       }
                   }
                   else if(this.url.endsWith("/"))
                   {
                       this.url=this.url.substring(0,this.url.length()-1);
                       if (this.url.contains(disAllowed.get(i))) {
                           return false;
                       }
                   }
                   else if (disAllowed.get(i).endsWith("/"))
                   {
                       this.url=this.url+"/";
                       if (this.url.contains(disAllowed.get(i))) {
                           return false;
                       }
                   }
                   else
                   {
                       if (this.url.contains(disAllowed.get(i))) {
                           return false;
                       }
                   }

               }


            //}

           // else
           // {
                return true;
           // }





        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        return true;

    }
}


