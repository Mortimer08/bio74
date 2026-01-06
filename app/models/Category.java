package models;

import models.core.TimeStamped;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class Category extends TimeStamped {

    public  String name;
    public String slug;
    @Lob
    public String description;
    @ManyToOne
    public SEOData seoData;

}
