package models.core;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public class TimeStamped extends BaseModel {

    public Date created = new Date();
    public Date updated = new Date();
    public EntityStatus status = EntityStatus.NEW;

    @PrePersist
    public void prePersist() {
        if (created == null)
            created = new Date();
        updated = created;
    }

    @PreUpdate
    public void preUpdate() {
        if (created == null)
            created = new Date();
        updated = new Date();
    }

}
