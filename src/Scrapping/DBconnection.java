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

    public void setURLcontent(String url,String title,String content) {
        try {
                String title2=title.replaceAll("'","''");
                String content2= content.replaceAll("'","''");
                String query = "INSERT INTO `urls` (`ID`, `URL`,`TITLE`,`CONTENT`) VALUES (NULL, '"+url+"','"+title2+"' , '"+content2+"')";
                st.execute(query);

        } catch (Exception E) {
            System.out.println(E);
        }
    }

}