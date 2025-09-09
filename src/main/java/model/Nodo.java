package model;

public class Nodo<T> {
    private final T valor;
    private Nodo<T> referencia;

    public Nodo(T valor) {
        this.valor = valor;
        this.referencia = null;
    }

    public T getValor() {
        return valor;
    }

    public Nodo<T> getReferencia() {
        return referencia;
    }

    public void setReferencia(Nodo<T> referencia) {
        this.referencia = referencia;
    }
}
