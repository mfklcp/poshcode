/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@SuppressWarnings("unused")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String nickname;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String senha;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.ALUNO;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.local;

    @OneToMany
    @JoinColumn( name = "id_autor")
    List<Questao> questaos;

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

    private String providerId;



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

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getSenha() {
        return senha;
    }

    public Role getRole() {
        return role;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public Date getCriadoEm() {
        return CriadoEm;
    }

    public Date getAtualizadoEm() {
        return AtualizadoEm;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public void setCriadoEm(Date criadoEm) {
        CriadoEm = criadoEm;
    }

    public void setAtualizadoEm(Date atualizadoEm) {
        AtualizadoEm = atualizadoEm;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<Questao> getQuestaos() {
        return questaos;
    }

    public void setQuestaos(List<Questao> questaos) {
        this.questaos = questaos;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", provider=" + provider +
                '}';
    }

}
