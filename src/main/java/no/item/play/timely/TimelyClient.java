package no.item.play.timely;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import no.item.play.oauth2.OAuthClient;
import no.item.play.timely.services.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TimelyClient {
    @Inject private Accounts accounts;
    @Inject private Projects projects;
    @Inject private Users users;
    @Inject private Clients clients;
    @Inject private Events events;
    @Inject private Reports reports;

    public static TimelyClient instance(OAuthClient client, String baseURL){
        Module module = new TimelyModule();
        // todo bind attributes
        Injector injector = Guice.createInjector(module);
        return injector.getInstance(TimelyClient.class);
    }

    public Accounts accounts(){
        return accounts;
    }

    public Projects projects(){
        return projects;
    }

    public Users users(){
        return users;
    }

    public Clients clients(){
        return clients;
    }

    public Events events(){
        return events;
    }

    public Reports reports(){
        return reports;
    }
}