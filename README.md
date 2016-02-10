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
| Exemple de sortie | {"key": "ird8cio1sq62bb8n09e6517j14"} |
| Erreurs possibles | arguments invalides, nom utilisateur inexistant, mot de passe incorrect |
| Avancement du Service | terminé |
| Classes JAVA | LoginService, LoginServlet, AuthentificationTools, DBStatic |
| Infos supplémentaires | |