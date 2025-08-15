package br.com.alura.challenge_LiterAlura.principal;

import br.com.alura.challenge_LiterAlura.service.ConsumoApi;

import java.util.Scanner;

public class Principal {

    private ConsumoApi consumo = new ConsumoApi();
    private Scanner leitura = new Scanner(System.in);
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

    private void ListarLivros() {
    }

    private void buscarLivroPeloTitulo() {
    }

    private void ListarAutores(){

    }

    private void ListarAutoresVivosPorAno(){

    }

    private void ListarLivrosPorIdioma(){

    }
}
