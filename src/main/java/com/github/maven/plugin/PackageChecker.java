package com.github.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * http://maven.apache.org/guides/plugin/guide-java-plugin-development.html
 * 
 * @goal check
 * 
 * @author bjxieb
 * @date 2014/9/19
 */
public class PackageChecker extends AbstractMojo {
  @Parameter
  private Boolean test;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Hello, world.");
    System.out.println(this.getPluginContext());

  }
}
