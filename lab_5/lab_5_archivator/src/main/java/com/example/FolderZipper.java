package com.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderZipper {

   public static void zipFolder(Path sourceFolderPath, Path zipPath) {

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipPath))) {

            Files.walk(sourceFolderPath)

                .filter(path -> !Files.isDirectory(path))

                .forEach(path -> {

                    ZipEntry zipEntry = new ZipEntry(sourceFolderPath.relativize(path).toString().replace("\\", "/"));

                    try {

                        zos.putNextEntry(zipEntry);
                        Files.copy(path, zos);
                        zos.closeEntry();

                    } catch (IOException e) {

                        System.err.println("Ошибка при добавлении файла: " + path + " → " + e.getMessage());

                    }
                });


    } catch (IOException e) {

        System.err.println("Ошибка архивации: " + e.getMessage());

    }
}

    

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        String sourceFolderString = sc.nextLine();
        String zipFilePathString = sc.nextLine();

        Path sourceFolder = Paths.get(sourceFolderString);
        Path zipFile = Paths.get(zipFilePathString + ".zip");
        
        sc.close();
        
        zipFolder(sourceFolder, zipFile);
        
        System.out.println("Архивация завершена: " + zipFile.toAbsolutePath());
    
        
    }
}
