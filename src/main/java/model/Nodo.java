package model;

public class Nodo<T> {
    private T valor;
    private Nodo<T> referencia;

    public Nodo(T valor, Nodo<T> referencia) {
        this.valor = valor;
        this.referencia = referencia;
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
