package br.com.usuario.aplication;

import br.com.usuario.dao.UsuarioDAO;
import br.com.usuario.model.Usuario;

import java.sql.SQLException;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws SQLException {


        /*┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                      Cadastramento de usuário
                               CREATE
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛*/

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setNome("Arnaldo Fagundes");
        usuario.setIdade(22);
        usuario.setDataCadastro(new Date());
        //usuarioDAO.save(usuario); //        <-- Comando que salva tudo



        /*┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
               Visualização de todos os registros do BD
                               READ
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛*/

        for(Usuario u : UsuarioDAO.getUsuarios()) {
            System.out.println("Usuário: "+ u.getNome());
        }



        /*┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    Atualizar cadastro do usuário
                               UPDATE
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛*/

        Usuario u1 = new Usuario();
        u1.setNome("Novo nome");
        u1.setIdade(00);
        u1.setDataCadastro(new Date());
        u1.setId(1); //                     <-- É o número que está no banco de dados da Primary Key
       // usuarioDAO.update((u1)); //         <-- Comando que atualiza tudo



        /*┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    Deletar o cadastro do usuário
                               DELETE
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛*/

        //usuarioDAO.deleteByID(1); //      <-- Comando que deleta pelo ID (Primary Key)
    }
}
