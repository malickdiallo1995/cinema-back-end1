package org.sid.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Salles implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;

    /**
     *  la salle se trouve dans un Cinema
     */
    @ManyToOne
     private Cinema cinema;

    @OneToMany(mappedBy = "salles")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Place> places;

    @OneToMany(mappedBy = "salles")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
}
