package fi.nano.tangential.ui;

import fi.nano.tangential.Game;
import fi.nano.tangential.gameLogic.enums.Direction;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Peli-ikkuna.
 *
 * @author Nanofus
 */
public class Window implements Runnable {

    private int windowWidth = 800;
    private int windowHeight = 600;
    
    private final Game game;

    private JFrame frame;

    private final InputListener inputListener;
    private Renderer renderer;
    private final ImageLoader imageLoader;

    public Window(Game game) {
        this.game = game;

        imageLoader = new ImageLoader();
        inputListener = new InputListener(game);
    }

    @Override
    public void run() {
        frame = new JFrame("Tangential");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(windowWidth+frame.getInsets().left+frame.getInsets().right, windowHeight+frame.getInsets().top+frame.getInsets().bottom));

        frame.addKeyListener(inputListener);

        renderer = new Renderer(imageLoader,game.GetLevel(),game.GetLevel().GetPlayer(),windowWidth,windowHeight);

        frame.add(renderer);
    }
    
    public void Refresh() {
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void RotateCamera(Direction direction) {
        renderer.RotateCamera(direction);
    }

}
