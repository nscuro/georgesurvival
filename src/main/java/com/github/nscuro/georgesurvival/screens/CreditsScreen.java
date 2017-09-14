package com.github.nscuro.georgesurvival.screens;

import com.github.nscuro.georgesurvival.StateCommunicationManager;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.FileReader;
import java.io.Reader;

public class CreditsScreen implements GameState {

    private Font mGameFont;
    private Image mBackground;
    private String mCreditsStr;
    private String mHelpStr;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        mGameFont = new AngelCodeFont("Data/Fonts/SFGushingMeadow.fnt", "Data/Fonts/SFGushingMeadow.png");
        mBackground = new Image("Data/Textures/Menu/Background.png");

        Reader reader = null;
        mCreditsStr = "";
        try {
            reader = new FileReader("Data/Credits.txt");
            for (int i; (i = reader.read()) != -1; ) {
                mCreditsStr += (char) i;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        mHelpStr = "Press <ESCAPE> to go Back";
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            input.pause();
            StateCommunicationManager.SetInputPaused(true);
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        mBackground.draw();

        g.setFont(mGameFont);
        g.drawString(mCreditsStr, 0, 0);
        g.drawString(mHelpStr, 0, (container.getHeight() - mGameFont.getHeight("0")));
    }

    @Override
    public void mouseWheelMoved(int change) {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
    }

    @Override
    public void mousePressed(int button, int x, int y) {
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }

    @Override
    public void keyPressed(int key, char c) {
    }

    @Override
    public void keyReleased(int key, char c) {
    }

    @Override
    public void controllerLeftPressed(int controller) {
    }

    @Override
    public void controllerLeftReleased(int controller) {
    }

    @Override
    public void controllerRightPressed(int controller) {
    }

    @Override
    public void controllerRightReleased(int controller) {
    }

    @Override
    public void controllerUpPressed(int controller) {
    }

    @Override
    public void controllerUpReleased(int controller) {
    }

    @Override
    public void controllerDownPressed(int controller) {
    }

    @Override
    public void controllerDownReleased(int controller) {
    }

    @Override
    public void controllerButtonPressed(int controller, int button) {
    }

    @Override
    public void controllerButtonReleased(int controller, int button) {
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
    }

}
