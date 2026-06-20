package com.upm.lab10.util;

/**
 * PING WENCHAO 226969
 * TbConstants - Global constant registry defining core authorization roles.
 * Prevents hard-coding magic strings throughout the security context.
 */
public class TbConstants {

    public static interface Roles {
        String USER = "ROLE_USER";
        String ADMIN = "ROLE_ADMIN";
    }
}