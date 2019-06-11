package com.example.rsserver.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Persistable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonIgnore
    private LocalDateTime updatedAt;

    @Version
    @Column @JsonIgnore
    private int version;

    @Transient
    @JsonIgnore
    @Override
    public boolean isNew() {
        return (null == getId());
    }

    @PrePersist
    void preCreate() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof AbstractEntity
                && new EqualsBuilder()
                .append(this.getClass(), ((AbstractEntity) other).getClass())
                .append(this.getId(), ((AbstractEntity) other).getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).append(this.getId()).toHashCode();
    }

    @Override
    public String toString() {
        return String.format("%s#%s", this.getClass().getSimpleName(), this.getId());
    }

}
