package com.fiap.tech.challenge.domain.city;

import com.fiap.tech.challenge.domain.address.Address;
import com.fiap.tech.challenge.domain.city.enumerated.CityConstraintEnum;
import com.fiap.tech.challenge.domain.state.State;
import com.fiap.tech.challenge.global.audity.Audity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_city")
@SQLDelete(sql = "UPDATE t_city SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = {"id"})
@EntityListeners({ CityEntityListener.class })
public class City extends Audity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_CITY")
    @SequenceGenerator(name = "SQ_CITY", sequenceName = "SQ_CITY", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_state", nullable = false)
    private State state;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Address> addressList;

    @Transient
    private transient City citySavedState;

    public void saveState(City citySavedState) {
        this.citySavedState = citySavedState;
    }

    @Override
    public String getConstraintErrorMessage(String constraintName) {
        return CityConstraintEnum.valueOf(constraintName.toUpperCase()).getErrorMessage();
    }
}