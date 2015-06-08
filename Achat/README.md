Achat :
Un employe obtient une notification permettant de l'informer qu'il y a un besoin d'acheter un produit specifique grace a un diminution du stock.
Pour cette idee on a besoin :
- Une forme (JSF page) dans l'application qui demarre initialement,s'il faut, affichant les produits qui sont manqués OU BIEN Une application mobile qui fait un push notification
et notifie l'employe du produit qui est manqué.
L'environement technique pour cette idee:
- Technologie Java EE ou Android SDK
- Web services Rest pour la notification dans Android

S'il y a besoin d'acheter un produit (apres que la notification est affichee) :
L'employe accede la forme "Demande Produit" et entre les donnees (produits) a acheter
Pour cette idee on a besoin :
- Une forme "Demande Produit " qui fait le CRUD pour la demande.
L'environement technique pour cette idee:
- Technologie Java EE

Apres la demande et lorsque les produits arrivent :
L'employe sauvegarde les produits , un changement du stock et de la table "demande" aura lieu.
Pour cette idee on a besoin :
- Une forme "Reception" qui fait le CRUD de la reception
L'environement technique pour cette idee:
- Technologie Java EE

Pour ce projet Achat les produits seront mis dans une chambre initiale de la branche, de cette chambre les produits seront distribues vers les autres chambres/branches/personnes d'ou
un autre projet qui est le cartographie

Parlons de la base de donnee concernant ce projet:

    La table User
    La table Product
    La notification sera dependante d'un flag dans la table du stock et d'une table produit
    La demande sera "CRUDée" d'une table Order et elle utilisera une table fournisseur
    La Reception d'un fournisseur sera "CRUDée" des 2 tables recep et recep_dtl

