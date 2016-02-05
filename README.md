# tweet
Twitter-like dynamic web project.

## Services

### Authentification
| | | | <br />
|-|-|-| <br />
| Nom du web service | RegisterService | <br />
| URL du web service | /register?username=""&password""=&email="" | <br />
| Description du service | Permet à l'utilisateur de s'enregistrer | <br />
| Parametres en entrée | username, password, email | <br />
| Format de sortie | JSON | <br />
| Exemple de sortie | { "output": "User registered", "username": "test", "email" : "test@test.com" } | <br />
| Erreurs possibles | connection DB échouée, enregistrement échoué | <br />
| Avancement du Service | mise en place de l'enregistrement dans la DB | <br />
| Classes JAVA | RegisterService, RegisterServlet | <br />
| Infos supplémentaires | | <br />