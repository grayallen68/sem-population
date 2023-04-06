package com.napier.sem;

public class City {
    //class to represent records in city table of world database
    //table: | id | name | countryCode | District | Population |

    private int ID;
    private String name;
    private String countryCode; //foreign key from country table
    private String district;
    private long population;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPopulation(long population) {
        this.population = population;
    }


    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public long getPopulation() {
        return population;
    }

}
