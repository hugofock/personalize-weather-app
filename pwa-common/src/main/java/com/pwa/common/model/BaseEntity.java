package com.pwa.common.model;

import com.pwa.common.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, IBaseEntity {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "VERSION", length = 5, nullable = false)
    private Long version;

    @Column(name = "CREATED_BY", length = 50, nullable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE", length = 50, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @Column(name = "LAST_MODIFIED_BY", length = 50, nullable = false)
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE", length = 50, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModifiedDate;

    public BaseEntity()
    {

    }

    public BaseEntity(Long version, String createdBy, DateTime createdDate, String lastModifiedBy, DateTime lastModifiedDate) {
        this.version = version;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(final Object obj) {
        if (StringUtils.isEmpty(getId())) {
            final int hashcode1 = System.identityHashCode(this);
            final int hashcode2 = System.identityHashCode(obj);

            return hashcode1 == hashcode2;
        }

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final BaseEntity other = (BaseEntity) obj;

        if (StringUtils.isEmpty(getId())) {
            if (StringUtils.isNotEmpty(getId())) {
                return false;
            }
        } else if (!StringUtils.equals(getId(), other.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}:{1}", getClass().getSimpleName(), getId());
    }

    public String toJsonString() {
        return ObjectUtil.toJson(this);
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
