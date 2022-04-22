package solve;

import gui.DummyMaze;
import gui.GuiMaze;
import gui.GuiMazeInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import lib.Maze;
import lib.Robot;

/**
 * @author Jeroen Avonts adapted and modified for CSAI assignment 2005 by Walter Schreppers
 * 
 * Date: Nov 13, 2004 Package: solve Project: AI 
 */
public class Main {

    /**
     * <code>file</code> bestandsnaam
     */
    static String file = null;

    /**
     * <code>maze</code> het doolhof
     */
    static Maze maze = null;

    /**
     * <code>gui</code> de gui
     */
    static GuiMazeInterface gui = null;

    /**
     * @param solv
     *            de besturing van de robot
     * @return afgelegde afstand
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static double run(BaseSolver solv) throws FileNotFoundException,
            IOException {
        PrintStream out = new PrintStream(new FileOutputStream(file + solv.fileExtension() ));
        Robot searchRobot = new Robot(maze);
        gui.setRobot(searchRobot);
        solv.setOut(out);
        solv.solveMaze(searchRobot, gui);
        out.close();
        System.out.println("Search Robot :\n"+searchRobot.toString()+"\n");
        return searchRobot.getDistance();
    }

    /**
     * @param args
     *            [-g|-h] maze Main class
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Geef een doolhof mee.");
            return;
        }
        boolean fast = true;
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-g")) {
                fast = false;
            } else if (args[i].equals("-h")) {
                System.out.println("-g\tgui");
                System.out.println("-h\thelp");
                return;
            } else if (!args[i].startsWith("-")) {
                file = args[i];
            }
        }
        if (file == null) {
            System.err.println("Geef een doolhof mee.");
            return;
        }
        try {
            maze = new Maze(file);
            System.out.println("*** Maze: " + file + "***");
            if (!fast)
                gui = new GuiMaze(maze);
            else
                gui = new DummyMaze();

            RandomSolver randomSolve = new RandomSolver( fast );
            run( randomSolve ); // this runs the random solver (extend other solvers from BaseSolver class and run here, 
                                // give them a different fileExtension so that you end up with distinct solution files for every algorithm)
            
            BufferedReader in = new BufferedReader(new FileReader(file+ randomSolve.fileExtension() ));
            Robot offlineRobot = new Robot(maze);
            offlineRobot.readIn(in);
            in.close();

            double shortDistance = offlineRobot.getDistance();
            System.out.println("Offline bot : \n" + offlineRobot.toString() +"\n");
            

        } catch (FileNotFoundException e) {
            System.err.println("doolhof niet gevonden: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error:" + e.getMessage());
        }
    }
}

