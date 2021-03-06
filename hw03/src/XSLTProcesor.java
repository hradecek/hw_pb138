
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bar
 */
public class XSLTProcesor {
    public static void main(String[] args) 
            throws TransformerConfigurationException, TransformerException {
        
        TransformerFactory tf = TransformerFactory.newInstance();
        
        System.out.println(tf.getClass());
        
        Transformer xsltProc = tf.newTransformer(
                new StreamSource(new File("src/company-to-html.xsl")));
        
        xsltProc.transform(
                new StreamSource(new File("src/company.xml")), 
                new StreamResult(new File("src/company.html")));
    }
}
