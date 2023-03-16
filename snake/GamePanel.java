package Timer;

import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.*;


public class GamePanel extends JPanel implements ActionListener{
    static final int SCREENWIDTH = 1200;
    static final int SCREENHEIGHT = 750;
    static final int UNITSIZE = 25;
    static final int GAMEUNITS = (SCREENWIDTH*SCREENHEIGHT) / UNITSIZE;
    static final int DELAY = 75;

    final int x[] = new int[GAMEUNITS];
    final int y[] = new int[GAMEUNITS];
    int snakeLength = 6;
    int foodAte;
    int foodX;
    int foodY;

    char direction = 'R';
    boolean running = false;
    private Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        checkFood();
        running = true;
        timer = new Timer(125, this);
        timer.start();
        
    }

    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.green);
            g.fillOval(foodX, foodY, UNITSIZE, UNITSIZE);

            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.yellow);
                    g.fillRoundRect(x[i], y[i], UNITSIZE, UNITSIZE, 10, 10);
                } else {
                    g.setColor(Color.yellow);
                    g.fillRoundRect(x[i], y[i], UNITSIZE, UNITSIZE, 10, 10);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", Font.BOLD, 35));
            FontMetrics score = getFontMetrics(g.getFont());
            g.drawString("Score: " + foodAte, (SCREENWIDTH - score.stringWidth("Score: " + foodAte))/2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
        
    }

    public void nextFood() {
        foodX = random.nextInt((int)(SCREENWIDTH/UNITSIZE))*UNITSIZE;
        foodY = random.nextInt((int)(SCREENHEIGHT/UNITSIZE))*UNITSIZE;
    }

    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];

        }

        switch(direction) {
            case 'U': y[0] = y[0] - UNITSIZE; break;
            case 'D': y[0] = y[0] + UNITSIZE; break;
            case 'L': x[0] = x[0] - UNITSIZE; break;
            case 'R': x[0] = x[0] + UNITSIZE; break;
        }
    }

    public void checkFood() {
        if ((x[0] == foodX) && (y[0] == foodY)) {
            snakeLength++;
            foodAte++;
            nextFood();
        }
    }

    public void checkCollision() {
        for (int i = snakeLength; i > 0; i--) {
            if ((x[0] == x[i]) && y[0] == y[i]) {
                running = false;
            }
        }
        // check if snake exceeds the programs border
        if ((x[0] < 0) || (x[0] > SCREENWIDTH) || (y[0] < 0) || (y[0] > SCREENHEIGHT)) {
            running = false;
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", Font.BOLD, 75));
        FontMetrics end = getFontMetrics(g.getFont());
        g.drawString("GAME OVER", (SCREENWIDTH - end.stringWidth("GAME OVER"))/2, (SCREENHEIGHT/2) - 100);
        FontMetrics score = getFontMetrics(g.getFont());
        g.drawString("Score: " + foodAte, (SCREENWIDTH - score.stringWidth("Score: " + foodAte))/2, SCREENHEIGHT/2 + 75
        - g.getFont().getSize());

    }

    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFood();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT: 
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT: 
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN: 
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_UP: 
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
        }
    }

    }
}
