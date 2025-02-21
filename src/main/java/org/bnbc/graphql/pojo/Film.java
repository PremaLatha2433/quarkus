package org.bnbc.graphql.pojo;

import org.bnbc.graphql.service.SearchResult;

import java.time.LocalDate;

public class Film implements SearchResult {

    public String title;
    public Integer episodeID;
    public String director;
    public LocalDate releaseDate;

}
