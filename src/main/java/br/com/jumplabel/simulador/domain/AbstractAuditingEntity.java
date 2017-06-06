package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe base para extender com os atributos de auditoria (usuario de criacao e alteracao) e (data de criacao e alteracao).
 * Exemplo br.com.jumplabel.simulador.domain.User 
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @CreatedBy    
    @Size(max = 50)
    @Column(name = "ds_usuario_criacao", length = 50)    
    @JsonIgnore
    private String dsUsuarioCriacao;

    @CreatedDate
    @Column(name = "dt_criacao")
    @JsonIgnore
    private ZonedDateTime dtCriacao = ZonedDateTime.now();

    @LastModifiedBy
    @Size(max = 50)
    @Column(name = "ds_usuario_atualizacao", length = 50)
    @JsonIgnore
    private String dsUsuarioAtualizacao;

    @LastModifiedDate
    @Column(name = "dt_atualizacao")
    @JsonIgnore
    private ZonedDateTime dtAtualizacao = ZonedDateTime.now();

	public String getDsUsuarioCriacao() {
		return dsUsuarioCriacao;
	}

	public void setDsUsuarioCriacao(String dsUsuarioCriacao) {
		this.dsUsuarioCriacao = dsUsuarioCriacao;
	}

	public ZonedDateTime getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(ZonedDateTime dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getDsUsuarioAtualizacao() {
		return dsUsuarioAtualizacao;
	}

	public void setDsUsuarioAtualizacao(String dsUsuarioAtualizacao) {
		this.dsUsuarioAtualizacao = dsUsuarioAtualizacao;
	}

	public ZonedDateTime getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(ZonedDateTime dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
