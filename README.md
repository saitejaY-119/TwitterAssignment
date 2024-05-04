# TwitterAssignment

This project provides a REST API for interacting with the Twitter API to search for users and retrieve their tweets.

## Installation

1. Clone the repository: `git clone https://github.com/your-username/TwitterAssignment.git`
2. Navigate to the project directory: `cd TwitterAssignment`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## Endpoints

### Search Tweet User

Endpoint: `GET /searchTweetUser`

Description: Searches for Twitter users based on the provided query.

Parameters:
- `query`: The search query

Headers:
- `Client-Id`: Your client ID
- `Client-Secret`: Your client secret

Example usage:

### Get User Tweets

Endpoint: `GET /getUserTweets`

Description: Retrieves tweets of a specific Twitter user.

Parameters:
- `username`: The Twitter username

Headers:
- `Client-Id`: Your client ID
- `Client-Secret`: Your client secret

Example usage:
## Authentication

To use the API endpoints, you need to obtain a client ID and client secret from Twitter and include them in the request headers.
