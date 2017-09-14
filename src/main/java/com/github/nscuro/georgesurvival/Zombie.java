package com.github.nscuro.georgesurvival;

import com.github.nscuro.georgesurvival.engine.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Zombie extends GameObject {

    private ZombieType mType;
    private float mSpeed;
    private int mLive;
    private int mDamage;

    public Zombie(Image img, float x, float y, ZombieType zType) {
        super(img, x, y);
        this.GetSprite().setRotation(180);
        this.mType = zType;

        switch (this.mType) {
            case SLOW:
                mSpeed = 0.05f;
                mLive = 50;
                mDamage = 20;
                break;
            case NORMAL:
                mSpeed = 0.06f;
                mLive = 40;
                mDamage = 15;
                break;
            case FAST:
                mSpeed = 0.08f;
                mLive = 30;
                mDamage = 10;
                break;
            case DOG:
                mSpeed = 0.1f;
                mLive = 20;
                mDamage = 5;
                break;
        }
    }

    @Override
    public void Update(float gameTime) {
        this.SetY(this.GetY() + (this.mSpeed * gameTime));

        this.GetCollisionArea().setX(this.GetX());
        this.GetCollisionArea().setY(this.GetY());
    }

    @Override
    public void Draw(Graphics g) {
        this.JustDraw(g);
    }

    public ZombieType GetType() {
        return mType;
    }

    public int GetLive() {
        return mLive;
    }

    public int GetDamage() {
        return mDamage;
    }

    public void SetLive(int live) {
        mLive = live;
    }

}
