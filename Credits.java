//[Credits.java]

// Imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

// Class
class Credits extends JFrame
{
  
  // Variables
  JLabel credits = new JLabel("Eric and Victor");
  JButton back = new JButton();
  JPanel contentPane = new ImagePanel("Image/menu.png");
  
  // Constructor
  Credits() {
    
    // Set up JFrame
    setSize(600, 600);
    setTitle("Credits");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    // Credits JLabel
    credits.setFont(Menu.font);
    credits.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Back Button
    back.setPreferredSize(new Dimension(200,50));
    back.setIcon(Menu.resize(new ImageIcon("Image/back.png"),200,50));
    back.setOpaque(false);
    back.setContentAreaFilled(false);
    back.setBorderPainted(false);
    back.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        try {
          Menu.startMenu(); 
        } catch (IOException io) {
          System.out.println("Menu file not found");
        }
      }
    });
    back.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // BoxLayout
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    // Add credits and back button
    contentPane.add(credits);
    contentPane.add(back);
    
    // Add JPanel
    add(contentPane);
    
    // Resize and set visible
    pack();
    setResizable(false);
    setVisible(true);
  }
  
  // Method to start the credits menu
  public static void creditsMenu() {
    Credits c = new Credits();
  }
}