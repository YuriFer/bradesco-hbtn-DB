package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.Date;
import java.util.List;

public class GestaoCursosMain {

    public static void main(String[] args) {
        
        System.out.println("=== Sistema de Gestão de Cursos ===");
        
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();
        
        System.out.println("\n=== Teste 1: Criando Aluno ===");
        Aluno aluno = new Aluno("Maria Santos", "ALU001", new Date(), "maria.santos@email.com");
        alunoModel.create(aluno);
        
        System.out.println("\n=== Teste 2: Criando outro Aluno ===");
        Aluno aluno2 = new Aluno("Pedro Oliveira", "ALU002", new Date(), "pedro.oliveira@email.com");
        alunoModel.create(aluno2);
        
        System.out.println("\n=== Teste 3: Criando Professor ===");
        Professor professor = new Professor("Dr. João Silva", "PROF001", "joao.silva@universidade.edu");
        
        System.out.println("\n=== Teste 4: Criando Curso ==="); 
        Curso curso = new Curso("Programação Java", "JAVA101");
        curso.setProfessor(professor);
        cursoModel.create(curso);
        
        System.out.println("\n=== Teste 5: Criando outro Curso ===");
        Curso curso2 = new Curso("Banco de Dados", "BD101");
        curso2.setProfessor(professor);
        cursoModel.create(curso2);
        
        System.out.println("\n=== Dados básicos criados com sucesso! ===");
        
        System.out.println("\n=== Lista de Alunos ===");
        List<Aluno> alunos = alunoModel.findAll();
        if (alunos != null && !alunos.isEmpty()) {
            for (Aluno a : alunos) {
                System.out.println("ID: " + a.getId() + " | Aluno: " + a.getNomeCompleto() + " | Matrícula: " + a.getMatricula());
                System.out.println("  Email: " + a.getEmail());
                System.out.println("  Data de Nascimento: " + a.getNascimento());
                System.out.println();
            }
        } else {
            System.out.println("Nenhum aluno encontrado.");
        }
        
        System.out.println("\n=== Lista de Cursos ===");
        List<Curso> cursos = cursoModel.findAll();
        if (cursos != null && !cursos.isEmpty()) {
            for (Curso c : cursos) {
                System.out.println("ID: " + c.getId() + " | Curso: " + c.getNome() + " (" + c.getSigla() + ")");
                if (c.getProfessor() != null) {
                    System.out.println("  Professor: " + c.getProfessor().getNomeCompleto());
                    System.out.println("  Email Professor: " + c.getProfessor().getEmail());
                    System.out.println("  Matrícula Professor: " + c.getProfessor().getMatricula());
                }
                System.out.println();
            }
        } else {
            System.out.println("Nenhum curso encontrado.");
        }
        
        System.out.println("\n=== Teste de Busca por ID ===");
        Aluno alunoEncontrado = alunoModel.findById(1L);
        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado por ID 1: " + alunoEncontrado.getNomeCompleto());
        } else {
            System.out.println("Aluno com ID 1 não encontrado.");
        }
        
        Curso cursoEncontrado = cursoModel.findById(1L);
        if (cursoEncontrado != null) {
            System.out.println("Curso encontrado por ID 1: " + cursoEncontrado.getNome());
        } else {
            System.out.println("Curso com ID 1 não encontrado.");
        }
        
        System.out.println("\n=== Teste de Atualização ===");
        if (alunoEncontrado != null) {
            alunoEncontrado.setEmail("maria.santos.novo@email.com");
            alunoModel.update(alunoEncontrado);
            System.out.println("Email do aluno atualizado para: " + alunoEncontrado.getEmail());
        }
        
    }
}