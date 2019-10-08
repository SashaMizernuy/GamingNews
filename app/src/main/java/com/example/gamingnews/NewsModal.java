package com.example.gamingnews;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsModal {


    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Currency {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }


    public class Image {


        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }

    public class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private Image image;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("price_week")
        @Expose
        private Integer priceWeek;
        @SerializedName("price_month")
        @Expose
        private Integer priceMonth;
        @SerializedName("currency")
        @Expose
        private Currency currency;
        @SerializedName("view_count")
        @Expose
        private Integer viewCount;
        @SerializedName("favorite")
        @Expose
        private Boolean favorite;
        @SerializedName("email_count")
        @Expose
        private Integer emailCount;
        @SerializedName("phone_count")
        @Expose
        private Integer phoneCount;
        @SerializedName("owner")
        @Expose
        private Boolean owner;
        @SerializedName("category")
        @Expose
        private Integer category;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getPriceWeek() {
            return priceWeek;
        }

        public void setPriceWeek(Integer priceWeek) {
            this.priceWeek = priceWeek;
        }

        public Integer getPriceMonth() {
            return priceMonth;
        }

        public void setPriceMonth(Integer priceMonth) {
            this.priceMonth = priceMonth;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public Integer getViewCount() {
            return viewCount;
        }

        public void setViewCount(Integer viewCount) {
            this.viewCount = viewCount;
        }

        public Boolean getFavorite() {
            return favorite;
        }

        public void setFavorite(Boolean favorite) {
            this.favorite = favorite;
        }

        public Integer getEmailCount() {
            return emailCount;
        }

        public void setEmailCount(Integer emailCount) {
            this.emailCount = emailCount;
        }

        public Integer getPhoneCount() {
            return phoneCount;
        }

        public void setPhoneCount(Integer phoneCount) {
            this.phoneCount = phoneCount;
        }

        public Boolean getOwner() {
            return owner;
        }

        public void setOwner(Boolean owner) {
            this.owner = owner;
        }

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

    }

}