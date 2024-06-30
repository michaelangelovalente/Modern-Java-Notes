import java.util.*;
import java.util.function.*;
import java.util.stream.*;

//##### ADDING INTERMEDIATE OPERATIONS ON A STREAM
public class StreamExampleTwo {
    public static void main(String[] args) {

        // ###
        List<String> strings = List.of("one", "two", "three", "four");

        // --- Issue: only intermediary operations --> no terminal operations.
        // Function<String, Integer> toLength = String::length;
        // Stream<Integer> ints = strings.stream()
        // .map(toLength);

        Function<String, Integer> toLength = String::length;
        List<Integer> lengths = strings.stream()
                .map(toLength)
                .collect(Collectors.toList());

        System.out.println("lengths = " + lengths);
        System.out.println("----------------");
        IntSummaryStatistics stats = strings.stream()
                .mapToInt(String::length)
                .summaryStatistics();
        System.out.println("stats = " + stats);

        // ###### Filtering a stream
        // .....

        // ##### Flatmapping a Stream to handle 1:p Relations
        List<State> states = List.of(new State(), new State()); // ....
        // example case of imperatuve (flat)map-reduce
        // int totalPop = 0;
        // for( State state: states ){
        // for( City city: state.getCitites() ){
        // totalPop += city.getPopulation();
        // }
        // }

        // version using streams
        // int totalPop = 0;
        // totalPop += states.
        // System.out.println("Total population = " + totalPop);

        // flatmap takes a Function as an arg that retursn a Stream object
        // Function<Strain, Stream<City>> stateToCity = state ->
        // state.getCitites().stream();
        int totalPopFlat = states.stream()
                .flatMap(state -> state.getCitites().stream())
                .map(City::getPopulation)
                .sum();
        System.out.println("Total population = " + totalPopFlat);

        // #### Using flatmap and MapMulti to Validate Elements Transformation

        // problematic solution for conversion from Str to Ints
        // bad example (below)
        Predicate<String> isANum = s -> {
            try {
                int i = Integer.parseInt(s); // double conversion! Once during the check and another during the
                                             // conversion
                return true;
            } catch (NumberFormatException nfe) {
                return false; // do not return after a catch!
            }
        };

        // Improved solution: conversion if you actually have a valid number
        Function<String, Stream<Integer>> flatParser = s -> {
            try {
                return Stream.of(Integer.parseInt(s));
            } catch (NumberFormatException nfe) {
            }
            return Stream.empty();
        };

        List<String> stringsOfNumbers = List.of("1", " ", "2", "3 ", "", "3");
        List<Integer> ints = stringsOfNumbers.stream()
                .flatMap(flatParser)
                .collect(Collectors.toList());

        System.out.println("ints = " + ints);
        // The issue with the code above... A stream is created for each element of the
        // stream that needs to be processed
        // solution mapMulti() --> when you create n streams you can use mapMulti()
        List<Integer> ints2 = stringsOfNumbers.stream()
                .<Integer>mapMulti((string, consumer) -> {
                    try {
                        consumer.accept();
                    } catch (NumberFormatException ignored) {

                    }
                })
                .collect(Collectors.toList());

        System.out.println("ints = " + ints2);

    }

    class City {
        private String name;
        private int population;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        // cuntructors, getters, toString, equals and hashCode...
    }

    class State {
        private String name;
        private List<City> citites;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<City> getCitites() {
            return citites;
        }

        public void setCitites(List<City> citites) {
            this.citites = citites;
        }
    }
}