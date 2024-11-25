
// Importation des bibliothèques nécessaires pour le parsing XML
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    // Méthode principale qui parse le fichier XML et retourne une liste de produits
    public List<Product> parseXml(String filePath) {
        // Initialisation de la liste qui contiendra tous les produits
        List<Product> products = new ArrayList<>();

        try {
            // Création d'une fabrique de parseur SAX (Simple API for XML)
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Création d'un parseur SAX à partir de la fabrique
            SAXParser saxParser = factory.newSAXParser();

            // Définition d'un gestionnaire personnalisé pour traiter les événements XML
            DefaultHandler handler = new DefaultHandler() {
                // Variable pour stocker le produit en cours de traitement
                Product product = null;
                // Variable pour stocker le contenu textuel entre les balises
                String content = null;

                // Méthode appelée à chaque fois qu'une balise ouvrante est rencontrée
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    // Si on trouve une balise "product", on crée un nouveau produit
                    if ("product".equalsIgnoreCase(qName)) {
                        product = new Product();
                    }
                }

                // Méthode appelée pour traiter le contenu textuel entre les balises
                @Override
                public void characters(char[] ch, int start, int length) {
                    content = new String(ch, start, length);
                }

                // Méthode appelée à chaque fois qu'une balise fermante est rencontrée
                @Override
                public void endElement(String uri, String localName, String qName) {
                    // Si un produit est en cours de traitement
                    if (product != null) {
                        // On remplit les attributs du produit selon la balise rencontrée
                        switch (qName) {
                            case "article_sku":
                                product.setArticleSku(content);
                                break;
                            case "brand":
                                product.setBrand(content);
                                break;
                            case "category":
                                product.setCategory(content);
                                break;
                        }
                    }
                    // Si on termine le traitement d'un produit, on l'ajoute à la liste
                    if ("product".equalsIgnoreCase(qName)) {
                        products.add(product);
                    }
                }
            };

            // Lancement du parsing du fichier XML avec notre gestionnaire
            saxParser.parse(filePath, handler);
        } catch (Exception e) {
            // En cas d'erreur, on affiche la trace de la pile
            e.printStackTrace();
        }
        // Retour de la liste des produits parsés
        return products;
    }
}
