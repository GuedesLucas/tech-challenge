# TechChallenge API

# Instruções

1. Certifique-se de possuir o docker e o docker-compose instalado
2. Copie o .env.example e mude o nome para .env
3. O conteudo do exemplo pode ser modificado mas tambem irá funcionar com os valores pré definidos
4. Rode o comando `docker compose build` para realizar o build das imagens
5. Para rodar a aplicação utiliza o comando `docker compose up -d`

```
Obs: os dados do banco ficarão armazenados na pasta ignorada pelo git chamada `data`, certifique de deleta-la caso altere alguma variável relativa ao banco. 
```

## Acesso ao PgAdmin

1. Abra um navegador web.
2. Acesse o PgAdmin em `http://localhost:15432/`.
3. Use as credenciais no .env para fazer o login

```
Obs: o host do banco deverá ser de acordo com o nome do container nesse caso usamos 'postgres'
```

## Acesso à Documentação Swagger

1. Após iniciar o projeto, acesse a documentação Swagger em `http://localhost:8080/swagger-ui/index.html`.
2. Você verá a documentação interativa da API, que inclui detalhes sobre todos os endpoints e parâmetros.

```
Obs: haverá alteração na porta da aplicação caso mude a porta no arquivo `.env`
```
