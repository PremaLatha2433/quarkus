package org.bnbc.graphql;

import org.bnbc.graphql.pojo.Ally;
import org.bnbc.graphql.pojo.Character;
import org.bnbc.graphql.pojo.Film;
import org.bnbc.graphql.pojo.Hero;
import org.bnbc.graphql.service.GalaxyService;
import org.bnbc.graphql.service.SearchResult;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

/**
 * GraphQL is a query language for APIs and a runtime for fulfilling those queries
 * with your existing data. GraphQL provides a complete and understandable description
 * of the data in your API, gives clients the power to ask for exactly what they need
 * and nothing more, makes it easier to evolve APIs over time, and enables powerful developer tools
 * GraphQL was originally developed by Facebook in 2012 and has been an open standard since 2015.
 *
 * GraphQL is not a replacement for REST API specification but merely an alternative.
 * Unlike REST, GraphQL API’s have the ability to benefit the client by:
 *
 * Preventing Over-fetching and Under-fetching
 * REST APIs are server-driven fixed data responses that cannot be determined by the client.
 * Although the client does not require all the fields the client must retrieve all the data
 * hence Over-fetching. A client may also require multiple REST API calls according to the
 * first call (HATEOAS) to retrieve all the data that is required thereby Under-fetching.
 *
 * API Evolution
 * Since GraphQL API’s returns data that are requested by the client adding additional fields
 * and capabilities to existing API will not create breaking changes to existing clients.
 */
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
    @Query
   public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
        return service.getHeroesBySurname(surname);
    }
}