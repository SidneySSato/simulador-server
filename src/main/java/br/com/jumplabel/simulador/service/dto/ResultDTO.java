package br.com.jumplabel.simulador.service.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.jumplabel.simulador.web.rest.errors.ErrorConstants;
import br.com.jumplabel.simulador.web.rest.errors.FieldErrorVM;


public class ResultDTO {

	public static String MSG_OPERACAO_SUCESSO = "Operacao executada com sucesso";
	public static String MSG_ERRO_VALIDACAO = "Operacao nao executada, pois ocorreu erro de validacao";
	public static String MSG_ERRO_VALIDACAO_CAMPO_OBRIGATORIO = "Operacao nao executada, pois ocorreu erro de validacao de campo obrigatorio";
	public static String MSG_ERRO_VALIDACAO_CAMPO_LISTA_OBRIGATORIA = "Operacao nao executada, pois ocorreu erro de validacao de lista obrigatoria";
	public static String MSG_ERRO_VALIDACAO_FK = "Operacao nao executada, pois ocorreu erro de validacao de fk";
	public static String MSG_ERRO_VALIDACAO_RANGE = "Operacao nao executada, pois ocorreu erro de validacao de range sobreposto";	
	public static String MSG_ERRO_VALIDACAO_VALOR_VALIDO_ATRIBUTO = "Operacao nao executada, pois ocorreu erro de validacao de valor valido para o atributo";
	public static String MSG_OPERACAO_NAO_EXECUTADA = "Operacao nao executada";
	
	/**
	 * Definicao para Situacao ATIVO - A
	 */
	public static final String DTO_CONSTANTE_ATIVO = "A";

	/**
	 * Definicao para Situacao INATIVO - I
	 */
	public static final String DTO_CONSTANTE_INATIVO = "I";
	
	/**
	 * Definicao para SIM - S
	 */
	public static final String DTO_CONSTANTE_SIM = "S";

	/**
	 * Definicao para Situacao NAO - N
	 */
	public static final String DTO_CONSTANTE_NAO = "N";
	
	public static ResultDTO getSuccessResult() {
		ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage(ResultDTO.MSG_OPERACAO_SUCESSO);
        resultDTO.setStatus(StatusType.SUCCESS);
        return resultDTO;
	}
	
