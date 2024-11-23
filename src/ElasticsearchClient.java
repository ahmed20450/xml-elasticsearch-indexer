
// Importation des classes nécessaires pour la gestion des exceptions d'entrée/sortie
import java.io.IOException;

// Importation des classes Elasticsearch pour la connexion client
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.apache.http.HttpHost;

/**
 * Classe utilitaire pour gérer la connexion à Elasticsearch
 * Implémente le pattern Singleton pour maintenir une seule instance du client
 */
public class ElasticsearchClient {
    // Instance unique du client Elasticsearch (pattern Singleton)
    // static pour être partagée entre toutes les instances de la classe
    private static RestHighLevelClient client;

    /**
     * Méthode pour obtenir une instance du client Elasticsearch
     * Crée une nouvelle instance si elle n'existe pas encore (lazy initialization)
     * 
     * @return Instance du client Elasticsearch
     */
    public static RestHighLevelClient getClient() {
        // Vérifie si le client n'a pas déjà été initialisé
        if (client == null) {
            // Création d'un nouveau client avec les paramètres de connexion
            client = new RestHighLevelClient(
                    RestClient.builder(
                            // Configuration pour se connecter à Elasticsearch en local
                            // - host: localhost
                            // - port: 9200 (port par défaut d'Elasticsearch)
                            // - protocole: http
                            new HttpHost("localhost", 9200, "http")));
        }
        return client;
    }

    /**
     * Méthode pour fermer proprement la connexion au client Elasticsearch
     * Importante pour libérer les ressources système
     */
    public static void closeClient() {
        try {
            // Vérifie si le client existe avant de tenter de le fermer
            if (client != null) {
                // Ferme la connexion au client
                client.close();
            }
        } catch (IOException e) {
            // En cas d'erreur lors de la fermeture, affiche la trace de l'erreur
            e.printStackTrace();
        }
    }
}