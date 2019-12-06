1) Création de la base de données :

Dans phpMyAdmin : lancer les requêtes sql données dans rest.project > src > sql. Elles créent la base de donnée "restproject".
Dans rest.project > src > rest.project.dao > DBdao.java: modifier l'URL de la base de données("dbURL"), l'utilisateur ("user") et le mot de passe ("pwd"), aux lignes 33,34,35.

2) Driver MySql :

Pour vérifier le bon import du driver, faire clique droit sur le projet > properties > Java Build Path > Libraries. Si des erreurs s'affichent, retirer (remove) puis rajouter (add external JARs) hibernate et mysql-connector.
Dans properties > Deployment Assembly, vérifier que "mysql-connector" s'affiche dans la colonne "Sources". Sinon, l'ajouter via "Add".

3) Lancer le projet :

Run > Run on server > Sélectionner Wildfly > finish

Ouvrir POSTMAN, copier l'URL : http://localhost:8080/rest.project/rest

- Afficher tous les Dvds (Books / VideoGames/ Users avec leurs médias respectifs) :
	Sélectionner GET > ajouter ../dvds (/books, /videogames, /users) à la suite de l'URL

- Sélectionner un seul DVD (book/video game/user) par son id :
	Sélectionner GET > Soit x l'id de l'élément recherché,
			 ajouter ../dvds/x (/books/x, /videogames/x, /users/x) à la suite de l'URL

- Rechercher les médias par lieu :
	Sélectionner GET > Soit xxx la ville (city) dont on veut récupérer les médias,
			 ajouter ../xxx à la suite de l'URL

- Ajouter un DVD (book/videogame) à ma liste de médias:
	Sélectionner POST > Sélectionner et remplir les paramètres du média à ajouter en indiquant mon id utilisateur (userID),
			ajouter ../dvds/addDvd (../books/addbook, ../videogames/addvideogame) à la suite de l'URL
 
- Supprimer un DVD (book/videogame) à ma liste de médias:
	Sélectionner DELETE > Soit x, l'id du média à supprimer
			ajouter ../dvds/x (../books/x, ../videogames/x) à la suite de l'URL

- Emprunter un DVD (book/videogame) :
	Sélectionner PUT >  Soit x l'id de l'élément recherché,
			ajouter ../dvds/x/borrow (/books/x/borrow, /videogames/x/borrow , /users/x/borrow ) à la suite de l'URL (l'attribut "borrowed" du 			média emprunté prend la valeur "true")

- Rendre un DVD (book/videogame) :
	Sélectionner PUT >  Soit x l'id de l'élément recherché,
			ajouter ../dvds/x/return (/books/x/return, /videogames/x/return , /users/x/return ) à la suite de l'URL (l'attribut "borrowed" du 			média emprunté prend la valeur "false")


		

