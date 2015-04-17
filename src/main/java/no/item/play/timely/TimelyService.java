package no.item.play.timely;

import no.item.play.oauth2.OAuthClient;
import no.item.play.timely.services.*;

public class TimelyService {
    private final OAuthClient client;
    private final String baseURL;

    public TimelyService(OAuthClient client, String baseURL){
        this.client = client;
        this.baseURL = baseURL;
    }

    public Projects projects(){
        return new Projects(client, baseURL);
    }

    public Users users(){
        return new Users(client, baseURL);
    }

    public Clients clients(){
        return new Clients(client, baseURL);
    }

    public Events events(){
        return new Events(client, baseURL);
    }

    public Reports reports(){
        return new Reports(client, baseURL);
    }
}