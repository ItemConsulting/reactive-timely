package no.item.play.timely.services;

import com.fasterxml.jackson.databind.JsonNode;
import no.item.play.oauth2.OAuthClient;
import play.libs.F.Promise;

public class Clients {
    private final OAuthClient client;
    private final String baseURL;

    public Clients(OAuthClient client, String baseURL){
        this.baseURL = baseURL;
        this.client = client;
    }

    /**
     * Clients List
     * @param limit Specifies the number of records to retrieve. Must be less than or equal to 100.
     *              Example: "limit=10"
     * @param offset Specifies the starting record for results to return.
     *               Example: specifying "offset=0" will start offset from 0 record, "offset=4" will start offset from 4th record, etc
     * @param order Reverses the sorting order, when you include the parameter
     *              Example: "order=desc" or "order=asc"
     * @return
     */
    public Promise<JsonNode> list(Integer limit, Integer offset, String order){
        return client.url(baseURL + "/api/v1/clients")
                .setQueryParameter("limit", limit)
                .setQueryParameter("offset", offset)
                .setQueryParameter("order", order)
                .get();
    }


    /**
     *
     * @param id The numerical ID of the desired client.
     *           Example Values: 123
     * @return
     */
    public Promise<JsonNode> byId(Integer id){
        return client.url(baseURL + "/api/v1/clients/" + id).get();
    }
}
