# String Calculator

## Objectif

Le but de cette application est de calculer la somme des numéros fournis en entrée. L'approche utilisée est le TDD.

Ce calculateur respecte les règles métiers suivantes :

- La somme d'une entrée vide est 0.
- Les délimiteurs par défaut sont la virgule ``` ,``` et la nouvelle ligne ```\n```.
- Ces deux délimiteurs peuvent être utilisés simultanément dans la même entrée.
- Le nombre de numéros à entrer n'a pas de limite.
- Un seul délimiteur est permis entre 2 numéros.
- Si la première ligne est de la forme ```//[delimiter]\n```, le caractère ```[delimiter]``` sera utilisé comme
  délimiteur. Celle ligne est optionnelle.
- Le délimiteur au début ou à la fin de l'entrée n'est pas accepté.

## Stack technique

- **Language** : Java
- **Gestion de dépendances** : Gradle

## Comment exécuter l'application

Il s'agit d'une application console. La classe main est  ``` Application ```

##### Comment exécuter les tests ?

```bash
./gradlew clean test
```

Le rapport de test sera disponible sous ``` build/testReport/index.html ```