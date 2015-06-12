import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import no.item.play.oauth2.OAuthClient;
import no.item.play.oauth2.RefreshTokenHolder;
import no.item.play.oauth2.SimpleRefreshTokenHolder;
import no.item.play.timely.TimelyClient;
import play.libs.F.Promise;

public class TimelyServiceTest {

    public void testProject(){
        Long timeout = 2000L;
        String baseUrl = "https://mypage.timelyapp.com";
        String authURL = baseUrl + "/api/v1/oauth/authorize";
        String tokenURL = baseUrl + "/api/v1/oauth/token";

        // this object's used to hold the refresh token (and pzzt... you can save it persistently)
        RefreshTokenHolder refreshTokenHolder = new SimpleRefreshTokenHolder();

        // sets up an oauth client that will do the request-token stuff for you
        OAuthClient client = OAuthClient.create(authURL, tokenURL)
                .clientId("...")
                .clientSecret("...")
                .redirectUri("http://localhost")
                .refreshTokenHolder(refreshTokenHolder)
                .build();

        // this is your timely service
        TimelyClient timely = new TimelyClient(client, baseUrl);

        // get events
        JsonNode eventList = timely.events()
                .list("2014-08-18", "2014-08-24")
                .get(timeout);

        // get first project's name (reactivly)
        Promise<String> projectName = timely.projects()
                .list(10, 0, "ASC")
                .map(json -> ((ArrayNode) json).get(0).path("name").asText());
    }
}
