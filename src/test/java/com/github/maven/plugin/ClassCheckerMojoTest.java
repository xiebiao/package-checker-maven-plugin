package com.github.maven.plugin;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;
import org.junit.Test;

import com.apache.maven.plugin.ClassCheckerMojo;

/**
 * @author xiebiao
 * @date 10/12/14
 */
public class ClassCheckerMojoTest {
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
    File pom = new File(basePath);
    System.out.println(pom.getPath());
    assertNotNull(pom);
    assertTrue(pom.exists());

    //ClassCheckerMojo myMojo = (ClassCheckerMojo) rule.lookupMojo("check", pom);

    ClassCheckerMojo myMojo = (ClassCheckerMojo) rule.lookupConfiguredMojo(pom, "check");
    assertNotNull(myMojo);
    myMojo.execute();
  }
}
