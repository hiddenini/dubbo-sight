package com.xz.framework.register;

import com.xz.framework.URL;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<String, List<URL>>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = REGISTER.get(interfaceName);
        if (CollectionUtils.isEmpty(urls)) {
            urls = new ArrayList<URL>();
        }
        urls.add(url);
        REGISTER.put(interfaceName, urls);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();
        List<URL> urls = REGISTER.get(interfaceName);
        return urls;
    }

    /**
     * 使用本机两个main方法的时候是在2个不同的jvm 所以map不能共享 用文件解决下
     */
    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
