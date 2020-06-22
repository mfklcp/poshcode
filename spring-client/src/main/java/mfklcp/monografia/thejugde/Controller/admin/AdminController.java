/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Controller.admin;

import mfklcp.monografia.thejugde.Domain.Usuario;
import mfklcp.monografia.thejugde.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired //n precisar instanciar metodo
    private UsuarioService usuarioService;


    @RequestMapping("/")
    String index(){
        return "admin/registration"; //retorna template
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(
            ModelAndView modelAndView
    ){
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("usuarios", usuarioService.findAll());
        modelAndView.setViewName("admin/registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(
            Usuario user,
            ModelAndView modelAndView
    ) {
        usuarioService.save(user);
        modelAndView.addObject("successMessage", "User has been registered successfully");
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("usuarios", usuarioService.findAll());
        modelAndView.setViewName("admin/registration");

        return modelAndView;
    }

}
