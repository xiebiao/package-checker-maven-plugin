package com.github.maven.plugin;

import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

/**
 * Mojo Description. @Mojo( name = "check" ) is the minimal required annotation.
 * 
 * 
 * @author bjxieb
 * @date 2014/9/19
 */
@Mojo(name = "check", defaultPhase = LifecyclePhase.PREPARE_PACKAGE)
@Execute(goal = "check")
public class PackageCheckerMojo extends AbstractMojo {

  @Parameter
  private Boolean justTest;

  @Parameter(defaultValue = "${session}", readonly = true)
  private MavenSession session;

  @Parameter(defaultValue = "${project}", readonly = true)
  @Component
  private MavenProject project;

  // private RepositorySystem repoSystem
  // @Parameter(defaultValue = "${project.build.directory}", readonly = true)
  // private File target;
  // @Parameter(defaultValue = "${settings}", readonly = true)
  // private Settings settings;

  /**
   * Local Repository.
   */
  @Parameter(defaultValue = "${localRepository}", readonly = true, required = true)
  protected ArtifactRepository localRepository;


  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {

    getLog().info("-----------------");
    List<Dependency> dependencyList = project.getDependencies();
    Set<Artifact> artifactSet = project.getDependencyArtifacts();

    // getLog().info("settings:" + settings.getLocalRepository());
    getLog().info("justTest:" + justTest);
    getLog().info("dependencyList:" + dependencyList.size());
    getLog().info("artifactSet:" + artifactSet.size());
    // getLog().info("justTest:" + localRepository.toString());
    getLog().info("-----------------");

  }
}
