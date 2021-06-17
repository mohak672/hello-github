import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AudioManagerTest {

    @Mock
    AudioManager audioManager;

    @Test
    public void testNormalRingerIsMaximized(){
        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_NORMAL);
        when(audioManager.getStreamMaxVolume()).thenReturn(100);
        
        VolumeUtil.maximizeVolume(audioManager);

        verify(audioManager).setStreamVolume(100);
    }

    @Test
    public void testSilentRingerIsNotDisturbed(){
        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_SILENT);

        VolumeUtil.maximizeVolume(audioManager);

        verify(audioManager).getRingerMode();
        verifyNoMoreInteractions(audioManager);
    }
}