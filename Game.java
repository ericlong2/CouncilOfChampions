// [Game.java]

// Imports
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javafx.scene.media.*;
import javafx.embed.swing.JFXPanel;

// Class
class Game extends JFrame
{
  
  // Variables
  static int playerClass;
  static long startTime;
  static MediaPlayer music;
  JMenuBar menuBar = new JMenuBar();
  JMenu menu = new JMenu("Menu");
  JMenuItem openInventory = new JMenuItem("Inventory");
  JMenuItem quests = new JMenuItem("Quests");
  JMenuItem save = new JMenuItem("Save");
  JMenuItem stats = new JMenuItem("Stats");
  JMenuItem exitToMenu = new JMenuItem("Exit to Menu");
  
  // Constructor
  Game() {
    
    // Set up JFrame
    setTitle("Council of Champions");
    setSize(600,600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    // Open Inventory Option
    openInventory.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Inventory.showInventory();
      }
    });
    menu.add(openInventory);
    
    // Show Quests Option
    quests.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Quests.display();
      }
    });
    menu.add(quests);
    
    // Save Option
    save.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        // Update Highscores
        File highscoreFile = new File("Save/highscores.txt");
        int hs1 = 0;
        int hs2 = 0;
        int hs3 = 0;
        int hs4 = 0;
        int hs5 = 0;
        try {
          
          // Get Highscores
          Scanner sc = new Scanner(highscoreFile);
          hs1 = sc.nextInt();
          hs2 = sc.nextInt();
          hs3 = sc.nextInt();
          hs4 = sc.nextInt();
          hs5 = sc.nextInt();
          
          // Change Highscores
          if (GamePanel.p.exp > hs5) {
            if (GamePanel.p.exp > hs4) {
              if (GamePanel.p.exp > hs3) {
                if (GamePanel.p.exp > hs2) {
                  if (GamePanel.p.exp > hs1) {
                    hs1 = GamePanel.p.exp;
                  } else {
                    hs2 = GamePanel.p.exp;
                  }
                } else {
                  hs3 = GamePanel.p.exp;
                }
              } else {
                hs4 = GamePanel.p.exp;
              }  
            } else {
              hs5 = GamePanel.p.exp;
            }
          }
         
          // Print highscores to file
          PrintWriter output = new PrintWriter(highscoreFile);
          output.println(hs1 + " " + hs2 + " " + hs3 + " " + hs4 + " " + hs5);
          
          // Open save file
          File saveFile = new File("Save/save.txt");
          output = new PrintWriter(saveFile);
          
          // Get timePlayed
          GamePanel.timePlayed = ((int)Math.round(System.nanoTime() - startTime)) / 1000000000 + GamePanel.timePlayed;
          
          // Update stats
          output.println(GamePanel.p.health + " " + GamePanel.p.exp + " " + GamePanel.p.shield + " " + GamePanel.p.attack + " " + GamePanel.p.gold + " " + playerClass + " " + GamePanel.timePlayed);
          
          // Monster stats
          for (int i = 0; i < 8; i++) {
            output.println(GamePanel.monsterDefeated[i]);
          } 
          
          // Boss kills and deaths
          output.println(GamePanel.bossDefeated + " " + GamePanel.deaths);
          
          // Inventory
          for (int i = 0; i < GamePanel.inventory.size(); i++) {
            Item it = GamePanel.inventory.get(i);
            if (it instanceof Weapon) {
              output.println(it.name + " " + ((Weapon)it).attack + " " + it.cost);
            } else {
              output.println(it.name + " " + ((Shield)it).shield + " " + it.cost);
            }
          }
        } catch (IOException io) {
          System.out.println("Save file not found");
        }
        
      }
    });
    menu.add(save);
    
    // Stats Option
    stats.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Stats.display();
      }
    });
    menu.add(stats);
    
    // Exit to menu option
    exitToMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        try { 
          Menu.startMenu();
        } catch (IOException io) {
          System.out.println("Menu file not foudn");
        }
      }
    });
    menu.add(exitToMenu);
    
    // Adding Menu Bar
    menuBar.add(menu);
    setJMenuBar(menuBar);
    
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        music.stop();
      }
    });
    // Adding GamePanel
    add(new GamePanel());
    
    // Resize and set visible
    pack();
    setResizable(false);
    setVisible(true);
  }
  
  // Player variables
  static int healthP;
  static int expP;
  static int shieldP;
  static int attackP;
  static int goldP;
  
  // Starting Game
  public static void startGame (int health, int exp, int shield, int attack, int gold, int classC) {
    
    // Current time in nanoseconds
    startTime = System.nanoTime();
    
    // Sets player variables
    healthP = health;
    expP = exp;
    shieldP = shield;
    attackP = attack;
    goldP = gold;
    playerClass = classC;
    
    // Opens the JFrame
    Game g = new Game();
  }
  
  static void startMusic(String filename){
    final JFXPanel fxPanel = new JFXPanel();
    Media hit = new Media(new File(filename).toURI().toString());
    music = new MediaPlayer(hit);
    music.play();
  }
}