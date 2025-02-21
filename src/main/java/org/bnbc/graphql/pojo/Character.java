package org.bnbc.graphql.pojo;

import org.bnbc.graphql.service.SearchResult;

public interface Character extends SearchResult {


    String getName();
    String getSurname();
}
