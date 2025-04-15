import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener{

    private boolean[][] grid;
    private BrickLayout brickLayout;
    private ArrayList<Brick> bricks;

    public DrawPanel() {
        this.addMouseListener(this);
        brickLayout = new BrickLayout("src/bricks",40,true);
        grid = brickLayout.getGrid();
        bricks = brickLayout.getBricks();
    }



 protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        int x = 10;
        int y = 10;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, 20, 20);
                if (grid[i][j]) {
                    g2.setColor(Color.RED);
                    g2.fillRect(x,y,20,20);
                }
                x += 25;
            }
            x = 10;
            y += 25;
        }
    }

    // falling brick attempt failed
    /* private void brickFall(){
        int start = 1;
        if (bricks != null && bricks.getBricks() != null){
        for (Brick brick : bricks.getBricks()){
            System.out.println(brick + "add");
            brick.setHeight(start);
            fall.add(brick);
            start++;
        }
    }
        System.out.println(fall.size() + "h");

    }

    private void dropBricks(){
        ArrayList<Brick> fallingBricks = new ArrayList<>();
        for (Brick brick: fall){
            int row = brick.getHeight() + 1;
            if (fallTest(brick, row)){
                brick.setHeight(row);
                fallingBricks.add(brick);
            }
            else {
                setBrick(brick);
            }
        }
        fall = fallingBricks;
        repaint();
    }

    private boolean fallTest(Brick brick, int row){
        if (row >= grid.length) {
            return false;
        }
        for (int i = brick.getStart(); i <= brick.getEnd(); i++){
            if (grid[row][i]){
                return false;
            }
        }
        return true;
    }

    private void setBrick(Brick brick){
        for (int i = brick.getStart(); i <= brick.getEnd(); i++){
            grid[brick.getHeight()][i] = true;
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
    } */

    private void placeBrick(Brick brick){
        int row = grid.length - brick.getHeight();
        for (int i = brick.getStart(); i <= brick.getEnd(); i++){
            grid[row][i] = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (bricks.size() != 0){
        Brick nextBrick = bricks.remove(0);
        placeBrick(nextBrick);
        }
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