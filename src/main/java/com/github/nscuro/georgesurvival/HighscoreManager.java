package com.github.nscuro.georgesurvival;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

public class HighscoreManager {

    private static int mHighscore_Score = 0;
    private static int mHighscore_Wave = 0;

    public static int GetScore() {
        return mHighscore_Score;
    }

    public static int GetWave() {
        return mHighscore_Wave;
    }

    public static void SetScore(int score) {
        mHighscore_Score = score;
    }

    public static void SetWave(int wave) {
        mHighscore_Wave = wave;
    }

    public static void WriteFile() {
        // Load Old Highscore
        Reader reader = null;
        int lastScore = 0;
        int lastWave = 0;
        try {
            reader = new FileReader("Data/Highscore.gs");
            Properties p = new Properties();
            p.load(reader);
            lastScore = Integer.parseInt(p.getProperty("Score", "0"));
            lastWave = Integer.parseInt(p.getProperty("Wave", "0"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Properties props = new Properties();
        if (mHighscore_Score > lastScore)
            props.setProperty("Score", Integer.toString(mHighscore_Score));
        if (mHighscore_Wave > lastWave)
            props.setProperty("Wave", Integer.toString(mHighscore_Wave));
        Writer writer = null;

        try {
            writer = new FileWriter("Data/Highscore.gs");
            props.store(writer, "George Survival Highscore");
            System.out.println("Highscore successfully stored!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void ReadFile() {
        Properties props = new Properties();
        Reader reader = null;

        try {
            reader = new FileReader("Data/Highscore.gs");
            props.load(reader);
            mHighscore_Score = Integer.parseInt(props.getProperty("Score", "0"));
            mHighscore_Wave = Integer.parseInt(props.getProperty("Wave", "0"));
            System.out.println("Highscore loadet succesfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
