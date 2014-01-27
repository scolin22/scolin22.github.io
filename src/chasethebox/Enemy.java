package chasethebox;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy extends Box {
    private boolean chasing = false;

    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Enemy(int x, int y) {
        setCenterX(x);
        setCenterY(y);

        Rectangle rect = new Rectangle(getCenterX() - getBox_width() / 2, getCenterY() - getBox_height() / 2, getBox_width(), getBox_height());

        Rectangle chase_rect = new Rectangle(getCenterX() - getCHASE_SCALE() * (getBox_width() / 2), getCenterY() - getCHASE_SCALE() * (getBox_height() / 2), getCHASE_SCALE() * getBox_width(), getCHASE_SCALE() * getBox_height());

        setR(rect);
        setChase_r(chase_rect);

        enemies.add(this);
    }

    public void update() {
        if (isChasing()) {
            chase();
        } else {
            if (checkCollision(getChase_r(), StartingClass.getBox().getChase_r())) {
                setChasing(true);
            }
        }
        super.update();

        if (checkCollision(getR(), StartingClass.getBox().getR())) {
            StartingClass.setLiving(false);
        }
    }

    private void chase() {
        int x = StartingClass.getBox().getCenterX();
        int y = StartingClass.getBox().getCenterY();

        int x_diff = x - getCenterX();
        int y_diff = y - getCenterY();

        double alpha = Math.sqrt((Math.pow(x_diff, 2) + Math.pow(y_diff, 2)) / Math.pow(getMOVESPEED() / 2, 2));

        int speedX = (int) (x_diff / alpha);
        int speedY = (int) (y_diff / alpha);

        setSpeedX(speedX);
        setSpeedY(speedY);

    }

    private boolean checkCollision(Rectangle antag, Rectangle victim) {
        return antag.intersects(victim);
    }

    public boolean isChasing() {
        return chasing;
    }

    public void setChasing(boolean chasing) {
        this.chasing = chasing;
    }

}
