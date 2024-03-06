package com.desafioitau.exceptions;

public class Error {

    private Integer status;
    private String mensagem;

    public Error() {
    }

    public Error(Integer status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
