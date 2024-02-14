import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileBackup {
    public static void backupFiles(String directory){
        File sourceDir = new File(directory);
        if (!sourceDir.exists() || !sourceDir.isDirectory()){
            System.out.println("Директория не существует.");
            return;
        }
        File backupDir = new File("./backup");
        if (!backupDir.exists()){
            backupDir.mkdir();
            System.out.println("Создана папка для резервной копии: " + backupDir.getAbsolutePath());
        }
        File[] files = sourceDir.listFiles();
        if (files != null){
            for (File file : files) {
                if (file.isFile()){
                    try{
                        Path sourceFilePath = file.toPath();
                        Path destFilePath = new File(backupDir, file.getName()).toPath();
                        Files.copy(sourceFilePath, destFilePath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Скопирован файл: " + file.getName());
                    } catch (IOException e){
                        System.out.println("Ошибка при копировании файлв" + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Резервное копирование завершено.");
    }
    public static void main(String[] args) {
        backupFiles("./my_directory");
    }
}
