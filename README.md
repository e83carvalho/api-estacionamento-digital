# Documenta��o de referencia
Aplica��o criada com a finalidade de cadastrar usu�rios e ve�culos para um sistema de estacionamento digital

### Adicionar usu�rio

curl --location --request POST 'http://localhost:8080/usuarios' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome":"TIM DUNCAN",
    "cpfCnpj":"637.984.330-21",
    "email":"tim_duncan@test.com",
    "senha":"kZ^QKTkux3#l"
    
}'

### Listar usu�rios

curl --location --request GET 'http://localhost:8080/usuarios'

### Editar usu�rio

curl --location --request PATCH 'http://localhost:8080/usuarios/0e8bae11-5a9e-40ab-bca1-b708d258bc3c' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome":"TIN DUNCAN ALTERADO",
    "senha":"senhaAlterada
    
}'

### Adicionar ve�culo no usu�rio

curl --location --request POST 'http://localhost:8080/usuarios/ae1a7d42-0e81-4d5e-a543-4da3485b1d59/veiculos' \
--header 'Content-Type: application/json' \
--data-raw '{
    "placa":"GFA-2342",
    "modelo":"Fiat",
    "favorito":false,
    "tipoVeiculo":"CARRO"
    
}'

### Editar ve�culo do usu�rio

curl --location --request PATCH 'http://localhost:8080/usuarios/ae1a7d42-0e81-4d5e-a543-4da3485b1d59/veiculos/07bd73db-2a94-4a44-a37a-5edbccb96709' \
--header 'Content-Type: application/json' \
--data-raw '{
    "placa":"GFA-2342",
    "modelo":"Fiat",
    "favorito":false,
    "tipoVeiculo":"CARRO"
    
}'


