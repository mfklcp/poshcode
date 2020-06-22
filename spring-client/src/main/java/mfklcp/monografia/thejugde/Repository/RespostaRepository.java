/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Repository;

import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Resposta;
import mfklcp.monografia.thejugde.Domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface RespostaRepository extends CrudRepository<Resposta,Long> {
    List<Resposta> findAllByQuestao(Questao questao);
    List<Resposta> findByAutorOrderByIdDesc(Usuario usuario);
    List<Resposta> findAllByAutorAndQuestao(Usuario usuario,Questao questao);

}



