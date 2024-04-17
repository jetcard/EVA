/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author Jyoverar
 */
public class ConvertPDFPagesToImages {

    public static void main(String[] args) {

        final File folder = new File("C:\\Nueva carpeta (4)\\Nueva carpeta\\");
        ConvertPDFPagesToImages dd = new ConvertPDFPagesToImages();
        dd.listFilesForFolder(folder);

    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //listFilesForFolder(fileEntry);
                System.out.println("pop.webcobranzas.test.pdftoimg.ConvertPDFPagesToImages.listFilesForFolder()");
            } else {
                System.out.println(fileEntry.getName());
                try {
                    String sourceDir = "C:\\Nueva carpeta (4)\\Nueva carpeta\\" + fileEntry.getName(); // Pdf files are read from this folder
                    String destinationDir = "C:\\Nueva carpeta (4)\\Nueva carpeta\\"; // converted images from pdf document are saved here

                    File sourceFile = new File(sourceDir);
                    File destinationFile = new File(destinationDir);
                    if (!destinationFile.exists()) {
                        destinationFile.mkdir();
                        System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
                    }
                    if (sourceFile.exists()) {
                        System.out.println("Images copied to Folder: " + destinationFile.getName());
                        PDDocument document = PDDocument.load(sourceDir);
                        List<PDPage> list = document.getDocumentCatalog().getAllPages();
                        System.out.println("Total files to be converted -> " + list.size());

                        String fileName = sourceFile.getName().replace(".pdf", "");
                        int pageNumber = 1;
                        for (PDPage page : list) {
                            BufferedImage image = page.convertToImage();
                            File outputfile = new File(destinationDir + fileName + "_" + pageNumber + ".png");
                            System.out.println("Image Created -> " + outputfile.getName());
                            ImageIO.write(image, "png", outputfile);
                            pageNumber++;
                        }
                        document.close();
                        System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
                    } else {
                        System.err.println(sourceFile.getName() + " File not exists");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
