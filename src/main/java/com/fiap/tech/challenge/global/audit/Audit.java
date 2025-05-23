package com.fiap.tech.challenge.global.audit;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import static com.fiap.tech.challenge.global.util.HashUtil.generateHash;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class, AuditEntityListener.class })
public abstract class Audit implements Serializable {

    protected Audit() {
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "hash_id", nullable = false, updatable = false)
    private String hashId = generateHash();

    @CreatedDate
    @Column(name = "created_in", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdIn = new Date();

    @LastModifiedDate
    @Column(name = "updated_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedIn = new Date();

    @Column(name = "deleted_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedIn;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = Boolean.FALSE;

    @Transient
    private transient Audit auditSavedState;

    public void saveState(Audit auditSavedState) {
        this.auditSavedState = auditSavedState;
    }
}