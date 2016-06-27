package com.pwa.model;

import com.pwa.common.model.BaseEntity;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CITIES")
public class City extends BaseEntity implements Serializable {

    @Id
    @Column(name = "CITY_ID", length = 70, unique = true, nullable = false)
    private String id;

    @Column(name = "SEARCH_ID", length = 70, unique = true, nullable = false)
    private Integer searchId;

    @Column(name = "NAME", length = 70, nullable = false)
    private String name;

    @Column(name = "COUNTRY", length = 70, nullable = false)
    private String country;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public City() {
    }

    public City(String id, Integer searchId, String name, String country, Long version, String createdBy, DateTime createdDate, String lastModifiedBy,
        DateTime lastModifiedDate) {
        super(version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
        this.id = id;
        this.searchId = searchId;
        this.name = name;
        this.country = country;
    }

}
