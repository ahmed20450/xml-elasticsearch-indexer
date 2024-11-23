# XML Elasticsearch Indexer

Ce programme extrait un fichier XML contenu dans une archive ZIP, analyse les données des produits, puis les indexe dans une base de données Elasticsearch.

---

## Fonctionnalités

1. Extraction d'un fichier XML à partir d'une archive ZIP.
2. Analyse des données produits à partir du fichier XML (article SKU, marque, catégorie).
3. Indexation des produits dans un index Elasticsearch.

---

## Prérequis

- **JDK 11** : Assurez-vous que Java 11 est installé et configuré sur votre machine.
- **Elasticsearch 7.4.1** : Une instance locale ou distante doit être disponible.
- **Git** : Pour cloner le dépôt.

---

### Étapes pour démarrer le programme

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/ahmed20450/xml-elasticsearch-indexer.git
   cd xml-elasticsearch-indexer
