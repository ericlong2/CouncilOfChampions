import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
class ImagePanel extends JPanel {
  BufferedImage image;
  ImagePanel(String filename) {
    try{this.image = ImageIO.read(new File(filename));}catch(IOException io){}
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, null);
  }
    
}