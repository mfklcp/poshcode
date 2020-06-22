/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Service;

import mfklcp.monografia.thejugde.Domain.Usuario;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    Usuario getUsuarioAtual(Principal principal);
}
