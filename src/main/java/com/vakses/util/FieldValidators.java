package com.vakses.util;

/**
 * Created by veraxmedax on 20/03/2018.
 */
public class FieldValidators {
    public static final String USERNAME = "^[a-z0-9_-]{3,15}$";
    public static final String PASSWORD = "^[A-Za-z0-9-_<>&@#$%*!+=]{8,20}$";
    public static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
            "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

}
