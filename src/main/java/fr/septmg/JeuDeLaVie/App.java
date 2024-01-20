package fr.septmg.JeuDeLaVie;
/**
 * @file App.java
 * @brief Contient la classe principale de l'application jeu de la vie.
 */

import javax.swing.JFrame;
import java.awt.Dimension;

/**
 * @class App
 * @brief Classe principale de l'application.
 */
public class App 
{
    public static int WIDTH = 500;
    public static int HEIGHT = 500;

     /**
     * @brief Fonction principale exécutée au démarrage de l'application.
     * @param args [in] Les arguments de la ligne de commande (non utilisés dans cet exemple).
     * @details Lancement de l'application
     * @pre ars[0] > 0 && args[0] <= 500 && args[1] > 0 && args[1] <= 500 && args[2] > 0
     */
    public static void main(String[] args)
    {
        int nbcasesX = 100;
        int nbcasesY = 100;
        int time = 0;

        if (args.length == 1) {
            int nbcases = 100;
            try {
                nbcases = Integer.parseInt(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(nbcases > 0 && nbcases <= 500) {
                nbcasesX = nbcases;
                nbcasesY = nbcases;
            }
        }
        else if (args.length > 1) {
            int nbcases1 = 100, nbcases2 = 100;
            try {
                nbcases1 = Integer.parseInt(args[0]);
                nbcases2 = Integer.parseInt(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(nbcases1 > 0 && nbcases2 > 0 && nbcases1 <= 500 && nbcases2 <= 500) {
                nbcasesX = nbcases1;
                nbcasesY = nbcases2;
            }

            if(args.length > 2) {
                try {
                    time = Integer.parseInt(args[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridPanel gridPanel = new GridPanel(nbcasesX, nbcasesY);
        frame.add(gridPanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
        while (frame.isActive()) {
            Dimension size = frame.getSize();
            int width = (int) size.getWidth();
            int height = (int) size.getHeight();
            
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gridPanel.nextGen(width, height);
        }
    }
}
