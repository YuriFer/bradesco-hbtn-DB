# Sistema Administrativo com JPA/Hibernate

Olá! 👋 Este é um projeto de estudo que desenvolvi para aprender sobre persistência de dados em Java usando JPA (Java Persistence API) e Hibernate. É um sistema bem simples, mas que demonstra os conceitos fundamentais de mapeamento objeto-relacional.

## O que este projeto faz?

Basicamente, é um sistema administrativo que permite gerenciar duas entidades principais:
- **Pessoas**: com informações como nome, email, idade, CPF e data de nascimento
- **Produtos**: com nome, quantidade, preço e status

O legal é que tudo fica persistido em um banco SQLite, então os dados não se perdem quando você fecha a aplicação.

## Por que criei isso?

Este projeto nasceu como uma forma de:
- Entender como funciona o JPA e Hibernate na prática
- Aprender a mapear classes Java para tabelas de banco de dados
- Praticar operações CRUD (Create, Read, Update, Delete)
- Trabalhar com SQLite, que é super leve e não precisa de instalação

## Tecnologias utilizadas

- **Java 17**: A linguagem principal
- **Maven**: Para gerenciar dependências e build
- **JPA/Hibernate**: Framework ORM para persistência
- **SQLite**: Banco de dados leve e embutido
- **SQLite Dialect**: Para compatibilidade do Hibernate com SQLite

## Como funciona?

O projeto está organizado de forma bem simples:

### Entidades (`entities/`)
- `Pessoa.java`: Representa uma pessoa com todos os campos mapeados
- `Produto.java`: Representa um produto do sistema

### Models (`models/`)
- `PessoaModel.java`: Contém todas as operações CRUD para pessoas
- `ProdutoModel.java`: Contém todas as operações CRUD para produtos

### Aplicação Principal
- `AdministrativoApp.java`: Onde tudo acontece! Demonstra como usar todas as funcionalidades

## Como executar?

1. **Pré-requisitos**: Você precisa ter o Java 17 e Maven instalados

2. **Clone ou baixe o projeto**

3. **Compile e execute**:
   ```bash
   cd jpa_hibernate
   mvn clean compile
   mvn exec:java -Dexec.mainClass="AdministrativoApp"
   ```

4. **Ou se preferir**, você pode compilar e executar diretamente:
   ```bash
   mvn clean package
   java -jar target/administrativo-api-1.0-SNAPSHOT.jar
   ```

## O que você vai ver rodando?

Quando executar a aplicação, ela vai:
- Criar algumas pessoas e produtos de exemplo
- Salvar tudo no banco de dados SQLite
- Buscar e exibir os dados salvos
- Fazer algumas atualizações
- Deletar alguns registros
- Mostrar os resultados de cada operação

Tudo com bastante log no console para você acompanhar o que está acontecendo!

## Estrutura do banco

O Hibernate cria automaticamente as tabelas `pessoa` e `produto` no arquivo `database_admin.db`. Você pode abrir este arquivo com qualquer cliente SQLite se quiser dar uma olhada nos dados.

## Aprendizados

Trabalhando neste projeto, aprendi:
- Como configurar o `persistence.xml` corretamente
- A diferença entre `GenerationType.IDENTITY` e `GenerationType.AUTO` (tive uns problemas com SQLite 😅)
- Como gerenciar transações adequadamente
- A importância do tratamento de exceções em operações de banco
- Como estruturar um projeto Java com Maven

## Possíveis melhorias

Se eu fosse continuar desenvolvendo este projeto, algumas coisas que faria:
- Adicionar validações nos campos (CPF válido, email válido, etc.)
- Criar uma interface web ou pelo menos um menu interativo
- Implementar relacionamentos entre as entidades (ex: uma pessoa pode ter vários produtos favoritos)
- Adicionar testes unitários
- Usar um banco mais robusto como PostgreSQL ou MySQL

## Considerações finais

Este é um projeto de estudo, então o código pode não estar perfeito para produção, mas serve muito bem para entender os conceitos básicos de JPA/Hibernate. Se você está começando a estudar essas tecnologias, espero que este exemplo seja útil!

Qualquer dúvida ou sugestão, fica à vontade para dar um feedback! 🚀

---

*Projeto desenvolvido como parte dos estudos em Java e persistência de dados.*
