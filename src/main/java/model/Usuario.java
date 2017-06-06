package model;

/**
 *
 * @author danmo
 */
public class Usuario {
    private String nome;
    private String senha;
    private String endereco;
    private String email;
    private boolean admin;

    public Usuario(String nome, String senha, String endereco, String email, boolean admin) {
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.email = email;
        this.admin = admin;
    }

    public boolean getAdmin(){
        return admin;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", senha=" + senha + ", endereco=" + endereco + ", email=" + email + '}';
    }
    
}
