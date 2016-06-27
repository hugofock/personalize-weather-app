package com.pwa.common.listener;

import com.pwa.common.model.IBaseEntity;
import com.pwa.common.spring.UserContext;
import org.joda.time.DateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.UUID;

public class EntityListener {

    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate(final Object obj) {
        if (obj instanceof IBaseEntity) {
            IBaseEntity baseEntity = (IBaseEntity) obj;

            DateTime now = new DateTime();

            String username = UserContext.getUsername();
            String id = baseEntity.getId();

            // New entity
            if (id == null) {
                baseEntity.setId(UUID.randomUUID().toString());
                baseEntity.setCreatedDate(now);
                baseEntity.setCreatedBy(username);
            }

            baseEntity.setLastModifiedBy(username);
            baseEntity.setLastModifiedDate(now);
        }
    }

}
