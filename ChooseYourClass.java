// [ChooseYourClass.java]

// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Class
class ChooseYourClass extends JFrame  
{
  
  // Variables
  int classC;
  JLabel chooseClass = new JLabel("Choose the difficulty:");
  JButton class1 = new JButton();
  JButton class2 = new JButton();
  JButton class3 = new JButton();
  JButton back = new JButton();
  JPanel contentPane = new ImagePanel("Image/menu.png");
  
  ChooseYourClass () {
    
    // JFrame
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle("Choose");
    setSize(600,400);
    
    // JLabel
    chooseClass.setFont(Menu.font);
    chooseClass.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Easy JButton
    class1.setPreferredSize(new Dimension(200,50));
    class1.setIcon(Menu.resize(new ImageIcon("Image/easy.png"),200,50));
    class1.setOpaque(false);
    class1.setContentAreaFilled(false);
    class1.setBorderPainted(false);
    class1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        classC = 1;
        Game.startGame(10, 10, 10, 10, 10, classC);
      }
    });
    class1.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Medium JButton
    class2.setPreferredSize(new Dimension(200,50));
    class2.setIcon(Menu.resize(new ImageIcon("Image/medium.png"),200,50));
    class2.setOpaque(false);
    class2.setContentAreaFilled(false);
    class2.setBorderPainted(false);
    class2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        classC = 2;
        Game.startGame(10, 10, 10, 10, 10, classC);
      }
    });
    class2.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Hard JButton
    class3.setPreferredSize(new Dimension(200,50));
    class3.setIcon(Menu.resize(new ImageIcon("Image/hard.png"),200,50));
    class3.setOpaque(false);
    class3.setContentAreaFilled(false);
    class3.setBorderPainted(false);
    class3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
        classC = 3;
        Game.startGame(10, 10, 10, 10, 10, classC);
      }
    });
    class3.setAlignmentX(Component.CENTER_ALIGNMENT);
    
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
    
    // Add JButtons and JLabel
    contentPane.add(chooseClass);
    contentPane.add(class1);
    contentPane.add(class2);
    contentPane.add(class3);
    contentPane.add(back);
    
    // Add JPanel
    add(contentPane);
    
    // Resize and set visible
    setResizable(false);
    pack();
    setVisible(true);
  }
  
  // Runs JFrame
  static void choose() {
    ChooseYourClass c = new ChooseYourClass();
  }
}