package Scrapping;

import Indexing_Process.Indexer;

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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/search_engine", "root", "");
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

    // FOR INDEXER :

    public int SetIndexTable(String w) {
       int id=0;
        try {

            String query = "INSERT INTO `indexer` (`ID`, `Word`,`IDF`) VALUES (NULL, '"+w+"',-1)";
            st.execute(query);

            String query1 = "select ID from indexer where Word = '"+w+"'";
            rs = st.executeQuery(query1);
            while (rs.next()) {
                id = rs.getInt("ID");
            }


        } catch (Exception E) {
            System.out.println(E);
        }

        return id;

    }

    public void SetLinkingTable(int LinkID,int WordId , int Position ) {
        try {

            String query = "INSERT INTO `linking` (`ID`,`ID_URL`, `ID_Indexer`,`position`) VALUES (NULL, '"+LinkID+"', '"+WordId+"', '"+Position+"')";
            st.execute(query);


        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public List<Integer> getLinkIDs() {
        List<Integer> arrID = new ArrayList<>();
        try {
            String query = "select ID from urls";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("ID");
                arrID.add(id);
                System.out.println(id);
            }


        } catch (Exception E) {
            System.out.println(E);
        }

      return arrID;

    }

    public List<List<String>> getLinks() {
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        int count =0;
        try {
            String query = "select * from urls";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int Word_ID = rs.getInt("ID");
                String url = rs.getString("URL");
                listOfLists.add(new ArrayList<String>());
                listOfLists.get(count).add(0,Integer.toString(Word_ID));
                listOfLists.get(count).add(1,url);
                count++;

            }
            for(int i=0;i<listOfLists.size();i++){
                System.out.println(listOfLists.get(i).get(0));
                System.out.println(listOfLists.get(i).get(1));

            }


        } catch (Exception E) {
            System.out.println(E);
        }

        return listOfLists;

    }

    public List<List<String>> getIDWord() {
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        int count=0;
        try {
            String query = "select ID,Word from indexer";
            rs = st.executeQuery(query);
            while (rs.next()) {
                int Word_ID = rs.getInt("ID");
                String Word = rs.getString("Word");
                listOfLists.add(new ArrayList<String>());
                listOfLists.get(count).add(0,Integer.toString(Word_ID));
                listOfLists.get(count).add(1,Word);
                count++;
            }


        } catch (Exception E) {
            System.out.println(E);
        }

        return listOfLists;

    }

    }


