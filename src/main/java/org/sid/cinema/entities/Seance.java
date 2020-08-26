package org.sid.cinema.entities;

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
public class Seance implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Format du date ==> recuperer Heure minute et seconde
     */
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @OneToMany(mappedBy = "seance")
    private Collection<Projection> projections;
}
