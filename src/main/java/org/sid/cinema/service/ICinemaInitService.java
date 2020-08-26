package org.sid.cinema.service;

/**
 * Initialisation des donnees de chaque entite
 */
public interface ICinemaInitService {
    public void initVilles();
    public void initCinemas();
    public void initSalles();
    public void initPlaces();
    public void initSeances();
    public void initCategories();
    public void initFilms();
    public void initProjections();
    public void initTickets();
}
