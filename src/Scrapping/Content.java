package Scrapping;

public class Content {

    private String Link;
    private String Title;
    private String Content;
    private boolean Visited;

    public Content (String link,String Title, String Content)
    {
        this.Link = link;
        this.Title = Title;
        this.Content=Content;
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
}
