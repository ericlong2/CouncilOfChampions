import java.awt.image.*;
import javax.imageio.*; 
class Drop extends Life {
  Drop(int health, int gold, int exp) {
    super(health, gold, exp);
  }
  static BufferedImage sprite;
  static void lowerHealth() {
    for (int i = 0; i < 50; i++) {
      for (int j = 0; j < 50; j++) {
        if (GamePanel.currentLifeMap[i][j] instanceof Drop) {
          GamePanel.currentLifeMap[i][j].health--;
          if (GamePanel.currentLifeMap[i][j].health <= 0) {
            GamePanel.currentLifeMap[i][j] = null;
          }
        }
      }
    }
  }
}