import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[bricks.size()][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }

    public int[][] getBrickLayout() {
        return brickLayout;
    }

    public void doOneBrick() {
        int e = 0;
        boolean a = true;
        if (!bricks.isEmpty()){
            for (int i = brickLayout.length-1; i >= 0; i--){
                boolean test = true;
                Brick b = bricks.remove(0);
                System.out.println(i);
                for (int k = 0; k <brickLayout.length; k++) {
                    for (int j = b.getStart(); j <= b.getEnd(); j++) {
                        System.out.println(k + " and " + j + " i");
                        if (brickLayout[k][j] == 1) {
                            System.out.println(k + " and " + j);
                            System.out.println(b);
                            test = false;
                            System.out.println(test + " no");
                            e = k;
                            a = false;
                            break;
                        }
                    }
                    if (!a){
                        break;
                    }
                }
                if (test) {
                    for (int j = b.getStart(); j <= b.getEnd(); j++) {
                        brickLayout[i][j] = 1;
                    }
                    break;
                }
                else {
                    for (int j = b.getStart(); j <= b.getEnd(); j++) {
                        brickLayout[e-1][j] = 1;
                        b.setHeight(i-e + 1);
                    }
                    printBrickLayout();
                    break;
                }
            }
        }
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

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}