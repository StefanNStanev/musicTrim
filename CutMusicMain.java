import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class CutMusicMain {


    public static void main(String[] args) {

        System.out.println("hello world!");

        File musicFile = new File("D:/projectsInJava/Masked Wolf - Astronaut In The Ocean.wav");
        //playSong(musicFile);

        copyAudio("D:/projectsInJava/Masked Wolf - Astronaut In The Ocean.wav",
                "D:/projectsInJava/Astronaut In The Ocean Trimmed.wav",
                5, 5);
    }

    public static void playSong(File file){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (Exception e){

        }
    }

    public static void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy) {
        AudioInputStream inputStream = null;
        AudioInputStream shortenedStream = null;
        try {
            File file = new File(sourceFileName);
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            AudioFormat format = fileFormat.getFormat();
            inputStream = AudioSystem.getAudioInputStream(file);

            int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
            inputStream.skip(startSecond * bytesPerSecond);
            long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
            shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);

            File destinationFile = new File(destinationFileName);
            AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if (shortenedStream != null) {
                try {
                    shortenedStream.close();
                } catch (Exception e) {
                    System.out.println(e); }
            }
        }
    }

}
