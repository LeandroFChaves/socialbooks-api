package br.com.socialbooks.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Autor {
	
	/* JsonInclude(Include.NON_NULL) - Só retorna esse atributo caso não seja null */
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "O campo nome não pode ser vazio.")
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@JsonInclude(Include.NON_NULL)
	private String nacionalidade;
	
	@OneToMany(mappedBy = "autor")
	@JsonIgnore
	private List<Livro> livro;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Livro> getLivro() {
		return livro;
	}

	public void setLivro(List<Livro> livro) {
		this.livro = livro;
	}

}
