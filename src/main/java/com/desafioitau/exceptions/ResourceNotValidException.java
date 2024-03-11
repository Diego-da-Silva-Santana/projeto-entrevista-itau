package com.desafioitau.exceptions;

public class ResourceNotValidException extends  RuntimeException{

    public ResourceNotValidException(String mensagem) {
        super(mensagem);
    }
}
