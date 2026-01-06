package models.auth;

import models.core.TimeStamped;
import play.data.validation.Required;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends TimeStamped {

    @Column(unique = true)
    public String username;
    @Required
    public String password;
    @Column(unique = true)
    public String email;
    public String firstName;
    public String lastName;
    public UserRole role;

    public static User getByUsername(final String username) {
        return find("username = ?1", username).first();
    }

}
