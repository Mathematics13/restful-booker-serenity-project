package com.restful.booker.testbase;

import com.restful.booker.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init()
    {
        propertyReader = PropertyReader.getInstance();                                        //if its common only declare here orelse NO
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
                                                                                              //Property Reader always returns String
       // RestAssured.basePath = Path.STUDENT;                                                //Classname.variable = "/student"
    }
}
