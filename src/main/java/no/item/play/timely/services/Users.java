package no.item.play.timely.services;

import com.fasterxml.jackson.databind.JsonNode;
import no.item.play.oauth2.OAuthClient;
import no.item.play.timely.TimelyBaseURL;
import no.item.play.timely.TimelyOauthClient;
import play.libs.F.Promise;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Users {
    private final OAuthClient client;
    private final String baseURL;

    @Inject
    public Users(@TimelyOauthClient OAuthClient client, @TimelyBaseURL String baseURL){
        this.baseURL = baseURL;
        this.client = client;
    }

    /**
     * User List
     * @param limit Specifies the number of records to retrieve. Must be less than or equal to 100.
     *              Example: "limit=10"
     * @param offset Specifies the starting record for results to return.
     *               Example: specifying "offset=0" will start offset from 0 record, "offset=4" will start offset from 4th record, etc
     * @param order Reverses the sorting order, when you include the parameter
     *              Example: "order=desc" or "order=asc"
     * @return A JsonNode containing a list of users
     */
    public Promise<JsonNode> list(Integer limit, Integer offset, String order){
        return client.url(baseURL + "/api/v1/users")
                .setQueryParameter("limit", limit)
                .setQueryParameter("offset", offset)
                .setQueryParameter("order", order)
                .get();
    }

    /**
     *
     * @param id The numerical ID of the desired user.
     *           Example Values: 123
     * @return A JsonNode containing a user
     */
    public Promise<JsonNode> byId(Integer id){
        return client.url(baseURL + "/api/v1/users/" + id).get();
    }
}
