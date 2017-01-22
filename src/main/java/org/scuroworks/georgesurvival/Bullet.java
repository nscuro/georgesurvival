package org.scuroworks.georgesurvival;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.scuroworks.georgesurvival.engine.GameObject;

public class Bullet extends GameObject {
    private float mSpeed;

    public Bullet(Image img, float x, float y, float speed) {
        super(img, x, y);
        this.mSpeed = speed;
    }

    @Override
    public void Update(float gameTime) {
        this.SetY(this.GetY() - (this.mSpeed * gameTime));

        this.GetCollisionArea().setX(this.GetX());
        this.GetCollisionArea().setY(this.GetY());
    }

    @Override
    public void Draw(Graphics g) {
        this.JustDraw(g);
    }

}
