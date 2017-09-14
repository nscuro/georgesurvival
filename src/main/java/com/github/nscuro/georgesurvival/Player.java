package com.github.nscuro.georgesurvival;

import com.github.nscuro.georgesurvival.engine.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends GameObject {

    private int mLive;
    private int mDamage;
    private int mDirection;    // 0 = None, 1 = Left, 2 = Right
    private float mVelocityX;
    private float mVelocityY;
    private WeaponType mCurrentWeapon;

    public Player(Image img, float x, float y) {
        super(img, x, y);
        mLive = 100;
        mDirection = 0;
        mVelocityX = 0.0f;
        mVelocityY = 0.0f;
        mCurrentWeapon = WeaponType.M9;
    }

    @Override
    public void Update(float gameTime) {
        this.SetX(this.GetX() + (mVelocityX * gameTime));
        this.SetY(this.GetY() + (mVelocityY * gameTime));
        this.mVelocityX *= 0.95f;
        this.mVelocityY *= 0.95f;

        switch (mCurrentWeapon) {
            case M9:
                mDamage = 5;
                break;
            case MP5:
                mDamage = 5;
                break;
            case AK47:
                mDamage = 10;
                break;
            case MG42:
                mDamage = 10;
                break;
        }

        this.GetCollisionArea().setX(this.GetX());
        this.GetCollisionArea().setY(this.GetY());
    }

    @Override
    public void Draw(Graphics g) {
        this.JustDraw(g);
    }

    public WeaponType GetCurrentWeapon() {
        return mCurrentWeapon;
    }

    public int GetDamage() {
        return mDamage;
    }

    public int GetLive() {
        return mLive;
    }

    public int GetDirection() {
        return mDirection;
    }

    public void SetLive(int live) {
        mLive = live;
    }

    public void SetDirection(int dir) {
        mDirection = dir;
    }

    public void SetCurrentWeapon(WeaponType wType) {
        this.mCurrentWeapon = wType;
    }

    public void ResetVelocity() {
        this.mVelocityX = 0.0f;
        this.mVelocityY = 0.0f;
    }

    public void MoveUp() {
        this.mVelocityY -= 0.07f;
    }

    public void MoveDown() {
        this.mVelocityY += 0.07f;
    }

    public void MoveLeft() {
        this.mVelocityX -= 0.07f;
    }

    public void MoveRight() {
        this.mVelocityX += 0.07f;
    }

}
