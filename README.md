# Exploring Mars
Teste de Programação Elo7

Descrição do Teste:
https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c

## Modelo
//TODO: Adicionar modelo.

## Minhas Suposições Sobre a Solução
 - Uma Sonda deve ser colocada dentro dos limites da Superfície
 - Uma Sonda não pode ser colocada no mesmo lugar que outra. Caso isso aconteça será lançada uma Exception.
 - Quando uma Sonda encontrar um obstáculo ela marcará aquela instrução como "failed" e passará para a próxima.
 - As sondas primeiro são "instaladas" para depois executar as instruções em sequencia, seguindo a ordem de sua instalação.
 - Workflow da Engine:  createSurface -> createExplorers -> registerInstructions -> executeInstructions.
 

## Project Modules:
 - mars-explorer-engine: Engine de criação e manipulação das sondas.
 - mars-explorer-api: API Rest para a engine. (WIP)

### WADL
http://localhost:8080/mars-explorer/api/application.wadl
http://localhost:8080/mars-explorer/api/application.wadl?detail=true

### Rodando a API da Engine via linha de comando:
```
cd mars-explorer-api
mvn spring-boot:run
```