# Exploring Mars
Teste de Programação Elo7

Descrição do Teste:
https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c

## Project Modules:
 - mars-explorer-engine: Engine de criação e manipulação das sondas. (WIP)
 - mars-explorer-api: API Rest para a engine. (WIP)
 - mars-explorer-gui: Interface Gráfica. (WIP)

## Rodando a API da Engine via linha de comando:
```
cd mars-explorer-api
mvn spring-boot:run
```

## Rodando a GUI da Engine via linha de comando:
```
cd mars-explorer-gui
mvn -Drunfx.args="-jar ./target/mars-explorer-gui-1.0-SNAPSHOT.jar" clean package org.codehaus.mojo:exec-maven-plugin:1.2.1:exec
```