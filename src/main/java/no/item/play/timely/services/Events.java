package no.item.play.timely.services;

import com.fasterxml.jackson.databind.JsonNode;
import no.item.play.oauth2.OAuthClient;
import no.item.play.timely.TimelyBaseURL;
import no.item.play.timely.TimelyOauthClient;
import play.libs.F.Promise;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Events {
    private final OAuthClient client;
    private final String baseURL;

    @Inject
    public Events(@TimelyOauthClient OAuthClient client, @TimelyBaseURL String baseURL){
        this.baseURL = baseURL;
        this.client = client;
    }

    /**
     *
     * @param since Specifies the start date for results to return.
     *              Example: since=2014-08-18
     * @param upto Specifies the end date for results to return.
     *             Example: upto=2014-08-24
     * @return A Json list of events
     */
    public Promise<JsonNode> list(String since, String upto){
        // return a wrapped version of this
        return client.url(baseURL + "/api/v1/events")
                .setQueryParameter("since", since)
                .setQueryParameter("upto", upto)
                .get();
    }
}
