package Scrapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
                System.out.println(id);
                System.out.println(url);
            }


        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public void SetURLsTable(List<URLS> urls) {
        try {
            for (int i = 0; i < urls.size(); i++) {

                String url = urls.get(i).getLink();
                String query = "INSERT INTO `urls` (`ID`, `URL`) VALUES (NULL, '"+url+"')";
                st.execute(query);

            }
        } catch (Exception E) {
            System.out.println(E);
        }
    }
}