package org.sid.cinema.entities;

import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Date;

@Projection(
        name = "p1",
        types = {org.sid.cinema.entities.Projection.class}
)
@CrossOrigin("*")
public interface ProjectionProj {
    /**
     * Infos a retourne
     */
    public Long getId();
    public double getPrix();
    public Date getdateProject();
    public Salles getSalles();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTickets();

}
