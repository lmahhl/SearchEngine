import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.ArrayList;
import java.util.List;

public class Indexer {


    String StemOfWord;

    List<String> ExampleArr = new ArrayList<>();


    String [] Stop_Word= new String []{"a", "about" ,"above" ,"after" ,"again" ,"against" ,"all", "am", "an" ,"and" ,"any", "are",
            "aren't" ,"as" ,"at", "be", "because", "been", "before" ,"being", "below", "between", "both",
            "but" ,"by" ,"can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does" ,"doesn't", "doing"
            ,"don't","down", "during", "each", "few", "for","from","further","had", "hadn't", "has" ,"hasn't" ,"have",
            "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself"
            ,"him","himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is",
            "isn't","it","it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself" ,"no"
            ,"nor" ,"not" ,"of" ,"off", "on","once" , "only", "or", "other", "ought" ,"our" ,"ours" ,"ourselves"
            ,"out" ,"over", "own","same", "shan't" ,"she", "she'd" ,"she'll" ,"she's", "should", "shouldn't", "so",
            "some", "such" ,"than" ,"that", "that's", "the", "their", "theirs", "them" ,"themselves" , "then", "there"
            ,"there's" , "these", "they" , "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too"
            ,"under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were", "weren't", "what"
            ,"what's", "when", "when's", "where", "where's", "which", "while" ,"who", "who's", "whom", "why", "why's",
            "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're", "you've" , "your" ,"yours" ,"yourself", "yourselves","-","'"};


    //TO CHECK IF THE WORD IS A STOPPING WORD OR NOT:
    public  boolean IsStoppingWord(String word){
        for (int i=0; i< Stop_Word.length ; i++)
        {
            if(word.equals(Stop_Word[i]))
            {
                return true;
            }

        }
        return false;
    }
    public int SearchDatabase(){
        for(int x=0; x<ExampleArr.size() ; x++){
            if(StemOfWord.equals(ExampleArr.get(x)))
            {
                return x;
            }
        }
        return -1;
    }

    //TO GET STEM OF THE WORD:
    public String Stemmer(String x) {

        SnowballStemmer snowballStemmer = new englishStemmer();
        snowballStemmer.setCurrent(x);
        snowballStemmer.stem();
        String result = snowballStemmer.getCurrent();
        return result;
    }



    public void Parsing() {
        try {

            //we need to loop on all documents in database
            Document doc = Jsoup.connect("https://www.bbc.com/news/world-europe-52271492").get();

            //to get text in html code
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
            String table = doc.getElementsByTag("table").text();

            // Place all test in array:
            String  Alltext [] = new String [] {title, h1, h2, h3, h4, h5, h6, p};
            //String  Alltext [] = new String [] {title};
            System.out.println("Title is : "+title);
            System.out.println("----------------------------");
            System.out.println("h1 is : "+UnorderedList);
            System.out.println("----------------------------");
            System.out.println("h2 is : "+OrderedList);
            System.out.println("----------------------------");
            System.out.println("h3 is : "+list);
            System.out.println("----------------------------");


            String[] result;
            // SPLIT TEXT TO WORDS:
            for(int i=0; i<Alltext.length ;i++) {
                //check if tag exists:
                if(Alltext[i].isEmpty())
                    continue;

                result = Alltext[i].split("[()+;$*=#, ?.:!\"]+");




            for (int j = 0; j < result.length; j++) {

                String LowerWord =result[j].toLowerCase();
                System.out.println("LowerWord is : "+LowerWord);
                boolean IsStoppingWord = IsStoppingWord(LowerWord);

                if(IsStoppingWord)
                    continue;

                StemOfWord= Stemmer(LowerWord);
                System.out.println("StemOfword is : "+StemOfWord);
                int word_id=SearchDatabase();
                if(word_id==-1){
                    //Insert in database
                    ExampleArr.add(StemOfWord);
                }
                else{
                    //update database
                }

                }
                for (int b= 0 ; b < ExampleArr.size() ; b ++)
                {
                   System.out.println(ExampleArr.get(b));
                    System.out.println("----------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
