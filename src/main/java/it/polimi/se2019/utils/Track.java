package it.polimi.se2019.utils;


/**
 * @author Simone Orlando
 */
/*public class Track implements Runnable{


    private String path;
    private Clip clip;
    private AudioInputStream audioInputStream;

    public Track(String path) {
        this.path = path;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
        }
        catch (IOException|UnsupportedAudioFileException e) {

        }
    }

    @Override
    public void run() {
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        catch (LineUnavailableException|IOException e) {

        }
    }
}*/
