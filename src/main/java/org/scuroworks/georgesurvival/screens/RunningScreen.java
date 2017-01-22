package org.scuroworks.georgesurvival.screens;

import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.scuroworks.georgesurvival.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RunningScreen implements GameState {

    private Font mGameFont;
    private Image mBackground;
    private Image mPlayerImage;
    private Image mZombieSlowImage;
    private Image mZombieNormalImage;
    private Image mZombieFastImage;
    private Image mZombieDogImage;
    private Image mWeaponM9;
    private Image mWeaponMP5;
    private Image mWeaponAK47;
    private Image mWeaponMG42;
    private Image mBulletShortImage;
    private Image mBulletNormalImage;
    private Image mBulletLongImage;
    private Image mBloodSplatter01Image;
    private Image mBloodSplatter02Image;
    private Image mBloodSplatter03Image;

    private Player mPlayer;
    private List<Bullet> mPlayerBulletList;
    private List<Zombie> mZombieList;
    private List<BloodSplatter> mBloodSplatterList;

    private int mWave;
    private int mScore;
    private float mFireButtonDelay;

    private String mWaveStr;
    private String mLiveStr;
    private String mScoreStr;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        mGameFont = new AngelCodeFont("Data/Fonts/SFGushingMeadow.fnt", "Data/Fonts/SFGushingMeadow.png");
        mBackground = new Image("Data/Textures/Background.png");
        mPlayerImage = new Image("Data/Textures/Player.png");
        mWeaponM9 = new Image("Data/Textures/Weapons/M9.png");
        mWeaponMP5 = new Image("Data/Textures/Weapons/MP5.png");
        mWeaponAK47 = new Image("Data/Textures/Weapons/AK47.png");
        mWeaponMG42 = new Image("Data/Textures/Weapons/MG42.png");
        mBulletShortImage = new Image("Data/Textures/Bullets/Short.png");
        mBulletNormalImage = new Image("Data/Textures/Bullets/Normal.png");
        mBulletLongImage = new Image("Data/Textures/Bullets/Long.png");
        mZombieSlowImage = new Image("Data/Textures/Zombies/Slow.png");
        mZombieNormalImage = new Image("Data/Textures/Zombies/Normal.png");
        mZombieFastImage = new Image("Data/Textures/Zombies/Fast.png");
        mZombieDogImage = new Image("Data/Textures/Zombies/Dog.png");
        mBloodSplatter01Image = new Image("Data/Textures/Effects/Blood01.png");
        mBloodSplatter02Image = new Image("Data/Textures/Effects/Blood02.png");
        mBloodSplatter03Image = new Image("Data/Textures/Effects/Blood03.png");

        mPlayer = new Player(mPlayerImage, (container.getWidth() - mPlayerImage.getWidth()) / 2,
                (container.getHeight() - mPlayerImage.getHeight()));
        mPlayerBulletList = new ArrayList<Bullet>();
        mZombieList = new ArrayList<Zombie>();
        mBloodSplatterList = new ArrayList<BloodSplatter>();

        mWave = 0;
        mScore = 0;
        mFireButtonDelay = 0.0f;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float gameTime = (float) delta;
        Input input = container.getInput();
        mFireButtonDelay -= gameTime;

        // Update HUD Strings
        mWaveStr = Integer.toString(mWave);
        for (int i = mWaveStr.length(); i < 3; i++)
            mWaveStr = "0" + mWaveStr;

        mLiveStr = Integer.toString(mPlayer.GetLive());
        for (int i = mLiveStr.length(); i < 3; i++)
            mLiveStr = "0" + mLiveStr;

        mScoreStr = Integer.toString(mScore);
        for (int i = mScoreStr.length(); i < 5; i++)
            mScoreStr = "0" + mScoreStr;

        // General Controls
        if (input.isKeyPressed(Input.KEY_ESCAPE))
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());

        // Player Controls
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            mPlayer.MoveUp();
        }
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            mPlayer.MoveDown();
        }
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            mPlayer.MoveLeft();
            mPlayer.SetDirection(1);
        }
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            mPlayer.MoveRight();
            mPlayer.SetDirection(2);
        }
        if (input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_E)) {
            AddPlayerBullet();
        }

        // Update Player
        mPlayer.Update(gameTime);

        // Keep Player on Screen
        if (mPlayer.GetX() <= -50.0f && mPlayer.GetDirection() == 1)
            mPlayer.SetX(container.getWidth() + 50.0f);
        else if (mPlayer.GetX() >= (container.getWidth() + 50.0f) && mPlayer.GetDirection() == 2)
            mPlayer.SetX(-50.0f);
        if (mPlayer.GetY() >= (container.getHeight() - (mPlayer.GetSprite().getHeight() / 2)))
            mPlayer.SetY(container.getHeight() - (mPlayer.GetSprite().getHeight() / 2));

        if (mPlayer.GetLive() <= 0) {
            HighscoreManager.SetScore(mScore);
            HighscoreManager.SetWave(mWave);
            HighscoreManager.WriteFile();
            ResetGame();
            game.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }

        // Update Bullets
        for (int i = 0; i < mPlayerBulletList.size(); i++) {
            Bullet bullet = mPlayerBulletList.get(i);
            boolean removeBullet = false;
            bullet.Update(gameTime);

            // Check Collision with Zombies
            for (int j = 0; j < mZombieList.size(); j++) {
                if (mZombieList.get(j).CollidesWith(bullet)) {
                    Zombie zombie = mZombieList.get(j);
                    AddBloodSplatter(mZombieList.get(j).GetX(), mZombieList.get(j).GetY());
                    zombie.SetLive(zombie.GetLive() - mPlayer.GetDamage());
                    removeBullet = true;
                }
            }

            // Remove Bullet if out of Screen
            if (mPlayerBulletList.contains(bullet) && bullet.GetY() <= -100.0f || removeBullet)
                mPlayerBulletList.remove(i);
        }

        // Update Zombies
        for (int i = 0; i < mZombieList.size(); i++) {
            Zombie zombie = mZombieList.get(i);
            zombie.Update(gameTime);

            // Keep Zombies on Screen
            if (zombie.GetY() >= container.getHeight()) {
                mScore -= 10;
                zombie.SetX(new Random().nextInt(700) + 60);
                zombie.SetY(-100.0f);
            }

            // Check Collision with Player
            if (zombie.CollidesWith(mPlayer)) {
                mPlayer.SetLive(mPlayer.GetLive() - zombie.GetDamage());
                AddBloodSplatter(mPlayer.GetX(), mPlayer.GetY());
                AddBloodSplatter(zombie.GetX(), zombie.GetY());
                mScore += 10;
                mZombieList.remove(i);
            }

            // Remove Zombie if Live's over
            if (zombie.GetLive() <= 0) {
                mZombieList.remove(zombie);
                mScore += 10;
            }
        }

        // Update Blood Splatter
        for (int i = 0; i < mBloodSplatterList.size(); i++) {
            mBloodSplatterList.get(i).Update(gameTime);

            // Remove Blood when Timed out
            if (mBloodSplatterList.get(i).GetTimeout() <= 0.0f)
                mBloodSplatterList.remove(i);
        }

        // Manage Wave
        if (mZombieList.size() <= 0) {
            mWave++;

            // Spawn 1 Zombie per Wavecount
            for (int i = 0; i < mWave; i++) {
                AddZombie();
            }
        }

        // Manage Weapons
        if (mScore >= 200 && mScore <= 600)
            mPlayer.SetCurrentWeapon(WeaponType.MP5);
        else if (mScore >= 600 && mScore <= 1400)
            mPlayer.SetCurrentWeapon(WeaponType.AK47);
        else if (mScore >= 1400)
            mPlayer.SetCurrentWeapon(WeaponType.MG42);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        mBackground.draw();

        for (BloodSplatter bl : mBloodSplatterList)
            bl.Draw(g);

        for (Bullet b : mPlayerBulletList)
            b.Draw(g);

        mPlayer.Draw(g);

        for (Zombie z : mZombieList)
            z.Draw(g);

        // Draw Current Weapon
        switch (mPlayer.GetCurrentWeapon()) {
            case M9:
                mWeaponM9.draw((container.getWidth() - mWeaponM9.getWidth()), (container.getHeight() - mWeaponM9.getHeight()));
                break;
            case MP5:
                mWeaponMP5.draw((container.getWidth() - mWeaponMP5.getWidth()), (container.getHeight() - mWeaponMP5.getHeight()));
                break;
            case AK47:
                mWeaponAK47.draw((container.getWidth() - mWeaponAK47.getWidth()), (container.getHeight() - mWeaponAK47.getHeight()));
                break;
            case MG42:
                mWeaponMG42.draw((container.getWidth() - mWeaponMG42.getWidth()), (container.getHeight() - mWeaponMG42.getHeight()));
                break;
        }

        // Draw HUD Strings
        g.setFont(mGameFont);
        g.drawString("Live: " + mLiveStr, 0, 0);
        g.drawString("Wave: " + mWaveStr, (container.getWidth() - mGameFont.getWidth("Wave: 000")) / 2, 0);
        g.drawString("Score: " + mScoreStr, (container.getWidth() - mGameFont.getWidth("Score: 00000")), 0);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        if (StateCommunicationManager.GetRestartRequested()) {
            this.ResetGame();
            StateCommunicationManager.SetRestartRequested(false);
        }
    }

    public void AddPlayerBullet() {
        if (mFireButtonDelay <= 0.0f) {
            Bullet bullet = null;
            float bulletPosX = (mPlayer.GetX() + (mPlayer.GetSprite().getWidth() / 2)) - 2.0f;

            switch (mPlayer.GetCurrentWeapon()) {
                case M9:
                    bullet = new Bullet(mBulletShortImage, bulletPosX, mPlayer.GetY(), 1.0f);
                    mFireButtonDelay = 200.0f;
                    break;
                case MP5:
                    bullet = new Bullet(mBulletShortImage, bulletPosX, mPlayer.GetY(), 1.3f);
                    mFireButtonDelay = 150.0f;
                    break;
                case AK47:
                    bullet = new Bullet(mBulletNormalImage, bulletPosX, mPlayer.GetY(), 1.6f);
                    mFireButtonDelay = 100.0f;
                    break;
                case MG42:
                    bullet = new Bullet(mBulletLongImage, bulletPosX, mPlayer.GetY() - 60.0f, 2.0f);
                    mFireButtonDelay = 50.0f;
                    break;
            }

            if (bullet != null)
                mPlayerBulletList.add(bullet);
        }
    }

    public void AddZombie() {
        Zombie zombie = null;
        int randType = new Random().nextInt(4);
        float randPosX = (float) new Random().nextInt(700);

        if (randType == 1)
            zombie = new Zombie(mZombieSlowImage, randPosX, -100.0f, ZombieType.SLOW);
        else if (randType == 0)
            zombie = new Zombie(mZombieNormalImage, randPosX, -100.0f, ZombieType.NORMAL);
        else if (randType == 2)
            zombie = new Zombie(mZombieFastImage, randPosX, -100.0f, ZombieType.FAST);
        else if (randType == 3)
            zombie = new Zombie(mZombieDogImage, randPosX, -100.0f, ZombieType.DOG);

        if (zombie != null)
            mZombieList.add(zombie);
    }

    public void AddBloodSplatter(float x, float y) {
        BloodSplatter blood = null;
        int blType = new Random().nextInt(2);

        if (blType == 0)
            blood = new BloodSplatter(mBloodSplatter01Image, x, y);
        else if (blType == 1)
            blood = new BloodSplatter(mBloodSplatter02Image, x, y);
        else if (blType == 2)
            blood = new BloodSplatter(mBloodSplatter03Image, x, y);

        if (blood != null)
            mBloodSplatterList.add(blood);
    }

    public void ResetGame() {
        this.mWave = 0;
        this.mScore = 0;
        this.mPlayer.SetLive(100);
        this.mPlayer.SetCurrentWeapon(WeaponType.M9);
        this.mPlayer.SetPosition((800 - mPlayerImage.getWidth()) / 2, (600 - mPlayerImage.getHeight()));
        this.mPlayer.ResetVelocity();

        this.mPlayerBulletList.clear();
        this.mZombieList.clear();
        this.mBloodSplatterList.clear();
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
