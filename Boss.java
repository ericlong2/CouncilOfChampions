// [Boss.java]

// Import
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;

// Class
class Boss extends Life
{
  
  // Constructor
  Boss(int health, int exp, int shield, int attack, int gold, int currentSprite) {
    super(health, exp, shield, attack, gold, currentSprite);
  }
  
  // Variables
  static BufferedImage[] sprites;
  
  // Load sprites
  static void loadSprites() throws IOException {
    BufferedImage sheet = ImageIO.read(new File("Image/boss.png"));
    sprites = new BufferedImage[4 * 3];
    int width = sheet.getWidth() / 3;
    int height = sheet.getHeight() / 4;
    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < 3; i++) {
        sprites[(j * 3) + i] = sheet.getSubimage(i * width,j * height,width,height);
      }
    }
  }
  
  // Move boss on map
  static void move() throws NullPointerException{
    for (int i = 0; i < 50; i++) {
      for (int j = 0; j < 50; j++) {
        
        // Checks if life is boss
        if (GamePanel.currentLifeMap[i][j] instanceof Monster && !GamePanel.currentLifeMap[i][j].inBattle) {
          
          // 50% chance of moving
          int move = rand.nextInt(2);
          if (move == 1) {
            
            // Gets Direction
            int direction = rand.nextInt(4);
            int x = j;
            int y = i;
            // Moving Left
            if (direction == GamePanel.LEFT && j > 0) {
              GamePanel.currentLifeMap[i][j].currentSprite = 5;
              x--;    
              
            // Moving Up
            } else if (direction == GamePanel.UP && i > 0) {
              GamePanel.currentLifeMap[i][j].currentSprite = 11;
              y--;
              
            // Moving Right
            } else if (direction == GamePanel.RIGHT && j < 49) {
              GamePanel.currentLifeMap[i][j].currentSprite = 8;
              x++;
              
            // Moving Down
            } else if (direction == GamePanel.DOWN && i < 49) {
              GamePanel.currentLifeMap[i][j].currentSprite = 2;
              y++;
            }
            
            if (GamePanel.currentMap[y][x] instanceof Land) {
              if (GamePanel.currentLifeMap[y][x] == null) {
                GamePanel.currentLifeMap[y][x] = GamePanel.currentLifeMap[i][j];
                GamePanel.currentLifeMap[i][j] = null;
              } else if (GamePanel.currentLifeMap[y][x] instanceof Player && !GamePanel.currentLifeMap[y][x].inBattle) {
                GamePanel.currentLifeMap[y][x].inBattle = true;
                GamePanel.currentLifeMap[i][j].inBattle = true;
                Question.showQuestion();
              }
            }
          }
        }
      }
    }
  }
  
  // Spawns the boss
  static void spawn() throws NullPointerException {
    while(true) {
      
      // Spawns at random place
      int x = rand.nextInt(50);
      int y = rand.nextInt(50);
      
      // Checks if spot is free
      if (GamePanel.currentMap[y][x] instanceof Land && GamePanel.currentLifeMap[y][x] == null) {
        
        // Spawns boss
        GamePanel.currentLifeMap[y][x] = new Boss(50000, 5000, 700, 50, 2000, 2);
        break;
      }      
    }
  }
}