package com.example.TwitterAssignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;

@RestController
public class TwitterController {
    private final String CONSUMER_KEY = "NVmvsBUhDZFolGoEqGlLQa8u4";
    private final String CONSUMER_SECRET = "tQBUBS6bXXVfmZ5W87bEItMDHEqyXbAPisA2AIcrUvF4vnscGZ";
    private final String ACCESS_TOKEN = "1786683835557486592-PMC6zSYva3xHKEpdtFRwd5WhGI6AFp";
    private final String ACCESS_TOKEN_SECRET = "6m42e9GlJ6uaxd6U8NCI39DPesjCj7BG9N0cmy9Lr6uwN";
    @GetMapping("/searchTweetUser")
    public ResponseEntity<List<User>> searchTweetUser(@RequestParam String query,
                                                      @RequestHeader("Client-Id") String clientId,
                                                      @RequestHeader("Client-Secret") String clientSecret) {
/*
        if (validateClient(clientId, clientSecret)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
*/

        Twitter twitter = getTwitterInstance();
        try {
            ResponseList<User> users = twitter.searchUsers(query, 5);
            return ResponseEntity.ok(users);
        } catch (TwitterException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getUserTweets")
    public ResponseEntity<List<Status>> getUserTweets(@RequestParam String username,
                                                      @RequestHeader("Client-Id") String clientId,
                                                      @RequestHeader("Client-Secret") String clientSecret) {
       /* if (validateClient(clientId, clientSecret)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }*/

        Twitter twitter = getTwitterInstance();
        try {
            return ResponseEntity.ok(twitter.getUserTimeline(username));
        } catch (TwitterException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Twitter getTwitterInstance() {
        return new TwitterFactory(new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
                .build())
                .getInstance();
    }

    private boolean validateClient(String clientId, String clientSecret) {
        // Generate random client ID and client secret
        String generatedClientId = generateRandomString();
        String generatedClientSecret = generateRandomString();

        // Print the provided client ID and client secret
        System.out.println("Provided Client ID: " + clientId);
        System.out.println("Provided Client Secret: " + clientSecret);

        // Print the generated random client ID and client secret
        System.out.println("Generated Random Client ID: " + generatedClientId);
        System.out.println("Generated Random Client Secret: " + generatedClientSecret);

        // Compare with provided client ID and client secret
        return clientId.equals(generatedClientId) && clientSecret.equals(generatedClientSecret);
    }

    private String generateRandomString() {
        // Generate random UUID
        UUID uuid = UUID.randomUUID();
        // Convert UUID to string
        String randomString = uuid.toString();
        // Remove hyphens from the UUID string
        randomString = randomString.replace("-", "");
        // Return the random string
        return randomString;
    }
}
