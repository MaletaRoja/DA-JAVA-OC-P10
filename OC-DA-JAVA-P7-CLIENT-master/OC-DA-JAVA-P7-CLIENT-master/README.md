# Présentation

L'application doit permettre aux usages d'une bibliothèque municipale de consulter les ouvrages de la bibliothèque, de suivre
leurs emprunts, de les prolonger une seule fois pour une durée de quatre semaines. 

Le site a été déployé sur un serveur Linode et utilise une base de données PostgreSQL sur une instance RDS  de AWS à l'adresse: http://192.46.239.178:8080/biblio/


# Déployement

Le site est déployé sur un serveur Tomcat version 9. <br/>

Le war de l'application est produit par Maven avec la commande suivante:  mvn clean install <br/>

Le war est ensuite déposé dans le dossier webapps de tomcat a partir du manager. <br/>
Les droits d'accès au manager sont configurés dans le fichier tomcat-users.xml situé dans le dossier conf de Tomcat par les
lignes suivantes:<br/>

```xml
<role rolename="manager-gui"/>
<user username="xxxxxx" password="xxxx" roles="tomcat, manager-gui"/>
```

