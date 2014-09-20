package com.github.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * http://maven.apache.org/guides/plugin/guide-java-plugin-development.html
 * @author bjxieb
 * @date 2014/9/19
 */
@Mojo( name = "sayhi")
public class PackageChecker extends AbstractMojo {
  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Hello, world.");
  }
}
