import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TwitterClientTest {

    @Mock
    ITweet iTweet;

    @Test
    public void testSendingTweet(){
        TwitterClient twitterClient = new TwitterClient();
        when(iTweet.getMessage()).thenReturn("Mockito Is Great");
        twitterClient.sendTweet(iTweet);
        verify(iTweet,atLeastOnce()).getMessage();
    }
}