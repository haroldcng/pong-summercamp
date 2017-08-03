import java.awt.*;
import javax.swing.*;
import Engine.GameWindow;
import corrupted.Game;

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main extends GameWindow
{


    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
        setRunner(new Pong());
    }
    
    public static void main(String[] args){
        (new Main()).startProgram();
    }
}
