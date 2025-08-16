package br.com.alura.challenge_LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @Column(unique = true)
    private String nome;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Livro> livros;

    public Autores(){}
    public Autores(DadosAutores dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    // ... (dentro da classe Autores)

    // Versão alternativa mantendo os detalhes
    @Override
    public String toString() {
        return nome + " (Nasc: " + anoNascimento + ", Falec: " + anoFalecimento + ")";
    }
}
