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
    }
    //when creating testing functions consider
        //    null.
        //    an empty list.
        //    a list with null member in it.
        //    a list with all non-null members (a normal list).

    //TESTS FOR PRINTING SINGLE RECORDS
    //these functions take a country or city object as a parameter
    @Test
    void printCountryRecordTestNull()
    {
        app.printCountryRecord(null);
    }
    @Test
    void printCityRecordTestNull()
    {
        app.printCityRecord(null);
    }
    @Test
    void printCapitalRecordTestNull()
    {
        app.printCapitalCityRecord(null);
    }
}
