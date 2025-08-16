package br.com.alura.challenge_LiterAlura.repository;

import br.com.alura.challenge_LiterAlura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoresRepository extends JpaRepository<Autores, Long> {
    List<Autores> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(Integer anoNascimento, Integer anoFalecimento);
}
