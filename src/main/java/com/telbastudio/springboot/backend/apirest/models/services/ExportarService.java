package com.telbastudio.springboot.backend.apirest.models.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ExportarService {

    public ExportResult exportarBaseDatos() {
        try {
            // Crear un archivo temporal para almacenar la salida de mysqldump
            File outputFile = File.createTempFile("exported_database", ".sql");
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "C:/xampp/mysql/bin/mysqldump",
                    "-u", "root", // Reemplaza "tuUsuario" con el nombre de usuario de tu base de datos
                    "--password=", // Reemplaza "tuContraseña" con la contraseña de tu base de datos
                    "--host=localhost", // Reemplaza "localhost" con la dirección de tu servidor de base de datos
                    "--port=3306", // Reemplaza "3306" con el puerto de tu servidor de base de datos
                    "db_sistema_gym" // Reemplaza "nombreDeTuBaseDeDatos" con el nombre de tu base de datos
            );

            // Redirigir la salida de mysqldump al archivo temporal
            processBuilder.redirectOutput(outputFile);

            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();

            // Verificar si el proceso se ejecutó correctamente (código de salida 0)
            if (exitCode == 0) {
                // Leer el contenido del archivo en un byte[]
                byte[] exportedData = readFileToByteArray(outputFile);

                // Eliminar el archivo temporal después de leer su contenido
                outputFile.delete();

                return new ExportResult(exportedData, "exported_database.sql");
            } else {
                // Manejar el error si es necesario
                System.err.println("Error al exportar la base de datos. Código de salida: " + exitCode);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            // Manejar excepciones
            e.printStackTrace();
            return null;
        }
    }

    private byte[] readFileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buf)) != -1) {
                bos.write(buf, 0, bytesRead);
            }

            return bos.toByteArray();
        }
    }
}
