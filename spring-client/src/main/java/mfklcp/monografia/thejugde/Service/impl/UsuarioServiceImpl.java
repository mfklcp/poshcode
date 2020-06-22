/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Service.impl;

import mfklcp.monografia.thejugde.Domain.Usuario;
import mfklcp.monografia.thejugde.Repository.UsuarioRepository;
import mfklcp.monografia.thejugde.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    public Usuario save(Usuario usuario){
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Usuario getUsuarioAtual(Principal principal) {
        return findByEmail(principal.getName()).get();
    }
}
