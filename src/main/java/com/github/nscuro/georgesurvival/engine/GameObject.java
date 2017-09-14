package com.github.nscuro.georgesurvival.engine;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {

    private Image Sprite;
    private float PositionX;
    private float PositionY;
    private Shape CollisionArea;

    public GameObject(Image img, float x, float y) {
        this.Sprite = img;
        this.PositionX = x;
        this.PositionY = y;
        this.CollisionArea = new Rectangle(this.PositionX, this.PositionY, this.Sprite.getWidth(), this.Sprite.getHeight());
    }

    public Image GetSprite() {
        return this.Sprite;
    }

    public float GetX() {
        return this.PositionX;
    }

    public float GetY() {
        return this.PositionY;
    }

    public Shape GetCollisionArea() {
        return this.CollisionArea;
    }

    public void SetX(float x) {
        this.PositionX = x;
    }

    public void SetY(float y) {
        this.PositionY = y;
    }

    public void SetPosition(float x, float y) {
        this.PositionX = x;
        this.PositionY = y;
    }

    public boolean CollidesWith(GameObject gObject) {
        return this.CollisionArea.contains(gObject.GetX(), gObject.GetY());
    }

    public abstract void Update(float gameTime);

    public abstract void Draw(Graphics g);

    public void JustDraw(Graphics g) {
        this.Sprite.draw(PositionX, PositionY);
    }

}
