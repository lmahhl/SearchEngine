package Indexing_Process;

import Indexing_Process.Indexer;
import Scrapping.DBconnection;

public class Main
{

    public static void  main(String[] arg)
    {
        DBconnection connect=new DBconnection();
        connect.SetLinkingTable(2,1,0);


    //Indexer I = new Indexer();
    //I.Parsing();
    }
}



