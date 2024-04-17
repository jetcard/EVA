/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.webcobranzas.test.pdftoimg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jyoverar
 */
public class ListFilesUtil {

    /**
     * List all the files and folders from a directory
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            System.out.println(file.getName());
        }
    }

    /**
     * List all the files under a directory
     *
     * @param directoryName to be listed
     */
    public void listFiles(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * List all the folder under a directory
     *
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * List all files from a directory and its subdirectories
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName) {
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                String path = file.getAbsolutePath();
                String ext = com.google.common.io.Files.getFileExtension(file.getAbsolutePath());//getFileExtension(file.getAbsolutePath());
                if (!ext.equals("db")) {
                    try {
                        Path pt = Paths.get(path);
                        BasicFileAttributes attr  = java.nio.file.Files.readAttributes(pt, BasicFileAttributes.class);

                        System.out.println(file.getAbsolutePath() + " -  EXT  " + ext);
                        System.out.println("           creationTime     = " + attr.creationTime());
                        System.out.println("           lastAccessTime   = " + attr.lastAccessTime());
                        System.out.println("           lastModifiedTime = " + attr.lastModifiedTime());

                        System.out.println("           isDirectory      = " + attr.isDirectory());
                        System.out.println("           isOther          = " + attr.isOther());
                        System.out.println("           isRegularFile    = " + attr.isRegularFile());
                        System.out.println("           isSymbolicLink   = " + attr.isSymbolicLink());
                        System.out.println("           size             = " + attr.size());
                    } catch (IOException ex) {
                        Logger.getLogger(ListFilesUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (file.isDirectory()) {
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }
}
