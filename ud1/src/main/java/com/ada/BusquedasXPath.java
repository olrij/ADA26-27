package com.ada;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class BusquedasXPath {
    
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, TransformerException {



        String ruta="/home/u/Descargas/personas.xml";
        Document doc=pasarXMLaDOM(ruta);

        // Crea el objeto XpathFactory
        XPath xpath=(XPath) XPathFactory.newInstance().newXPath();


        //mostrarInfoJuan(xpath, doc);
        //mostrarPersonasMayoresDeX(xpath, doc, 30);
        borrarPersonasIDMayorAX(xpath, doc, 200, ruta);



        
    
    }

    public static void mostrarInfoJuan(XPath xpath,Document doc) throws XPathExpressionException{

        //Crea un XpathExpression con la consulta deseada
        String xpathExpression="/personas/persona[./nombre='Juan']";
        //Consultas
        NodeList nodos=(NodeList) xpath.evaluate(xpathExpression,doc, XPathConstants.NODESET);

        for(int i=0;i<nodos.getLength();i++){

            Element persona=(Element)nodos.item(i);

            
            NodeList hp = persona.getChildNodes();

            for(int j=0;j<hp.getLength();j++){

                Node hhp=hp.item(j);

                switch (hhp.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element ehp=(Element) hhp;
                        System.out.println(ehp.getTagName()+": "+ehp.getTextContent());
                        break;

                    case Node.TEXT_NODE:
                        
                        break;
                
                    default:
                        break;
                }

            }


            



        }



    }

    public static void mostrarPersonasMayoresDeX(XPath xpath,Document doc, int edadX) throws XPathExpressionException{

        //Crea un XpathExpression con la consulta deseada
        String xpathExpression="/*/*[./edad>="+edadX+"]";
        //Consultas
        NodeList nodos=(NodeList) xpath.evaluate(xpathExpression,doc, XPathConstants.NODESET);

        for(int i=0;i<nodos.getLength();i++){

            Element persona=(Element)nodos.item(i);

            
            NodeList hp = persona.getChildNodes();

            for(int j=0;j<hp.getLength();j++){

                Node hhp=hp.item(j);

                switch (hhp.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element ehp=(Element) hhp;
                        System.out.println(ehp.getTagName()+": "+ehp.getTextContent());
                        break;

                    case Node.TEXT_NODE:
                        
                        break;
                
                    default:
                        break;
                }

            }
            System.out.println("---------------------");


            



        }



    }

        public static void borrarPersonasIDMayorAX(XPath xpath,Document doc,int idX,String ruta) throws XPathExpressionException, TransformerException{

        //Crea un XpathExpression con la consulta deseada
        String xpathExpression="/*/*[./@id>="+idX+"]";


        //Consultas
        NodeList nodos=(NodeList) xpath.evaluate(xpathExpression,doc, XPathConstants.NODESET);

        for(int i=0;i<nodos.getLength();i++){

            Element persona=(Element)nodos.item(i);

            Element personas=(Element) persona.getParentNode();

            personas.removeChild(persona);
            
        }

        pasarDOMaXML(doc,ruta);



    }




    public static Document pasarXMLaDOM(String ruta) throws SAXException, IOException, ParserConfigurationException{

        // 1o Creamos una nueva instancia de una fabrica de constructores de documentos.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 2o A partir de la instancia anterior, fabricamos un constructor de documentos, que procesará el XML.
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 3o Procesamos el documento (almacenado en un archivo) y lo convertimos en un árbol DOM.
        Document doc=db.parse(ruta);
        return doc;


    }

    public static void pasarDOMaXML(Document doc,String rutaXML) throws TransformerException{
        // 1o Creamos una instancia de la clase File para acceder al archivo donde guardaremos el
        // XML.
        File f=new File(rutaXML);
        // 2o Creamos una nueva instancia del transformador a través de la fábrica de
        // transformadores.
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        // 3o Establecemos algunas opciones de salida, como por ejemplo, la codificación de salida.
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 4o Creamos el StreamResult, intermediaria entre el transformador y el archivo de destino.
        StreamResult result = new StreamResult(f);
        // 5o Creamos el DOMSource, intermediaria entre el transformador y el árbol DOM.
        DOMSource source = new DOMSource(doc);
        //6o Realizamos la transformación.
        transformer.transform(source, result);
    }

}
