package org.example.service;



public class TestDataReaderDecorator implements TestDataReaderMethods {

    protected TestDataReaderMethods testDataReaderDecorator;

    public TestDataReaderDecorator(TestDataReaderMethods testDataReaderDecorator) {
        this.testDataReaderDecorator = testDataReaderDecorator;
    }

    @Override
    public String getTestData(String key){
        return testDataReaderDecorator.getTestData(key);
    }

    public String getTestData(String constant, String nameOrPassword) {
        return resourceBundle.getString(constant);
    }
}
