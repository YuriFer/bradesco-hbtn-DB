package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "sigla", nullable = false)
    private String sigla;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "curso_aluno",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();
    
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MaterialCurso> materiais = new ArrayList<>();
    
    public Curso() {}
    
    public Curso(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public Professor getProfessor() {
        return professor;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public List<MaterialCurso> getMateriais() {
        return materiais;
    }
    
    public void setMateriais(List<MaterialCurso> materiais) {
        this.materiais = materiais;
    }
    
    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
        aluno.getCursos().add(this);
    }
    
    public void removeAluno(Aluno aluno) {
        alunos.remove(aluno);
        aluno.getCursos().remove(this);
    }
    
    public void addMaterial(MaterialCurso material) {
        materiais.add(material);
        material.setCurso(this);
    }
    
    public void removeMaterial(MaterialCurso material) {
        materiais.remove(material);
        material.setCurso(null);
    }
    
    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}