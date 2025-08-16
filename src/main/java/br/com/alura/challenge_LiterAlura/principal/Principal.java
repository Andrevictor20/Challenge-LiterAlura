package br.com.alura.challenge_LiterAlura.principal;

import br.com.alura.challenge_LiterAlura.model.DadosLivro;
import br.com.alura.challenge_LiterAlura.model.DadosResultados;
import br.com.alura.challenge_LiterAlura.model.Livro;
import br.com.alura.challenge_LiterAlura.repository.LivroRepository;
import br.com.alura.challenge_LiterAlura.service.ConsumoApi;
import br.com.alura.challenge_LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private ConsumoApi consumo = new ConsumoApi();
    private Scanner leitura = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();
    private List<Livro> livros = new ArrayList<>();
    private List<DadosLivro> dadosLivros= new ArrayList<>();
    private String ENDERECO = "https://gutendex.com/books?search=";
    private final LivroRepository repositorio;

    @Autowired
    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao=-1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros em determinado idioma
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    ListarLivros();
                    break;
                case 3:
                    ListarAutores();
                    break;
                case 4:
                    ListarAutoresVivosPorAno();
                    break;
                case 5:
                    ListarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private Optional<DadosLivro> getDadosLivro() {
        System.out.println("Digite o nome do livro para a busca: ");
        var nomeLivro = leitura.nextLine();
        var enderecoBusca = ENDERECO + nomeLivro.replace(" ", "%20");
        System.out.println("URL da busca: " + enderecoBusca);

        var json = consumo.obterDados(enderecoBusca);
        DadosResultados dadosResultados = conversor.obterDados(json, DadosResultados.class);
        return dadosResultados.resultados().stream().findFirst();
    }

    private void buscarLivroPeloTitulo() {
        Optional<DadosLivro> dadosLivroOpcional = getDadosLivro();
        if (dadosLivroOpcional.isPresent()) {
            DadosLivro dadosLivro = dadosLivroOpcional.get();
            Livro livro = new Livro(dadosLivro);
            repositorio.save(livro);
            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro); // Imprime os dados do livro salvo
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }


    private void ListarLivros() {
    }



    private void ListarAutores(){

    }

    private void ListarAutoresVivosPorAno(){

    }

    private void ListarLivrosPorIdioma(){

    }
}
