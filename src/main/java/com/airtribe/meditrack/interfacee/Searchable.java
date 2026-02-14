package com.airtribe.meditrack.interfacee;

import java.util.List;

public interface Searchable<T> {

    List<T> searchByName(String name);

    default void printSearchResults(List<T> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            results.forEach(System.out::println);
        }
    }
}
