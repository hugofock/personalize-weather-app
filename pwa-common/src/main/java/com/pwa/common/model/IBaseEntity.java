package com.pwa.common.model;

import org.joda.time.DateTime;

public interface IBaseEntity {

    /**
     * Gets the primary key of the entity.
     *
     * @return id
     */
    String getId();

    /**
     * Sets the primary key of the entity.
     *
     * @param id
     */
    void setId(String id);

    /**
     * Returns the user who created this entity.
     *
     * @return creator of this record
     */
    String getCreatedBy();

    /**
     * Sets the user who created this entity.
     *
     * @param createdBy the creating entity to set
     */
    void setCreatedBy(final String createdBy);

    /**
     * Returns the creation date of the entity.
     *
     * @return the createdDate
     */
    DateTime getCreatedDate();

    /**
     * Sets the creation date of the entity.
     *
     * @param createdDate the creation date to set
     */
    void setCreatedDate(DateTime createdDate);

    /**
     * Returns the user who modified the entity lastly.
     *
     * @return the lastModifiedBy
     */
    String getLastModifiedBy();

    /**
     * Sets the user who modified the entity lastly.
     *
     * @param lastModifiedBy the last modifying entity to set
     */
    void setLastModifiedBy(String lastModifiedBy);

    /**
     * Returns the date of the last modification.
     *
     * @return the lastModifiedDate
     */
    DateTime getLastModifiedDate();

    /**
     * Sets the date of the last modification.
     *
     * @param lastModifiedDate the date of the last modification to set
     */
    void setLastModifiedDate(DateTime lastModifiedDate);

}
