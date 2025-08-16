package br.com.alura.challenge_LiterAlura.principal;

import br.com.alura.challenge_LiterAlura.model.Autores;
import br.com.alura.challenge_LiterAlura.model.DadosLivro;
import br.com.alura.challenge_LiterAlura.model.DadosResultados;
import br.com.alura.challenge_LiterAlura.model.Livro;
import br.com.alura.challenge_LiterAlura.repository.AutoresRepository;
import br.com.alura.challenge_LiterAlura.repository.LivroRepository;
import br.com.alura.challenge_LiterAlura.service.ConsumoApi;
import br.com.alura.challenge_LiterAlura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    private final LivroRepository livroRepositorio;
    private final AutoresRepository autoresRepositoio;
    private ConsumoApi consumo = new ConsumoApi();
    private Scanner leitura = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();
    private List<Livro> livros = new ArrayList<>();
    private List<Autores> autores = new ArrayList<>();
    private List<DadosLivro> dadosLivros= new ArrayList<>();
    private String ENDERECO = "https://gutendex.com/books?search=";

    @Autowired
    public Principal(LivroRepository livroRepositorio, AutoresRepository autoresRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autoresRepositoio = autoresRepositorio;
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

    private Optional<DadosLivro> getDadosLivroDaApi() {
        System.out.println("Digite o nome do livro para a busca: ");
        var nomeLivro = leitura.nextLine();
        var enderecoBusca = ENDERECO + nomeLivro.replace(" ", "%20");
        System.out.println("URL da busca: " + enderecoBusca);

        var json = consumo.obterDados(enderecoBusca);

        DadosResultados resultados = conversor.obterDados(json, DadosResultados.class);

        return resultados.resultados().stream().findFirst();
    }

    private void buscarLivroPeloTitulo() {
        Optional<DadosLivro> dadosLivroOpcional = getDadosLivroDaApi();

        if (dadosLivroOpcional.isPresent()) {
            DadosLivro dadosDoLivro = dadosLivroOpcional.get();

            Livro livro = new Livro(dadosDoLivro);

            livroRepositorio.save(livro);


            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }

    private void ListarLivros() {
        System.out.println("Livros cadastrados: ");
        livros = livroRepositorio.findAll();
        livros.stream().forEach(System.out::println);
    }

    private void ListarAutores(){
        System.out.println("Autores cadastrados: ");
        autores = autoresRepositoio.findAll();
        autores.stream().forEach(System.out::println);

    }

    private void ListarAutoresVivosPorAno(){
        System.out.println("Digite o ano que deseja verificar: ");
        var ano = leitura.nextInt();

        List<Autores> autoresVivos = autoresRepositoio
                .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqualOrAnoFalecimentoIsNull(ano, ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo no ano " + ano);
        } else {
            autoresVivos.forEach(System.out::println);
        }

    }

    private void ListarLivrosPorIdioma(){
        System.out.println("Digite o idioma (pt,en,fr,es,etc.) do livro : ");
        var idioma = leitura.nextLine();
        livros = livroRepositorio.findByIdiomasContaining(idioma);
        if(livros.isEmpty()){
            System.out.println("Não há livros cadastrados com esse idioma");
        }else{
            livros.stream().forEach(System.out::println);
        }

    }
}
