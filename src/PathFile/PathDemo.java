package PathFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) {
        Path path = Paths.get("c:/Users/hp/IdeaProjects/FileDemo/out/production/FileDemo");
        System.out.println("path=" + path);
        System.out.println("file name=" + path.getFileName());
        System.out.println("parent=" + path.getParent());
        System.out.println("root=" + path.getRoot());
        System.out.println("name count=" + path.getNameCount());
        System.out.println("folder 1=" + path.getName(1));
        System.out.println("folder 2=" + path.getName(2));
        System.out.println("folder 3=" + path.getName(3));

        if (Files.isDirectory(path)) {
            System.out.println(path + " is directory");
        } else {
            System.out.println(path + " is file");
        }
        System.out.println("readable=" + Files.isReadable(path));
        System.out.println("executable=" + Files.isExecutable(path));
        System.out.println("reglar file =" + Files.isRegularFile(path));
        try {
            System.out.println("hidden=" + Files.isHidden(path));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
