package com.napier.sem;

import java.sql.*;

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

    public void displayCountry(Country country)
    {
        if (country != null)
        {
            System.out.println(
                    "code: " + country.getCode() + "\n" +
                    "name: " + country.getName() + "\n" +
            //need to call get city by id function here
                    "continent: " + country.getContinent() + "\n" +
                    "region: " + country.getRegion() + "\n" +
                    "population: " + country.getPopulation() + "\n" +
                    "capital: " + country.getCapital() + "\n"
            );
        }
    }
    public void displayCity(City city)
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

        //Test getting country by name
        Country country = a.getCountryByName("Aruba");
        //Test displaying contents of country object
        long p = a.getWorldPopulation();
        System.out.println("World Population: " + p);

        long c = a.getCountryPopulation("Belize");
        System.out.println("Population: " + c);

        Country tCountry = a.getCountryByName("Belize");
        a.displayCountry(tCountry);

        City tCity = a.getCityByID(tCountry.getCapital());
        a.displayCity(tCity);

        long cp = a.getCityPopulation("Belmopan");
        System.out.println("City Population: " + cp);
        // Disconnect from database
        a.disconnect();
    }
}

