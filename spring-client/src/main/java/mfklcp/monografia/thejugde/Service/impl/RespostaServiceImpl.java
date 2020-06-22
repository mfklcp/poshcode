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

import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Resposta;
import mfklcp.monografia.thejugde.Domain.Usuario;
import mfklcp.monografia.thejugde.Repository.RespostaRepository;
import mfklcp.monografia.thejugde.Service.QuestaoService;
import mfklcp.monografia.thejugde.Service.RespostaService;
import mfklcp.monografia.thejugde.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaServiceImpl implements RespostaService {

    @Autowired
    private RespostaRepository repository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private QuestaoService questaoService;

    @Override
    public Resposta save(Long questaoId, Usuario usuario ,MultipartFile arquivo) {

        Resposta resposta = new Resposta();
        Questao questao = questaoService.findById(questaoId).get();

        String fileName = questao.getUrlFile()+
                "_por_" +
                usuario.getEmail()+"_"+
                new Timestamp(System.currentTimeMillis());

        try {
            storageService.store(arquivo, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resposta.setUrl_code(fileName);
        resposta.setOriginalName(arquivo.getOriginalFilename());
        resposta.setAutor(usuario);
        resposta.setQuestao(questao);
        repository.save(resposta);

        RestTemplate restTemplate = new RestTemplate();
        new Thread(() -> restTemplate.getForObject("http://poshcode.com.br:8000/api/solve/"+resposta.getId(),Object.class)).start(); //chamar projeto python
        return resposta;
    }

    @Override
    public Optional<Resposta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Resposta> findAllByQuestao(Questao questao) {
        return repository.findAllByQuestao(questao);
    }

    @Override
    public List<Resposta> findByAutorOrderByIdDesc(Usuario usuario) {
        return repository.findByAutorOrderByIdDesc(usuario);
    }

    @Override
    public List<Resposta> findAllByAutorAndQuestao(Usuario usuario, Questao questao) {
        return repository.findAllByAutorAndQuestao(usuario,questao);
    }
}
