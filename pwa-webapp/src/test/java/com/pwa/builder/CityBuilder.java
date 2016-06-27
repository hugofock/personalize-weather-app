package com.pwa.builder;

import com.pwa.model.City;
import org.joda.time.DateTime;

public class CityBuilder {

    private String id;
    private Integer searchId;
    private String name;
    private String country;
    private Long version;
    private String createdBy;
    private DateTime createdDate;
    private String lastModifiedBy;
    private DateTime lastModifiedDate;

    public City build() {
        return new City(id, searchId, name, country, version, createdBy, createdDate, lastModifiedBy, lastModifiedDate);
    }

    public CityBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CityBuilder withSearchId(Integer searchId) {
        this.searchId = searchId;
        return this;
    }

    public CityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CityBuilder withCountry(String country) {
        this.country = country;
        return this;
    }


    public CityBuilder withVersion(Long version) {
        this.version = version;
        return this;

    }

    public CityBuilder withCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;

    }

    public CityBuilder withCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
        return this;

    }

    public CityBuilder withLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;

    }

    public CityBuilder withLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;

    }
}