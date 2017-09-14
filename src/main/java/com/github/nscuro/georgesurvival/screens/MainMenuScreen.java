package com.github.nscuro.georgesurvival.screens;

import com.github.nscuro.georgesurvival.HighscoreManager;
import com.github.nscuro.georgesurvival.StateCommunicationManager;
import com.github.nscuro.georgesurvival.engine.MenuItem;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuScreen implements GameState {

    private Font mGameFont;
    private Image mBackground;
    private Image mHeader;
    private Image mCopyright;

    private Image mItemStartGameImage;
    private Image mItemStartGameHoverImage;
    private Image mItemShowCreditsImage;
    private Image mItemShowCreditsHoverImage;
    private Image mItemExitImage;
    private Image mItemExitHoverImage;

    private MenuItem mItemStartGame;
    private MenuItem mItemShowCredits;
    private MenuItem mItemExit;

    private String mHighscoreStr;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        mGameFont = new AngelCodeFont("Data/Fonts/SFGushingMeadow.fnt", "Data/Fonts/SFGushingMeadow.png");
        mBackground = new Image("Data/Textures/Menu/Background.png");
        mHeader = new Image("Data/Textures/Menu/Header.png");
        mCopyright = new Image("Data/Textures/Menu/Copyright.png");

        mItemStartGameImage = new Image("Data/Textures/Menu/StartGame.png");
        mItemStartGameHoverImage = new Image("Data/Textures/Menu/StartGame_Hover.png");
        mItemShowCreditsImage = new Image("Data/Textures/Menu/ShowCredits.png");
        mItemShowCreditsHoverImage = new Image("Data/Textures/Menu/ShowCredits_Hover.png");
        mItemExitImage = new Image("Data/Textures/Menu/Exit.png");
        mItemExitHoverImage = new Image("Data/Textures/Menu/Exit_Hover.png");

        mItemStartGame = new MenuItem(mItemStartGameImage, mItemStartGameHoverImage,
                (container.getWidth() - mItemStartGameImage.getWidth()) / 2, 150.0f, 1);
        mItemShowCredits = new MenuItem(mItemShowCreditsImage, mItemShowCreditsHoverImage,
                (container.getWidth() - mItemShowCreditsImage.getWidth()) / 2, 250.0f, 2);
        mItemExit = new MenuItem(mItemExitImage, mItemExitHoverImage,
                (container.getWidth() - mItemExitImage.getWidth()) / 2, 350.0f, 3);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (mItemStartGame.GetCollisionArea().contains(input.getMouseX(), input.getMouseY()))
                game.enterState(1, new EmptyTransition(), new BlobbyTransition());
            else if (mItemShowCredits.GetCollisionArea().contains(input.getMouseX(), input.getMouseY()))
                game.enterState(4, new FadeOutTransition(), new FadeInTransition());
            else if (mItemExit.GetCollisionArea().contains(input.getMouseX(), input.getMouseY()))
                container.exit();
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        mBackground.draw();
        mHeader.draw((container.getWidth() - mHeader.getWidth()) / 2, 0);
        mCopyright.draw(0, (container.getHeight() - mCopyright.getHeight()));

        mItemStartGame.Draw(g);
        mItemShowCredits.Draw(g);
        mItemExit.Draw(g);

        g.setFont(mGameFont);
        g.drawString(mHighscoreStr, (container.getWidth() - mGameFont.getWidth("    Score: 00000")),
                (container.getHeight() - mGameFont.getHeight("0\n0\n0")));
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (mItemStartGame.GetCollisionArea().contains(newx, newy)) {
            mItemStartGame.SetHovered(true);
        } else {
            mItemStartGame.SetHovered(false);
        }
        if (mItemShowCredits.GetCollisionArea().contains(newx, newy)) {
            mItemShowCredits.SetHovered(true);
        } else {
            mItemShowCredits.SetHovered(false);
        }
        if (mItemExit.GetCollisionArea().contains(newx, newy)) {
            mItemExit.SetHovered(true);
        } else {
            mItemExit.SetHovered(false);
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        HighscoreManager.ReadFile();
        mHighscoreStr = "Highscore:\n    Score: " + Integer.toString(HighscoreManager.GetScore())
                + "\n    Wave: " + HighscoreManager.GetWave();

        if (StateCommunicationManager.GetInputPaused())
            container.getInput().resume();
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
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
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
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
    }
}
