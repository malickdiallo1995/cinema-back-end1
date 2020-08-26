package org.sid.cinema.service;

import org.sid.cinema.dao.*;
import org.sid.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    public VilleRepository villeRepository;
    @Autowired
    public  CinemaRepository cinemaRepository;
    @Autowired
    public SallesRepository sallesRepository;
    @Autowired
    public PlaceRepository placeRepository;
    @Autowired
    public SeanceRepository seanceRepository;
    @Autowired
    public CategorieRepository categorieRepository;
    @Autowired
    public FilmRepository filmRepository;
    @Autowired
    public ProjectionRepository projectionRepository;
    @Autowired
    public TicketRepository ticketRepository;
    /**
     * Ajouter des Villes
     */
    @Override
    public void initVilles() {
        //Liste de villes
        Stream.of("Dakar","Kaolack","Saint-Louis","Thies")
        .forEach(villeName->{
            //Pour chaque ville on l'ajoute a la table Ville dans la base
             Ville ville = new Ville();
             ville.setName(villeName);
             villeRepository.save(ville);
        });
    }

    /**
     * Ajouter des cinemas dans une ville
     */
    @Override
    public void initCinemas() {
            //Liste des villes
            villeRepository.findAll()
                .forEach(ville->{
                        Stream.of("CinemasI","CinemasII","CinemasIII","CinemasIV")
                                .forEach(cinemaName->{
                                    Cinema cinema = new Cinema();
                                    cinema.setNom(cinemaName);
                                    cinema.setNombreSales(3+(int)(Math.random()*7));
                                    cinema.setVille(ville);
                                    cinemaRepository.save(cinema);
                                });
                    });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll()
                .forEach(cinema->{
                    for (int i=0;i<cinema.getNombreSales(); i++){
                        Salles salles = new Salles();
                        salles.setName("Salle "+i+1);
                        salles.setCinema(cinema);
                        salles.setNombrePlace(15+(int)(Math.random()*10));
                        sallesRepository.save(salles);
                    }
                });
    }

    @Override
    public void initPlaces() {
        sallesRepository.findAll()
            .forEach(salle->{
                for (int i=0; i < salle.getNombrePlace(); i++){
                    Place place = new Place();
                    place.setNumero(i+1);
                    place.setSalles(salle);
                    placeRepository.save(place);
                }
            });
    }

    @Override
    public void initSeances() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(seance->{
            Seance seance1 = new Seance();
            try {
                seance1.setHeureDebut(df.parse(seance));
                seanceRepository.save(seance1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Actions","Drame","Fictions","TeleRealité").forEach(categorie->{
            Categorie categorie1 = new Categorie();
            categorie1.setName(categorie);
            categorieRepository.save(categorie1);
        });
    }

    @Override
    public void initFilms() {
        double[] durees = new double[]{1,1.5,2,2.5,3};
        List<Categorie> categorieList=  categorieRepository.findAll() ;
        Stream.of("Game of Thrones","Avengers","Wiri Wiri","Thores","Black Panthers").forEach(titreFilm->{
            Film film1 = new Film();
            film1.setTitre(titreFilm);
            film1.setDuree(durees[new Random().nextInt(durees.length)]);
            film1.setPhoto(titreFilm.replaceAll(" ", ""));
            film1.setCategorie(categorieList.get(new Random().nextInt(categorieList.size())));
            filmRepository.save(film1);
         });
    }

    @Override
    public void initProjections() {
        double[] prix = new double[]{30,51,60,70,80,90,100};
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salles -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceRepository.findAll() .forEach(seance -> {
                            Projection projection = new Projection();
                            projection.setDateProject(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prix[new Random().nextInt(prix.length)]);
                            projection.setSeance(seance);
                            projection.setSalles(salles);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection -> {
             projection.getSalles().getPlaces().forEach(place -> {
                 Ticket ticket = new Ticket();
                 ticket.setPlace(place);
                 ticket.setPirx(projection.getPrix());
                 ticket.setProjection(projection);
                 ticket.setReserve(false);
                 ticketRepository.save(ticket);
             });
        });
    }
}
