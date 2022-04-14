package desafio.entidade;

public class Cliente {
	private int id;
    private String razaoSocial;
    private String cnpj;
    private String tipoTributacao;
    private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTipoTributacao() {
		return tipoTributacao;
	}
	public void setTipoTributacao(String tipoTributacao) {
		this.tipoTributacao = tipoTributacao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
