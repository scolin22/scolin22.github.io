package chasethebox;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

public class StartingClass extends Applet implements Runnable, KeyListener {

    enum GameState {
        RUNNING, LOSE, WIN
    }

    private static GameState state = GameState.RUNNING;

    final private int WIDTH = 800;
    final private int HEIGHT = 480;

    private static Box box;
    private static Enemy bad1;
    private static Collectible clbl1;
    private static int score = 0;
    private static boolean living = true;
    private static boolean win = false;
    private static boolean more = false;

    private Image image, character, collectible, enemy;
    private Graphics second;
    private URL base;

    private Random rand = new Random();

    @Override
    public void init() {
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Chase the Box");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Image Setups
        enemy = getImage(base, "data/enemy.png");
        character = getImage(base, "data/box.png");
        collectible = getImage(base, "data/collectible.png");

    }

    @Override
    public void start() {
        box = new Box();
        bad1 = new Enemy(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
        clbl1 = new Collectible(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void run() {
        while (state == GameState.RUNNING) {
            if (isMore()) {
                Enemy.enemies.add(new Enemy(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)));
                Collectible.collectibles.add(new Collectible(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)));
                Collectible.collectibles.add(new Collectible(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)));

                setMore(false);
            }

            box.update();
            for (Enemy enmy : Enemy.enemies) {
                enmy.update();
            }
            for (Collectible clbl : Collectible.collectibles) {
                clbl.update();
            }

            if (!isLiving()) {
                state = GameState.LOSE;
            }

            if (isWin()) {
                state = GameState.WIN;
            }

            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);

    }

    @Override
    public void paint(Graphics g) {
        if (state == GameState.RUNNING) {
            for (Collectible clbl : Collectible.collectibles) {
                if (clbl.isVisible()) {
                    g.drawImage(collectible, clbl.getCenterX() - 5, clbl.getCenterY() - 5, this);
                }
            }
            for (Enemy enmy : Enemy.enemies) {
                if (enmy.isChasing()) {
                    g.drawImage(enemy, enmy.getCenterX() - 5, enmy.getCenterY() - 5, this);
                } else {
                    g.drawImage(collectible, enmy.getCenterX() - 5, enmy.getCenterY() - 5, this);
                }
            }
            g.drawImage(character, box.getCenterX() - 5, box.getCenterY() - 5, this);
        } else if (state == GameState.LOSE) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 480);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            g.drawString("LOSER", 350, 240);
            g.drawString("YOUR SCORE: " + getScore(), 350, 260);
        } else if (state == GameState.WIN) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 480);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
            g.drawString("WINNER", 350, 240);
            g.drawString("YOUR SCORE: " + getScore(), 350, 260);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("+ UP");
            box.moveUp();
            break;

        case KeyEvent.VK_DOWN:
            System.out.println("+ DOWN");
            box.moveDown();
            break;

        case KeyEvent.VK_LEFT:
            System.out.println("+ LEFT");
            box.moveLeft();
            break;

        case KeyEvent.VK_RIGHT:
            System.out.println("+ RIGHT");
            box.moveRight();
            break;

        case KeyEvent.VK_SPACE:
            System.out.println("+ SPACE");
            break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("- UP");
            box.setMovingUp(false);
            box.stop();
            break;

        case KeyEvent.VK_DOWN:
            System.out.println("- DOWN");
            box.setMovingDown(false);
            box.stop();
            break;

        case KeyEvent.VK_LEFT:
            System.out.println("- LEFT");
            box.setMovingLeft(false);
            box.stop();
            break;

        case KeyEvent.VK_RIGHT:
            System.out.println("- RIGHT");
            box.setMovingRight(false);
            box.stop();
            break;

        case KeyEvent.VK_SPACE:
            System.out.println("- SPACE");
            break;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public static Box getBox() {
        return box;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        StartingClass.score = score;
    }

    public static boolean isLiving() {
        return living;
    }

    public static void setLiving(boolean living) {
        StartingClass.living = living;
    }

    public static boolean isWin() {
        return win;
    }

    public static void setWin(boolean win) {
        StartingClass.win = win;
    }

    public static boolean isMore() {
        return more;
    }

    public static void setMore(boolean more) {
        StartingClass.more = more;
    }

}
