# GLO-3004 Spécification formelle et vérification de logiciels
## Projet 1 étape 2
Axel Berneron 111 104 436 <br />
Marek Tremblay 111 100 094


## Entrées de la console
Nous demandons les entrées suivantes lors du lancement de l'application : 
- Le nombre de trains A;
- Le nombre de trains B; 
- Le nombre de trains C.

## Intervalle de temps pour chaque nouveau train
Ce type de paramètre n'est pas demandé en entrée utilisateur, mais est bel et bien
défini dans les classes de convois "ConvoiTrainA", "ConvoiTrainB", "ConvoiTrainC"
et est nommé (respectivement à chaque convoi) "TEMPS_NOUVEAU_TRAINA", "TEMPS_NOUVEAU_TRAINB", 
"TEMPS_NOUVEAU_TRAINC". (les valeurs sont en secondes).

## Probabilités d'événements
Pour chaque événement de l'application, soit le délai ou la panne, des probabilités sont calculées 
pour savoir si l'événement va survenir.

### Délai
La probabilité de délai fonctionne comme suit : Il y a 30% de chance qu'elle survienne avant qu'un train
entre dans une station. Le délai est de 1 seconde et peut se reproduire plusieurs fois de suite.
Le code du délai se trouve dans la classe "Train".

### Panne
La probabilité de panne fonctionne comme suit : Il y a 20% de chance qu'elle survienne avant qu'un train
entre dans une station. Le délai d'une panne est de 1 seconde, suivi de 7 secondes de réparation. La réparation a
50% de chance de se produire (on simule ici la disponibilité d'un ingénieur réparateur) et le délai entre chaque 
tentative de réparation est de 1 seconde. Le code de la panne se trouve dans la classe "Panne".