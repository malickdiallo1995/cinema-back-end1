package org.sid.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private double longitude,latitude,altitude;
    private int nombreSales;

    /**
     *     Association cinema salle
     *     Collection || liste de salle dans cinema
     *     Cinema contient plusieurs salles
     */
    @OneToMany(mappedBy = "cinema")
    private Collection<Salles> salles;

    /**
     * le cinema se trouve dans une ville
     */
    @ManyToOne
    private Ville ville;
}
