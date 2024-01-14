# hotel_app
github: https://github.com/spaghetttti/hotel_app

Aperçu du projet:

Le système de réservation d'hôtel est une application basée sur Java développée à l'aide du framework Spring Boot avec une base de données PostgreSQL. Le système permet aux utilisateurs de réserver et de consulter les chambres disponibles dans divers hôtels via des agences qui collaborent avec les hôtels. Les entités clés du système sont Agency, Hotel, Room et Reservation.

Éléments essentiels:

Entités :

Agency : représente les agences collaborant avec les hôtels. Chaque agence dispose de ses propres identifiants de connexion et d'un pourcentage de remise pour les hôtels partenaires.
Hotel : représente les hôtels avec des détails tels que le nom, l'emplacement, le nombre d'étoiles et les informations de contact.
Room : représente les chambres individuelles au sein des hôtels, en spécifiant des détails tels que le type de chambre, la capacité, le prix et la disponibilité.
Reservation : représente les réservations effectuées par les utilisateurs, y compris les informations sur les clients, les dates de réservation et les détails de la chambre associée.
Des classes:

Contrôleurs : chaque entité possède une classe de contrôleur définissant les points de terminaison pour gérer les requêtes HTTP.
Services : les classes de services encapsulent la logique métier, gérant le traitement des données et l'interaction entre les contrôleurs et les référentiels.
Repositories : les référentiels étendent JpaRepository pour gérer les requêtes courantes et fournir des requêtes personnalisées pour les interactions avec la base de données.
DemoApplication : Le point d’entrée de l’application.
PasswordHasher : une classe d'utilitaires pour le hachage et la vérification des mots de passe, principalement utilisée pour l'authentification des agences.
ApplicationConfig : configure l'insertion initiale des données dans la base de données au démarrage de l'application.

Interface Web:
Le projet comprend deux modèles HTML, fournissant une interface conviviale pour créer des réservations et rechercher des chambres disponibles.
Reservation.html : Présente un formulaire pour créer de nouvelles réservations dans la base de données. Les utilisateurs peuvent saisir des détails tels que les informations sur les clients, les détails de la carte de crédit et les dates de réservation.
RoomsLookup.html : contient un formulaire et un tableau de réponses. Les utilisateurs peuvent soumettre des critères pour une recherche de chambres et le système affiche les chambres disponibles avec les remises appliquées par l'agence de l'utilisateur.




Documentation:

L'application suit les principes de l'API RESTful, avec des points de terminaison définis pour diverses opérations. Les requêtes personnalisées et les opérations CRUD standard sont gérées efficacement à l'aide des référentiels Spring Data JPA.

Sécurité:
PasswordHasher garantit une gestion sécurisée des mots de passe pour les agences, contribuant ainsi à la sécurité globale de l'application.

Initialisation des données :
ApplicationConfig joue un rôle crucial dans l'initialisation de la base de données avec les tables nécessaires et les données initiales à chaque exécution d'application.




