package com.github.nscuro.georgesurvival.screens;

import com.github.nscuro.georgesurvival.StateCommunicationManager;
import com.github.nscuro.georgesurvival.engine.MenuItem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class PauseMenuScreen implements GameState {

    private Image mBackground;
    private Image mHeader;
    private Image mCopyright;

    private Image mItemContinueImage;
    private Image mItemContinueHoverImage;
    private Image mItemRestartImage;
    private Image mItemRestartHoverImage;
    private Image mItemMainMenuImage;
    private Image mItemMainMenuHoverImage;
    private Image mItemExitImage;
    private Image mItemExitHoverImage;

    private MenuItem mItemContinue;
    private MenuItem mItemRestart;
    private MenuItem mItemMainMenu;
    private MenuItem mItemExit;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        mBackground = new Image("Data/Textures/Menu/Background.png");
        mHeader = new Image("Data/Textures/Menu/Header_Pause.png");
        mCopyright = new Image("Data/Textures/Menu/Copyright.png");

        mItemContinueImage = new Image("Data/Textures/Menu/Continue.png");
        mItemContinueHoverImage = new Image("Data/Textures/Menu/Continue_Hover.png");
        mItemRestartImage = new Image("Data/Textures/Menu/Restart.png");
        mItemRestartHoverImage = new Image("Data/Textures/Menu/Restart_Hover.png");
        mItemMainMenuImage = new Image("Data/Textures/Menu/MainMenu.png");
        mItemMainMenuHoverImage = new Image("Data/Textures/Menu/MainMenu_Hover.png");
        mItemExitImage = new Image("Data/Textures/Menu/Exit.png");
        mItemExitHoverImage = new Image("Data/Textures/Menu/Exit_Hover.png");

        mItemContinue = new MenuItem(mItemContinueImage, mItemContinueHoverImage,
                (container.getWidth() - mItemContinueImage.getWidth()) / 2, 150.0f, 0);
        mItemRestart = new MenuItem(mItemRestartImage, mItemRestartHoverImage,
                (container.getWidth() - mItemRestartImage.getWidth()) / 2, 250.0f, 0);
        mItemMainMenu = new MenuItem(mItemMainMenuImage, mItemMainMenuHoverImage,
                (container.getWidth() - mItemMainMenuImage.getWidth()) / 2, 350.0f, 0);
        mItemExit = new MenuItem(mItemExitImage, mItemExitHoverImage,
                (container.getWidth() - mItemExitImage.getWidth()) / 2, 450.0f, 0);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            if (mItemContinue.GetCollisionArea().contains(input.getMouseX(), input.getMouseY()))
                game.enterState(1, new FadeOutTransition(), new FadeInTransition());
            else if (mItemRestart.GetCollisionArea().contains(input.getMouseX(), input.getMouseY())) {
                StateCommunicationManager.SetRestartRequested(true);
                game.enterState(1, new FadeOutTransition(), new FadeInTransition());
            } else if (mItemMainMenu.GetCollisionArea().contains(input.getMouseX(), input.getMouseY()))
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());
            else if (mItemExit.GetCollisionArea().contains(input.getMouseX(), input.getMouseY()))
                container.exit();
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        mBackground.draw();
        mHeader.draw((container.getWidth() - mHeader.getWidth()) / 2, 0);
        mCopyright.draw(0, (container.getHeight() - mCopyright.getHeight()));

        mItemContinue.Draw(g);
        mItemRestart.Draw(g);
        mItemMainMenu.Draw(g);
        mItemExit.Draw(g);
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        if (mItemContinue.GetCollisionArea().contains(newx, newy)) {
            mItemContinue.SetHovered(true);
        } else {
            mItemContinue.SetHovered(false);
        }
        if (mItemRestart.GetCollisionArea().contains(newx, newy)) {
            mItemRestart.SetHovered(true);
        } else {
            mItemRestart.SetHovered(false);
        }
        if (mItemMainMenu.GetCollisionArea().contains(newx, newy)) {
            mItemMainMenu.SetHovered(true);
        } else {
            mItemMainMenu.SetHovered(false);
        }
        if (mItemExit.GetCollisionArea().contains(newx, newy)) {
            mItemExit.SetHovered(true);
        } else {
            mItemExit.SetHovered(false);
        }
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
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
    }
}
