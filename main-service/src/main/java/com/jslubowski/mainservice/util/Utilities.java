package com.jslubowski.mainservice.util;

import java.security.Principal;

public class Utilities {

    public static String currentUserName(Principal principal){ return principal.getName(); }
}
