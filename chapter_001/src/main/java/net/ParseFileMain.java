package net;

import io.ParseFile;

import java.io.File;

/**
 *
 */

public class ParseFileMain {
    public static   void main(String[] args) {
        ParseFile parseFile = new ParseFile();
        System.out.println("file to read");
        File input = new File("/home/sekator/projects/Project/chapter_001/src/main/resources/temp.txt");
        parseFile.setFile(input);
        String withUnicode = parseFile.getContent();
        System.out.println(withUnicode);
        File output = new File("output.txt");
        parseFile.setFile(output);
        parseFile.saveContent(withUnicode);

    }
}
