package com.example.demo.pr2.Task4;

import com.example.demo.pr2.Task3.Main;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Observer {
    private static Map<Path, String> fileContents = new HashMap<>();



    public static void main(String[] args) {
        fileContents.put(Paths.get("src/main/java/com/example/demo/pr2/Task4/test.txt"),"");
        try {
            Path directory = Paths.get("src/main/java/com/example/demo/pr2/Task4");

            WatchService watchService = FileSystems.getDefault().newWatchService();
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

            while (true) {
                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path eventPath = (Path) event.context();

                    if (!eventPath.toString().endsWith("~")) {
                        if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                            System.out.println("Создан новый файл: " + eventPath);
                        } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                            String currentContent = getFileContent(directory.resolve(eventPath));
                            String previousContent = fileContents.get(directory.resolve(eventPath));
                            if (currentContent != null && !currentContent.equals(previousContent)) {
                                    System.out.println("Изменен файл: " + eventPath);
                                    analyzeFileChanges(previousContent, currentContent);
                                    fileContents.put(directory.resolve(eventPath), currentContent);
                            }
                        }
                    }
                }

                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getFileContent(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            return String.join("\n", lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void analyzeFileChanges(String previousContent, String currentContent) {
        // Разделяем строки предыдущего и текущего содержимого
        String[] previousLines = previousContent.split("\n");
        String[] currentLines = currentContent.split("\n");

        // Создаем множества для строк предыдущего и текущего содержимого
        Set<String> previousSet = new HashSet<>();
        Set<String> currentSet = new HashSet<>();

        for (String line : previousLines) {
            previousSet.add(line);
        }

        for (String line : currentLines) {
            currentSet.add(line);
        }

        // Находим добавленные строки
        for (String line : currentSet) {
            if (!previousSet.contains(line)) {
                System.out.println("Добавлена строка: " + line);
            }
        }

        // Находим удаленные строки
        for (String line : previousSet) {
            if (!currentSet.contains(line)) {
                System.out.println("Удалена строка: " + line);
            }
        }
    }
}
