TP_Cinema_Amin
Description
TP_Cinema_Amin est une application Java de gestion de billetterie de cinéma. Elle propose un système de calcul du prix des tickets en fonction de plusieurs critères (type de ticket, jour de la semaine, séance 3D, groupe, etc.) et met en place une architecture modulaire accompagnée de tests unitaires et d’une intégration continue avec CI/CD.

Fonctionnalités principales
Types de billets : Adulte, Enfant, Senior, Étudiant
Règles de tarification :
Tarif de base selon le type de billet
-20% chaque mercredi sur l'ensemble du panier
+2€ par ticket pour les séances 3D
-10% pour les groupes (4 tickets ou plus)
Arrondi bancaire au centime le plus proche
Calcul détaillé (PriceBreakdown) avec transparence sur chaque étape du calcul
Couverture de tests avec Junit 5 et reporting Jacoco
Intégration CI/CD (Jenkins/manual ou autres workflows automatisés, voir ProjetCICD)

Structure du projet
src/main/java/edu/cinema/pricing/ : moteur de tarification (PricingEngine, TicketType, PriceBreakdown)
pom.xml : gestion des dépendances Maven, configuration des plugins pour les tests et la couverture de code
ProjetCICD : scripts et configuration pour l'intégration et le déploiement continus
Enoncé Projet Cinema.pdf : spécifications détaillées du projet

Lancement
Prérequis : Java 17+, Maven 3.6+
Clone le dépôt :


<img width="1196" height="286" alt="image" src="https://github.com/user-attachments/assets/59bb4904-0c81-4f01-a2b1-8e0c3242e0ff" />

<img width="1309" height="234" alt="image" src="https://github.com/user-attachments/assets/edf88273-4665-47bd-85a4-fab32cb47a74" />

<img width="1256" height="167" alt="image" src="https://github.com/user-attachments/assets/0ac89678-dfcf-468d-bee2-1a8528e0abea" />

<img width="1271" height="235" alt="image" src="https://github.com/user-attachments/assets/2c4cdca1-76e7-4615-a15a-c16aff7b0dc1" />

<img width="1292" height="197" alt="image" src="https://github.com/user-attachments/assets/4085da12-f722-40be-9b3d-18c098340323" />
