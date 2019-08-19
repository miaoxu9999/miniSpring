package com.mooc.mx.core;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @ClassName ClassScanner
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/18 11:05
 * @Version 1.0
 **/
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes= new ArrayList<>();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()){
            URL resource = resources.nextElement();
            if (resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classes.addAll(getClassesFromJar(jarFilePath, path));

            }
        }
        return classes;
    }

    public static List<Class<?> >getClassesFromJar(String jarPath, String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
        while (jarEntryEnumeration.hasMoreElements()){
            JarEntry jarEntry = jarEntryEnumeration.nextElement();
            String entryName = jarEntry.getName();
            if (entryName.startsWith(path) && entryName.endsWith(".class")){
                String fullName = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                classes.add(Class.forName(fullName));
            }
        }

        return classes;
    }

}
