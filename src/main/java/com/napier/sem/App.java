package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

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

    public void printCountries(){
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
                    "SELECT * FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                System.out.println(rset.getInt("population") + " " + rset.getString("name"));

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");

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

                Country country = new Country();
                String countryCode = rset.getString("country.code");
                String countryName = rset.getString("country.name");
                String continent = rset.getString("country.continent");
                String region = rset.getString("country.region");
                int population = rset.getInt("country.population");
                int capitalID = rset.getInt("country.capital");

                country.setCode(countryCode);
                country.setName(countryName);
                country.setContinent(continent);
                country.setRegion(region);
                country.setPopulation(population);
                country.setCapital(capitalID);

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

                Country country = new Country();
                String countryCode = rset.getString("country.code");
                String countryName = rset.getString("country.name");
                String continent = rset.getString("country.continent");
                String region = rset.getString("country.region");
                int population = rset.getInt("country.population");
                int capitalID = rset.getInt("country.capital");

                country.setCode(countryCode);
                country.setName(countryName);
                country.setContinent(continent);
                country.setRegion(region);
                country.setPopulation(population);
                country.setCapital(capitalID);

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
                City city = new City();
                int id = rset.getInt("city.id");
                String name = rset.getString("city.name");
                String countryCode = rset.getString("city.countrycode");
                String district = rset.getString("city.district");
                long population = rset.getLong("city.population");

                city.setID(id);
                city.setName(name);
                city.setCountryCode(countryCode);
                city.setDistrict(district);
                city.setPopulation(population);

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
                City city = new City();
                int id = rset.getInt("city.id");
                String name = rset.getString("city.name");
                String countryCode = rset.getString("city.countrycode");
                String district = rset.getString("city.district");
                long population = rset.getLong("city.population");

                city.setID(id);
                city.setName(name);
                city.setCountryCode(countryCode);
                city.setDistrict(district);
                city.setPopulation(population);

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

    public void printCountryRecord(Country country)
    {
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
    public void printCityRecord(City city)
    {
        if (city != null)
        {
            System.out.println(
                    "ID: " + city.getID() + "\n" +
                            "name: " + city.getName() + "\n" +
                            //need to call get city by id function here
                            "country code: " + city.getCountryCode() + "\n" +
                            "district: " + city.getDistrict()+ "\n" +
                            "population: " + city.getPopulation() + "\n"
            );
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
        //apply unit test to this function
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
        return city.getPopulation();
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

        ArrayList<Country> c = a.getCountriesInRegion("Eastern Asia",3);
        a.printCountryReport(c);
        // Disconnect from database
        a.disconnect();
    }
}

