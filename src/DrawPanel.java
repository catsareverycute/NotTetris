import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {

    private boolean[][] grid;
    private int[][] brickWall;
    private int x = 10;
    private int y = 10;
    private int height = 1;
    private int i = 0;
    private int j = 0;

    public DrawPanel() {
        grid = new boolean[30][40];
        this.addMouseListener(this);
        long time = System.currentTimeMillis();
        System.out.println(time);
        BrickLayout bricks = new BrickLayout("src/bricks",7,true);
        brickWall = bricks.getBrickLayout();
        }



 protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < brickWall[0].length; j++) {
                g2.drawRect(x, y, 20, 20);
                this.i = i;
                this.j = j;
                if (change()){
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, 20, 20);
                }
                g2.setColor(Color.BLACK);
                x += 25;
            }
            x = 10;
            y += 25;
        }
        height++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void randomGen(){
        grid = new boolean[30][40];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++) {
                int yes = (int)(Math.random()*10);
                if (yes < 3){
                    grid[i][j] = true;
                }
            }
        }
    }

    protected boolean change() {
        if (brickWall[i][j] == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        change();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}