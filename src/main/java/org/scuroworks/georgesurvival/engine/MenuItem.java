package org.scuroworks.georgesurvival.engine;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class MenuItem {

    private int id;
    private Image Sprite;
    private Image HoverSprite;
    private float PositionX;
    private float PositionY;
    private Shape CollisionArea;
    private boolean mHovered;

    public MenuItem(Image img, Image himg, float x, float y, int id) {
        this.id = id;
        this.Sprite = img;
        this.HoverSprite = himg;
        this.PositionX = x;
        this.PositionY = y;
        this.CollisionArea = new Rectangle(PositionX, PositionY, this.Sprite.getWidth(), this.Sprite.getHeight());
        this.mHovered = false;
    }

    public int GetID() {
        return id;
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

    public void SetSprite(Image img) {
        this.Sprite = img;
    }

    public void SetPosition(float x, float y) {
        this.PositionX = x;
        this.PositionY = y;
    }

    public void SetHovered(boolean hovered) {
        this.mHovered = hovered;
    }

    public void Draw(Graphics g) {
        if (!mHovered) {
            Sprite.draw(PositionX, PositionY);
        } else {
            HoverSprite.draw(PositionX, PositionY);
        }
    }

}
