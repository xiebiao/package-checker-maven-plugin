package com.github.maven.plugin;

import com.apache.maven.plugin.DemoMojo;
import org.apache.maven.plugin.testing.MojoRule;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.Xpp3DomBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * @author xiebiao
 * @date 10/14/14
 */
public class DemoMojoTest {
    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {}

        @Override
        protected void after() {}
    };

    private String pom;
    private Xpp3Dom pomDom;
    private PlexusConfiguration pluginConfiguration;
    /**
     * @throws Exception if any
     */
    @Test
    public void testExecute() throws Exception {
          pom =
                "<project>" +
                        "<dependencies><dependency><groupId>commons-io</groupId><artifactId>commons-io</artifactId></dependency></dependencies>"+
                        "<build>" +
                        "<plugins>" +
                        "<plugin>" +
                        "<artifactId>maven-demo-plugin</artifactId>" +
                        "<configuration>" +
                        "<keyOne>valueOne</keyOne>" +
                        "<keyTwo>valueTwo</keyTwo>" +
                        "</configuration>" +
                        "</plugin>" +
                        "</plugins>" +
                        "</build>" +
                        "</project>";

        pomDom = Xpp3DomBuilder.build(new StringReader(pom));
        pluginConfiguration = rule.extractPluginConfiguration( "maven-demo-plugin", pomDom );
        DemoMojo mojo = new DemoMojo();

        mojo = (DemoMojo) rule.configureMojo( mojo, pluginConfiguration );
        mojo.execute();
        assertEquals( "valueOne", mojo.getKeyOne() );
        assertEquals( "valueTwo", mojo.getKeyTwo() );
    }
}
