package org.sid.cinema.dao;

import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Salles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface SallesRepository extends JpaRepository<Salles,Long> {
}
