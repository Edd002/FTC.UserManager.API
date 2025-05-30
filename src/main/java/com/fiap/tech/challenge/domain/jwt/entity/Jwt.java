package com.fiap.tech.challenge.domain.jwt.entity;

import com.fiap.tech.challenge.domain.jwt.JwtEntityListener;
import com.fiap.tech.challenge.domain.jwt.enumerated.constraint.JwtConstraint;
import com.fiap.tech.challenge.domain.user.entity.User;
import com.fiap.tech.challenge.global.audit.Audit;
import com.fiap.tech.challenge.global.audit.constraint.ConstraintMapper;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_jwt")
@SQLDelete(sql = "UPDATE t_jwt SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ JwtEntityListener.class })
@ConstraintMapper(constraintClass = JwtConstraint.class)
public class Jwt extends Audit implements Serializable {

    protected Jwt() {}

    public Jwt(@NonNull Long id) {
        this.setId(id);
    }

    public Jwt(@NonNull String bearerToken, @NonNull User user) {
        this.setBearerToken(bearerToken);
        this.setUser(user);
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_JWT")
    @SequenceGenerator(name = "SQ_JWT", sequenceName = "SQ_JWT", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "bearer_token", nullable = false)
    private String bearerToken;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @Transient
    private transient Jwt jwtSavedState;

    public void saveState(Jwt jwtSavedState) {
        this.jwtSavedState = jwtSavedState;
    }

    @Override
    public void setUpdatedIn(Date updatedIn) {
        super.setUpdatedIn(updatedIn);
    }
}