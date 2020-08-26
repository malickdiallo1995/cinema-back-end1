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
public class Ville implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private double longitude, latitude,altitude;

    /**
     * dans une ville on a une collection || liste de cinemas
     */
    @OneToMany(mappedBy = "ville")
    private Collection<Cinema>cinemas;
}
