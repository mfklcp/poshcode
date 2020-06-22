/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class Questao {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String titulo;
    String corpo;

    String urlEntradas;
    String urlSaidas;

    String urlFile;

    @ManyToOne
    @JoinColumn( name = "id_autor")
    Usuario autor;

    @OneToMany
    @JoinColumn( name = "id_resposta" )
    List<Resposta> respostas;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "criado_em", nullable = false)
    private Date CriadoEm;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "atualizado_em")
    private Date AtualizadoEm;

    @PrePersist
    protected void prePersist() {
        if (this.CriadoEm == null) CriadoEm = new Date();
        if (this.AtualizadoEm == null) AtualizadoEm = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.AtualizadoEm = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }


    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getCriadoEm() {
        return CriadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        CriadoEm = criadoEm;
    }

    public Date getAtualizadoEm() {
        return AtualizadoEm;
    }

    public void setAtualizadoEm(Date atualizadoEm) {
        AtualizadoEm = atualizadoEm;
    }

    public String getUrlEntradas() {
        return urlEntradas;
    }

    public void setUrlEntradas(String urlEntradas) {
        this.urlEntradas = urlEntradas;
    }

    public String getUrlSaidas() {
        return urlSaidas;
    }

    public void setUrlSaidas(String urlSaidas) {
        this.urlSaidas = urlSaidas;
    }

    @Override
    public String toString() {
        return "Questao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", corpo='" + corpo + '\'' +
                ", urlEntradas='" + urlEntradas + '\'' +
                ", urlSaidas='" + urlSaidas + '\'' +
                ", urlFile='" + urlFile + '\'' +
                ", autor=" + autor +
                ", respostas=" + respostas +
                ", CriadoEm=" + CriadoEm +
                ", AtualizadoEm=" + AtualizadoEm +
                '}';
    }
}
