// [Inventory.java]

// Imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// Class
class Inventory extends JFrame
{
  
  // JFrame variables
  JPanel contentPane = new JPanel();
  JButton back = new JButton();
  
  // Contsructor
  Inventory() {
    
    // Set up JFrame
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Inventory");
    setSize(500,500);
    
    // Get Inventory Items
    for (int i = 0; i < GamePanel.inventory.size(); i++) {
      Item it = GamePanel.inventory.get(i);
      int index = i;
      JButton item;
      if (it instanceof Weapon) {
        item = new JButton(it.name + "\n" + ((Weapon)it).attack + "attack\n" + it.cost + " gold");
      } else {
        item = new JButton(it.name + "\n" + ((Shield)it).shield + "shield\n" + it.cost + " gold");
      }
      
      // JButton for Item
      item.setFont(Menu.font);
      item.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          GamePanel.p.gold = GamePanel.p.gold + it.cost;
          GamePanel.inventory.remove(index);
          dispose();
          showInventory();
        }
      });
      item.setAlignmentX(Component.CENTER_ALIGNMENT);
      contentPane.add(item);
    }
    
    // Back JButton
    back.setPreferredSize(new Dimension(200,50));
    back.setIcon(Menu.resize(new ImageIcon("Image/back.png"),200,50));
    back.setOpaque(false);
    back.setContentAreaFilled(false);
    back.setBorderPainted(false);
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    back.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Add JButton
    contentPane.add(back);
    
    // Add JPanel
    add(contentPane);
    
    // Resize and set visible
    setResizable(false);
    setVisible(true);
  }
  
  // Show Inventory
  static void showInventory () {
    // Open JFrame
    Inventory i = new Inventory();
  }
  
  // Get Players Current Weapon
  static void getCurrentWeapon() {
    int maxDamage = 0;
    boolean hasWeapon = false;
    
    // Gets weapon with the most damage
    for (int i = 0; i < GamePanel.inventory.size(); i++) {
      Item it = GamePanel.inventory.get(i);
      int damage = 0;
      if (it instanceof Weapon) {
        damage = ((Weapon)it).attack;
        hasWeapon = true;
      }
      if (damage > maxDamage) {
        GamePanel.currentWeapon = (Weapon)it;
        maxDamage = damage;
      }
    } 
    
    // Sets the weapon
    if (!hasWeapon) {
      GamePanel.currentWeapon = null;
    }
    if (GamePanel.currentWeapon == null) {
      GamePanel.currentWeapon = new Weapon(0, "Starting Weapon", 10, 1);
    }
    GamePanel.p.attack = GamePanel.currentWeapon.attack;
  }
  
  // Gets Player's Current Shield
  static void getCurrentShield() {
    int maxShield = 0;
    boolean hasShield = false;
    
    // Gets Shield with the most shield
    for (int i = 0; i < GamePanel.inventory.size(); i++) {
      Item it = GamePanel.inventory.get(i);
      int shield = 0;
      if (it instanceof Shield) {
        shield = ((Shield)it).shield;
        hasShield = true;
      }
      if (shield > maxShield) {
        GamePanel.currentShield = (Shield)it;
        maxShield = shield;
      }
    }
    // Sets the current shield
    if (!hasShield) {
      GamePanel.currentShield = null;
    }
    if (GamePanel.currentShield == null) {
      GamePanel.currentShield = new Shield(0, "Starting Shield", 0, 1);
    }
    GamePanel.p.shield = GamePanel.currentShield.shield;
  }
}