# tweet
Twitter-like dynamic web project.

## Services

### Authentification

|   |   |
|---|---|
| Nom du web service | RegisterService |
| URL du web service | /register |
| Description du service | Permet à l'utilisateur de s'enregistrer |
| Parametres en entrée | username, password, nom, prenom, email |
| Format de sortie | JSON |
| Exemple de sortie | {"user": "login"} |
| Erreurs possibles | arguments invalides, nom utilisateur existant |
| Avancement du Service | terminé |
| Classes JAVA | RegisterService, RegisterServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |


|   |   |
|---|---|
| Nom du web service | LoginService |
| URL du web service | /login |
| Description du service | Permet à l'utilisateur de se connecter |
| Parametres en entrée | username, password |
| Format de sortie | JSON |
| Exemple de sortie | {"key": "67489ab4e1504fcca6d83455370fe6e4"} |
| Erreurs possibles | arguments invalides, nom utilisateur inexistant, mot de passe incorrect |
| Avancement du Service | service terminé |
| Classes JAVA | LoginService, LoginServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |


|   |   |
|---|---|
| Nom du web service | LogoutService |
| URL du web service | /logout |
| Description du service | Permet à l'utilisateur de se déconnecter |
| Parametres en entrée | session key |
| Format de sortie | JSON |
| Exemple de sortie | {} |
| Erreurs possibles | arguments invalides, session inexistante |
| Avancement du Service | terminé |
| Classes JAVA | LogoutService, LogoutServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |


### Comments

|   |   |
|---|---|
| Nom du web service | AddCommentService |
| URL du web service | /comment/add |
| Description du service | Permet à l'utilisateur d'ajouter un commentaire |
| Parametres en entrée | session key, comment |
| Format de sortie | JSON |
| Exemple de sortie | {} |
| Erreurs possibles | arguments invalides, session inexistante |
| Avancement du Service | servlet à faire |
| Classes JAVA | AddCommentService, AddCommentServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |

|   |   |
|---|---|
| Nom du web service | GetCommentService |
| URL du web service | /comment/add |
| Description du service | Permet de récupérer les messages |
| Parametres en entrée | page, session key |
| Format de sortie | JSON |
| Exemple de sortie | {} |
| Erreurs possibles | arguments invalides, session inexistante |
| Avancement du Service | terminé |
| Classes JAVA | GetCommentService, GetCommentServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |




### Friends

|   |   |
|---|---|
| Nom du web service | AddFriendService |
| URL du web service | /friend/add |
| Description du service | Permet à l'utilisateur d'ajouter un ami |
| Parametres en entrée | session key, username friend |
| Format de sortie | JSON |
| Exemple de sortie | {} |
| Erreurs possibles | arguments invalides, session inexistante |
| Avancement du Service | à faire |
| Classes JAVA | AddFriendService, AddFriendServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |

|   |   |
|---|---|
| Nom du web service | RemoveFriendService |
| URL du web service | /friend/remove |
| Description du service | Permet à l'utilisateur d'enlever un ami |
| Parametres en entrée | session key, username friend |
| Format de sortie | JSON |
| Exemple de sortie | {} |
| Erreurs possibles | arguments invalides, session inexistante |
| Avancement du Service | terminé |
| Classes JAVA | RemoveFriendService, RemoveFriendServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |

|   |   |
|---|---|
| Nom du web service | GetFriendService |
| URL du web service | /friend/get |
| Description du service | Permet de récupérer liste amis |
| Parametres en entrée | session key |
| Format de sortie | JSON |
| Exemple de sortie | {} |
| Erreurs possibles | arguments invalides, session inexistante |
| Avancement du Service | terminé |
| Classes JAVA | GetFriendService, GetFriendServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |
