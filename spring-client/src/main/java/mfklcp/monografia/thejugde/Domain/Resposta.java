/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIME;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @ManyToOne
    @JoinColumn( name = "id_resposta")
    Questao questao;


    @Column( nullable = false )
    String url_code;

    @Column( nullable = true )
    String originalName;

    @ManyToOne
    @JoinColumn( name = "id_autor" )
    Usuario autor;

    
    Boolean correta;

    @Temporal(TIME)
    Date tempoDeExecucao;

    String tempoDeExecStr;

    String retorno;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "criado_em", nullable = false)
    private Date CriadoEm;

    @PrePersist
    protected void prePersist() {
        if (this.CriadoEm == null) CriadoEm = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public String getUrl_code() {
        return url_code;
    }

    public void setUrl_code(String url_code) {
        this.url_code = url_code;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Boolean getCorreta() {
        return correta;
    }

    public void setCorreta(Boolean correta) {
        this.correta = correta;
    }

    public Date getTempoDeExecucao() {
        return tempoDeExecucao;
    }

    public void setTempoDeExecucao(Date tempoDeExecucao) {
        this.tempoDeExecucao = tempoDeExecucao;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }


    public Date getCriadoEm() {
        return CriadoEm;
    }

    public String getTempoDeExecStr() {
        return tempoDeExecStr;
    }

    public void setTempoDeExecStr(String tempoDeExecStr) {
        this.tempoDeExecStr = tempoDeExecStr;
    }

    public void setCriadoEm(Date criadoEm) {
        CriadoEm = criadoEm;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "id=" + id +
                ", questao=" + questao +
                ", url_code='" + url_code + '\'' +
                ", autor=" + autor +
                ", correta=" + correta +
                ", tempoDeExecucao=" + tempoDeExecucao +
                ", retorno='" + retorno + '\'' +
                ", CriadoEm=" + CriadoEm +
                '}';
    }
}
