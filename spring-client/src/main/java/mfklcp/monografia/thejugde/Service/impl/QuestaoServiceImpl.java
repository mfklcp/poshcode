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

import com.github.slugify.Slugify;
import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Usuario;
import mfklcp.monografia.thejugde.Repository.QuestaoRepository;
import mfklcp.monografia.thejugde.Service.QuestaoService;
import mfklcp.monografia.thejugde.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class QuestaoServiceImpl implements QuestaoService {

    @Autowired
    private QuestaoRepository repository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private Slugify slugify;


    @Override
    public Questao save(Questao questao, MultipartFile arquivo, MultipartFile entradas, MultipartFile saidas) {

        System.out.println(questao);

        questao.setUrlFile(slugify.slugify(questao.getTitulo()+"_entradas"));
        questao.setUrlSaidas(slugify.slugify(questao.getTitulo()+"_saidas"));
        questao.setUrlEntradas(slugify.slugify(questao.getTitulo()));

        try {
            storageService.store(entradas,questao.getUrlEntradas());
            storageService.store(saidas,questao.getUrlSaidas());
            storageService.store(arquivo,questao.getUrlFile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return repository.save(questao);
    }

    @Override
    public Optional<Questao> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<Questao> findByAutorOrderByIdDesc(Usuario autor) {
        return (List<Questao>) repository.findByAutorOrderByIdDesc(autor);
    }

    @Override
    public Questao findByUrlFile(String file) {
        return repository.findByUrlFile(file);
    }

    @Override
    public List<Questao> findAll() {
        return (List<Questao>) repository.findAll();
    }

}
