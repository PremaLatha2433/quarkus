package org.bnbc.graphql;

import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
public class FilmResource {


    private final GalaxyService service;

    public FilmResource(GalaxyService service) {
        this.service = service;
    }

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query("allHeros")
    @Description("Get all Heroes from a galaxy far far away")
    public List<Hero> getHeroes() {
        return service.getHeroes();
    }
    @Query("getFilmById")
    @Description("Get one film from list of films")
    public Film getFilmById(@Name("episodeID") int episodeID){
        return  service.getFilm(episodeID);
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }
    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }
    @Query
    public List<Ally> allies() {
        return service.getAllAllies();
    }
    @Query
    @Description("Get all characters from a galaxy far far away")
    public List<Character> characters() {
        return service.getAllCharacters();
    }
    @Query
    @Description("Search for heroes or films")
    public List<SearchResult> search(String query) {
        return service.search(query);
    }

    /**
     * Mutations are used when data is created, updated or deleted.
     * @param hero
     * @return
     */
    @Mutation
    public Hero createHero(Hero hero) {
        service.addHero(hero);
        return hero;
    }

    @Mutation
    public Hero deleteHero(int id) {
        return service.deleteHero(id);
    }
}