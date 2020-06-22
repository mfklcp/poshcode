/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Service;

import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestaoService{
    Questao save(
            Questao questao,
            MultipartFile arquivo ,
            MultipartFile entrada,
            MultipartFile saida
    );

    Optional<Questao> findById(Long id);
    List<Questao> findByAutorOrderByIdDesc(Usuario autor);
    Questao findByUrlFile(String file);
    List<Questao> findAll();
}
