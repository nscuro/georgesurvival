package com.github.nscuro.georgesurvival;

import com.github.nscuro.georgesurvival.screens.CreditsScreen;
import com.github.nscuro.georgesurvival.screens.GameOverScreen;
import com.github.nscuro.georgesurvival.screens.IntroScreen;
import com.github.nscuro.georgesurvival.screens.MainMenuScreen;
import com.github.nscuro.georgesurvival.screens.PauseMenuScreen;
import com.github.nscuro.georgesurvival.screens.RunningScreen;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

public class GeorgeSurvivalGame extends StateBasedGame {

    public GeorgeSurvivalGame() throws SlickException {
        super("George Survival");

        Music backgroundMusic = new Music("Data/Sounds/BackgroundMusic.ogg");
        backgroundMusic.loop();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainMenuScreen());
        addState(new RunningScreen());
        addState(new PauseMenuScreen());
        addState(new CreditsScreen());
        addState(new GameOverScreen());
        addState(new IntroScreen());

        enterState(5);
    }

    public static void main(String[] args) throws SlickException {
        System.setProperty("org.lwjgl.librarypath", new File("Lib").getAbsolutePath());

        AppGameContainer app = new AppGameContainer(new GeorgeSurvivalGame());
        app.setMaximumLogicUpdateInterval(60);
        app.setDisplayMode(800, 600, false);
        app.setIcon("Data/Icon.png");
        app.setClearEachFrame(true);
        app.setShowFPS(false);
        app.setVSync(true);
        app.start();
    }
}
