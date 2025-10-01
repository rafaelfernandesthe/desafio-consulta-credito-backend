package br.com.desafio.consultacredito.exceptions;

public class NotFoundBusinessException extends RuntimeException {
    public NotFoundBusinessException(String message) { super(message); }
}