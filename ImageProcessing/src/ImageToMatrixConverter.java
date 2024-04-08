import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToMatrixConverter {
    public static void main(String[] args) {
        String imagePath = "D:/University/TMATH 208 Matrix/Tree 25x25.png";
        String[][] matrix = imageToMatrix(imagePath);

        if (matrix != null) {
            for (String[] row : matrix) {
                for (String value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        }
    }
    public static String[][] imageToMatrix(String imagePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = resizeImage(image, 25, 25); //Change for image size.

            String[][] pixelMatrix = new String[25][25];
            int width = resizedImage.getWidth();
            int height = resizedImage.getHeight();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = new Color(resizedImage.getRGB(x, y));
                    String hexValue = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
                    pixelMatrix[y][x] = hexValue;
                }
            }

            return pixelMatrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        return resizedImage;
    }
}
