package models;

import models.core.TimeStamped;

import javax.persistence.Lob;

public class Lead extends TimeStamped {

    public String customerName;
    public String phone;
    public String email;
    @Lob
    public String message;
    public String sourceUrl;

}
