package Scrapping;

public class Content {

    private String Link;
    private String Title;
    private String Content;
    private String H1;
    private String H2;
    private String H3;
    private String H4;
    private String H5;
    private String H6;
    private String Td;
    private String Th;
    private String Description;
    private String list;
    private String OrderedList;
    private String UnorderedList;
    private boolean Visited;
    private boolean Crawled;

    public Content (String link,String Title, String Content,String H1,String H2,String H3,String H4,String H5,String H6,String Td,String Th,String Description,String list,String OrderedList,String UnorderedList)
    {
        this.Link = link;
        this.Title = Title;
        this.Content=Content;
        this.H1=H1;
        this.H2=H2;
        this.H3=H3;
        this.H4=H4;
        this.H5=H5;
        this.H6=H6;
        //this.Description= Description;
        this.Td=Td;
        this.Th=Th;
        this.list=list;
        this.OrderedList=OrderedList;
        this.UnorderedList=UnorderedList;
    }


    public Content(String link) {
        this.Link=link;
    }

    public boolean getVisited() {
        return Visited;
    }
    public void setVisited(boolean visited) {
        Visited = visited;
    }
    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        this.Link = link;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
    public String getH1() {
        return H1;
    }

    public void setH1(String h1) { H1 =h1; }

    public String getH2() {
        return H2;
    }

    public void setH2(String h2) { H2 =h2; }

    public String getH3() { return H3; }

    public void setH3(String h3) { H3 = h3; }

    public String getH4() { return H4; }

    public void setH4(String h4) { H4 = h4; }

    public String getH5() { return H5; }

    public void setH5(String h5) { H5 = h5; }

    public String getH6() { return H6; }

    public void setH6(String h6) { H6 = h6; }

    public String getTd() { return Td; }

    public void setTd(String TD) { Td = TD; }

    public String getTh() { return  Th; }

    public void setTh(String TH) { Th = TH; }

    public String getDescription() { return Description; }

    public void setDescription(String description) { Description = description; }

    public String getList() { return list; }

    public void setList(String List) { list = List; }

    public String getOrderedListList() { return OrderedList; }

    public void setOrderedList(String orderedList) { OrderedList = orderedList; }

    public String getUnorderedList() { return UnorderedList; }

    public void setUnorderedList(String unorderedList) { UnorderedList = unorderedList; }
    public void setCrawled(boolean x)
    {
        this.Crawled=x;
    }
    public boolean getCrawled()
    {
        return this.Crawled;
    }

}



