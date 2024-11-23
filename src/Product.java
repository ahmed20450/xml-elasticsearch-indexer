
// Importation des classes nécessaires pour la manipulation des Maps
import java.util.HashMap;
import java.util.Map;

// Classe représentant un produit avec ses caractéristiques
public class Product {
    // Attributs privés de la classe
    private String articleSku; // Identifiant unique du produit
    private String brand; // Marque du produit
    private String category; // Catégorie du produit

    // Méthodes d'accès (getters) et de modification (setters)

    // Récupère l'identifiant du produit
    public String getArticleSku() {
        return articleSku;
    }

    // Définit l'identifiant du produit
    public void setArticleSku(String articleSku) {
        this.articleSku = articleSku;
    }

    // Récupère la marque du produit
    public String getBrand() {
        return brand;
    }

    // Définit la marque du produit
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Récupère la catégorie du produit
    public String getCategory() {
        return category;
    }

    // Définit la catégorie du produit
    public void setCategory(String category) {
        this.category = category;
    }

    // Méthode pour convertir l'objet Product en Map
    public Map<String, Object> toMap() {
        // Création d'une nouvelle HashMap pour stocker les attributs
        Map<String, Object> map = new HashMap<>();
        // Ajout de chaque attribut dans la map avec sa clé correspondante
        map.put("articleSku", articleSku);
        map.put("brand", brand);
        map.put("category", category);
        // Retourne la map contenant tous les attributs du produit
        return map;
    }
}