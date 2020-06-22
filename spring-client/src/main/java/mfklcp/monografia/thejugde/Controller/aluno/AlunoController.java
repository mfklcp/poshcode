/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Controller.aluno;


import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Resposta;
import mfklcp.monografia.thejugde.Domain.Usuario;
import mfklcp.monografia.thejugde.Service.QuestaoService;
import mfklcp.monografia.thejugde.Service.RespostaService;
import mfklcp.monografia.thejugde.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private QuestaoService questaoService;
    @Autowired
    private RespostaService respostaService;


    @RequestMapping("/")
    ModelAndView index(Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        Usuario usuario = usuarioService.getUsuarioAtual(principal);

        modelAndView.addObject("user", usuario);
        modelAndView.addObject("questoes",questaoService.findAll());
        modelAndView.addObject("respostas",respostaService.findByAutorOrderByIdDesc(usuario));
        modelAndView.setViewName("aluno/home");
        return modelAndView;
    }

    @RequestMapping("/questao/{questaoFile}")
    ModelAndView questao(
            @PathVariable("questaoFile") String questaoTitulo,
            Principal principal,
            ModelAndView modelAndView){

        Questao questao = questaoService.findByUrlFile(questaoTitulo);
        modelAndView.addObject("resposta",new Resposta());
        modelAndView.addObject("user",  usuarioService.getUsuarioAtual(principal)  );
        modelAndView.addObject("questao",questao );
        modelAndView.setViewName("aluno/questao");
        return modelAndView;
    }

    @PostMapping("/resposta")
    String responder( @RequestParam("file_resposta") MultipartFile arquivo,@RequestParam("questaoId") Long questaoId,Principal principal)
    {
        respostaService.save(questaoId, usuarioService.getUsuarioAtual(principal) , arquivo);

        return "redirect:/aluno/";

    }

}
