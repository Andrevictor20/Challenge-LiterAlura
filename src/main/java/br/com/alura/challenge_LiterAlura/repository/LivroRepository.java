package br.com.alura.challenge_LiterAlura.repository;

import br.com.alura.challenge_LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro,Long> {

    List<Livro> findByIdiomasContaining(String idioma);
}
