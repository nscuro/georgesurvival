package com.github.nscuro.georgesurvival.screens;

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

public class IntroScreen implements GameState {

    private Font mIntroFont;
    private Image mBackground;
    private Image mDeveloperBadge;
    private Image mSlickBadge;
    private float mTimeout;

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        mIntroFont = new AngelCodeFont("Data/Fonts/IntroFont.fnt", "Data/Fonts/IntroFont.png");
        mBackground = new Image("Data/Textures/Menu/Background.png");
        mDeveloperBadge = new Image("Data/Textures/Developer_Badge.png");
        mSlickBadge = new Image("Data/Textures/Slick_Badge.png");

        mTimeout = 4000.0f;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        mTimeout -= (float) delta;

        if (mTimeout <= 0.0f || input.isKeyPressed(Input.KEY_SPACE) || input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        mBackground.draw();

        g.setFont(mIntroFont);

        g.drawString("A Game by", (container.getWidth() - mIntroFont.getWidth("A Game by")) / 2, 128.0f);
        mDeveloperBadge.draw((container.getWidth() - mDeveloperBadge.getWidth()) / 2, 194.0f);

        g.drawString("Using the 2D Game Framework", (container.getWidth() - mIntroFont.getWidth("Using the 2D Game Framework")) / 2, 320.0f);
        mSlickBadge.draw((container.getWidth() - mSlickBadge.getWidth()) / 2, 400.0f);
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        mDeveloperBadge.destroy();
        mSlickBadge.destroy();
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

}
