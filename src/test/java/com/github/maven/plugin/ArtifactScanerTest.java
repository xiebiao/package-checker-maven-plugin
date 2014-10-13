package com.github.maven.plugin;

import com.apache.maven.plugin.ArtifactScaner;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author xiebiao
 * @date 10/8/14
 */
public class ArtifactScanerTest extends TestCase {
    @Test
    public void test_read_artifact(){
        String dic = "/Users/xiebiao/.m2/repository/commons-logging/commons-logging/1.0";
        ArtifactScaner artifactScaner  = new ArtifactScaner(dic);
        artifactScaner.getClassInArtifactMap();
    }
}
