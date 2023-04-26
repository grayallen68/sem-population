package com.napier.sem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Country getCountryByName(String name){
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

//            String strSelect =
//                    "SELECT emp_no, first_name, last_name "
//                            + "FROM employees "
//                            + "WHERE emp_no = " + ID;

            String strSelect =
                    "SELECT * FROM country WHERE country.name = '" + name + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check one is returned
            if (rset.next())
            {
                String countryCode = rset.getString("country.code");
                String countryName = rset.getString("country.name");
                String continent = rset.getString("country.continent");
                String region = rset.getString("country.region");
                int population = rset.getInt("country.population");
                int capitalID = rset.getInt("country.capital");

                Country country = new Country(countryCode, countryName, continent, region, population, capitalID);

                return country;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }
    public Country getCountryByCode(String countryCd){
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

//            String strSelect =
//                    "SELECT emp_no, first_name, last_name "
//                            + "FROM employees "
//                            + "WHERE emp_no = " + ID;

            String strSelect =
                    "SELECT * FROM country WHERE country.code = '" + countryCd + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check one is returned
            if (rset.next())
            {


                String countryCode = rset.getString("country.code");
                String countryName = rset.getString("country.name");
                String continent = rset.getString("country.continent");
                String region = rset.getString("country.region");
                int population = rset.getInt("country.population");
                int capitalID = rset.getInt("country.capital");

                Country country = new Country(countryCode, countryName, continent, region, population, capitalID);

                return country;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public City getCityByID(int cityID){
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT * FROM city WHERE city.id = '" + cityID + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check one is returned
            if (rset.next())
            {

                int id = rset.getInt("city.id");
                String name = rset.getString("city.name");
                String countryCode = rset.getString("city.countrycode");
                String district = rset.getString("city.district");
                long population = rset.getLong("city.population");

                City city = new City(id, name, countryCode, district, population);

                return city;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public City getCityByName(String cityName){
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT * FROM city WHERE city.name = '" + cityName + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check one is returned
            if (rset.next())
            {

                int id = rset.getInt("city.id");
                String name = rset.getString("city.name");
                String countryCode = rset.getString("city.countrycode");
                String district = rset.getString("city.district");
                long population = rset.getLong("city.population");

                City city = new City(id, name, countryCode, district, population);

                return city;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public void printCountryRecord(Country country){
        if (country != null)
        {
            //get city form id and extract the name

            City city = getCityByID(country.getCapital());
            //some countries don't have a capital code in database
            String capitalName = "n/a";
            if(city != null){
                capitalName = city.getName();
            }

            String countryRow = String.format(
                    "%-10s %-50s %-20s %-35s %-12s %-8s",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    capitalName
            );
            System.out.println(countryRow);
        }
    }
    public void printCityRecord(City city){
        if (city != null)
        {
            //get country by code and extract the name

            Country country = getCountryByCode(city.getCountryCode());
            //in case it didn't return a country name
            String countryName = "n/a";
            if(country != null){
                countryName = country.getName();
            }

            String cityRow = String.format(
                    "%-50s %-50s %-50s %-10s",
                    city.getName(),
                    countryName,
                    city.getDistrict(),
                    city.getPopulation()
            );

            System.out.println(cityRow);
        }
    }

    public void printCapitalCityRecord(City city){
        if (city != null)
        {
            //get country by code and extract the name

            Country country = getCountryByCode(city.getCountryCode());
            //in case it didn't return a country name
            String countryName = "n/a";
            if(country != null){
                countryName = country.getName();
            }

            String cityRow = String.format(
                    "%-50s %-50s %-10s",
                    city.getName(),
                    countryName,
                    city.getPopulation()
            );

            System.out.println(cityRow);
        }
    }

    //COUNTRY REPORTS

    public ArrayList<Country> getAllCountries(){
        //return a list of country objects

        try{
            Statement stmt = con.createStatement();

            //to keep the code simple, we will only request the country code here
            //the use the getCountryByCode function to create the object
            String strSelect =
                    "SELECT code FROM country ORDER BY country.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country;
                String code = rset.getString("country.code");
                //use the code to get a country object
                country = getCountryByCode(code);
                countries.add(country);
            }

            return countries;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<Country> getAllCountriesInContinent(String continent){
        //return a list of country objects
        //check if the country's continent name matches the passed in text
        try{
            Statement stmt = con.createStatement();

            //get code of the countries the match the requested continent
            String strSelect =
                    "SELECT code FROM country WHERE country.continent =  '" + continent + "' " +
                            "ORDER BY country.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country;
                String code = rset.getString("country.code");
                //use the code to get a country object
                country = getCountryByCode(code);
                countries.add(country);
            }

            return countries;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<Country> getAllCountriesInRegion(String region){
        //return a list of country objects
        //check if the country's continent name matches the passed in text
        try{
            Statement stmt = con.createStatement();

            //get code of the countries the match the requested continent
            String strSelect =
                    "SELECT code FROM country WHERE country.region =  '" + region + "' " +
                            "ORDER BY country.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country;
                String code = rset.getString("country.code");
                //use the code to get a country object
                country = getCountryByCode(code);
                countries.add(country);
            }

            return countries;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<Country> getCountries(int count){
        //limit the results of the query based on the count
        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get code of the countries the match the requested continent
            String strSelect =
                    "SELECT code FROM country " +
                    "ORDER BY country.population DESC " +
                    "LIMIT " + count;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country;
                String code = rset.getString("country.code");
                //use the code to get a country object
                country = getCountryByCode(code);
                countries.add(country);
            }

            return countries;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<Country> getCountriesInContinent(String continent,int count){
        //limit the results of the query based on the count
        if(continent == null){
            return null;
        }

        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get code of the countries the match the requested continent
            String strSelect =
                    "SELECT code FROM country " +
                    "WHERE country.continent = '"+ continent +"' " +
                    "ORDER BY country.population DESC " +
                    "LIMIT " + count;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country;
                String code = rset.getString("country.code");
                //use the code to get a country object
                country = getCountryByCode(code);
                countries.add(country);
            }

            return countries;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<Country> getCountriesInRegion(String region, int count){

        if(region == null){
            return null;
        }

        //limit the results of the query based on the count
        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get code of the countries that match the requested region
            String strSelect =
                    "SELECT code FROM country " +
                            "WHERE country.region = '"+ region +"' " +
                            "ORDER BY country.population DESC " +
                            "LIMIT " + count;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<Country> countries = new ArrayList<Country>();

            while (rset.next()) {
                Country country;
                String code = rset.getString("country.code");
                //use the code to get a country object
                country = getCountryByCode(code);
                countries.add(country);
            }

            return countries;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public void printCountryReport(ArrayList<Country> countries){
        if(countries == null || countries.size() <= 0){
            System.out.println("List Empty");
            return;
        }
        //generates a report from the passed in country list
        //print a line for each object in the array
        //formats the results to look like a table
        //call the printCountryRecord() function for each element in the arraylist
        String header = String.format(
                "%-10s %-50s %-20s %-35s %-12s %-8s",
                "Code",
                "Name",
                "Continent",
                "Region",
                "Population",
                "Capital"
        );
        System.out.println(header);
        for (Country c : countries){
            if (c == null)
                continue;
            printCountryRecord(c);
        }


    }

    //CITY REPORTS
    public ArrayList<City> getCities(int count){
        //Max count of cities is 4079
        //limit the results of the query based on the count
        //select all records from the city table a sort them based on population
        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get id of the cities and call the getCityById function
            String strSelect =
                    "SELECT id FROM city " +
                            "ORDER BY city.population DESC " +
                            "LIMIT " + count;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city;
                int id = rset.getInt("city.id");
                //use the id to get city object
                city = getCityByID(id);
                if(city != null){
                    cities.add(city);
                }

            }

            return cities;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }
    public ArrayList<City> getCitiesInContinent(String continent, int count){
        //limit the results of the query based on the count
        //select countries that match the countrycode of the object
        //then filter the results based on the country's continent field
        if(continent == null){
            return null;
        }

        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get id of the cities and call the getCityById function
            String strSelect =
                    "SELECT id FROM city " +
                            "ORDER BY city.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city;
                int id = rset.getInt("city.id");
                //use the id to get city object
                city = getCityByID(id);

                //only add city if the related country matches the passed in continent
                Country country = getCountryByCode(city.getCountryCode());

                if(country.getContinent().equals(continent)){
                    if(cities.size() < count && city!=null) {
                        cities.add(city);
                    }
                }

            }

            return cities;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<City> getCitiesInRegion(String region, int count){
        //limit the results of the query based on the count
        //select countries that match the countrycode of the object
        //then filter the results based on the country's region field
        if(region == null){
            return null;
        }

        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get id of the cities and call the getCityById function
            String strSelect =
                    "SELECT id FROM city " +
                            "ORDER BY city.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city;
                int id = rset.getInt("city.id");
                //use the id to get city object
                city = getCityByID(id);

                //only add city if the related country matches the passed in continent
                Country country = getCountryByCode(city.getCountryCode());

                if(country.getRegion().equals(region)){
                    if(cities.size() < count && city!=null) {
                        cities.add(city);
                    }
                }

            }

            return cities;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<City> getCitiesInCountry(String countryName, int count){
        //limit the results of the query based on the count
        //select countries that match the countrycode of the object
        //then filter the results based on the country's name field
        if(countryName == null){
            return null;
        }

        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get id of the cities and call the getCityById function
            String strSelect =
                    "SELECT id FROM city " +
                            "ORDER BY city.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city;
                int id = rset.getInt("city.id");
                //use the id to get city object
                city = getCityByID(id);

                //only add city if the related country matches the passed in continent
                Country country = getCountryByCode(city.getCountryCode());
                if(country.getName().equals(countryName)){
                    if(cities.size() < count && city != null) {
                        cities.add(city);
                    }
                }

            }

            return cities;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<City> getCitiesInDistrict(String district, int count){
        //limit the results of the query based on the count
        //select countries that match the countrycode of the object
        //then filter the results based on the city's district field

        if(district == null){
            return null;
        }

        if(count < 0)
            count = 0;
        try{
            Statement stmt = con.createStatement();

            //get id of the cities and call the getCityById function
            String strSelect =
                    "SELECT id FROM city " +
                            "ORDER BY city.population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //create an array of country objects to hold all the countries
            ArrayList<City> cities = new ArrayList<City>();

            while (rset.next()) {
                City city;
                int id = rset.getInt("city.id");
                //use the id to get city object
                city = getCityByID(id);

                //only add city if its district matches

                if(city.getDistrict().equals(district)){
                    if(cities.size() < count && city!=null) {
                        cities.add(city);
                    }
                }

            }

            return cities;

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public ArrayList<City> getAllCities(){
        //use the getCities Function but set the count to a high number to include all the cities
        return getCities(100000);
    }
    public ArrayList<City> getAllCitiesInContinent(String continent){
        //use the getCitiesInContinent Function but set the count to a high number to include all the cities
        return getCitiesInContinent(continent,100000);
    }

    public ArrayList<City> getAllCitiesInRegion(String region){
        //use the getCitiesInContinent Function but set the count to a high number to include all the cities
        return getCitiesInRegion(region,100000);
    }

    public ArrayList<City> getAllCitiesInCountry(String countryName){
        //use the getCitiesInContinent Function but set the count to a high number to include all the cities
        return getCitiesInCountry(countryName,100000);
    }

    public ArrayList<City> getAllCitiesInDistrict(String district){
        //use the getCitiesInContinent Function but set the count to a high number to include all the cities
        return getCitiesInDistrict(district,100000);
    }

    public void printCityReport(ArrayList<City> cities){
        //print a formatted table based on the passed in array of city objects
        //apply unit test to this function
        if(cities == null || cities.size() <= 0){
            System.out.println("List Empty");
            return;
        }

        String header = String.format(
                "%-50s %-50s %-50s %-10s",
                "Name",
                "Country",
                "District",
                "Population"
        );
        System.out.println(header);
        for (City c : cities){
            if (c == null)
                continue;
            printCityRecord(c);
        }
    }

    //CAPITAL CITY REPORTS
    public ArrayList<City> getCapitalCities(int count){
        if(count < 0){
            count = 0;
        }
        //return a list of the cities that are capitals
        ArrayList<City> cities = new ArrayList<City>();
        //get all the countries and use their capitalID to select cities from the city table
        ArrayList<Country> countries= getAllCountries();
        //create a list of cities and sort based on city population
        ArrayList<City> allCapitals = new ArrayList<City>();
        for(int i = 0; i<countries.size(); i++){
            int capID = countries.get(i).getCapital();
            City cit = getCityByID(capID);
            //if the city isn't null
            if(cit != null){
                allCapitals.add(cit);
            }

        }
        allCapitals.sort(Comparator.comparing(city -> city.getPopulation(), Comparator.reverseOrder()));
        for(int i = 0; i<count; i++){
            if(i >= allCapitals.size()){
                break;
            }
            //add to the cities list that will be returned
            cities.add(allCapitals.get(i));
        }
        return cities;
    }

    public ArrayList<City> getCapitalCitiesInContinent(String continent, int count){
        if(count < 0){
            count = 0;
        }
        //return a list of the cities that are capitals
        ArrayList<City> cities = new ArrayList<City>();
        //get all the countries and use their capitalID to select cities from the city table
        ArrayList<Country> countries= getAllCountries();
        //create a list of cities and sort based on city population
        ArrayList<City> allCapitals = new ArrayList<City>();
        for(int i = 0; i<countries.size(); i++){
            int capID = countries.get(i).getCapital();
            City cit = getCityByID(capID);
            //if the city isn't null
            if(cit != null && countries.get(i).getContinent().equals(continent)){
                allCapitals.add(cit);
            }

        }
        allCapitals.sort(Comparator.comparing(city -> city.getPopulation(), Comparator.reverseOrder()));
        for(int i = 0; i<count; i++){
            if(i >= allCapitals.size()){
                break;
            }
            //add to the cities list that will be returned
            cities.add(allCapitals.get(i));
        }
        return cities;
    }

    public ArrayList<City> getCapitalCitiesInRegion(String region, int count){
        if(count < 0){
            count = 0;
        }
        //return a list of the cities that are capitals
        ArrayList<City> cities = new ArrayList<City>();
        //get all the countries and use their capitalID to select cities from the city table
        ArrayList<Country> countries= getAllCountries();
        //create a list of cities and sort based on city population
        ArrayList<City> allCapitals = new ArrayList<City>();
        for(int i = 0; i<countries.size(); i++){
            int capID = countries.get(i).getCapital();
            City cit = getCityByID(capID);
            //if the city isn't null
            if(cit != null && countries.get(i).getRegion().equals(region)){
                allCapitals.add(cit);
            }

        }
        allCapitals.sort(Comparator.comparing(city -> city.getPopulation(), Comparator.reverseOrder()));
        for(int i = 0; i<count; i++){
            if(i >= allCapitals.size()){
                break;
            }
            //add to the cities list that will be returned
            cities.add(allCapitals.get(i));
        }
        return cities;
    }

    public ArrayList<City> getAllCapitalCities(){
        return getCapitalCities(100000);
    }
    public ArrayList<City> getAllCapitalCitiesInContinent(String continent){
        return getCapitalCitiesInContinent(continent, 100000);
    }
    public ArrayList<City> getAllCapitalCitiesInRegion(String region){
        return getCapitalCitiesInRegion(region, 100000);
    }
    public void printCapitalCityReport(ArrayList<City> cities){
        //print a formatted table based on the passed in array of city objects
        //apply unit test to this function
        if(cities == null || cities.size() <= 0){
            System.out.println("List Empty");
            return;
        }

        String header = String.format(
                "%-50s %-50s %-10s",
                "Name",
                "Country",
                "Population"
        );
        System.out.println(header);
        for (City c : cities){
            if (c == null)
                continue;
            printCapitalCityRecord(c);
        }
    }

    //POPULATION REPORTS
    public void continentPopulationReport(String continent){
        //query the database for countries in the requested continent
        //for each country, get its population from the country database
        //for all cities that match the country code, return a sum of their populations
        //divide the city sum by the country population to get a percentage
        if(continent == null){
            return;
        }

        long countriesSum = 0;
        long citiesSum = 0;
        ArrayList<Country> countries = getAllCountriesInContinent(continent);
        ArrayList<City> cities = getAllCitiesInContinent(continent);

        //get sum of population of countries in continent
        for(int i=0; i<countries.size(); i++){
            //increase the countriesSum
            countriesSum += countries.get(i).getPopulation();
        }

        for(int i=0; i<cities.size(); i++){
            //increase the countriesSum
            citiesSum += cities.get(i).getPopulation();
        }

        double percentageFactor = (double)citiesSum/(double)countriesSum;
        if(countriesSum < 1){
            System.out.println("Not Found");
            return;
        }

        System.out.println(citiesSum + " " + countriesSum + " " + percentageFactor);
        //Establishing record values
        String name = continent;
        long population = countriesSum;
        Double cityPercentage = BigDecimal.valueOf(percentageFactor*100.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        Double notCityPercentage = BigDecimal.valueOf((1.0 - percentageFactor)*100.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        String cityPopulation = citiesSum + " (" + cityPercentage +"%) ";
        String notCityPopulation = (countriesSum - citiesSum) + " (" + notCityPercentage +"%) ";

        String header = String.format(
                "%-20s %-30s %-30s %-30s",
                "Continent",
                "Total Population",
                "Living in Cities",
                "Not in Cities"
        );
        System.out.println(header);
        String report = String.format(
                "%-20s %-30s %-30s %-30s",
                name,
                population,
                cityPopulation,
                notCityPopulation
        );
        System.out.println(report);

    }

    public void regionPopulationReport(String region){
        //query the database for countries in the requested region
        //for each country, get its population from the country database
        //for all cities that match the country code, return a sum of their populations
        //divide the city sum by the country population to get a percentage
        if(region == null){
            return;
        }

        long countriesSum = 0;
        long citiesSum = 0;
        ArrayList<Country> countries = getAllCountriesInRegion(region);
        ArrayList<City> cities = getAllCitiesInRegion(region);

        //get sum of population of countries in continent
        for(int i=0; i<countries.size(); i++){
            //increase the countriesSum
            countriesSum += countries.get(i).getPopulation();
        }

        for(int i=0; i<cities.size(); i++){
            //increase the countriesSum
            citiesSum += cities.get(i).getPopulation();
        }

        double percentageFactor = (double)citiesSum/(double)countriesSum;
        if(countriesSum < 1){
            System.out.println("Not Found");
            return;
        }

        System.out.println(citiesSum + " " + countriesSum + " " + percentageFactor);
        //Establishing record values
        String name = region;
        long population = countriesSum;
        Double cityPercentage = BigDecimal.valueOf(percentageFactor*100.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        Double notCityPercentage = BigDecimal.valueOf((1.0 - percentageFactor)*100.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        String cityPopulation = citiesSum + " (" + cityPercentage +"%) ";
        String notCityPopulation = (countriesSum - citiesSum) + " (" + notCityPercentage +"%) ";

        String header = String.format(
                "%-20s %-30s %-30s %-30s",
                "Region",
                "Total Population",
                "Living in Cities",
                "Not in Cities"
        );
        System.out.println(header);
        String report = String.format(
                "%-20s %-30s %-30s %-30s",
                name,
                population,
                cityPopulation,
                notCityPopulation
        );
        System.out.println(report);

    }

    public void countryPopulationReport(String countryName){
        //query the database for countries in the requested region
        //get population from the country database
        //for all cities that match the country code, return a sum of their populations
        //divide the city sum by the country population to get a percentage
        if(countryName == null){
            return;
        }

        long countriesSum = 0;
        long citiesSum = 0;
        Country country = getCountryByName(countryName);
        ArrayList<City> cities = getAllCitiesInCountry(countryName);

        if(country == null){
            System.out.println("Not Found");
            return;
        }
        //get sum of population of countries in continent
        countriesSum = country.getPopulation();

        for(int i=0; i<cities.size(); i++){
            //increase the countriesSum
            citiesSum += cities.get(i).getPopulation();
        }

        double percentageFactor = (double)citiesSum/(double)countriesSum;

        //Establishing record values
        String name = countryName;
        long population = countriesSum;
        double cityPercentage = BigDecimal.valueOf(percentageFactor*100.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        double notCityPercentage = BigDecimal.valueOf((1.0 - percentageFactor)*100.0)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

        String cityPopulation = citiesSum + " (" + cityPercentage +"%) ";
        String notCityPopulation = (countriesSum - citiesSum) + " (" + notCityPercentage +"%) ";

        String header = String.format(
                "%-20s %-30s %-30s %-30s",
                "Country",
                "Total Population",
                "Living in Cities",
                "Not in Cities"
        );
        System.out.println(header);
        String report = String.format(
                "%-20s %-30s %-30s %-30s",
                name,
                population,
                cityPopulation,
                notCityPopulation
        );
        System.out.println(report);

    }

    //POPULATION INFORMATION REQUESTS
    public long getWorldPopulation(){
        long population;

        //get population of all countries in the database
        //return the sum of all of them
        try{
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT SUM(population) AS population FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                population = rset.getLong("population");


                return population;
            }else{
                return 0;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return 0;
        }
    }

    public long getContinentPopulation(String continent){
        long population;
        //get all countries where their continent is equal to the text passed in
        //sum their populations
        try{
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT SUM(population) AS population FROM country "
                    +"WHERE country.continent = '" + continent + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                population = rset.getLong("population");


                return population;
            }else{
                return 0;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return 0;
        }
    }

    public long getRegionPopulation(String region){
        long population;
        //get all countries where their region is equal to the text passed in
        //sum their populations
        try{
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT SUM(population) AS population FROM country "
                            +"WHERE country.region = '" + region + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                population = rset.getLong("population");


                return population;
            }else{
                return 0;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return 0;
        }
    }

    public long getCountryPopulation(String country){
        long population;
        //use getCountryByName function
        Country c = getCountryByName(country);

        if(c == null){
            //return negative integer to indicate none was found
            return -1;
        }
        //print the population and return the
        return c.getPopulation();
    }

    public long getDistrictPopulation(String district){
        long population;
        //get all cities where district is equal to the text passed in
        //sum their populations
        try{
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT SUM(population) AS population FROM city "
                            +"WHERE city.district = '" + district + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                population = rset.getLong("population");


                return population;
            }else{
                return 0;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return 0;
        }
    }

    public long getCityPopulation(String cityName){
        //use get city by name function
        City city = getCityByName(cityName);

        if(city == null){
            //return negative integer to indicate none was found
            return -1;
        }
        return city.getPopulation();
    }

    //LANGUAGE REPORT
    public long getLanguagePopulation(String language){
        //this function needs to query the countrylanguage table
        //for the specified language, get all related country codes
        //sum the population of the retrieved country codes to use in the report table
        if(language == null){
            return -1;
        }
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement

            String strSelect =
                    "SELECT CountryCode FROM countrylanguage " +
                            " WHERE countrylanguage.language = '" + language +"' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            long populationCount = 0;
            // Check one is returned
            while(rset.next()){
                String countryCode = rset.getString("countrylanguage.CountryCode");
                Country country = getCountryByCode(countryCode);
                if(country != null){
                    populationCount += country.getPopulation();
                }
            }
            return populationCount;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return 0;
        }
    }
    public void generateLanguageReport(){
        //specified languages: Chinese, English, Hindi, Spanish, Arabic
        ArrayList<String> languages = new ArrayList<String>(Arrays.asList(
                "Chinese","English", "Hindi", "Spanish", "Arabic"
        ));
        //get the population count for each of the specified languages
        String header = String.format(
                "%-20s %-20s %-20s",
                "Language",
                "Population",
                "Percentage of World Population"
        );
        System.out.println(header);
        for(int i = 0; i<languages.size(); i++){
            String lang = languages.get(i);
            long population = getLanguagePopulation(lang);
            long worldPopulation = getWorldPopulation();
            double percentage = ((double)population/(double)worldPopulation)*100.0;
            percentage = BigDecimal.valueOf(percentage)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            //print row
            String row = String.format(
                    "%-20s %-20s %-20s",
                    lang,
                    population,
                    percentage + "%"
            );
            System.out.println(row);
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if(args.length < 1){
            a.connect("localhost:33060", 3000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        //functions to run for requirements screenshots
        //1.) All the countries in the world organised by largest population to smallest.
//        ArrayList<Country> countries = a.getAllCountries();
//        a.printCountryReport(countries);

        //2.) All the countries in a continent organised by largest population to smallest.
//        ArrayList<Country> countries = a.getAllCountriesInContinent("Asia");
//        a.printCountryReport(countries);

        //3.) All the countries in a region organised by largest population to smallest.
//        ArrayList<Country> countries = a.getAllCountriesInRegion("Central America");
//        a.printCountryReport(countries);

        //4.) The top N populated countries in the world where N is provided by the user.
//        ArrayList<Country> countries = a.getCountries(10);
//        a.printCountryReport(countries);

        //5.) The top N populated countries in a continent where N is provided by the user.
//        ArrayList<Country> countries = a.getCountriesInContinent("North America", 10);
//        a.printCountryReport(countries);

        //6.) The top N populated countries in a region where N is provided by the user.
//        ArrayList<Country> countries = a.getCountriesInRegion("Central America", 10);
//        a.printCountryReport(countries);

        //7.) All the cities in the world organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCities();
//        a.printCityReport(cities);

        //8.) All the cities in a continent organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCitiesInContinent("Asia");
//        a.printCityReport(cities);

        //9.) All the cities in a continent organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCitiesInRegion("Caribbean");
//        a.printCityReport(cities);

        //10.) All the cities in a country organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCitiesInCountry("India");
//        a.printCityReport(cities);

        //11.) All the cities in a district organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCitiesInDistrict("Seoul");
//        a.printCityReport(cities);

        //12.) The top N populated cities in the world where N is provided by the user.
//        ArrayList<City> cities = a.getCities(10);
//        a.printCityReport(cities);

        //13.) The top N populated cities in a continent where N is provided by the user.
//        ArrayList<City> cities = a.getCitiesInContinent("Europe", 10);
//        a.printCityReport(cities);

        //14.) The top N populated cities in a region where N is provided by the user.
//        ArrayList<City> cities = a.getCitiesInRegion("Central America", 10);
//        a.printCityReport(cities);

        //15.) The top N populated cities in a country where N is provided by the user.
//        ArrayList<City> cities = a.getCitiesInCountry("India", 10);
//        a.printCityReport(cities);

        //16.) The top N populated cities in a district where N is provided by the user.
//        ArrayList<City> cities = a.getCitiesInDistrict("Seoul", 10);
//        a.printCityReport(cities);

        //17.) All the capital cities in the world organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCapitalCities();
//        a.printCapitalCityReport(cities);

        //18.) All the capital cities in a continent organised by largest population to smallest.
//        ArrayList<City> cities = a.getAllCapitalCitiesInContinent("South America");
//        a.printCapitalCityReport(cities);

        //19.) All the capital cities in a region organised by largest to smallest.
//        ArrayList<City> cities = a.getAllCapitalCitiesInRegion("Caribbean");
//        a.printCapitalCityReport(cities);

        //20.) The top N populated capital cities in the world where N is provided by the user.
//        ArrayList<City> cities = a.getCapitalCities(10);
//        a.printCapitalCityReport(cities);

        //21.) The top N populated capital cities in a continent where N is provided by the user.
//        ArrayList<City> cities = a.getCapitalCitiesInContinent("Asia", 10);
//        a.printCapitalCityReport(cities);

        //22.) The top N populated capital cities in a region where N is provided by the user.
//        ArrayList<City> cities = a.getCapitalCitiesInRegion("Eastern Asia", 10);
//        a.printCapitalCityReport(cities);

        //23.) The population of people, people living in cities, and people not living in cities in each continent.
//        a.continentPopulationReport("Asia");

        //24.) The population of people, people living in cities, and people not living in cities in each region.
//        a.regionPopulationReport("Central America");

        //25.) The population of people, people living in cities, and people not living in cities in each country.
//        a.countryPopulationReport("India");


        //26.) The population of the world.
//        long population = a.getWorldPopulation();
//        System.out.println(population);

        //27.) The population of a continent.
//        long population = a.getContinentPopulation("Asia");
//        System.out.println(population);

        //28.) The population of a region.
//        long population = a.getRegionPopulation("Central America");
//        System.out.println(population);

        //29.) The population of a country.
//        long population = a.getCountryPopulation("Belize");
//        System.out.println(population);

        //30.) The population of a district.
//        long population = a.getDistrictPopulation("Seoul");
//        System.out.println(population);

        //31.) The population of a district.
//        long population = a.getCityPopulation("New York");
//        System.out.println(population);

        //32.) The population of a district.
        a.generateLanguageReport();

        // Disconnect from database
        a.disconnect();
    }
}

