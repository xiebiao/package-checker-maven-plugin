package com.github.maven.plugin;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

/**
 * @author xiebiao
 * @date 9/21/14
 */
public class PackageCheckerMojoTest extends AbstractMojoTestCase {
  protected void setUp() throws Exception {
    // required for mojo lookups to work
    super.setUp();
  }


  /**
   * @throws Exception
   */
  public void testMojoGoal() throws Exception {
    File testPom = new File(getBasedir(), "src/test/resources/pom.xml");
    assertTrue(testPom.exists());
    PackageCheckerMojo mojo = (PackageCheckerMojo) lookupMojo("check", testPom);
    assertNotNull(mojo);
  }
}
