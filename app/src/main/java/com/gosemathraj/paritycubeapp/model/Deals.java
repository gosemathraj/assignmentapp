package com.gosemathraj.paritycubeapp.model;

/**
 * Created by iamsparsh on 14/6/16.
 */
public class Deals {

    private String id;
    private String title;
    private String off_percent;
    private String current_price;
    private String original_price;
    private String image_thumb;
    private String like_count;
    private String comments_count;
    private String dealer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public Deals(String title, String off_percent, String current_price, String original_price, String image_thumb) {
        this.title = title;
        this.off_percent = off_percent;
        this.current_price = current_price;
        this.original_price = original_price;
        this.image_thumb = image_thumb;
    }

    public Deals(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOff_percent() {
        return off_percent;
    }

    public void setOff_percent(String off_percent) {
        this.off_percent = off_percent;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }
}
