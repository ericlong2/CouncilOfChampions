// [Draw.java]

// Imports
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

// Class

class Draw 
{
  
  static void draw(Graphics g) throws NullPointerException, IOException {
    // Setup and import images from sprite sheet
    Player.visibleArea();
    BufferedImage background = Maps.mapBackgrounds[GamePanel.currentMapNum];
    BufferedImage subBackground = background.getSubimage(Player.leftCol * (background.getWidth() / 50), Player.upRow * (background.getHeight() / 50), background.getWidth() / 2, background.getHeight() / 2);
    BufferedImage border = ImageIO.read(new File("Image/border.png"));
    g.drawImage(subBackground, 0, 0, 600, 600, null);
    
    for (int i = Player.upRow; i <= Player.downRow; i++) {
      for (int j = Player.leftCol; j <= Player.rightCol; j++) {
        // If nothing, draw a border
        if (GamePanel.currentMap[i][j] == null) {
          g.drawImage(border, (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        } 
        // If building, draw a building
        else if (GamePanel.currentMap[i][j] instanceof Building) {
          g.drawImage(Building.sprites[GamePanel.currentMap[i][j].currentSprite - 1], (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        }
        // If Player, draw a Player
        if (GamePanel.currentLifeMap[i][j] instanceof Player) {
          g.drawImage(Player.sprites[GamePanel.p.currentSprite - 1], (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        } 
        // If Boss, draw a Boss
        else if (GamePanel.currentLifeMap[i][j] instanceof Boss) {
          g.drawImage(Boss.sprites[GamePanel.currentLifeMap[i][j].currentSprite - 1], (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        } 
        // If Monster, draw a Monster
        else if (GamePanel.currentLifeMap[i][j] instanceof Monster) {
          g.drawImage(Monster.sprites[((Monster)GamePanel.currentLifeMap[i][j]).monsterNum - 1][GamePanel.currentLifeMap[i][j].currentSprite - 1], (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        } 
        // If NPC, draw a NPC
        else if (GamePanel.currentLifeMap[i][j] instanceof NPC) {
          g.drawImage(NPC.sprites[((NPC)GamePanel.currentLifeMap[i][j]).npcNum - 1][GamePanel.currentLifeMap[i][j].currentSprite - 1], (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        } else if (GamePanel.currentLifeMap[i][j] instanceof Drop) {
          g.drawImage(Drop.sprite, (j - Player.leftCol) * 24, (i - Player.upRow) * 24, 24, 24, null);
        }
      }
    }
  }
}