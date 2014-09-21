package com.github.maven.plugin;

import static org.junit.Assert.assertNotNull;

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
  public void testSomething() throws Exception {
    // File pom = new File("src/test/resources/pom.xml");
    // assertTrue(pom.exists());
    // rule.lookup(PackageChecker.class);
    // Mojo mojo = rule.lookupMojo("check", pom);
    // assertNotNull(mojo);
    PackageChecker packageChecker = rule.lookup(PackageChecker.class);
    assertNotNull(packageChecker);
    packageChecker.execute();


  }
}
