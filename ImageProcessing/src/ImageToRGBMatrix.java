import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageToRGBMatrix {
    public static void main(String[] args) {
        String imagePath = "D:/University/My Personal Programs/ImageProcessing/New-Small-Tree.png";
        int[][][] matrix = imageToRGBMatrix(imagePath);

        if (matrix != null) {
            for (int[][] row : matrix) {
                for (int[] pixel : row) {
                    System.out.print("(" + pixel[0] + ", " + pixel[1] + ", " + pixel[2] + ") ");
                }
                System.out.println();
            }
        }
    }

    public static int[][][] imageToRGBMatrix(String imagePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));

            int[][][] rgbMatrix = new int[image.getHeight()][image.getWidth()][3];

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    Color color = new Color(rgb);
                    rgbMatrix[y][x][0] = color.getRed();
                    rgbMatrix[y][x][1] = color.getGreen();
                    rgbMatrix[y][x][2] = color.getBlue();
                }
            }

            return rgbMatrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
