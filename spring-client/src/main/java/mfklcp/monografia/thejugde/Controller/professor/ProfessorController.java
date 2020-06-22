/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Controller.professor;

import mfklcp.monografia.thejugde.Domain.Questao;
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
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private QuestaoService questaoService;
    @Autowired
    private RespostaService respostaService;


   @RequestMapping("/")
    ModelAndView index(Principal principal){
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("questao", new Questao());
       modelAndView.addObject("user", usuarioService.getUsuarioAtual(principal) );
       modelAndView.addObject("questoes", questaoService.findByAutorOrderByIdDesc(usuarioService.getUsuarioAtual(principal)) );
       modelAndView.setViewName("professor/home");
       return modelAndView;
    }

    @RequestMapping("/respostas/{questaoFile}")
    ModelAndView respostas (
            @PathVariable("questaoFile") String questaoTitulo,
            Principal principal,
            ModelAndView modelAndView
    ){

       modelAndView.addObject("respostas",
               respostaService.findAllByAutorAndQuestao(
                       usuarioService.getUsuarioAtual(principal),
                       questaoService.findByUrlFile(questaoTitulo)
               )
    );
       return modelAndView;
    }


    @PostMapping(value = "/")
    String salvaQuestao(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("entradas") MultipartFile entradas,
            @RequestParam("saidas") MultipartFile saidas,
            Questao questao,
            Principal principal
    )
    {
        questao.setAutor( usuarioService.getUsuarioAtual(principal) );
        questaoService.save(questao,arquivo,entradas,saidas);
        return "redirect:/professor/";

    }



}
