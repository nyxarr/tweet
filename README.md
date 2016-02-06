# tweet
Twitter-like dynamic web project.

## Services

### Authentification

|   |   |
|---|---|
| Nom du web service | RegisterService |
| URL du web service | /register |
| Description du service | Permet à l'utilisateur de s'enregistrer |
| Parametres en entrée | username, password, email |
| Format de sortie | JSON |
| Exemple de sortie | {"success":"Utilisateur enregistré.","user":{"email":"test@test.fr","username":"test"}} |
| Erreurs possibles | connection DB échouée, enregistrement échoué, nom utilisateur existant |
| Avancement du Service | mise en place de l'enregistrement dans la DB |
| Classes JAVA | RegisterService, RegisterServlet |
| Infos supplémentaires | |
