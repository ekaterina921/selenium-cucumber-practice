package org.example.service;

import lombok.extern.log4j.Log4j;


public class TestDataReader implements TestDataReaderMethods{

    @Override
    public String getTestData(String key){
        return resourceBundle.getString(key);
    }


}