# Documentação de referencia
Aplicação criada com a finalidade de cadastrar usuários e veículos para um sistema de estacionamento digital

### Adicionar usuário

curl --location --request POST 'http://localhost:8080/usuarios' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome":"TIM DUNCAN",
    "cpfCnpj":"637.984.330-21",
    "email":"tim_duncan@test.com",
    "senha":"kZ^QKTkux3#l"
    
}'

### Listar usuários

curl --location --request GET 'http://localhost:8080/usuarios'

### Editar usuário

curl --location --request PATCH 'http://localhost:8080/usuarios/0e8bae11-5a9e-40ab-bca1-b708d258bc3c' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome":"TIN DUNCAN ALTERADO",
    "senha":"senhaAlterada
    
}'

### Adicionar veículo no usuário

curl --location --request POST 'http://localhost:8080/usuarios/ae1a7d42-0e81-4d5e-a543-4da3485b1d59/veiculos' \
--header 'Content-Type: application/json' \
--data-raw '{
    "placa":"GFA-2342",
    "modelo":"Fiat",
    "favorito":false,
    "tipoVeiculo":"CARRO"
    
}'

### Editar veículo do usuário

curl --location --request PATCH 'http://localhost:8080/usuarios/ae1a7d42-0e81-4d5e-a543-4da3485b1d59/veiculos/07bd73db-2a94-4a44-a37a-5edbccb96709' \
--header 'Content-Type: application/json' \
--data-raw '{
    "placa":"GFA-2342",
    "modelo":"Fiat",
    "favorito":false,
    "tipoVeiculo":"CARRO"
    
}'


