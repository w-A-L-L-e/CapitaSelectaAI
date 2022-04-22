package gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lib.Direction;
import lib.Maze;
import lib.Pos;
import lib.Robot;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: gui Project: AI TODO
 */
public class GuiMaze extends JFrame implements GuiMazeInterface {

    /**
     * <code>rob</code> is de robot van de maze
     */
    private Robot rob;

    /**
     * <code>maze</code> is de maze waar de robot zich in bevindt
     */
    private Maze maze;

    /**
     * <code>north</code> image met de robot richting het noorden
     */
    private Icon north;

    /**
     * <code>east</code> image met de robot richting het oosten
     */
    private Icon east;

    /**
     * <code>south</code> image met de robot richting het zuiden
     */
    private Icon south;

    /**
     * <code>west</code> image met de robot richting het westen
     */
    private Icon west;

    /**
     * <code>road</code> image voor een gewone weg
     */
    private Icon road;

    /**
     * <code>floor</code> image voor de vloer
     */
    private Icon floor;

    /**
     * <code>turn</code> image voor een kruispunt
     */
    private Icon turn;

    /**
     * <code>start</code> image voor de startpositie
     */
    private Icon start;

    /**
     * <code>end</code> image voor de endpositie
     */
    private Icon end;

    /**
     * <code>matrix</code> matrix waar de images zitten
     */
    private JLabel matrix[][];

    /**
     * @param maze
     *            Gui constructor
     */
    public GuiMaze(Maze maze) {
        north = new ImageIcon("images/north.png");
        east = new ImageIcon("images/east.png");
        south = new ImageIcon("images/south.png");
        west = new ImageIcon("images/west.png");
        road = new ImageIcon("images/road.png");
        floor = new ImageIcon("images/floor.png");
        turn = new ImageIcon("images/turn.png");
        start = new ImageIcon("images/start.png");
        end = new ImageIcon("images/end.png");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.maze = maze;
        getContentPane().setLayout(
                new GridLayout(maze.getRows(), maze.getCols(), 0, 0));
        matrix = new JLabel[maze.getCols()][maze.getRows()];
        for (int x = 0; x < maze.getRows(); ++x) {
            for (int y = 0; y < maze.getCols(); ++y) {
                matrix[y][x] = new JLabel();
                getContentPane().add(matrix[y][x]);
            }
        }
        update();

        setSize(maze.getCols() * 22 + 5, maze.getRows() * 24 + 5);
        setVisible(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see gui.GuiMazeInterface#setRobot(lib.Robot)
     */
    public void setRobot(Robot rob) {
        this.rob = rob;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gui.GuiMazeInterface#update()
     */
    public void update() {
        for (int x = 0; x < maze.getCols(); ++x) {
            for (int y = 0; y < maze.getRows(); ++y) {
                switch (maze.get(x, y)) {
                case Maze.cFloor:
                    matrix[x][y].setIcon(floor);
                    break;
                case Maze.cRoad:
                    matrix[x][y].setIcon(road);
                    break;
                case Maze.cTurn:
                    matrix[x][y].setIcon(turn);
                    break;
                case Maze.cStart:
                    matrix[x][y].setIcon(start);
                    break;
                case Maze.cEnd:
                    matrix[x][y].setIcon(end);
                    break;
                default:
                    System.err.println("Niet gedefinieerde cell");
                }
            }
        }
        if (rob != null) {
            Pos p = rob.getPosition();
            if ((p.getX() >= 0 && p.getX() < maze.getCols())
                    && (p.getY() >= 0 && p.getY() < maze.getRows())) {
                switch (rob.getDir().getType()) {
                case Direction.north:
                    matrix[p.getX()][p.getY()].setIcon(north);
                    break;
                case Direction.east:
                    matrix[p.getX()][p.getY()].setIcon(east);
                    break;
                case Direction.south:
                    matrix[p.getX()][p.getY()].setIcon(south);
                    break;
                case Direction.west:
                    matrix[p.getX()][p.getY()].setIcon(west);
                    break;
                default:
                    System.err.println("Niet gedefinieerde richting");
                }
            } else
                System.err.println("Robot niet in het veld positie " + p);
        }
    }

}
