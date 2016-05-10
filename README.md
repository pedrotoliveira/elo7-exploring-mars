# Exploring Mars
Teste de Programação Elo7

Descrição do Teste:
https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c

## Modelo
//TODO: Adicionar modelo.

## Minhas Suposições sobre a Solução
 - Workflow da Engine:  createSurface -> createExplorers -> registerInstructions -> executeInstructions.
 - Uma Sonda não pode ser colocada no mesmo lugar que outra.

## Project Modules:
 - mars-explorer-engine: Engine de criação e manipulação das sondas.
 - mars-explorer-api: API Rest para a engine. (WIP)

## Rodando a API da Engine via linha de comando:
```
cd mars-explorer-api
mvn spring-boot:run
```