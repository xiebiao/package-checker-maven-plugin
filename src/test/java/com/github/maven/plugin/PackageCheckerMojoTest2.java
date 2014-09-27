package com.github.maven.plugin;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author xiebiao
 * @date 9/21/14
 */
public class PackageCheckerMojoTest2 {
  @Rule
  public MojoRule rule = new MojoRule() {
    @Override
    protected void before() throws Throwable {}

    @Override
    protected void after() {}
  };


  /**
   * @throws Exception if any
   */
  @Test
  public void testExecute() throws Exception {
    String basePath = this.getClass().getClassLoader().getResource("").getFile();
    System.out.println(basePath);
    File pom = new File(basePath);
    System.out.println(pom.getPath());
    assertNotNull(pom);
    assertTrue(pom.exists());

    PackageCheckerMojo myMojo = (PackageCheckerMojo) rule.lookupConfiguredMojo(pom, "check");
    assertNotNull(myMojo);
    myMojo.execute();
  }
}
