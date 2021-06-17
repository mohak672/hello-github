import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfigureThreadingUtilTest {

    @Mock
    MyApplication myApplication;

    @Test
    public void testApplication(){
        ConfigureThreadingUtil.configureThreadPool(myApplication);
        verify(myApplication).getNumberOfThreads();
        verifyNoMoreInteractions(myApplication);
    }
}