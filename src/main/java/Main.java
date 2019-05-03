import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static Model model = ModelFactory.createDefaultModel();

    public static void main (String args[]) {
        String outputFileName = "sample.rdf";
        try {
            FileWriter out = new FileWriter(outputFileName);
            // some definitions
            Model m = ModelFactory.createDefaultModel();
            String nsA = "http://somewhere/else#";
            String nsB = "http://nowhere/else#";
            Resource root = m.createResource( nsA + "root" );
            Property P = m.createProperty( nsA + "P" );
            Property Q = m.createProperty( nsB + "Q" );
            Resource x = m.createResource( nsA + "x" );
            Resource y = m.createResource( nsA + "y" );
            Resource z = m.createResource( nsA + "z" );

            m.add(root, P, x ).add(root, P, y ).add(y, Q, z);
            System.out.println( "# -- no special prefixes defined" );

            m.write( System.out );
            System.out.println( "# -- nsA defined" );

            m.setNsPrefix( "nsA", nsA );
            m.setNsPrefix( "nsB", nsB );
            m.write( System.out );
            System.out.println( "# -- nsA and cat defined" );

            m.setNsPrefix( "cat", nsB );

            m.write(out, "RDF/XML");

        } catch (IOException e) {

        }
    }
}
