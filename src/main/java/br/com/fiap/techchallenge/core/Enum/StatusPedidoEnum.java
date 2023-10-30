package br.com.fiap.techchallenge.core.Enum;

public enum StatusPedidoEnum {
    RECEBIDO(1),
    EM_PREPARACAO(2),
    PRONTO(3),
    FINALIZADO(4);

    private final int ordem;

    StatusPedidoEnum(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }
}
