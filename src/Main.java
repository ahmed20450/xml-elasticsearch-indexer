import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Chemin vers le fichier ZIP contenant le fichier XML
        String zipFilePath = "C:/Users/Bengaied Ahmed/Downloads/Adelean_test_recrutement_java_program/java_program/xml.zip";
        // Chemin temporaire où le fichier XML extrait sera sauvegardé
        String extractedXmlFilePath = "C:/Users/Bengaied Ahmed/Downloads/Adelean_test_recrutement_java_program/java_program/xml.xml";

        // Extraction du fichier XML depuis le fichier ZIP
        unzipFile(zipFilePath, extractedXmlFilePath);

        // Création d'une instance du parseur XML pour analyser le fichier extrait
        XmlParser parser = new XmlParser();
        // Analyse du fichier XML pour extraire les produits
        List<Product> products = parser.parseXml(extractedXmlFilePath);

        // Création d'une instance pour indexer les produits dans Elasticsearch
        ElasticsearchIndexer indexer = new ElasticsearchIndexer();
        // Indexation des produits dans la base de données Elasticsearch
        indexer.indexProducts(products);

        // Fermeture du client Elasticsearch pour libérer les ressources
        ElasticsearchClient.closeClient();
    }

    /**
     * Méthode pour extraire un fichier XML à partir d'une archive ZIP.
     * 
     * @param zipFilePath    Chemin complet vers le fichier ZIP.
     * @param outputFilePath Chemin où le fichier XML extrait sera sauvegardé.
     */
    public static void unzipFile(String zipFilePath, String outputFilePath) {
        // Utilisation de try-with-resources pour gérer les ressources de manière
        // automatique
        try (FileInputStream fis = new FileInputStream(zipFilePath); // Lecture du fichier ZIP
                ZipInputStream zis = new ZipInputStream(fis)) { // Création d'un flux pour parcourir le contenu du ZIP
            ZipEntry entry;
            // Parcours des entrées (fichiers ou dossiers) dans le fichier ZIP
            while ((entry = zis.getNextEntry()) != null) {
                // Vérifie si l'entrée est un fichier XML (vérification de l'extension .xml)
                if (entry.getName().endsWith(".xml")) {
                    // Écriture du fichier extrait à l'emplacement spécifié
                    try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                        // Création d'un buffer pour lire les données
                        byte[] buffer = new byte[1024];
                        int length;
                        // Lecture des données depuis le fichier ZIP et écriture dans le fichier de
                        // sortie
                        while ((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length); // Écriture des données
                        }
                    }
                    // Ferme l'entrée courante dans le fichier ZIP
                    zis.closeEntry();
                    // Sort de la boucle après avoir extrait un fichier XML
                    break;
                }
            }
        } catch (Exception e) {
            // En cas d'erreur, affiche la pile d'exécution pour le débogage
            e.printStackTrace();
        }
    }
}
