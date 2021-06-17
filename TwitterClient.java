public class TwitterClient {

    public void sendTweet(ITweet iTweet){
        String message = iTweet.getMessage();
        System.out.println(message);
    }
}

interface ITweet{
    String getMessage();
}
