package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class MongoDBConnection {

    // Variáveis de configuração
    private static final String USERNAME = "yurifernandes2502_db_user";
    private static final String PASSWORD = "Ou63PF28JGw0e33w";
    private static final String CLUSTER_URL = "devcluster.co8295f.mongodb.net"; // Substitua pelo seu cluster se for diferente
    private static final String DATABASE_NAME = "DevCluster"; // Substitua pelo nome do seu banco de dados

    private MongoClient mongoClient;
    private MongoDatabase database;


    public MongoDBConnection() {
        try {
            // String de conexão com credenciais
            String connectionString = String.format("mongodb+srv://"+USERNAME+":"+PASSWORD+"@"+CLUSTER_URL+"/?retryWrites=true&w=majority&appName="+DATABASE_NAME);


            // Configuração do cliente MongoDB
            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();


            // Criando o cliente
            mongoClient = MongoClients.create(settings);


            // Selecionando o banco de dados
            database = mongoClient.getDatabase(DATABASE_NAME);


            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public MongoDatabase getDatabase() {
        return database;
    }


    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão encerrada com sucesso.");
        }
    }


    public static void main(String[] args) {
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        UsuarioOperations usuarioOperation = new UsuarioOperations(mongoDBConnection);

        if(usuarioOperation.getAllUsuarios().isEmpty()) {
            try {
                Usuario user1 = new Usuario("Alice", 25);
                Usuario user2 = new Usuario("Bob", 30);
                Usuario user3 = new Usuario("Charlie", 35);
                usuarioOperation.insertUsuario(user1);
                usuarioOperation.insertUsuario(user2);
                usuarioOperation.insertUsuario(user3);
            } catch (Exception e) {
                System.err.println("Erro ao inserir usuários: " + e.getMessage());
            }
        }

        usuarioOperation.getAllUsuarios().forEach(doc -> System.out.println(Usuario.fromDocument(doc)));

        try {
            Usuario updatedUser = new Usuario("Bob", 32);
            usuarioOperation.updateUsuario(updatedUser);
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }

        usuarioOperation.getAllUsuarios().forEach(doc -> System.out.println(Usuario.fromDocument(doc)));
        usuarioOperation.deleteUsuarioByName("Charlie");
        usuarioOperation.getAllUsuarios().forEach(doc -> System.out.println(Usuario.fromDocument(doc)));
        mongoDBConnection.closeConnection();
    }


}