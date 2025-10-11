package com.example;

import java.util.List;

import org.bson.Document;

public class UsuarioOperations {
    private MongoDBConnection mongoDBConnection;

    public UsuarioOperations(MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }

    public void insertUsuario(Usuario usuario) {
        mongoDBConnection.getDatabase().getCollection("users")
                .insertOne(usuario.toDocument());
    }

    public void deleteUsuarioByName(String nome) {
        mongoDBConnection.getDatabase().getCollection("users")
                .findOneAndDelete(new Document("nome", nome));
    }

    public List<Document> getAllUsuarios() {
        List<Document> documents = mongoDBConnection.getDatabase().getCollection("users")
                .find().into(new java.util.ArrayList<>());
        return documents;
    }

    public void updateUsuario(Usuario usuario) {
        mongoDBConnection.getDatabase().getCollection("users")
                .updateOne(new Document("nome", usuario.getNome()),
                        new Document("$set", usuario.toDocument()));
    }
}
