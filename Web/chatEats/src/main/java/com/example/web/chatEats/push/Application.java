package com.example.web.chatEats.push;

import com.example.web.chatEats.push.provider.AuthRequestFilter;
import com.example.web.chatEats.push.provider.GsonProvider;
//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.example.web.chatEats.push.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application() {
        packages(AccountService.class.getPackage().getName());

        register(AuthRequestFilter.class);
        //register(JacksonJsonProvider.class);
        register(GsonProvider.class);

        register(Logger.class);
    }

}
