package no.item.play.timely.services;

import com.fasterxml.jackson.databind.JsonNode;
import no.item.play.oauth2.OAuthClient;
import no.item.play.timely.TimelyBaseURL;
import no.item.play.timely.TimelyOauthClient;
import play.Logger;
import play.libs.F;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Accounts {
    private final OAuthClient client;
    private final String baseURL;

    @Inject
    public Accounts(@TimelyOauthClient OAuthClient client, @TimelyBaseURL String baseURL){
        this.baseURL = baseURL;
        this.client = client;
    }

    /**
     * List accounts
     * @return A json list of accounts
     */
    public F.Promise<JsonNode> list() {
        Logger.debug("accounts list");
        return client.url(baseURL + "/1.0/accounts").get();
    }
}