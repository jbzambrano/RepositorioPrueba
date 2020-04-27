package sw2.clase03.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class Region {


    @Id
    private String regionid;
    private String regiondescription;

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getRegiondescription() {
        return regiondescription;
    }

    public void setRegiondescription(String regiondescription) {
        this.regiondescription = regiondescription;
    }
}
