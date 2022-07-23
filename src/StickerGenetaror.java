import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerGenetaror {

  public void createSticker(InputStream inputStream, String archiveName) throws Exception {

    // Read img
    BufferedImage originalImg = ImageIO.read(inputStream);

    // Create a new img in memory with transparency and with new size
    int width = originalImg.getWidth();
    int height = originalImg.getHeight();
    int newHeight = height + 200;
    BufferedImage newImg = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // Copy the original img to the new image in memory
    Graphics2D graphics = (Graphics2D) newImg.getGraphics();
    graphics.drawImage(originalImg, 0, 0, null);

    // Font config
    var font = new Font("Comic Sans MS", Font.BOLD, 64);
    graphics.setFont(font);
    graphics.setColor(Color.YELLOW);

    // Write a sentence on the new img
    graphics.drawString("Top 1 ImDb", width / 5, newHeight - 100);
    // Write a new img to a file
    ImageIO.write(newImg, "png", new File("stickers/" + archiveName));

  }

}