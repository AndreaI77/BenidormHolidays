package com.andrea.springapirest.servicesImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    /**
     * Guarda un archivo en la ruta indicada con nombre único.
     * 
     * @param basePath Carpeta base donde guardar el archivo
     * @param file     Archivo a guardar
     * @return Nombre final del archivo guardado
     * @throws IOException Si hay error al guardar
     */
  
    public String storeFile(String basePath, MultipartFile file) throws IOException {
    	if (file == null || file.isEmpty()) {
            throw new IOException("Archivo vacío");
        }

        Path folder = Paths.get(basePath);
        Files.createDirectories(folder);

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        String fileName = System.currentTimeMillis() + "." + ext;

        Path filePath = folder.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }
    
    public void deleteApartamentoFolder(String basePath, Integer id) {
        Path folderPath = Paths.get(basePath+id);

        try {
            FileSystemUtils.deleteRecursively(folderPath);
        } catch (Exception e) {
            System.err.println("Error borrando carpeta del apartamento: " + id);
        }
    }
    
    public void deleteFile(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("Error eliminando archivo", e);
        }
    }
}
