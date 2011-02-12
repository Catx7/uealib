package taboosearch.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FeaturesSpaceReader {
	
	public FeaturesSpace readFromFile(String filename) throws SAXException, IOException, ParserConfigurationException {
		File file = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		
		
		NodeList imageNodes = doc.getDocumentElement().getElementsByTagName("image");
		
		ArrayList<Feature> features = new ArrayList<Feature>();
		for (int imageIdx = 0; imageIdx < imageNodes.getLength(); imageIdx++) {
		    Element imageNode = (Element)imageNodes.item(imageIdx);
		    String imageFilename = imageNode.getElementsByTagName("filename").item(0).getTextContent();
		    String imageFeatures = imageNode.getElementsByTagName("features").item(0).getTextContent();
		    
		    Scanner s = new Scanner(imageFeatures);
		    s.useLocale(Locale.US);
		    
		    double[] featureComponents = new double[112];
		    int idx = 0;
		    while (s.hasNext()) featureComponents[idx++] = s.nextDouble();
		    
		    Feature feature = new Feature(imageFilename, featureComponents);
		    features.add(feature);
		}
		Collections.sort(features);
		/*for (int i = 0; i < features.size(); ++i) {
			Feature f = features.get(i);
			System.out.println(f.getName());
		}*/
		FeaturesSpace result = new FeaturesSpace(features);
		return result;
	}
	
}
