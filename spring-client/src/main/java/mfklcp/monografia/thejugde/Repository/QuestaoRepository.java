/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Repository;

import mfklcp.monografia.thejugde.Domain.Questao;
import mfklcp.monografia.thejugde.Domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends CrudRepository<Questao,Long> {

    
    List<Questao> findByAutorOrderByIdDesc(Usuario autor);
    Questao findByUrlFile(String file);

}
