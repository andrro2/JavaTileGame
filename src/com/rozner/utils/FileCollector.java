package com.rozner.utils;

import com.rozner.worlds.TestWorld;
import com.rozner.worlds.WorldManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCollector {

    private XmlEncodrerDecoder decoder = new XmlEncodrerDecoder();


    public void loadWorlds() {
        try (Stream<Path> walk = Files.walk(Paths.get("res/worlds"))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".xml")).collect(Collectors.toList());

            for(String path : result){
                TestWorld world = decoder.deserializeFromXML(path);
                WorldManager.getInstance().setWorlds(new ArrayList<>());
                WorldManager.getInstance().addToWorldsList(world);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
