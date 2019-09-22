import java.io.*;

import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class XmlConverter {

    TransformerHandler handler;
    StreamResult output;

    public static void main(String args[]) {
        new XmlConverter().runConverter();
    }

    public void runConverter() {
        String file = "input.txt";
        try {            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            output = new StreamResult("output.xml");
            openXml();
            boolean firstPerson = true; //Used to achieve correct XML structe on the "person" element
            String line;
            while((line = reader.readLine()) != null) {
                String[] elements = line.split("\\|");

                switch (line.charAt(0)) {
                case 'P':
                    String[] personInstance = {"person", "firstname", elements[1], "lastname", elements[2]};
                    createXmlElement(personInstance,firstPerson);
                    firstPerson = false; 
                    break;
                case 'T':
                    String[] phoneInstance = {"phone", "mobile", elements[1], "landline", elements[2]};
                    createXmlElement(phoneInstance,firstPerson);
                    break;
                case 'A':
                    String[] addressInstance = {"address", "street", elements[1], "city", elements[2], "postcode", elements[3]};
                    createXmlElement(addressInstance,firstPerson);
                    break;
                case 'F':
                    String[] familyInstance = {"family", "name", elements[1], "yearofbirth", elements[2]};
                    createXmlElement(familyInstance,firstPerson);
                    break;
                }
            }
            reader.close();
            closeXml();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    
    public void openXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        handler = factory.newTransformerHandler();
        Transformer serializer = handler.getTransformer();
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        handler.setResult(output);
        handler.startDocument();
        handler.startElement(null, null, "people", null);
    }

    
    public void createXmlElement(String[] elements, boolean firstPerson) throws SAXException {
        if (elements[0] == "person" && firstPerson == false) {
            handler.endElement(null, null, elements[0]); //Close the previous "person" tag
        }
        handler.startElement(null, null, elements[0], null);
        for (int i=1; i<elements.length; i+=2) {
            handler.startElement(null, null, elements[i], null);
            handler.characters(elements[i+1].toCharArray(), 0, elements[i+1].length());
            handler.endElement(null, null, elements[i]);
        }
        if (elements[0] != "person") {
            handler.endElement(null, null, elements[0]);
        }
    }

    
    public void closeXml() throws SAXException {
        handler.endElement(null, null, "person");
        handler.endElement(null, null, "people");
        handler.endDocument();
    }
}
    
