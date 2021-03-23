import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {
    public static void main(String[] args) {
        // Provided images are apple.jpg, flower.jpg, and kitten.jpg
        System.out.println(new File("apple.jpg").getAbsolutePath());
        int[][] imageData = imgToTwoD("./src/images/apple.jpg");
        // Or load an image using a link
        //int[][] imageData = imgToTwoD("https://exampleimage.img");

        //viewImageData(imageData); //outputs pixel data to console
        int[][] trimmed = trimBorders(imageData, 60);
        twoDToImage(trimmed, "./src/images/trimmed_apple.jpg");
        // int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));

        // Painting with pixels
        int[][] negative = negativeColor(imageData);
        twoDToImage(negative, "./src/images/negative_apple.jpg");

        int[][] stretch = stretchHorizontally(imageData);
        twoDToImage(stretch, "./src/images/stretch_apple.jpg");

        int[][] shrink = shrinkVertically(imageData);
        twoDToImage(shrink, "./src/images/shrink_apple.jpg");

        int[][] invert = invertImage(imageData);
        twoDToImage(invert, "./src/images/invert_apple.jpg");

        int[][] filtered = colorFilter(imageData,265,-0,50);
        twoDToImage(filtered, "./src/images/filtered_apple.jpg");

        int[][] blankImg = new int[500][500];
        int[][] randImg = paintRandomImage(blankImg);
        twoDToImage(randImg, "./src/images/rand_image.jpg");

        int[] rgba = {255,255,0,255};

        int[][] recImg = paintRectangle(randImg,200,200,100,100,getColorIntValFromRGBA(rgba));
        twoDToImage(recImg, "./src/images/rectangle_image.jpg");

        int[][] randRectImg = generateRectangles(randImg, 1000);
        twoDToImage(randRectImg, "./src/images/rand_rectangle_image.jpg");

    }

    // Image Processing Methods
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        // this method trims the borders of the image
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            for (int i = 0; i < trimmedImg.length; i++) {
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }

    public static int[][] negativeColor(int[][] imageTwoD) {
        // this method turns the image into a negative
        int[][] negativeImg = new int[imageTwoD.length][imageTwoD[0].length];
        for(int i = 0; i < imageTwoD.length; i++){
            for(int j = 0; j < imageTwoD[i].length; j++){
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                negativeImg[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return negativeImg;
    }

    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        // this method stretches an image on x axis
        int[][] stretchImg = new int[imageTwoD.length][imageTwoD[0].length*2];
        int colIndex = 0;
        for(int i = 0; i < imageTwoD.length; i++){
            for(int j = 0; j < imageTwoD[i].length; j++){
                colIndex = j*2;
                stretchImg[i][colIndex] = imageTwoD[i][j];
                stretchImg[i][colIndex+1] = imageTwoD[i][j];
            }
        }
        return stretchImg;
    }

    public static int[][] shrinkVertically(int[][] imageTwoD) {
        // this method shrinks an image on the y axis
        int[][] shrinkImg = new int[imageTwoD.length/2][imageTwoD[0].length];
        for(int i = 0; i < imageTwoD[0].length; i++){
            for(int j = 0; j < imageTwoD.length-1; j+=2){
                shrinkImg[j/2][i] = imageTwoD[j][i];
            }
        }
        return shrinkImg;
    }

    public static int[][] invertImage(int[][] imageTwoD) {
        // this method inverts an image
        int[][] invertImg = new int[imageTwoD.length][imageTwoD[0].length];
        for(int i = 0; i < imageTwoD.length; i++){
            for(int j = 0; j < imageTwoD[i].length; j++){
                invertImg[i][j] = imageTwoD[(imageTwoD.length-1)-i][(imageTwoD[i].length-1)-j];
            }
        }
        return invertImg;
    }

    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        // this method filters an image
        int[][] filterImg = new int[imageTwoD.length][imageTwoD[0].length];
        for(int i = 0; i < imageTwoD.length; i++){
            for(int j = 0; j < imageTwoD[i].length; j++){
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                int newRed = rgba[0] + redChangeValue;
                int newGreen = rgba[1] + greenChangeValue;
                int newBlue = rgba[2] + blueChangeValue;
                if(newRed > 255){
                    newRed = 255;
                }else if(newRed < 0){
                    newRed = 0;
                }

                if(newGreen > 255){
                    newGreen = 255;
                }else if(newGreen < 0){
                    newGreen = 0;
                }

                if(newBlue > 255){
                    newBlue = 255;
                }else if(newBlue < 0){
                    newBlue = 0;
                }

                rgba[0] = newRed;
                rgba[1] = newGreen;
                rgba[2] = newBlue;

                filterImg[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return filterImg;
    }

    // Painting Methods
    public static int[][] paintRandomImage(int[][] canvas) {
        // this method uses an image passed in by replacing every pixel with a randomly colored pixel
        Random rand = new Random();

        for(int i = 0; i < canvas.length; i++){
            for(int j = 0; j < canvas[i].length; j++){
                int randRed = rand.nextInt(256);
                int randGreen = rand.nextInt(256);
                int randBlue = rand.nextInt(256);

                int[] rgbaVals = {randRed, randGreen, randBlue, 255};
                canvas[i][j] = getColorIntValFromRGBA(rgbaVals);
            }
        }
        return canvas;
    }

    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        // this method is to draw a rectangle on an image
        for(int i=0; i<canvas.length; i++){
            for(int j=0; j<canvas[i].length; j++){
                if(i >= rowPosition && i <= rowPosition + width){
                    if(j >= colPosition && j <= colPosition + height){
                        canvas[i][j] = color;
                    }
                }
            }
        }
        return canvas;
    }

    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        // this method is used to generate randomly placed rectangles
        Random rand = new Random();

        for(int i = 0; i < numRectangles; i++){
            int randWidth = rand.nextInt(canvas[0].length);
            int randHeight = rand.nextInt(canvas.length);

            int randRow = rand.nextInt(canvas.length-randHeight);
            int randCol = rand.nextInt(canvas[0].length-randWidth);

            int randRed = rand.nextInt(256);
            int randGreen = rand.nextInt(256);
            int randBlue = rand.nextInt(256);

            int[] rgbaVals = {randRed, randGreen, randBlue, 255};
            int randColour = getColorIntValFromRGBA(rgbaVals);

            canvas = paintRectangle(canvas, randWidth, randHeight, randRow, randCol, randColour);
        }

        return canvas;
    }

    // Utility Methods
    //used to take in the image and convert to 2D array
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = null;
            if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }

    //used to save the modded image with new name
    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }

    //used to get the rgba value from a pixel
    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

    //used to get the colour value
    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }

    //used to view the image data
    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner.");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}

    // static boolean isPalindrome(String str,
    //                             int x, int y)
    // {
    //     while (x < y)
    //     {
    //         if (str.charAt(x) != str.charAt(y))
    //             return false;
    //         x++;
    //         y--;
    //     }
    //     return true;
    // }