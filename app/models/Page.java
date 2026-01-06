package models;

import models.core.TimeStamped;

import javax.persistence.ManyToOne;

public class Page extends TimeStamped {

    public PageType type;
    public String slug;
    public String h1;
    public String content;
    @ManyToOne
    public SEOData seoData;

}
