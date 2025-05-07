package spring.boot.desafio.uber.model;

import lombok.Data;

@Data
public class MovieLocation {

    private String title;
    private int release_year;
    private String locations;
    private String distributor;
    private String director;
    private String actor_1;
    private String actor_2;
    private String actor_3;
    private double latitude;
    private double longitude;
}
