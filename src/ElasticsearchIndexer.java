
// Importation des classes nécessaires pour l'indexation en masse dans Elasticsearch
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

// Importations pour la gestion des entrées/sorties et des collections
import java.io.IOException;
import java.util.List;

/**
 * Classe responsable de l'indexation des produits dans Elasticsearch
 * Utilise l'API Bulk pour optimiser les performances d'indexation
 */
public class ElasticsearchIndexer {
    /**
     * Méthode pour indexer une liste de produits dans Elasticsearch
     * Utilise l'API Bulk pour envoyer plusieurs documents en une seule requête
     * 
     * @param products Liste des produits à indexer
     */
    public void indexProducts(List<Product> products) {
        // Utilisation du try-with-resources pour fermer automatiquement le client
        // même en cas d'exception
        try (RestHighLevelClient client = ElasticsearchClient.getClient()) {
            // Création d'une requête Bulk pour l'indexation en masse
            BulkRequest bulkRequest = new BulkRequest();

            // Parcours de tous les produits à indexer
            for (Product product : products) {
                // Création d'une requête d'indexation pour chaque produit
                IndexRequest request = new IndexRequest("products") // Nom de l'index
                        .id(product.getArticleSku()) // ID du document (SKU du produit)
                        .source(product.toMap()); // Conversion du produit en Map
                // Ajout de la requête d'indexation à la requête Bulk
                bulkRequest.add(request);
            }

            // Exécution de la requête Bulk avec les options par défaut
            BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);

            // Vérification du résultat de l'indexation
            if (response.hasFailures()) {
                // En cas d'erreurs, affichage du message d'erreur
                System.err.println("Indexing errors: " + response.buildFailureMessage());
            } else {
                // Si tout s'est bien passé, affichage d'un message de succès
                System.out.println("All products indexed successfully.");
            }
        } catch (IOException e) {
            // En cas d'erreur d'entrée/sortie, affichage de la trace
            e.printStackTrace();
        }
    }
}