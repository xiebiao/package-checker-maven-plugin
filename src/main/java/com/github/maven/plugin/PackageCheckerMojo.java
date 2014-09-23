package com.github.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.repository.RepositorySystem;

/**
 * http://maven.apache.org/guides/plugin/guide-java-plugin-development.html
 * 
 * @goal check
 * 
 * @author bjxieb
 * @date 2014/9/19
 */
public class PackageCheckerMojo extends AbstractMojo {
  @Parameter
  private Boolean test;
  private RepositorySystem repoSystem;


  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Hello, world.");
    System.out.println(this.getPluginContext());


  }
}
