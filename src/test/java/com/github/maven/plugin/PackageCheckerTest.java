package com.github.maven.plugin;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author xiebiao
 * @date 9/21/14
 */
public class PackageCheckerTest {
  @Rule
  public MojoRule rule = new MojoRule() {
    @Override
    protected void before() throws Throwable {}

    @Override
    protected void after() {}
  };

  @Test
  public void testExecute() throws Exception {
    String file =
        PackageCheckerTest.class.getClassLoader().getResource("META-INF/maven/pom.xml").getFile();
    System.out.println(file);
    rule.executeMojo(new File(file), "check");
//    assertNotNull( pom );
//    assertTrue( pom.exists() );
    //
    // MyMojo myMojo = (MyMojo) rule.lookupMojo( "touch", pom );
    // assertNotNull( myMojo );
    // myMojo.execute();


  }
}
