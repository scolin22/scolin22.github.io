package chasethebox;

import java.awt.Rectangle;

public class Box {

    private int centerY = 240;
    private int centerX = 400;
    private int speedY = 0;
    private int speedX = 0;
    private int box_height = 10;
    private int box_width = 10;

    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    final private int WIDTH = 800;
    final private int HEIGHT = 480;
    final private int MOVESPEED = 4;
    final private int CHASE_SCALE = 15;

    private Rectangle r = new Rectangle(getCenterX() - getBox_width() / 2, getCenterY() - getBox_height() / 2, getBox_width(), getBox_height());

    private Rectangle chase_r = new Rectangle(getCenterX() - CHASE_SCALE * (getBox_width() / 2), getCenterY() - CHASE_SCALE * (getBox_height() / 2), CHASE_SCALE * getBox_width(), CHASE_SCALE * getBox_height());

    public Box() {

    }

    public void update() {
        if (centerY + speedY + box_height / 2 >= HEIGHT) {
            centerY = HEIGHT - box_height / 2;
        } else if (centerY + speedY - box_height / 2 < 0) {
            centerY = box_height / 2;
        } else {
            centerY += speedY;
        }

        if (centerX + speedX + box_width / 2 >= WIDTH) {
            centerX = WIDTH - box_width / 2;
        } else if (centerX + speedX - box_width / 2 < 0) {
            centerX = box_width / 2;
        } else {
            centerX += speedX;
        }

        r.setLocation(getCenterX() - getBox_width() / 2, getCenterY() - getBox_height() / 2);
        chase_r.setLocation(getCenterX() - CHASE_SCALE * (getBox_width() / 2), getCenterY() - CHASE_SCALE * (getBox_height() / 2));
    }

    public void moveUp() {
        setMovingUp(true);
        setSpeedY(-MOVESPEED);

    }

    public void moveDown() {
        setMovingDown(true);
        setSpeedY(MOVESPEED);
    }

    public void moveLeft() {
        setMovingLeft(true);
        setSpeedX(-MOVESPEED);
    }

    public void moveRight() {
        setMovingRight(true);
        setSpeedX(MOVESPEED);
    }

    public void stop() {
        if (!isMovingUp() && !isMovingDown()) {
            speedY = 0;
        }

        if (!isMovingUp() && isMovingDown()) {
            moveDown();
        }

        if (isMovingUp() && !isMovingDown()) {
            moveUp();
        }

        if (!isMovingRight() && !isMovingLeft()) {
            speedX = 0;
        }

        if (!isMovingRight() && isMovingLeft()) {
            moveLeft();
        }

        if (isMovingRight() && !isMovingLeft()) {
            moveRight();
        }

    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getBox_height() {
        return box_height;
    }

    public void setBox_height(int box_height) {
        this.box_height = box_height;
    }

    public int getBox_width() {
        return box_width;
    }

    public void setBox_width(int box_width) {
        this.box_width = box_width;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getMOVESPEED() {
        return MOVESPEED;
    }

    public int getCHASE_SCALE() {
        return CHASE_SCALE;
    }

    public Rectangle getChase_r() {
        return chase_r;
    }

    public void setChase_r(Rectangle chase_r) {
        this.chase_r = chase_r;
    }
}
