package no.item.play.timely.services;

import com.fasterxml.jackson.databind.JsonNode;
import no.item.play.oauth2.OAuthClient;
import no.item.play.timely.TimelyBaseURL;
import no.item.play.timely.TimelyOauthClient;
import play.libs.F.Promise;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Reports {
    private final OAuthClient client;
    private final String baseURL;

    @Inject
    public Reports(@TimelyOauthClient OAuthClient client, @TimelyBaseURL String baseURL){
        this.baseURL = baseURL;
        this.client = client;
    }

    /**
     * Reports
     * @param users Specifies the users to get report, should be array of users with numerical user id
     *              Example: "users": ["175551", "117861"]
     * @param startDate Specifies the start date for report
     *                  Example: "start_date" : "Jan 01, 2014"
     * @param endDate Specifies the end date for report
     *                Example: "end_date" : "Dec 31, 2014"
     * @param projects Specifies the projects to get report, should be array of projects with numerical project id
     *                 Example: "projects": ["1751", "1171"]
     * @param estimated Specifies the report type to retrive estimated or billed events.
     *                  Example: "estimated": false or "estimated": true
     * @return A JsonNode containing a list of reports
     */
    public Promise<JsonNode> list(String users, String startDate,  String endDate, String projects, Boolean estimated){
        return client.url(baseURL + "/1.0/1/reports")
                .setQueryParameter("users", users)
                .setQueryParameter("start_date", startDate)
                .setQueryParameter("end_date", endDate)
                .setQueryParameter("projects", projects)
                .setQueryParameter("estimated", estimated)
                .get();
    }


}
