package br.com.jumplabel.simulador.web.rest.errors;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_ACCESS_DENIED = "error.accessDenied";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String ERR_METHOD_NOT_SUPPORTED = "error.methodNotSupported";
    public static final String ERR_INTERNAL_SERVER_ERROR = "error.internalServerError";
    
    public static final String VAL_VALOR_OBRIGATORIO = "validacao.valorObrigatorio";
    public static final String VAL_VALOR_REPETIDO_BASE = "validacao.valorRepetidoBase";
    public static final String VAL_VALOR_NAO_ENCONTRADO_BASE = "validacao.valorNaoEncontradoBase";
    public static final String VAL_VALIDACAO_FK = "validacao.validacaoFK";
    public static final String VAL_VALIDACAO_RANGE_SOBREPOSTO = "validacao.validacaoRangeSobreposto";
    public static final String VAL_VALIDACAO_VALOR_VALIDO = "validacao.validacaoValorValido";
    public static final String VAL_VALIDACAO_TAMANHO_LISTA = "validacao.validacaoTamanhoLista";
    
    private ErrorConstants() {
    }

}
