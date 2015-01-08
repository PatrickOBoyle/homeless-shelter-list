package com.patrickoboyle.computersandsociety;

// Class to create new shelters as required

public class HomelessShelters {

    private String shelterName = "";
    private String shelterAddress ="";
    private int shelterVacancies = 0;
    private double walkingTime = 0.0;
    private double shelterLng = 0.0;
    private double shelterLat = 0.0;

    public HomelessShelters(String name, String address, int vacancies, double walkTime, double lng, double lat){
        super();
        // Assign parameter values to private variables
        this.shelterName = name;
        this.shelterAddress = address;
        this.shelterVacancies = vacancies;
        this.walkingTime = walkTime;
        this.shelterLng = lng;
        this.shelterLat = lat;
    }

    public String getShelterName(){
        return shelterName;
    }
    public String getShelterAddress(){
        return shelterAddress;
    }
    public int getVacancies(){ return shelterVacancies; }
    public double getWalkingTime(){ return walkingTime; }
    public double getShelterLng(){
        return shelterLng;
    }
    public double getShelterLat(){
        return shelterLat;
    }

}
