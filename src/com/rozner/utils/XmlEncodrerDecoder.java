package com.rozner.utils;

import com.rozner.worlds.TestWorld;

import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlEncodrerDecoder {


    public XmlEncodrerDecoder() {
    }

    public void DecodeObjectToXml(String path, TestWorld world) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(world);
            encoder.close();

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TestWorld deserializeFromXML(String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);

            XMLDecoder decoder = new XMLDecoder(fis);
            TestWorld loadedWorld = (TestWorld) decoder.readObject();
            decoder.close();
            fis.close();
            return loadedWorld;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
