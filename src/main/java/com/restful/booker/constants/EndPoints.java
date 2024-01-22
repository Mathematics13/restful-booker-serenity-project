package com.restful.booker.constants;

public class EndPoints
{
    public static final String CREATE_TOKEN = "/auth";                                    //static final = constant
    public static final String ALL_BOOKING = "/booking";                                //declare constants

    public static final String CREATE_BOOKING = "/booking";
    public static final String GET_SINGLE_BOOKING_BY_ID = "/booking/{id}";            //No end points in config.properties, only in EndPoints
    public static final String UPDATE_BOOKING_BY_ID = "/booking/{id}";             //constant variables caps

    public static final String PARTIAL_UPDATE_BOOKING_BY_ID = "/booking/{id}";
    public static final String DELETE_BOOKING_BY_ID = "/booking/{id}";           //not a serenity feature

    public static final String PING = "/ping";

}
