# TP_Cinema_Amin 
Réalisé par Nassim Ouachen, Alessio Cogerino et Paul Lugien 

## Présentation

TP_Cinema_Amin est une application Java professionnelle dédiée à la gestion de la billetterie d’un cinéma. Elle intègre un moteur de tarification dynamique, prenant en compte différents paramètres : type de billet, jour de la semaine, séances 3D, groupes, etc. Le projet propose une architecture modulaire, une couverture de tests unitaires élevée et une intégration continue automatisée via Jenkins et Jacoco.

## Fonctionnalités principales

- **Types de billets gérés** : Adulte, Enfant, Senior, Étudiant
- **Règles de tarification avancées** :
    - Tarif de base selon le type de billet
    - **-20%** sur tout le panier chaque mercredi
    - **+2 €** par ticket pour les séances 3D
    - **-10%** pour les groupes (dès 4 tickets)
- **Arrondi bancaire au centime le plus proche**
- **Détail transparent des calculs** (PriceBreakdown)
- **Tests unitaires automatisés** (JUnit 5, Jacoco)
- **CI/CD** : Jenkins exécute les tests toutes les 5 minutes et publie les rapports JUnit (`*/target/surefire-reports/.xml`)

---

## Qualité logicielle et supervision

Suivi continu et automatisé grâce à Jenkins et Jacoco :

<img width="1292" height="197" alt="image" src="https://github.com/user-attachments/assets/4085da12-f722-40be-9b3d-18c098340323" />
*Cette capture présente le taux de couverture Jacoco, démontrant une couverture de **64%** des tests unitaires sur les fichiers métiers.*

<img width="1196" height="286" alt="image" src="https://github.com/user-attachments/assets/59bb4904-0c81-4f01-a2b1-8e0c3242e0ff" />
*Installation du plugin JaCoCo sur Jenkins*

<img width="1271" height="235" alt="image" src="https://github.com/user-attachments/assets/2c4cdca1-76e7-4615-a15a-c16aff7b0dc1" />
<img width="852" height="637" alt="image" src="https://github.com/user-attachments/assets/9790a755-1829-4f48-ad71-db5afb0f2195" />
  
*Illustration de la pipeline Jenkins : les tests sont déclenchés toutes les 5 minutes avec une publication automatisée des rapports de tests, pour garantir la stabilité et la qualité du projet.*

---

## Structure du projet

- `src/main/java/edu/cinema/pricing/` : moteur de tarification (`PricingEngine`, `TicketType`, `PriceBreakdown`)
- `src/test/java/edu/cinema/pricing/` : tests unitaires complets
- `pom.xml` : dépendances Maven et configuration des plugins (JUnit, Jacoco…)
- `ProjetCICD` : scripts et workflows pour la CI/CD (exemple : Jenkinsfile)
- `Enoncé Projet Cinema.pdf` : spécifications et exigences fonctionnelles

---

## Prise en main

1. **Prérequis :**
   - Java 17 ou supérieur
   - Maven 3.6+
2. **Clonage du dépôt :**
git clone https://github.com/NasEpsi/TP_Cinema_Amin.git
3. **Compilation, tests & rapports :**
Les rapports JUnit et Jacoco sont générés à chaque build.
**Intégration continue :**
4.- Jenkins exécute les tests toutes les 5 minutes et publie les rapports de couverture automatiquement.

<img width="1645" height="744" alt="image" src="https://github.com/user-attachments/assets/70750314-1f80-45d8-8e82-ce3e19f2f674" />
**Voici le coverage final du projet apres avoir suivi toutes les etapes**







