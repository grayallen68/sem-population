package com.napier.sem;

public class CountryLanguage {
    //class to represent records in the countryLanguage table
    private String countryCode; //foreign key from country table
    private String languageID; //duplicated to match a language with multiple countries
    private boolean isOfficial; //set to true if database value is "T"
    private float percentage;


    public float getPercentage() {
        return percentage;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLanguageID() {
        return languageID;
    }

    public boolean getOfficial(){
        return this.isOfficial;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setLanguageID(String languageID) {
        this.languageID = languageID;
    }

    public void setOfficial(String dbValue) {
        //database value is either "T" or "F"
        if(dbValue == null){
            this.isOfficial = false;
            return;
        }
        if(dbValue == "T"){
            this.isOfficial = true;
        }else {
            this.isOfficial = false;
        }
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
