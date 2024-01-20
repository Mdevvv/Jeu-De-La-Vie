package fr.septmg.JeuDeLaVie;
/**
 * @file App.java
 * @brief Contient la classe principale de l'application jeu de la vie.
 */

import javax.swing.*;

/**
 * @class App
 * @brief Classe principale de l'application.
 */
public class App 
{
     /**
     * @brief Fonction principale exécutée au démarrage de l'application.
     * @param args Les arguments de la ligne de commande (non utilisés dans cet exemple).
     */
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Hello World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 250);
        frame.setVisible(true);
    }
}
