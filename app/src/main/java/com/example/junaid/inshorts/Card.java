package com.example.junaid.inshorts;

/**
 * Created by junaid on 29/01/16.
 */
public class Card {
    public String imageUrl;
    public String body;
    public String shortLine;
    public String countOfLikes;
    public String moreAt;
    public String reportedBy;

    public Card(String imageUrl, String body, String shortLine, String noOfLikes, String moreAt, String reportedBy){
        this.imageUrl   = imageUrl;
        this.body       = body;
        this.shortLine  = shortLine;
        this.countOfLikes=noOfLikes;
        this.moreAt     = moreAt;
        this.reportedBy = reportedBy;
    }
}
