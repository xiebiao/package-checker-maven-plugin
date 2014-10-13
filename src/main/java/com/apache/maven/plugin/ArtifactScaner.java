package com.apache.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * @author xiebiao
 * @date 9/28/14
 */
public class ArtifactScaner {

  private String artifactDirectory;
  private final static String JAR_SUFFIX = ".jar";
  private final static String CLASS_SUFFIX = ".class";

  private Map<String, String> classInArtifactMap = new HashMap<String, String>();
  private Map<String, List<String>> duplicateClassInArtifactMap =
      new HashMap<String, List<String>>();

  public ArtifactScaner(String artifactDirectory) {
    this.artifactDirectory = artifactDirectory;
  }

  public Map<String, String> getClassInArtifactMap() {
    String[] artifacts = this.artifacts();
    for (String artifact : artifacts) {
      Map<String, String> classMap = this.readClassInJar(artifact);
      Iterator<String> it = classMap.keySet().iterator();
      while (it.hasNext()) {
        String clazz = it.next();
        if (this.classInArtifactMap.get(clazz) == null) {
          this.classInArtifactMap.put(clazz, classMap.get(clazz));
        } else {
          // this.duplicateClassInArtifactMap.put()
        }
        System.out.println(clazz);
      }
    }
    return null;
  }

  private String[] artifacts() {
    File file = new File(this.artifactDirectory);
    if (file.isDirectory()) {
      return file.list();
    } else {
      throw new RuntimeException("Can't find artifact in:" + this.artifactDirectory);
    }
  }

  private Map<String, String> readClassInJar(String artifactName) {
    Map<String, String> classMap = new HashMap<String, String>();

    try {
      if (artifactName.endsWith(JAR_SUFFIX)) {
        JarFile jarFile = new JarFile(this.artifactDirectory + File.separator + artifactName);
        Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
        while (jarEntryEnumeration.hasMoreElements()) {
          JarEntry jarEntry = jarEntryEnumeration.nextElement();
          if (jarEntry.getName().endsWith(CLASS_SUFFIX)) {
            classMap.put(jarEntry.getName(), artifactName);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return classMap;
  }
}
