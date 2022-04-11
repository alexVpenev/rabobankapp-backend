package com.example.projectbackend.Config;

public class AuthenticationConfigConstants {
    public static final String SECRET = "Java_to_Dev_Secret";
    public static final long EXPIRATION_TIME = 3600000; // 1 hour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/account/register";
}