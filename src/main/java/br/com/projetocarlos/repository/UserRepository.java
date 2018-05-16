package br.com.projetocarlos.repository;

import br.com.projetocarlos.controlador.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository {

    private Connection connection;

    public UserRepository() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetocarlos", "root", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> buscarTodos() {
        try {
            String sql = "select * from user;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<User> list = new ArrayList<>();
            User user = null;
            while (rs.next()) {
                Long id = rs.getLong("id");
                String usuario = rs.getString("usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                user = new User(id, usuario, email, senha);
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public User salvar(User user) {
        try {
            String sql = "insert into usere(usuario, email) values(?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getEmail());
            ps.execute();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            return user;
        }
    }

    public boolean exluirUserPeloId(Long id) {
        try {
            String sql = "delete from user where id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public User buscarPeloId(Long id) {
        try {
            String sql = "select * from user where id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long idTemp = rs.getLong("id");
                String usuario = rs.getString("usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                return new User(idTemp, usuario, email, senha);
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User buscaPorEmailESenha(User user) {
        try {
            String sql = "select * from user where email = ? and senha = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getSenha());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String usuario = rs.getString("usuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                return new User(id, usuario, email, senha);
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
