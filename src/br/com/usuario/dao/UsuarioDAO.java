package br.com.usuario.dao;

import br.com.usuario.factory.ConnectionFactory;
import br.com.usuario.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static br.com.usuario.factory.ConnectionFactory.createConnectionToPostgres;

public class UsuarioDAO {


        /*┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                 CRUD (Create, Read, Update e Delete em Inglês)
                 é uma sigla utilizada para se referir às quatro
                 operações básicas realizadas em banco de dados
                relacionais que são consulta, inclusão, alteração
                            e exclusão dos registros.
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛*/


    // ──────────▹ Create
    public void save(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO ListaDeUsuarios(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // Cria uma conexão com o banco de dados
            try {
                conn = createConnectionToPostgres();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            // Cria uma PreparedStatement (forma para executar uma Query no SQL mais segura)
            assert conn != null;
            pstm = conn.prepareStatement(sql);


            // Adiciona os valores que são esperados pela Query
            pstm.setString(1, usuario.getNome());
            pstm.setInt(2, usuario.getIdade());
            pstm.setDate(3, new Date(usuario.getDataCadastro().getTime()));


            // Executa a Query
            pstm.execute();


        }finally {

            // Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            }catch (Exception e){
                   e.printStackTrace();
            }
        }
    }




    // ──────────▹ Read
    public static List<Usuario> getUsuarios(){

        String sql = "SELECT * FROM ListaDeUsuarios";

        List<Usuario> usuarios = new ArrayList<Usuario>();

        Connection conn = null;
        PreparedStatement pstm = null;

        // Classe que vai recuperar os dados do banco (SELECT)
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToPostgres();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()){
                Usuario usuario = new Usuario();

                // Recupera o ID
                usuario.setId(rset.getInt("id"));
                // Recupera o nome
                usuario.setNome(rset.getString("nome"));
                // Recupera a idade
                usuario.setId(rset.getInt("idade"));
                // Recupera a data de cadastro
                usuario.setDataCadastro(rset.getDate("dataCadastro"));

                usuarios.add(usuario); //           <-- Comando para adicionar o usuário na lista
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if(rset!=null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            return usuarios;
        }
    }




    // ──────────▹ Update
    public void update(Usuario usuario){
        String sql = "UPDATE ListaDeUsuarios SET nome = ?, idade = ?, dataCadastro = ?"+
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            // Cria conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToPostgres();

            // Cria a classe para executar a Query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            // Adiciona os valores para atualizar
            pstm.setString(1, usuario.getNome());
            pstm.setInt(2, usuario.getIdade());
            pstm.setDate(3, new Date(usuario.getDataCadastro().getTime()));

            // ID de registro que deseja atualizar
            pstm.setInt(4, usuario.getId());

            // Executa a Query
            pstm.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }




    // ──────────▹ Delete
    public void deleteByID(int id) {
        String sql = "DELETE FROM ListaDeUsuarios WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToPostgres();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setInt(1, id);

            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(pstm != null) {
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
