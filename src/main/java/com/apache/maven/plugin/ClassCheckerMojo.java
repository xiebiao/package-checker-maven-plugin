package com.apache.maven.plugin;

import java.io.File;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.dependency.fromDependencies.AbstractFromDependenciesMojo;
import org.apache.maven.plugin.dependency.utils.DependencyStatusSets;
import org.apache.maven.plugin.dependency.utils.DependencyUtil;
import org.apache.maven.plugin.dependency.utils.filters.DestFileFilter;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.shared.artifact.filter.collection.ArtifactsFilter;


/**
 * @author xiebiao
 * @date 10/12/14
 */
@Mojo(name = "check", defaultPhase = LifecyclePhase.COMPILE)
@Execute(goal = "check")
public class ClassCheckerMojo extends AbstractFromDependenciesMojo {
  @Parameter
  private Boolean justTest;
  /**
   * Either append the artifact's baseVersion or uniqueVersion to the filename. Will only be used if
   * {@link #isStripVersion()} is {@code false}.
   * 
   * @since 2.6
   */
  @Parameter(property = "mdep.useBaseVersion", defaultValue = "true")
  protected boolean useBaseVersion = true;
  @Component
  private PluginDescriptor pluginDescriptor;
  protected void doExecute() throws MojoExecutionException {

    getLog().info("dependencies:" + this.getProject().getDependencies().size());
    getLog().info("artifacts:" + this.getProject().getArtifacts().size());

    DependencyStatusSets dependencyStatusSets = this.getDependencySets(false);

    Set<Artifact> resolvedDependencies = dependencyStatusSets.getResolvedDependencies();
    Set<Artifact> skippedDependencies = dependencyStatusSets.getSkippedDependencies();
    Set<Artifact> unResolvedDependencies = dependencyStatusSets.getUnResolvedDependencies();
    getLog().info(project.getArtifacts().size() + "");
    getLog().info("resolvedDependencies:" + resolvedDependencies.size());
    getLog().info("skippedDependencies:" + skippedDependencies.size());
    // getLog().info("unResolvedDependencies:" + unResolvedDependencies.size());
    for (Artifact artifact : resolvedDependencies) {

      copyArtifact(artifact, isStripVersion(), this.prependGroupId, this.useBaseVersion,
          this.stripClassifier);
    }
    getLog().info("-----------------");

  }

  /**
   * Copies the Artifact after building the destination file name if overridden. This method also
   * checks if the classifier is set and adds it to the destination file name if needed.
   *
   * @param artifact representing the object to be copied.
   * @param removeVersion specifies if the version should be removed from the file name when
   *        copying.
   * @param prependGroupId specifies if the groupId should be prepend to the file while copying.
   * @param useBaseVersion specifies if the baseVersion of the artifact should be used instead of
   *        the version.
   * @param removeClassifier specifies if the classifier should be removed from the file name when
   *        copying.
   * @throws MojoExecutionException with a message if an error occurs.
   * @see org.apache.maven.plugin.dependency.utils.DependencyUtil#copyFile(java.io.File,
   *      java.io.File, Log)
   * @see org.apache.maven.plugin.dependency.utils.DependencyUtil#getFormattedFileName(Artifact,
   *      boolean)
   */
  protected void copyArtifact(Artifact artifact, boolean removeVersion, boolean prependGroupId,
      boolean useBaseVersion, boolean removeClassifier) throws MojoExecutionException {
    String destFileName =
        DependencyUtil.getFormattedFileName(artifact, removeVersion, prependGroupId,
            useBaseVersion, removeClassifier);
    File destDir;
    destDir =
        DependencyUtil.getFormattedOutputDirectory(useSubDirectoryPerScope, useSubDirectoryPerType,
            useSubDirectoryPerArtifact, useRepositoryLayout, stripVersion, outputDirectory,
            artifact);
    File destFile = new File(destDir, destFileName);

    copyFile(artifact.getFile(), destFile);
  }

  protected ArtifactsFilter getMarkedArtifactFilter() {
    return new DestFileFilter(this.overWriteReleases, this.overWriteSnapshots,
        this.overWriteIfNewer, this.useSubDirectoryPerArtifact, this.useSubDirectoryPerType,
        this.useSubDirectoryPerScope, this.useRepositoryLayout, this.stripVersion,
        this.outputDirectory);
  }
}
