package ru.netology;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
        openZip("D://Games/savegames/zip.zip", "D://Games/savegames/");
        openProgress("D://Games/savegames/save2.dat");
    }

    public static void openZip(String wayZip, String wayOutZip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(wayZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла

// распаковка
                FileOutputStream fout = new FileOutputStream(wayOutZip + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage(
            ));
        }

    }

    public static void openProgress(String file2) {
        GameProgress gameProgress = null;
// откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream(file2);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
// десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage(
            ));
        }
        System.out.println(gameProgress);
    }
}
