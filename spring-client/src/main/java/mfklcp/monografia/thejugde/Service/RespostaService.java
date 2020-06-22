/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Service;

import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Resposta;
import mfklcp.monografia.thejugde.Domain.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface RespostaService {
    Resposta save(Long questaoId, Usuario usuario ,MultipartFile arquivo);
    Optional<Resposta> findById(Long id);
    List<Resposta> findAllByQuestao(Questao questao);
    List<Resposta> findByAutorOrderByIdDesc(Usuario usuario);
    List<Resposta> findAllByAutorAndQuestao(Usuario usuario,Questao questao);
}
