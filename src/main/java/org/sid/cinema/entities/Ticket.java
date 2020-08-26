package org.sid.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ticket implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double pirx;
    @Column(unique = false,nullable = true )
    private Integer codePayment;
    private  boolean reserve;

    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;

}
