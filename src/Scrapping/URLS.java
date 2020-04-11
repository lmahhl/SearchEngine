package Scrapping;

public class URLS {

    private String Link;
    private String Title;
    private String Content;

    public URLS (String link,String Title, String Content)
    {
        this.Link = link;
        this.Title = Title;
        this.Content=Content;
    }
    public URLS ()
    {

    }

    public URLS(String s) {
        this.Link = s;
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
}