	public static ResultDTO getOperacaoNaoExecutadaResultError(String code, String detailMessage) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setMessage(ResultDTO.MSG_OPERACAO_NAO_EXECUTADA);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.setCode(code);
		resultDTO.setDetailMessage(detailMessage);
        return resultDTO;
	}
	
	public static ResultDTO getValidacaoResultError(String code) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.setCode(code);
        return resultDTO;
	}
	
	public static ResultDTO getValidacaoResultError(String code, String message) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setMessage(message);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.setCode(code);
        return resultDTO;
	}
	
	public static ResultDTO getValorNaoEncontradoResultError(String field, String dto) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setMessage(ResultDTO.MSG_OPERACAO_NAO_EXECUTADA);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.addFieldErrors(dto, field, "");
		resultDTO.setCode(ErrorConstants.VAL_VALOR_NAO_ENCONTRADO_BASE);
        return resultDTO;
	}
	
	public static ResultDTO getValidacaoCampoObrigatorioResultError(String field, String dto) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO_CAMPO_OBRIGATORIO);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.addFieldErrors(dto, field, "");
		resultDTO.setCode(ErrorConstants.VAL_VALOR_OBRIGATORIO);
        return resultDTO;
	}
	
	public static ResultDTO getValorRepetidoResultError(String field, String dto, String message) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addFieldErrors(dto, field, message);
		resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.setCode(ErrorConstants.VAL_VALOR_REPETIDO_BASE);
        return resultDTO;
	}	

	public static ResultDTO getValidacaoFKResultError(String fkPai, String fkFilho, String dto) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addFieldErrors(dto, "fkPai=[" + fkPai +"], fkFilho=[" +fkFilho +"]", "");
		resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO_FK);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.setCode(ErrorConstants.VAL_VALIDACAO_FK);
        return resultDTO;
	}
	
	public static ResultDTO getValidacaoOverlapRangeError(Long rangeInicio, Long rangeFinal, String dto) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.addFieldErrors(dto, "rangeInicio=[" + rangeInicio +"], rangeFinal=[" +rangeFinal +"] tem overlap", "");
		resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO_RANGE);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.setCode(ErrorConstants.VAL_VALIDACAO_RANGE_SOBREPOSTO);
        return resultDTO;
	}
	
	public static ResultDTO getValidacaoTipoCharSimNao(String campo, String dto) {		
		if (!(campo != null && (campo.equals(DTO_CONSTANTE_SIM) || campo.equals(DTO_CONSTANTE_NAO)))) {
			ResultDTO resultDTO = new ResultDTO();
			resultDTO.addFieldErrors(dto, "tipo Indicador=[" + campo +"] deve ser S ou N", "");
			resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO_VALOR_VALIDO_ATRIBUTO);
			resultDTO.setStatus(StatusType.ERROR);
			resultDTO.setCode(ErrorConstants.VAL_VALIDACAO_VALOR_VALIDO);
			return resultDTO;
		} else {
			return null;
		}
	}
	
	public static ResultDTO getValidacaoTipoCharAtivoInativo(String campo, String dto) {		
		if (!(campo != null && (campo.equals(DTO_CONSTANTE_ATIVO) || campo.equals(DTO_CONSTANTE_INATIVO)))) {
			ResultDTO resultDTO = new ResultDTO();
			resultDTO.addFieldErrors(dto, dto + "=[" + campo +"] deve ser A ou I", "");
			resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO_VALOR_VALIDO_ATRIBUTO);
			resultDTO.setStatus(StatusType.ERROR);
			resultDTO.setCode(ErrorConstants.VAL_VALIDACAO_VALOR_VALIDO);
			return resultDTO;
		} else {
			return null;
		}
	}
	
	public static ResultDTO getValidacaoCampoListaObrigatorioResultError(String field, String dto, String message) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setMessage(ResultDTO.MSG_ERRO_VALIDACAO_CAMPO_LISTA_OBRIGATORIA);
		resultDTO.setStatus(StatusType.ERROR);
		resultDTO.addFieldErrors(dto, field, message);
		resultDTO.setCode(ErrorConstants.VAL_VALOR_OBRIGATORIO);
        return resultDTO;
	}
	
	
	/**
	 * Mensagem com a descricao da execucao
	 */
	@JsonInclude(Include.NON_NULL)	
	private String message;

	/**
	 * Lista de Campos que ocorreram validacao de erros de negocio ou de infraestrutura
	 */
	@JsonInclude(Include.NON_NULL)
	private List<FieldErrorVM> fieldErrors;
	
	/**
	 * Codigo do erro nao descritivo, utilizado pela camada web para fazer um de-para de mensagem
	 */
	@JsonInclude(Include.NON_NULL)	
	private String code;

	/**
	 * Status da execucao da mensagem, SUCCESS ou ERROR
	 */
	@JsonInclude(Include.NON_NULL)	
	private StatusType status;
	
	/**
	 * Detalhe da execycao da mensagem, pode conter o detalhe do erro ou do sucesso
	 */
	@JsonInclude(Include.NON_NULL)	
	private String detailMessage;
	

	public ResultDTO() {
		super();
	}

	public ResultDTO(StatusType status, String message, String code) {
		super();
		this.message = message;
		this.status = status;
		this.code = code;

	}

	public enum StatusType {
		SUCCESS, ERROR
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResultDTO resultDTO = (ResultDTO) o;

		if (!Objects.equals(status, resultDTO.status))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(status);
	}

	@Override
	public String toString() {
		return "ResultDTO{" + "status=" + status + ", message='" + message + "'" + ", code='" + code + "'" + '}';
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public List<FieldErrorVM> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<FieldErrorVM> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	public void addFieldErrors(String dto,String field,String message) {
		
		FieldErrorVM fieldErrorVM = new FieldErrorVM(dto, field, message);
		
		if (fieldErrors == null) {
			fieldErrors = new ArrayList<FieldErrorVM>();
		}
		fieldErrors.add(fieldErrorVM);
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}

}
