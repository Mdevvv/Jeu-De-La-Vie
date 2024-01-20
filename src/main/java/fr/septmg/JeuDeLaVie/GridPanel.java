package fr.septmg.JeuDeLaVie;
/**
 * @file GridPanel.java
 * @brief Contient la classe principale de la grille.
 */

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @class GridPanel
 * @brief Classe principale de la grille.
 * @details Classe qui contient la grille de jeu de la vie.
 */
public class GridPanel extends JPanel {
    private boolean[][] grid;
    private static int celluleDimensionX;
    private static int celluleDimensionY;

    private int getWidth;
    private int getHeight;

    public GridPanel(int getWidth, int getHeight) {
        grid = new boolean[getHeight][getWidth];
        Random random = new Random();
        
        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                grid[x][y] = random.nextInt(2) == 1;
            }
        }
        this.getHeight = getHeight;
        this.getWidth = getWidth;
        celluleDimensionX = App.WIDTH/getWidth;
        celluleDimensionY = App.HEIGHT/getHeight;
    }
    private void drawCell(Graphics g, int x, int y) {
        g.fillRect(x * celluleDimensionX, y * celluleDimensionY, celluleDimensionX, celluleDimensionY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        super.paintComponent(g);
        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (grid[x][y]) {
                    drawCell(g, x, y);
                }
            }
        }
    }

    private int countNeyborAlive(int x, int y) {
        int result = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int voisinX = x + i;
                int voisinY = y + j;

                // Vérifier que le voisin est à l'intérieur de la grille
                if (voisinX >= 0 && voisinX < grid[0].length && voisinY >= 0 && voisinY < grid.length) {
                    // Ignorer la cellule elle-même
                    if (i != 0 || j != 0) {
                        // Incrémenter le compteur si le voisin est vivant
                        if (grid[voisinX][voisinY]) {
                            result++;
                        }
                    }
                }
            }
        }

        return result;
    }

    public void nextGen(int width, int height) {
        celluleDimensionX = width/getWidth;
        celluleDimensionY = height/getHeight;
        boolean[][] updatedGrid = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                int neighborAlive = countNeyborAlive(x, y);

                if (grid[x][y])
                    // Cellule vivante
                    updatedGrid[x][y] = neighborAlive == 2 || neighborAlive == 3;
                else
                    // Cellule morte
                    updatedGrid[x][y] = neighborAlive == 3;
            }
        }
        grid = updatedGrid;
        repaint();
    }
}