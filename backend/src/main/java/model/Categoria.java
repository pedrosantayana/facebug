package model;

public class Categoria {
    private String nome;
    private int UUID;

    public Categoria() {

    }

    public Categoria(String nome, int UUID){
        this.nome = nome;
        this.UUID = UUID;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getUUID() {
        return UUID;
    }
    public void setUUID(int uUID) {
        UUID = uUID;
    }

    
}
