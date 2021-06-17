public class AudioManager {
    private int volume = 50;
    private RINGER_MODE mode = RINGER_MODE.RINGER_MODE_SILENT;

    public RINGER_MODE getRingerMode() {
        return this.mode;
    }
    public int getStreamMaxVolume() {
        return this.volume;
    }
    public void setStreamVolume(int max) {
        this.volume = max;
    }
    public void makeReallyLoad() {
        if (this.mode.equals(RINGER_MODE.RINGER_MODE_NORMAL)) {
            this.setStreamVolume(100);
        }
    }
}

enum RINGER_MODE {
    RINGER_MODE_NORMAL, RINGER_MODE_SILENT
}

class VolumeUtil {
    public static void maximizeVolume(AudioManager audioManager) {
        if (audioManager.getRingerMode() != RINGER_MODE.RINGER_MODE_SILENT) {
            int max = audioManager.getStreamMaxVolume();
            audioManager.setStreamVolume(max);
        }
    }
}
