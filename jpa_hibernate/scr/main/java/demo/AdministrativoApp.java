import java.sql.Date;
import java.util.List;
import entities.Pessoa;
import entities.Produto;
import models.PessoaModel;
import models.ProdutoModel;

public class AdministrativoApp {

    public static void main(String[] args) {
        ProdutoModel produtoModel = new ProdutoModel();
        PessoaModel pessoaModel = new PessoaModel();

        Produto p1 = new Produto();
        p1.setNome("TV");
        p1.setPreco(300.0);
        p1.setQuantidade(100);
        p1.setStatus(true);

        Produto p2 = new Produto();
        p2.setNome("Notebook");
        p2.setPreco(2000.0);
        p2.setQuantidade(50);
        p2.setStatus(true);

        // 1) Criando um produto
        produtoModel.create(p1);
        produtoModel.create(p2);

        //2) Buscando todos os produtos na base de dados
        List<Produto> produtos = produtoModel.findAll();
        System.out.println("Qtde de produtos encontrados: " + produtos.size());
        for (Produto prod : produtos) {
            System.out.println(prod);
        }

        Produto produto = produtoModel.findById(p1);
        System.out.println("Produto encontrado: " + produto);

        p1.setPreco(350.0);
        produtoModel.update(p1);
        Produto produtoAtualizado = produtoModel.findById(p1);
        System.out.println("Produto atualizado: " + produtoAtualizado);

        produtoModel.delete(p2);
        Produto produtoDeletado = produtoModel.findById(p2);
        System.out.println("Produto deletado (deve ser null): " + produtoDeletado);

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João");
        pessoa1.setEmail("joao@example.com");
        pessoa1.setIdade(30);
        pessoa1.setCpf("12345678901");
        pessoa1.setDataNascimento(Date.valueOf("1990-01-01"));

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria");
        pessoa2.setEmail("maria@example.com");
        pessoa2.setIdade(25);
        pessoa2.setCpf("98765432100");
        pessoa2.setDataNascimento(Date.valueOf("1995-05-15"));

        pessoaModel.create(pessoa1);
        pessoaModel.create(pessoa2);

        List<Pessoa> pessoas = pessoaModel.findAll();
        System.out.println("Qtde de pessoas encontradas: " + pessoas.size());
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }

        Pessoa pessoaEncontrada = pessoaModel.findById(pessoa1);
        System.out.println("Pessoa encontrada: " + pessoaEncontrada.getNome());

        pessoa1.setNome("João Silva");
        pessoaModel.update(pessoa1);
        Pessoa pessoaAtualizada = pessoaModel.findById(pessoa1);
        System.out.println("Pessoa atualizada: " + pessoaAtualizada.getNome());

        pessoaModel.delete(pessoa2);
        Pessoa pessoaDeletada = pessoaModel.findById(pessoa2);
        System.out.println("Pessoa deletada (deve ser null): " + pessoaDeletada);
    }
}