# Reactive Timely
A Java library to access timely in a reactive manner

## Usage

```java
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
        TimelyService timely = new TimelyService(client, baseUrl);

        // get events
        JsonNode eventList = timely.events()
                .list("2014-08-18", "2014-08-24")
                .get(timeout);

        // get first project's name (reactivly)
        Promise<String> projectName = timely.projects()
                .list(10, 0, "ASC")
                .map(json -> ((ArrayNode) json).get(0).path("name").asText());
```