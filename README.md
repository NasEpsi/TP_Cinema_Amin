┌─────────────────────────────────────────────────────────────────────────────┐
│ # TP_Cinema_Amin                                                           │
│                                                                             │
│ ## Description                                                              │
│ TP_Cinema_Amin est une application Java permettant de gérer la billetterie  │
│ d’un cinéma. Elle intègre un moteur de calcul du prix des tickets selon     │
│ divers critères : type de billet, jour de la semaine, séance 3D,            │
│ tarification groupe, etc. Le projet est conçu de façon modulaire avec une   │
│ architecture orientée métiers, une couverture de tests unitaires et une     │
│ intégration continue automatisée (CI/CD) via Jenkins.                       │
│                                                                             │
│ ## Fonctionnalités principales                                              │
│ - **Types de billets** : Adulte, Enfant, Senior, Étudiant                   │
│ - **Règles de tarification** :                                              │
│     - Tarif de base selon le type de billet                                 │
│     - -20% sur tout le panier chaque mercredi                               │
│     - +2 € par ticket pour les séances 3D                                   │
│     - -10% pour les groupes dès 4 tickets et plus                           │
│ - **Arrondi bancaire au centime le plus proche**                            │
│ - **Calcul détaillé (PriceBreakdown)** : transparence sur chaque étape      │
│ - **Couverture de tests avec JUnit 5, reporting Jacoco**                    │
│ - **Intégration CI/CD** : rapports de test via Jenkins toutes les 5 minutes │
│ après chaque build ; publication des rapports JUnit                         │
│ (`*/target/surefire-reports/.xml`)                                          │
│                                                                             │
│ > **INSÉRER ICI UNE CAPTURE D’ÉCRAN** montrant la page Jenkins ou le        │
│ reporting de couverture Jacoco pour illustrer le taux de couverture (64%).  │
│                                                                             │
│ ## Structure du projet                                                      │
│ - `src/main/java/edu/cinema/pricing/` : moteur de tarification              │
│   (PricingEngine, TicketType, PriceBreakdown)                               │
│ - `src/test/java/edu/cinema/pricing/` : tests unitaires                     │
│ - `pom.xml` : déclaration des dépendances Maven et configuration des plugins│
│ - `ProjetCICD` : scripts/config pour l'intégration et déploiement continus  │
│ - `Enoncé Projet Cinema.pdf` : spécifications du projet                     │
│                                                                             │
│ ## Lancement                                                                │
│ 1. **Prérequis** : Java 17+, Maven 3.6+                                     │
│ 2. **Clonage du dépôt** :                                                   │
│    git clone https://github.com/NasEpsi/TP_Cinema_Amin.git                  │
│ 3. **Build et tests** :                                                     │
│    mvn clean verify                                                         │
│    Les rapports de tests sont générés après chaque build.                   │
│ 4. **Intégration continue** : Jenkins lance les tests toutes les 5 minutes  │
│    et publie les résultats statistiques.                                    │
│                                                                             │
│ ## Extrait Discord (15:58) :                                                │
│ > On va faire en sorte que les tests se déroulent toutes les 5 minutes dans │
│ le post build action nous allons publier les rapports de test JUnit :       │
│ */target/surefire-reports/.xml                                              │
│ > **INSÉRER ICI UNE CAPTURE D’ÉCRAN** de la conversation Discord à 15:58.   │
└─────────────────────────────────────────────────────────────────────────────┘


<img width="1196" height="286" alt="image" src="https://github.com/user-attachments/assets/59bb4904-0c81-4f01-a2b1-8e0c3242e0ff" />

