package org.sid.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Projection implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateProject;
    private double prix;

    /**
     * Une projection se deroule dans une salle et concerne un film
     */
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Salles salles;
    @ManyToOne
    private Film film;
    @OneToMany(mappedBy = "projection")
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;
}
