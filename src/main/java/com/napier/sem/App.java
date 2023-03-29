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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
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
//                Employee emp = new Employee();
//                emp.emp_no = rset.getInt("emp_no");
//                emp.first_name = rset.getString("first_name");
//                emp.last_name = rset.getString("last_name");
//                emp.title = rset.getString("title");
//                emp.salary = rset.getInt("salary");
//                emp.dept_name = rset.getString("dept_name");
//                emp.manager = rset.getString("manager");

//                return emp;
            }
//            else
//                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
//            return null;
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
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {

                Country country = new Country();
                String countryName = rset.getString("country.name");
                String continent = rset.getString("country.continent");
                int population = rset.getInt("country.population");

                country.setName(countryName);
                country.setContinent(continent);
                country.setPopulation(population);

                return country;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayCountry(Country country)
    {
        if (country != null)
        {
            System.out.println(
                    "name: " + country.getName() + "\n"
            //need to call get continent by id function here
                    + "continent: " + country.getContinent() + "\n"
                    + "population: " + country.getPopulation()
            );
        }
    }

    //POPULATION INFORMATION REQUESTS
    public int getWorldPopulation(){
        int population;
        //get population of all countries in the database
        //return the sum of all of them
        try{
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT SUM(population) FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                population = rset.getInt("population");
                return population;
            }else{
                return 0;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return 0;
        }



    }


    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();


        //Test getting country by name
        Country country = a.getCountryByName("Aruba");
        //Test displaying contents of country object
        int p = a.getWorldPopulation();
        System.out.println("World Population" + p);


        // Disconnect from database
        a.disconnect();
    }
}