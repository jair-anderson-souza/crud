/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetocarlos.service;

import br.com.projetocarlos.controlador.domain.User;
import br.com.projetocarlos.repository.UserRepository;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public List<User> listar() {
        return this.userRepository.buscarTodos();
    }

    public User save(User user) {
        return this.userRepository.salvar(user);
    }

    public boolean delete(Long id) {
        return this.userRepository.exluirUserPeloId(id);
    }

    public User editar(Long id) {
        return this.userRepository.buscarPeloId(id);
    }

    public User logar(User user) {
        return this.userRepository.buscaPorEmailESenha(user);
    }
}
