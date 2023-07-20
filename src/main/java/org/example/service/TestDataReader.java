package org.example.service;


public class TestDataReader implements TestDataReaderMethods{

    @Override
    public String getTestData(String key){
        return resourceBundle.getString(key);
    }


}