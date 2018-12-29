package concurrent.semaphoreTest;

import java.util.concurrent.Semaphore;

/**
 * @author Cain
 * @Description
 * @date 2018/10/27
 */
public class Playground {

    private int trackNumber;
    private Semaphore semaphore;
    private Track[] tracks;

    static class Track{
        private boolean used;
        private String trackName;

        public String getTrackName() {
            return trackName;
        }

        public void setTrackName(String trackName) {
            this.trackName = trackName;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }
    }

    public Playground(int trackNumber){
        this.trackNumber = trackNumber;
        semaphore = new Semaphore(trackNumber);
        initTracks();
    }

    private void initTracks(){
        tracks = new Track[trackNumber];
        for(int i=1; i<=trackNumber; i++){
            Track track = new Track();
            track.setTrackName("第条"+i+"跑道");
            track.setUsed(false);
            tracks[i-1] = track;
        }
    }

    public Track getUnusedTrack() throws InterruptedException {
        semaphore.acquire();
        for(Track track : tracks){
            synchronized (track){
                if(!track.isUsed()){
                    track.setUsed(true);
                    return track;
                }
            }
        }
        semaphore.release();
        return null;
    }

    public void giveBackTrack(Track t){
        for(Track track : tracks){
            if(track.getTrackName().equals(t.getTrackName())){
                synchronized (track){
                    track.setUsed(false);
                    semaphore.release();
                }
                return;
            }
        }
    }
}
