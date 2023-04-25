package com.napier.sem;

public class Country {
    //country class to be used to represent records in the country reports
    //columns: code | name | continent | region | population | capital

    private String code; //primary key
    private String name;
    private String continent;
    private String region;
    private long population;
    private int capitalID; //foreign key from city table

    //construstor
    public Country(String code, String name, String continent, String region, long population, int capitalID){
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capitalID = capitalID;
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getContinent(){
        return continent;
    }
    public void setContinent(String continent){
        this.continent = continent;
    }

    public String getRegion(){
        return region;
    }
    public void setRegion(String region){
        this.region = region;
    }

   public long getPopulation(){
        return population;
   }
   public void setPopulation(int population){
        this.population = population;
   }

   public int getCapital(){
        return capitalID;
   }

    public void setCapital(int capitalID) {
        this.capitalID = capitalID;
    }

}
