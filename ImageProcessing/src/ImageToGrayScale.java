import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageToGrayScale {
    public static void main(String[] args) {
        //displayGrayScale();
        displaySharpen();
        //displayGaussianBlurImage();
    }

    public static int[][] imageToMatrix(String imagePath) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));

            int[][] pixelMatrix = new int[image.getHeight()][image.getWidth()];

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    int grayscaleValue = convertRGBToGrayscale(rgb);
                    int scaledValue = (int) Math.round(grayscaleValue * 100.0 / 255.0); // Scalar for 0-100 gray Scale.
                    pixelMatrix[y][x] = scaledValue;
                }
            }

            return pixelMatrix;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * convertRGBToGrayscale takes a rgb value from one of the matrix cells
     * and coverts it into grayscale from a value 0-100.
     * Where 100 is white and 0 is black.
     * @param rgb Passes RGB color value from 0-255.
     * @return returns the grayscale value from 0-100.
     */
    public static int convertRGBToGrayscale(int rgb) {
        Color color = new Color(rgb);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return (int) (0.2989 * red + 0.5870 * green + 0.1140 * blue);
    }

    public static int[][] sharpenImage(int[][] grayscaleMatrix) {
        int[][] sharpMatrix = new int[grayscaleMatrix.length][grayscaleMatrix[0].length];
        int[][] sharpKernel = { {0, -1, 0},
                                {-1, 5, -1},
                                {0, -1, 0} };

        //access the inner part of our picture, not the border.
        for (int i = 0; i < grayscaleMatrix.length; i++) { //row
            for(int j = 0; j < grayscaleMatrix[0].length; j++) { //column
                int sum = 0;

                for(int kernalI = 0; kernalI <= 1; kernalI++) {
                    for(int kernalJ = 0; kernalJ <= 1; kernalJ++) {
                        int borderX = Math.min(Math.max(i + kernalI, 0), grayscaleMatrix.length - 1);
                        int borderY = Math.min(Math.max(j + kernalJ, 0), grayscaleMatrix[0].length - 1);
                        sum += grayscaleMatrix[borderX][borderY] * sharpKernel[kernalI + 1][kernalJ + 1];
                    }
                }
                sharpMatrix[i][j] = Math.min(Math.max(sum, 0), 100);
            }
        }
        return sharpMatrix;
    }

    public static int[][] gaussianBlurImage(int[][] grayscaleMatrix) {
        int[][] blurMatrix = new int[grayscaleMatrix.length][grayscaleMatrix[0].length];
        int[][] gaussianKernel = { {1, 2, 1},
                                    {2, 4, 1},
                                    {1, 2, 1} };

        //access the inner part of our picture, not the border.
        for (int i = 0; i < grayscaleMatrix.length; i++) { //row
            for(int j = 0; j < grayscaleMatrix[0].length; j++) { //column
                int sum = 0;

                for(int kernalI = -1; kernalI <= 1; kernalI++) {
                    for(int kernalJ = -1; kernalJ <= 1; kernalJ++) {
                        int borderX = Math.min(Math.max(i + kernalI, 0), grayscaleMatrix.length - 1);
                        int borderY = Math.min(Math.max(j + kernalJ, 0), grayscaleMatrix[0].length - 1);
                        sum += grayscaleMatrix[borderX][borderY] * gaussianKernel[kernalI + 1][kernalJ + 1];
                    }
                }
                sum /= 16;
                blurMatrix[i][j] = Math.min(Math.max(sum, 0), 100); //compress sum value into a range from 0-100.
            }
        }
        return blurMatrix;
    }


    public static void displayGrayScale() {
        String imagePath = "D:/University/My Personal Programs/ImageProcessing/New-Small-Tree.png";
        int[][] matrix = imageToMatrix(imagePath);

        if (matrix != null) {
            int maxValue = getMaxValue(matrix);
            int spacing = String.valueOf(maxValue).length() + 1;
            for (int[] row : matrix) {
                for (int value : row) {
                    System.out.printf("%-" + spacing + "d ", value);
                }
                System.out.println();
            }
        }
    }

//    public static void displaySharpen() {
//        String imagePath = "D:\\Downloads\\Matrix project\\sun 25 x 25.png"; //D:/University/My Personal Programs/ImageProcessing/New-Small-Tree.png
//        int[][] matrix = imageToMatrix(imagePath);
//
//        if (matrix != null) {
//            int[][] sharpMatrix = sharpenImage(matrix);
//            for (int[] row : sharpMatrix) {
//                for (int value : row) {
//                    System.out.print(value + " ");
//                }
//                System.out.println();
//            }
//        }
//    }

    public static void displaySharpen() {
        //Star
        //D:/University/My Personal Programs/ImageProcessing/New-Small-Tree.png
        String imagePath = "D:\\Downloads\\Matrix project\\sun 25 x 25.png"; //D:/University/My Personal Programs/ImageProcessing/New-Small-Tree.png
        int[][] matrix = imageToMatrix(imagePath);

        if (matrix != null) {
            int[][] sharpMatrix = sharpenImage(matrix);
            int maxValue = getMaxValue(sharpMatrix);
            int spacing = String.valueOf(maxValue).length() + 1;

            for (int[] row : sharpMatrix) {
                for (int value : row) {
                    System.out.printf("%-" + spacing + "d ", value);
                }
                System.out.println();
            }
        }
    }

    public static void displayGaussianBlurImage() {
        String imagePath = "D:\\Downloads\\Matrix project\\sun 25 x 25.png";
        int[][] matrix = imageToMatrix(imagePath);

        if (matrix != null) {
            int[][] gaussianMatrixBlur = gaussianBlurImage(matrix);
            int maxValue = getMaxValue(gaussianMatrixBlur);
            int spacing = String.valueOf(maxValue).length() + 1;

            for (int[] row : gaussianMatrixBlur) {
                for (int value : row) {
                    System.out.printf("%-" + spacing + "d ", value);
                }
                System.out.println();
            }
        }
    }

    public static int getMaxValue(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int pixel : row) {
                if (pixel > max) {
                    max = pixel;
                }
            }
        }
        return max;
    }

}