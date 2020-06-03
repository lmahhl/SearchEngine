package Scrapping;

import javax.swing.text.AbstractDocument;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;

public class DBconnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBconnection() {
        try {

            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enginedata", "root", "");
            st = con.createStatement();
        } catch (Exception E) {
            System.out.println("Error" + E);
        }

    }

    public void GetData() {
        try {
            String query = "select * from urls";
            rs = st.executeQuery(query);
            while (rs.next()) {

                int id = rs.getInt("ID");
                String url = rs.getString("URL");
               // System.out.println(id);
               // System.out.println(url);
            }


        } catch (Exception E) {
            System.out.println(E);
        }

    }
    public List<Content> getUrls() throws SQLException {
        List<Content> URLS= new ArrayList<>();
        String query = "select * from urls";
        rs = st.executeQuery(query);
        while (rs.next()) {

            URLS.add(new Content(rs.getString("URL")));

        }
        return URLS;
    }
    public List<Content> getUncrawledUrls() throws SQLException {
        List<Content> URLS= new ArrayList<>();
        String query = "select * from urls where Crawled=0";
        rs = st.executeQuery(query);
        while (rs.next()) {

            URLS.add(new Content(rs.getString("URL")));

        }
        return URLS;
    }
    public void setCrawled(String url) throws SQLException {


        String query= "UPDATE `urls` SET `Crawled`=1 WHERE URL='"+url+"' ";
        st.executeUpdate(query);
    }
    public int getCount() throws SQLException {
        ResultSet r = st.executeQuery("SELECT COUNT(*) AS rowcount FROM urls");
        r.next();
        int count = r.getInt("rowcount");
        r.close();
        return count;
    }
    public void setURLcontent(String url,String title,String PURL,String H1,String H2,String H3, String H4, String H5, String H6,String p, String list,String OL, String UOL, String td, String th,String Date,String Location) {
        try {
                String titleRep=title.replaceAll("'","''");
                String H1Rep=H1.replaceAll("'","''");
                String H2Rep= H2.replaceAll("'","''");
                String H3Rep=H3.replaceAll("'","''");
                String H4Rep= H4.replaceAll("'","''");
                String H5Rep=H5.replaceAll("'","''");
                String H6Rep= H6.replaceAll("'","''");

                String pRep=p.replaceAll("'","''");
                String listRep=list.replaceAll("'","''");
                String OLRep=OL.replaceAll("'","''");
                String UOLRep = UOL.replaceAll("'","''");
                String tdRep =td.replaceAll("'","''");
                String thRep=th.replaceAll("'","''");
                String dateRep=Date.replaceAll("'","''");
                String locationRep=Location.replaceAll("'","''");





                String query = "INSERT INTO `urls` (`ID`, `URL`,`TITLE`,`H1`,`H2`,`H3`,`H4`,`H5`,`H6`,`P`,`LIST`,`OL`,`UOL`,`TD`,`TH`,`PUBDATE`,`LOCATION`) VALUES (NULL, '"+url+"','"+titleRep+"' , '"+H1Rep+"', '"+H2Rep+"', '"+H3Rep+"', '"+H4Rep+"', '"+H5Rep+"', '"+H6Rep+"', '"+pRep+"', '"+listRep+"', '"+OLRep+"', '"+UOLRep+"', '"+tdRep+"', '"+thRep+"', '"+dateRep+"', '"+locationRep+"')";
                st.execute(query);
                String query2="UPDATE `urls` SET `OUTLINKS`=`OUTLINKS`+1 WHERE URL='"+PURL+"'";
                st.execute(query2);

        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public void setURLRelation(String url, String pUrl) throws SQLException {
        String query = "INSERT INTO `urlrs` (`PURL`, `CURL`) VALUES ('"+pUrl+"', '"+url+"') ";
        st.execute(query);
    }

    public void setImage(String imageURL, String imageDescription,String imageTitle) throws SQLException {
        String imageTitleRep=imageTitle.replaceAll("'","''");
        String imageDescriptionRep=imageDescription.replaceAll("'","''");
        String query = "INSERT INTO `imageurls` (`IURL`,`TITLE`,`DESCRIPTION`) VALUES ('"+imageURL+"', '"+imageTitleRep+"', '"+imageDescriptionRep+"') ";
        st.execute(query);
    }

    public boolean checkTable() throws SQLException {
        String query = "select * from urls";
        rs = st.executeQuery(query);
        if(rs.next())
        {
            return true;
        }
        return false;
    }

    public void addtoBackup(String url) throws SQLException {
        String query = "INSERT INTO `backup` (`URL`) VALUES ('"+url+"') ";
        st.execute(query);

    }

    public void removeFromBackup(String link) throws SQLException {
        String query = "DELETE FROM `backup` WHERE  URL='"+link+"' ";
        st.execute(query);
    }
    public List<Content> getBackupUrls() throws SQLException {
        List<Content> URLS= new ArrayList<>();
        String query = "select * from backup";
        rs = st.executeQuery(query);
        while (rs.next()) {

            URLS.add(new Content(rs.getString("URL")));

        }
        return URLS;
    }

    public void deleteallTables() throws SQLException {
        String query = "truncate urls";
        String query1 = "truncate urlrs";
        String query2 = "truncate backup";
        String query3 = "truncate imageurls";
        st.execute(query);
        st.execute(query1);
        st.execute(query2);
        st.execute(query3);
    }
}