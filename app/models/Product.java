package models;

import models.core.Image;
import models.core.TimeStamped;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class Product extends TimeStamped {

    public String title;
    public String slug;
    public String description;
    @Lob
    public String fullDescription;
    @ManyToOne
    public Category category;
    @ManyToOne
    public Image image;
    public Integer price;
    @ManyToOne
    public SEOData seo;

}
