/* Comandos para utilizar no Postgre.
   PS: Substituir *ListaDeUsuarios* pelo nome da tabela. */

create table ListaDeUsuarios(
    id SERIAL PRIMARY KEY,
    nome varchar(40),
    idade int,
    dataCadastro date)