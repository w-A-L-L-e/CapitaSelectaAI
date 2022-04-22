package lib;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Jeroen Avonts and Walter Schreppers
 * 
 * Date: Nov 13, 2004 Package: lib Project: AI TODO
 */
public class Maze {
    /**
     * Light <code>cFloor</code>
     */
    public final static int cFloor = 0;

    /**
     * Light <code>cRoad</code>
     */
    public final static int cRoad = 1;

    /**
     * Light <code>cTurn</code>
     */
    public final static int cTurn = 2;

    /**
     * Light <code>cStart</code>
     */
    public final static int cStart = 3;

    /**
     * Light <code>cEnd</code>
     */
    public final static int cEnd = 4;

    /**
     * <code>matrix</code> matrix waarin de elementen van de maze insteken
     */
    private int matrix[][];

    /**
     * <code>fStart</code> de startpositie
     */
    private Pos fStart;

    /**
     * <code>fEnd</code> de endpositie
     */
    private Pos fEnd;

    /**
     * <code>rows</code> het aantal rijen
     */
    private int rows;

    /**
     * <code>cols</code> het aantal kolommen
     */
    private int cols;

    /**
     * @param file
     *            File with maze
     * @throws FileNotFoundException
     * @throws IOException
     *             Construct a maze from file
     */
    public Maze(String file) throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        while (!in.ready()) {
        }

        String read = in.readLine();
        rows = Integer.valueOf(read.trim());
        read = in.readLine();
        cols = Integer.valueOf(read.trim());

        matrix = new int[cols][rows];
        for (int y = 0; y < rows; ++y) {
            String line = in.readLine();
            String items[] = line.split(" ");
            if (items.length != cols)
                throw new IOException();
            for (int x = 0; x < items.length; ++x) {
                if (items[x].equals("_"))
                    matrix[x][y] = cFloor;
                else if (items[x].equals("*"))
                    matrix[x][y] = cRoad;
                else if (items[x].equals("#"))
                    matrix[x][y] = cTurn;
                else if (items[x].equals("S")) {
                    matrix[x][y] = cStart;
                    fStart = new Pos(x, y);
                } else if (items[x].equals("E")) {
                    matrix[x][y] = cEnd;
                    fEnd = new Pos(x, y);
                } else
                    matrix[x][y] = cFloor;
            }
        }
        in.close();

    }

    /**
     * @param x
     *            Position x
     * @param y
     *            Position y
     * @return light on (x,y) in the maze
     */
    public int get(int x, int y) {
        if (x < 0 || x >= cols)
            return cFloor;
        else if (y < 0 || y >= rows)
            return cFloor;
        else
            return matrix[x][y];
    }

    /**
     * @param p
     *            Position
     * @return light on p
     */
    public int get(Pos p) {
        return get(p.getX(), p.getY());
    }

    /**
     * @return Position of cEnd
     */
    public Pos getFEnd() {
        return fEnd;
    }

    /**
     * @return Position of cStart
     */
    public Pos getFStart() {
        return fStart;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (matrix == null)
            return "";
        String out = "";
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                out += getStringValue(x, y) + " ";
            }
            out += "\n";
        }
        return out;
    }

    /**
     * @return number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param x
     *            Position x
     * @param y
     *            Position y
     * @return string value of (x,y) in the maze
     */
    public String getStringValue(int x, int y) {
        switch (get(x, y)) {
        case cFloor:
            return "_";

        case cRoad:
            return "*";

        case cTurn:
            return "#";

        case cStart:
            return "S";

        case cEnd:
            return "E";
        default:
            return "_";

        }

    }

    /**
     * @param p
     *            Position
     * @return String value of p in the maze
     */
    public String getStringValue(Pos p) {
        return getStringValue(p.getX(), p.getY());
    }
}
