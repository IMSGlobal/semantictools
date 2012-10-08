package org.semantictools.plugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.semantictools.publish.DocumentationGenerator;

/**
 * Invokes the semantictools DocunentationGenerator.
 * 
 * @goal generate
 * 
 * @phase generate-sources
 * @author Greg McFall
 *
 */
public class DocumentationPlugin extends AbstractMojo {


  /**
   * Location of the directory that contains the RDF source files and the
   * configuration properties files that drive the documentation process.
   * <p>
   * The default location for this directory is
   * </p>
   * <pre>
   *   src/main/resources/rdf
   * </pre>
   * @parameter expression="${basedir}/src/main/resources/rdf"
   */
  private File rdfDir;
  
  /**
   * The directory in which the output artifacts will be stored locally.
   * <p>
   * The default location for this directory is
   * </p>
   * <pre>
   *   target/generated-sources/rdf
   * </pre>
   * @parameter expression="${basedir}/target/generated-sources/rdf"
   */
  private File outputDir;
  
  /**
   * A flag that specifies whether or not the generated artifacts should
   * be published to semantictools.appspot.com
   * @parameter expression="false"
   */
  private boolean publish;
  
  /**
   * The endpoint to which documentation will be published.  This parameter
   * is meaningful only if publish=true.
   * 
   * @parameter expression="http://semantic-tools.appspot.com/admin/upload.do"
   */
  private String publishEndpoint;
  
  public void execute() throws MojoExecutionException, MojoFailureException {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String version = dateFormat.format(new Date());
    
    DocumentationGenerator generator = new DocumentationGenerator(rdfDir, outputDir, publish);
    generator.setUploadEndpoint(publishEndpoint);
    generator.setVersion(version);
    
    try {
      generator.run();
    } catch (Exception e) {
      throw new MojoExecutionException("Failed to generate documentation", e);
    }

  }

}
