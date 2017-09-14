package com.github.nscuro.georgesurvival;

import com.github.nscuro.georgesurvival.engine.GameObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class BloodSplatter extends GameObject {

    private float mTimeout;

    public BloodSplatter(Image img, float x, float y) {
        super(img, x, y);
        this.mTimeout = 4000.0f;
    }

    @Override
    public void Update(float gameTime) {
        this.mTimeout -= gameTime;
    }

    @Override
    public void Draw(Graphics g) {
        this.JustDraw(g);
    }

    public float GetTimeout() {
        return this.mTimeout;
    }

}
