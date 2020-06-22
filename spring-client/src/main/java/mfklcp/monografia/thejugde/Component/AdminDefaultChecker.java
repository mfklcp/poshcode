/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Component;

import mfklcp.monografia.thejugde.Domain.AuthProvider;
import mfklcp.monografia.thejugde.Domain.Role;
import mfklcp.monografia.thejugde.Domain.Usuario;
import mfklcp.monografia.thejugde.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminDefaultChecker implements CommandLineRunner {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {


        Optional<Usuario> admin = usuarioService.findByEmail("adm@poshcode.com.br");

        if(!admin.isPresent()) {
            System.out.println("Começando a criar ADMIN Default...");
            Usuario adminDefault = new Usuario();

            adminDefault.setNome("Admin");
            adminDefault.setSenha("admin123");
            adminDefault.setEmail("adm@poshcode.com.br");
            adminDefault.setNickname("Sudo");
            adminDefault.setSobrenome("PoshCode");
            adminDefault.setRole(Role.ADMIN);
            adminDefault.setProvider(AuthProvider.local);

            usuarioService.save(adminDefault);
            System.out.println("ADMIN Default criado com sucesso");
        }
    }
}
