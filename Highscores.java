// [Highscores.java]

// Imports
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

// Class
class Highscores extends JFrame
{
  
  // JFrame variables
  JLabel highscores = new JLabel("Highscores");
  JLabel highscore1 = new JLabel();
  JLabel highscore2 = new JLabel();
  JLabel highscore3 = new JLabel();
  JLabel highscore4 = new JLabel();
  JLabel highscore5 = new JLabel();
  JButton back = new JButton("Back");
  JPanel contentPane = new ImagePanel("Image/menu.png");
  
  // Construction
  Highscores() {
    
    // Set up JFrame
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Highscores");
    setSize(600,600);
    
    // Highscores title JLabel
    highscores.setFont(Menu.font);
    highscores.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Open highscore file
    File highscoreFile = new File("Save/highscores.txt");
    int hs1 = 0;
    int hs2 = 0;
    int hs3 = 0;
    int hs4 = 0;
    int hs5 = 0;
    
    // Gets highscores
    try {
      Scanner sc = new Scanner(highscoreFile);
      hs1 = sc.nextInt();
      hs2 = sc.nextInt();
      hs3 = sc.nextInt();
      hs4 = sc.nextInt();
      hs5 = sc.nextInt();
    } catch (IOException io) {
      System.out.println("Highscore file not found");
    }
    
    // First Highscore JLabel
    highscore1.setText("1. " + hs1);
    highscore1.setFont(Menu.font);
    highscore1.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Second Highscore JLabel
    highscore2.setText("2. " + hs2);
    highscore2.setFont(Menu.font);
    highscore2.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Third Highscore JLabel
    highscore3.setText("3. " + hs3);
    highscore3.setFont(Menu.font);
    highscore3.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Fourth Highscore JLabel
    highscore4.setText("4. " + hs4);
    highscore4.setFont(Menu.font);
    highscore4.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Fifth Highscore JLabel
    highscore5.setText("5. " + hs5);
    highscore5.setFont(Menu.font);
    highscore5.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Back JButton
    back.setBackground(Color.RED);
    back.setOpaque(true);
    back.setFont(Menu.font);
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
    back.setAlignmentY(Component.TOP_ALIGNMENT);
    
    // BoxLayout
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
    // Add JLabels and back JButton
    contentPane.add(highscores);
    contentPane.add(highscore1);
    contentPane.add(highscore2);
    contentPane.add(highscore3);
    contentPane.add(highscore4);
    contentPane.add(highscore5);
    contentPane.add(back);
    
    // Add JPanel
    add(contentPane);
    
    // Resize and set visible
    pack();
    setResizable(false);
    setVisible(true);
  }
  
  // Display highscores
  static void displayHighscores() {
    //Opens JFrame
    Highscores h = new Highscores();
  }
}