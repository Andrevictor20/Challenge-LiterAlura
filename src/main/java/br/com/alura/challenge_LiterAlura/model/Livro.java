package br.com.alura.challenge_LiterAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autores> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idioma", nullable = false)
    private List<String> idiomas;

    private Integer downloads;

    public Livro(){}
    public Livro(DadosLivro dados){
        this.titulo = dados.titulo();
        this.idiomas = dados.idiomas();
        this.downloads = dados.downloads();
        if (dados.autores() != null && !dados.autores().isEmpty()) {
            this.autores = dados.autores().stream()
                    .map(Autores::new)
                    .collect(Collectors.toList());
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Autores> getAutores() {
        return autores;
    }

    public void setAutores(List<Autores> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }


    @Override
    public String toString() {
        String detalhesAutores = (autores != null && !autores.isEmpty()) ?
                autores.stream()
                        .map(Autores::toString) // 1
                        .collect(Collectors.joining("\n             ")) :
                "Autor desconhecido";

        return """
           ----- LIVRO -----
           Título: %s
           Autor(es): %s
           Idioma(s): %s
           Downloads: %d
           -----------------
           """.formatted(titulo, detalhesAutores, idiomas, downloads);
    }


}
