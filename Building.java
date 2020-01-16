// [Building.java]

// Imports
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.*;
import java.util.*;

// Class
class Building extends Map
{
  
  // Constructor
  Building(int currentSprite) {
    super(currentSprite);
  }
  
  // Variables
  static BufferedImage[] sprites;
  
  // Method to load the building sprites
  static void loadSprites() throws IOException{ 
    sprites = new BufferedImage[4];
    sprites[0] = ImageIO.read(new File("Image/buil1.png"));
    sprites[1] = ImageIO.read(new File("Image/buil2.png"));
    sprites[2] = ImageIO.read(new File("Image/buil3.png"));
    sprites[3] = ImageIO.read(new File("Image/buil4.png"));
  }
  
  // Spawning the buildings intp the map
  static void spawn() {
    Maps.maps[0][27][13] = new Building(3);
    Maps.maps[0][20][20] = new Building(1);
    Maps.maps[0][19][33] = new Building(1);
    Maps.maps[0][32][24] = new Building(2);
    Maps.maps[0][23][32] = new Building(2);
  }
}