import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private ArrayList<Brick> bricksCopy;
    private int[][] brickLayout;
    private boolean[][] grid;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        bricksCopy = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
            bricksCopy.add(b);
        }
        brickLayout = new int[bricks.size()][cols];
        grid = new boolean[30][40];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
        for (int i = 0; i < brickLayout.length; i++){
            for (int j = 0; j < brickLayout[0].length;j++){
                System.out.print(brickLayout[i][j]);
            }
            System.out.println();
        }
    }

    public int[][] getBrickLayout() {
        return brickLayout;
    }

    public boolean[][] getGrid(){
        return grid;
    }
    
    public ArrayList<Brick> getBricks(){
        return bricksCopy;
    }

    public Brick getNextBrick(){
        return bricks.remove(0);
    }

    public void placeBrick(){
        if (!bricks.isEmpty()){
            doOneBrick();
        }
    }

    public void doOneBrick() {
        
        Brick b = bricks.remove(0);
        int row = brickLayout.length - 1; 

        for (int i = 0; i < brickLayout.length; i++) {
            for (int j = b.getStart(); j <= b.getEnd(); j++) {
                if (brickLayout[i][j] == 1) { 
                    row = i - 1;
                    break;
                }
            }
            if (row < brickLayout.length - 1){
                break;
            }
            
        }
        for (int k = b.getStart(); k <= b.getEnd();k++){
            brickLayout[row][k]=1;
        }
        b.setHeight(brickLayout.length-row);
        System.out.println(b);
        }
        
    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

}