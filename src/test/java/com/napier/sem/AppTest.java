package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 3000);
    }
    //when creating testing functions consider
        //    null.
        //    an empty list.
        //    a list with null member in it.
        //    a list with all non-null members (a normal list).

    //TESTS FOR PRINTING REPORTS
    //these functions take lists of Country objects or city objects then output them in a report

    //COUNTRY REPORTS
    @Test
    void printCountryReportTest(){
        ArrayList<Country> countries = app.getCountries(10);
        app.printCountryReport(countries);
    }
    @Test
    void printCountryReportTestNull(){
        ArrayList<Country> countries = null;
        app.printCountryReport(countries);
    }

    @Test
    void printCountryReportTestEmpty(){
        ArrayList<Country> countries = app.getCountries(0);
        app.printCountryReport(countries);
    }

    @Test
    void printCountryReportTestNullMember(){
        ArrayList<Country> countries = app.getCountries(10);
        countries.set(4, null);
        app.printCountryReport(countries);
    }

    //CITY REPORTS

    @Test
    void printCityReportTest(){
        ArrayList<City> cities = app.getCities(10);
        app.printCityReport(cities);
    }
    @Test
    void printCityReportTestNull(){
        ArrayList<City> cities = null;
        app.printCityReport(cities);
    }

    @Test
    void printCityReportTestEmpty(){
        ArrayList<City> cities = app.getCities(0);
        app.printCityReport(cities);
    }

    @Test
    void printCityReportTestNullMember(){
        ArrayList<City> cities = app.getCities(10);
        cities.set(4, null);
        app.printCityReport(cities);
    }

    //CAPITAL CITY REPORTS
    @Test
    void printCapCityReportTest(){
        ArrayList<City> cities = app.getCapitalCities(10);
        app.printCapitalCityReport(cities);
    }
    @Test
    void printCapCityReportTestNull(){
        ArrayList<City> cities = null;
        app.printCapitalCityReport(cities);
    }

    @Test
    void printCapCityReportTestEmpty(){
        ArrayList<City> cities = app.getCapitalCities(0);
        app.printCapitalCityReport(cities);
    }

    @Test
    void printCapCityReportTestNullMember(){
        ArrayList<City> cities = app.getCapitalCities(10);
        cities.set(4, null);
        app.printCapitalCityReport(cities);
    }

    //TESTS FOR PRINTING SINGLE RECORDS
    //these functions take a country or city object as a parameter
    @Test
    void printRecordTestsNull()
    {
        app.printCountryRecord(null);
        app.printCityRecord(null);
        app.printCapitalCityRecord(null);
    }

    //TESTS FOR GETTING SINGLE COUNTRY FROM THE DATABASE
    //these functions query the db based on a parameter
    @Test
    void getCountryTests(){
        //null
        app.getCountryByName(null);
        app.getCountryByCode(null);

        //not in db
        app.getCountryByName("nonexistent");
        app.getCountryByCode("nonexistent");

    }

    @Test
    void getCityTests(){
        //negative value, not in db
        app.getCityByID(-2);
    }

    //TESTING FUNCTIONS THAT RETURN A LIST OF COUNTRIES
    //these functions are passed strings and integer(counts) that affect the query
    @Test
    void getCountryListTest(){
        //negative count
        app.getCountries(-20);
        app.getCountriesInContinent("Asia", -20);
        app.getCountriesInRegion("Central America", -20);

        //count greater than database record count
        app.getCountries(10000);
        app.getCountriesInContinent("Asia", 10000);
        app.getCountriesInRegion("Central America", 10000);

        //passed in region or continent not in database
        app.getCountriesInContinent("notfound", 10);
        app.getCountriesInRegion("notfound", 10);

        app.getAllCountriesInContinent("notfound");
        app.getAllCountriesInRegion("notfound");

        //null strings
        app.getCountriesInContinent(null, 10);
        app.getCountriesInRegion(null, 10);

        app.getAllCountriesInContinent(null);
        app.getAllCountriesInRegion(null);

    }

    //TESTING FUNCTIONS THAT RETURN A LIST OF CITIES
    //these functions are passed strings and integer(counts) that affect the query
    @Test
    void getCityListTest(){
        //negative count
        app.getCities(-10);
        app.getCitiesInCountry("China", -10);
        app.getCitiesInDistrict("New York", -10);
        app.getCitiesInRegion("Central America", -10);
        app.getCitiesInContinent("Asia", -10);

        //count greater than database cities count
        app.getCities(100000);
        app.getCitiesInCountry("China", 100000);
        app.getCitiesInDistrict("New York", 100000);
        app.getCitiesInRegion("Central America", 100000);
        app.getCitiesInContinent("Asia", 100000);

        //passed in region, continent, country or district not in databse
        app.getCitiesInCountry("notfound", 10);
        app.getCitiesInDistrict("notfound", 10);
        app.getCitiesInRegion("notfound", 10);
        app.getCitiesInContinent("notfound", 10);

        app.getAllCitiesInCountry("notfound");
        app.getAllCitiesInDistrict("notfound");
        app.getAllCitiesInRegion("notfound");
        app.getAllCitiesInContinent("notfound");

        //null strings
        app.getCitiesInCountry(null, 10);
        app.getCitiesInDistrict(null, 10);
        app.getCitiesInRegion(null, 10);
        app.getCitiesInContinent(null, 10);

        app.getAllCitiesInCountry(null);
        app.getAllCitiesInDistrict(null);
        app.getAllCitiesInRegion(null);
        app.getAllCitiesInContinent(null);

    }

    //TESTING FUNCTIONS THAT RETURN A LIST OF CITIES (CAPITALS)
    @Test
    void getCapCityListTest(){
        //negative count
        app.getCapitalCities(-10);
        app.getCapitalCitiesInRegion("Central America", -10);
        app.getCapitalCitiesInContinent("Asia", -10);


        //count greater than database cities count
        app.getCapitalCities(100000);
        app.getCapitalCitiesInRegion("Central America", 100000);
        app.getCapitalCitiesInContinent("Asia", 100000);

        //passed in region, continent, country or district not in databse
        app.getCapitalCitiesInRegion("notfound", 10);
        app.getCapitalCitiesInContinent("notfound", 10);

        app.getAllCapitalCities();
        app.getAllCapitalCitiesInContinent("notfound");
        app.getAllCapitalCitiesInRegion("notfound");

        //null strings
        app.getCapitalCitiesInRegion(null, 10);
        app.getCapitalCitiesInContinent(null, 10);

        app.getAllCapitalCities();
        app.getAllCapitalCitiesInContinent(null);
        app.getAllCapitalCitiesInRegion(null);
    }

    //POPULATION REPORTS
    @Test
    void populationReportTests(){
        //valid values
        app.continentPopulationReport("Asia");
        app.regionPopulationReport("Central America");
        app.countryPopulationReport("Belize");

        //not in database
        app.continentPopulationReport("notfound");
        app.regionPopulationReport("notfound");
        app.countryPopulationReport("notfound");

        //null Strings
        app.continentPopulationReport(null);
        app.regionPopulationReport(null);
        app.countryPopulationReport(null);

    }

    //POPULATION INFORMATION REQUESTS
    @Test
    void populationInfoTests(){
        //valid values
        app.getWorldPopulation();
        app.getContinentPopulation("Asia");
        app.getRegionPopulation("Central America");
        app.getCountryPopulation("China");
        app.getDistrictPopulation("New York");
        app.getCityPopulation("New York");

        //not in database
        app.getContinentPopulation("notfound");
        app.getRegionPopulation("notfound");
        app.getCountryPopulation("notfound");
        app.getDistrictPopulation("notfound");
        app.getCityPopulation("notfound");

        //null strings
        app.getContinentPopulation(null);
        app.getRegionPopulation(null);
        app.getCountryPopulation(null);
        app.getDistrictPopulation(null);
        app.getCityPopulation(null);
    }

    //LANGUAGE REPORT FUNCTIONS
    @Test
    void testGetLanguagePopulation(){
        //valid
        app.getLanguagePopulation("English");

        //not in database
        app.getLanguagePopulation("notfound");

        //null value
        app.getLanguagePopulation(null);
    }

}