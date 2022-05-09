package br.com.usuario.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String USERNAME = "postgres"; //   <-- Nome do usuário no Postgres (padrão)
    private static final String PASSWORD = ""; //           <-- Senha do banco de dados (se houver)


    //Caminho do banco de dados, porta, nome do banco de dados
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/DB_Certificados"; //    <-- Substituir */DB_Certificados* pelo caminho do BD

    public static Connection createConnectionToPostgres() throws SQLException, ClassNotFoundException { //    <-- Conexão com o BD
        Class.forName("org.postgresql.Driver"); //    <-- Permite a classe ser carregada pela JVM
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); //      <-- Permite a conexão com o BD
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = createConnectionToPostgres(); //       <-- Recupera a conexão com o BD

        if(conn!=null){ //          <-- Testa se a conexão é NÃO nula
            System.out.println("Conexão obtida com sucesso!"); //          <-- Mensagem recebida caso for positivo
            conn.close();
        }
    }
}
