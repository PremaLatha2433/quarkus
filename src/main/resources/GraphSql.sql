how to add graphql to your project : ./mvnw quarkus:add-extension -Dextensions='quarkus-smallrye-graphql' it will add below dependency in your pom
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-smallrye-graphql</artifactId>
</dependency>
url:http://localhost:8080/q/graphql-ui/
schema url:http://localhost:8080/graphql/schema.graphql
1.{
allFilms{
  director,episodeID,releaseDate,title
},
  allHeros{
    name,surname,height,mass,darkSide,episodeIds,lightSaber
}
  getFilmById(episodeID:4){
  releaseDate,director,title,episodeID
}
}
2.query getAllies {
    allies {
        name
        surname
    }
}
3.query {
  getFilmById(episodeID: 5) {
    title
    director
    releaseDate
  }
}

4.query getFilm {
  film(filmId: 6) {
    title
    director
    releaseDate
    episodeID
  }
}

5.query getFilm {
  film0: film(filmId: 5) {
    title
    director
    releaseDate
    episodeID
  }
  film1: film(filmId: 6) {
    title
    director
    releaseDate
    episodeID
  }
}
6.query getFilmHeroes {
  film(filmId: 6) {
    title
    director
    releaseDate
    episodeID
    heroes {
      name
      height
      mass
      darkSide
      lightSaber
    }
  }
}
7.query getCharacters {
    characters {
        name
        surname
    }
}
8.query searchTheGalaxy {
    search(query: "a") {
        ... on Film {
            title
            director
        }
        ... on Character {
            name
            surname
        }
    }
}
9.mutation addHero {
  createHero(hero: {
      name: "ram",
      surname: "choclate"
      height: 1.85
      mass: 80
      darkSide: false
      episodeIds: [4, 5, 6]
  	}
  )
  {
    name
    surname
  }
}
10.mutation DeleteHero {
  deleteHero(id :3){
    name
    surname
  }
}
11.query heroWithDefaultSurname {
  heroesWithSurname{
    name
    surname
    lightSaber
  }
}
12.query heroWithSurnames {
  heroesWithSurname(surname: "Vader") {
    name
    surname
    lightSaber
  }
}