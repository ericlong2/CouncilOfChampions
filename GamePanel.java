// [GamePanel.java]

// Imports
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

// Class
class GamePanel extends JPanel implements KeyListener 
{
  
  // Variable declaration
  static final int LEFT = 0;
  static final int UP = 1;
  static final int RIGHT = 2;
  static final int DOWN = 3;
  static int timePlayed;
  static boolean movedFromSpawn = true;
  static Shield currentShield;
  static Weapon currentWeapon;
  static LinkedList<Quest> quests = new LinkedList<Quest>();
  static LinkedList<Item> inventory = new LinkedList<Item>();
  static int currentMapNum;
  static Map currentMap[][];
  static Life currentLifeMap[][];
  static int[] monsterDefeated = new int[8];
  static int bossDefeated;
  static int deaths;
  static Player p;
  
  // Constructor
  GamePanel()  {
    // Setup the game panel
    setPreferredSize(new Dimension(600,600));
    setFocusable(true);
    addKeyListener(this);
    try{GamePanel.load();}catch(IOException e){}
    currentMapNum = 0;
    Game.startMusic("Music/town.mp3");
    movedFromSpawn = false;
    currentMap = Maps.maps[currentMapNum];
    resetMaps();
    NPC.spawn(); // Spawns NPCs
    p = new Player(Game.healthP, Game.expP, Game.shieldP, Game.attackP, Game.goldP, 8); // Create player with stats
    GamePanel.p.currentSprite = 2;
    currentLifeMap[25][25] = p; // Size of map
    Inventory.getCurrentShield();
    Inventory.getCurrentWeapon();
  }
  
  // Method to reset the map
  static void resetMaps() {
    currentLifeMap = new Life[50][50];
  }
  
  // Method to check for keyboard input, translates to player movement
  public void keyReleased(KeyEvent e) {
    // Player coordinates on the map
    int posX = Player.getPosX();
    int posY = Player.getPosY();
    int key = e.getKeyChar();
    
    // Diffrentiates between vertical and horizontal movement
    switch(key) {
      case 'a':
        Move.movePlayer(posX, posY, posX - 1, posY, LEFT); // Move left
        break;
      case 's':
        Move.movePlayer(posX, posY, posX, posY + 1, DOWN); // Move down
        break;
      case 'd':
        Move.movePlayer(posX, posY, posX + 1, posY, RIGHT); // Move right
        break;
      case 'w':
        Move.movePlayer(posX, posY, posX, posY - 1, UP); // Move up
        break;
      default:
        break;
    }
  }
  
  public void keyPressed(KeyEvent e) {}
 
  public void keyTyped(KeyEvent e) {}
  
  // Draw map
  public void paintComponent(Graphics g) { 
    super.paintComponent(g);
    draw(g);
    repaint();
  }
  
  static void draw(Graphics g) {
    try {
      Draw.draw(g);
      Thread.sleep(100);
      move();
      Quests.checkCompletedQuests();
      Drop.lowerHealth();
    } catch (Exception e) {}
  }
  
  // Moves monsters and npcs on the map
  static void move() {
    NPC.move();
    Monster.move();
  }
  
  // Loads map
  static void load() throws IOException{
      Boss.loadSprites();
      Building.loadSprites();
      Maps.getMaps();
      Building.spawn();
      Monster.loadSprites();
      Monster.load();
      NPC.loadSprites();
      NPC.load();
      Player.loadSprites();
      Shield.loadSprites();
      Shield.load();
      Weapon.loadSprites();
      Weapon.load();
      Drop.sprite = ImageIO.read(new File("Image/drop.png"));
  }
}