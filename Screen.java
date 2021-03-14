import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Screen extends JPanel implements KeyListener, MouseListener {

    private Player player;
    private World world;
    private Timer timer;
    private Thread timerThread;

    private Color skyColor = new Color(107, 208, 255);
    private Font font = new Font("Arial", Font.BOLD, 30);

    Robot robot;
    private Point mousePoint;
    private int mstartx, mstarty;

    private boolean locked = false;

    private boolean gameOver = false, gameWon = false;

    private boolean stop = false;

    private EzImage load, win, lose;
    private int loadBar = 0;

    public Screen() {
        this.setLayout(null);

        player = new Player("Bob");
        world = new World("GameMap", player);
        player.addToWorld(world);
        PublicReference.setPlayer(player);
        PublicReference.setWorld(world);

        addKeyListener(this);
        addMouseListener(this);

        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Roofbot");
        }

        load = new EzImage("Loading.png");
        win = new EzImage("Win.png");
        lose = new EzImage("Lose.png");

        timer = new Timer(this);
        timerThread = new Thread(timer);
        timerThread.start();

        this.setFocusable(true);
    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1000, 750);
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);

        g.setColor(skyColor);
        g.fillRect(0,0,1000,750);

        world.render(g);

        player.render(g);

        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("Time remaining: " + timer.getTimeString() + " Coins remaining: " + world.getItemsRemaining(), 20, 40);

        if (gameOver) {
            if (gameWon) {
                win.draw(g, 0, 0);
            } else {
                lose.draw(g, 0, 0);
            }
        }

        if (loadBar < 100) {
            loadBar++;
            load.draw(g, 0, 0);
            g.setColor(Color.GRAY);
            g.fillRect(250, 550, loadBar * 5, 30);
        }
    }

    

    public void start() {
        // System.out.println("controls started");
        while (true) {
            try {
                Thread.sleep(40);
            } catch (Exception e) {
                System.out.println("oof");
            }

            // getMouseInfo();
            // world.highlightMouse(mousePoint.x - this.getLocationOnScreen().x, mousePoint.y - this.getLocationOnScreen().y);
            world.update();
            if (world.getItemsRemaining() == 0) endGame();

            repaint();
            if (gameOver) return;
        }
        

        
    }

    public void getMouseInfo() {
        if (MouseInfo.getPointerInfo() != null)
            mousePoint =  MouseInfo.getPointerInfo().getLocation();
    }

    public void keyTyped(KeyEvent e) {
        ;
    }

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 38) {
            player.moveZ(1);
        } else if (e.getKeyCode() == 39) {
            player.moveX(-1);
        } else if (e.getKeyCode() == 40) {
            player.moveZ(-1);
        } else if (e.getKeyCode() == 37) {
            player.moveX(1);
        } /*else if (e.getKeyCode() == 32) {
            player.moveY(0.2);
        } else if (e.getKeyCode() == 16) {
            player.moveY(-0.2);
        } else if (e.getKeyCode() == 69) {
            if (locked) {
                locked = false;
            } else locked = true;
        } else if (e.getKeyCode() == 83) {
            world.saveWorld();
        } else if (e.getKeyCode() >= 49 && e.getKeyCode() <= 57) {
            player.setHotbarSlot(e.getKeyCode() - 49);
        }*/
        // System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
        // System.out.println(e.getKeyCode());
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        ;
    }

    public void mouseClicked (MouseEvent mouseEvent) {
        // world.highlightMouse(mouseEvent.getX(), mouseEvent.getY());
        // world.interpretMouseClick(mouseEvent);
        // System.out.println("clicked");
    } 

    public void mouseEntered (MouseEvent mouseEvent) {}

    public void mousePressed (MouseEvent mouseEvent) {} 

    public void mouseReleased (MouseEvent mouseEvent) {} 

    public void mouseExited (MouseEvent mouseEvent) {}

    public void endGame() {
        if (world.getItemsRemaining() == 0) {
            gameWon = true;
        } else {
            gameWon = false;
        }
        gameOver = true;
        repaint();
    }
}