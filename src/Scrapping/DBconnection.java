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


    public void setURLcontent(String url,String title,String content,String H1,String H2,String H3, String H4, String H5, String H6) {
        try {
                String titleRep=title.replaceAll("'","''");
                String contentRep= content.replaceAll("'","''");
                String H1Rep=H1.replaceAll("'","''");
                String H2Rep= H2.replaceAll("'","''");
                String H3Rep=H3.replaceAll("'","''");
                String H4Rep= H4.replaceAll("'","''");
                String H5Rep=H5.replaceAll("'","''");
                String H6Rep= H6.replaceAll("'","''");




                String query = "INSERT INTO `urls` (`ID`, `URL`,`TITLE`,`CONTENT`,`H1`,`H2`,`H3`,`H4`,`H5`,`H6`) VALUES (NULL, '"+url+"','"+titleRep+"' , '"+contentRep+"', '"+H1Rep+"', '"+H2Rep+"', '"+H3Rep+"', '"+H4Rep+"', '"+H5Rep+"', '"+H6Rep+"')";
                st.execute(query);

        } catch (Exception E) {
            System.out.println(E);
        }
    }

}