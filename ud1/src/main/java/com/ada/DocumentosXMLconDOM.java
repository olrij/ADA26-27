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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DocumentosXMLconDOM {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {

        String ruta="/home/u/Descargas/personas.xml";

        Document document = pasarXMLaDOM(ruta);

        //mostrarInfoPersona(1, document);

        //leerDocument(document);

        //borrarPersona(12, document,"personas.xml");

        anadirPersona(document, "Pepitooo", 54, 202, ruta);


        
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


    public static void mostrarInfoPersona(int idP, Document doc){

        NodeList personas = doc.getElementsByTagName("persona");
        

        for(int i=0;i<personas.getLength();i++){

            Element persona=(Element) personas.item(i);

            if(persona.getAttribute("id").equals(""+idP)){

                // Una vez identificada la persona obtengo sus datos

                NodeList hijosPersona = persona.getChildNodes();

                for(int j=0;j<hijosPersona.getLength();j++){
                    Node hp=hijosPersona.item(j);


                    switch (hp.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element he=(Element) hp;

                        System.out.print(he.getTagName()+": "+he.getTextContent());
                        
                        break;

                    case Node.TEXT_NODE:
                        Text ht=(Text) hp;
                        System.out.print(ht.getWholeText());
                        break;
                
                    default:
                        break;
                }


                }
                
                



            }


        }

    }


    public static void borrarPersona(int idP, Document doc,String rutaNueva) throws TransformerException{

        NodeList personas = doc.getElementsByTagName("persona");
        

        for(int i=0;i<personas.getLength();i++){

            Element persona=(Element) personas.item(i);

            if(persona.getAttribute("id").equals(""+idP)){

                // Una vez identificada la persona hago la operación
                Element padre = (Element) persona.getParentNode();

                padre.removeChild(persona);
                




            }


        }


        // Actualizar documento
        pasarDOMaXML(doc, rutaNueva);


    }

       public static void anadirPersona(Document doc,String nombreP,int edadP,int idP,String rutaNueva) throws TransformerException{

        // Obtengo el padre
        Element raiz=doc.getDocumentElement();

        // Creo el hijo

        Element nombre=doc.createElement("nombre");
        nombre.setTextContent(nombreP);
        Element edad=doc.createElement("edad");
        edad.setTextContent(""+edadP);

        Element persona=doc.createElement("persona");
        persona.setAttribute("id", ""+idP);

        persona.appendChild(nombre);
        persona.appendChild(edad);


        // Una vez preparada persona la añado al doc
        raiz.appendChild(persona);


        

        


        // Actualizar documento
        pasarDOMaXML(doc, rutaNueva);


    }
    

        public static void leerDocument(Document doc){

        // Obtengo la raíz

        Element raiz=doc.getDocumentElement();
        System.out.print(raiz.getTagName());

        NodeList personas=raiz.getChildNodes();
        

        for(int i=0;i<personas.getLength();i++){

            Node np=personas.item(i);

             switch (np.getNodeType()) {
                case Node.ELEMENT_NODE:
                    Element ne=(Element) np;

                    System.out.print(ne.getTagName()+": ");

                    NodeList hne=ne.getChildNodes();

                    for(int j=0;j<hne.getLength();j++){

                        Node nhne=hne.item(j);

                        switch (nhne.getNodeType()) {
                            case Node.ELEMENT_NODE:
                                Element ehne=(Element) nhne;

                                System.out.print(ehne.getTagName()+": "+ehne.getTextContent());
                                break;
                            
                            case Node.TEXT_NODE:
                                Text thne=(Text) nhne;
                                System.out.print(thne.getWholeText());
                                break;
                        
                            default:
                                break;
                        }


                    }



                        
                    break;

                case Node.TEXT_NODE:
                    Text nt=(Text) np;
                    System.out.print(nt.getWholeText());
                    break;
                
                default:
                    break;
                }

        

        }

    }




}
