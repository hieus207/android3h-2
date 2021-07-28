package com.example.mototest.Model;

public class ContentPost {
    private int Idpost;
    private String Title;
    private String Content;
    private String Image;
    public ContentPost(int Idpost , String Title  , String Content , String Image){
        this.Idpost = Idpost;
        this.Title = Title;
        this.Content = Content;
        this.Image = Image;

    }

    public int getIdpost() {
        return Idpost;
    }

    public void setIdpost(int idpost) {
        Idpost = idpost;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
