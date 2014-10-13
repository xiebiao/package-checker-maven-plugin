package com.apache.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @author xiebiao
 * @date 10/14/14
 */
public class DemoMojo extends AbstractMojo {

  private String keyOne;
  private String keyTwo;

  @Parameter
  private MavenProject mavenProject;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("This is demo mojo......");
    getLog().info(this.mavenProject.getDependencies().size() + "");
  }

  public String getKeyOne() {
    return keyOne;
  }

  public void setKeyOne(String keyOne) {
    this.keyOne = keyOne;
  }

  public String getKeyTwo() {
    return keyTwo;
  }

  public void setKeyTwo(String keyTwo) {
    this.keyTwo = keyTwo;
  }
}
