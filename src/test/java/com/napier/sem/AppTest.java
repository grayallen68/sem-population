package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
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
    void printCountryReportTest() {
        ArrayList<Country> countries = new ArrayList<Country>(){{
            add(new Country("CHN", "China", "Asia", "Eastern Asia", 1277558000, 000));
            add(new Country("IND", "India", "Asia", "Southern and Central Asia", 1013662000, 000));
            add(new Country("USA", "United States ", "North America", "North America", 278357000, 000));
            add(new Country("IDN", "Indonesia", "Asia", "Southeast Asia", 212107000, 000));
            add(new Country("BRA", "Brazil", "South America", "South America", 170115000, 000));
        }};

        app.printCountryReport(countries);
    }

    @Test
    void printCountryReportTestNull() {
        ArrayList<Country> countries = null;
        app.printCountryReport(countries);
    }

    @Test
    void printCountryReportTestEmpty() {
        ArrayList<Country> countries = new ArrayList<>();
        app.printCountryReport(countries);
    }

    @Test
    void printCountryReportTestNullMember() {
        ArrayList<Country> countries = new ArrayList<Country>(){{
            add(new Country("CHN", "China", "Asia", "Eastern Asia", 1277558000, 000));
            add(new Country("IND", "India", "Asia", "Southern and Central Asia", 1013662000, 000));
            add(new Country("USA", "United States ", "North America", "North America", 278357000, 000));
            add(new Country("IDN", "Indonesia", "Asia", "Southeast Asia", 212107000, 000));
            add(new Country("BRA", "Brazil", "South America", "South America", 170115000, 000));
        }};
        countries.set(3, null);
        app.printCountryReport(countries);
    }

    //CITY REPORTS

    @Test
    void printCityReportTest() {
        ArrayList<City> cities = new ArrayList<City>(){{
            add(new City(01, "Mumbai", "India", "Maharashtra", 10500000));
            add(new City(02, "Seoul", "South Korea", "Seoul", 9981619));
            add(new City(03, "São Paulo", "Brazil", "São Paulo", 9968485));
            add(new City(04, "Shanghai", "China", "Shanghai", 9696300));
            add(new City(05, "Jakarta", "Indonesia", "Jakarta Raya", 9604900));
        }};
        app.printCityReport(cities);
    }

    @Test
    void printCityReportTestNull() {
        ArrayList<City> cities = null;
        app.printCityReport(cities);
    }

    @Test
    void printCityReportTestEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        app.printCityReport(cities);
    }

    @Test
    void printCityReportTestNullMember() {
        ArrayList<City> cities = new ArrayList<City>(){{
            add(new City(01, "Mumbai", "India", "Maharashtra", 10500000));
            add(new City(02, "Seoul", "South Korea", "Seoul", 9981619));
            add(new City(03, "São Paulo", "Brazil", "São Paulo", 9968485));
            add(new City(04, "Shanghai", "China", "Shanghai", 9696300));
            add(new City(05, "Jakarta", "Indonesia", "Jakarta Raya", 9604900));
        }};
        cities.set(3, null);
        app.printCityReport(cities);
    }

    //CAPITAL CITY REPORTS
    @Test
    void printCapCityReportTest() {
        ArrayList<City> cities = new ArrayList<City>(){{
            add(new City(01, "Mumbai", "India", "Maharashtra", 10500000));
            add(new City(02, "Seoul", "South Korea", "Seoul", 9981619));
            add(new City(03, "São Paulo", "Brazil", "São Paulo", 9968485));
            add(new City(04, "Shanghai", "China", "Shanghai", 9696300));
            add(new City(05, "Jakarta", "Indonesia", "Jakarta Raya", 9604900));
        }};
        app.printCapitalCityReport(cities);
    }

    @Test
    void printCapCityReportTestNull() {
        ArrayList<City> cities = null;
        app.printCapitalCityReport(cities);
    }

    @Test
    void printCapCityReportTestEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        app.printCapitalCityReport(cities);
    }

    @Test
    void printCapCityReportTestNullMember() {
        ArrayList<City> cities = new ArrayList<City>(){{
            add(new City(01, "Mumbai", "India", "Maharashtra", 10500000));
            add(new City(02, "Seoul", "South Korea", "Seoul", 9981619));
            add(new City(03, "São Paulo", "Brazil", "São Paulo", 9968485));
            add(new City(04, "Shanghai", "China", "Shanghai", 9696300));
            add(new City(05, "Jakarta", "Indonesia", "Jakarta Raya", 9604900));
        }};
        cities.set(3, null);
        app.printCapitalCityReport(cities);
    }

    //TESTS FOR PRINTING SINGLE RECORDS
    //these functions take a country or city object as a parameter
    @Test
    void printRecordTestsNull() {
        app.printCountryRecord(null);
        app.printCityRecord(null);
        app.printCapitalCityRecord(null);
    }

}
