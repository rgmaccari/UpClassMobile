package enums;

public enum turmaEnum {
    PRIMEIRO_ANO_A("1° A"),
    PRIMEIRO_ANO_B("1° B"),
    SEGUNDO_ANO_A("2° A"),
    SEGUNDO_ANO_B("2° B");

    private String descricao;

    private turmaEnum(String descricao) {
        this.descricao = descricao;
    }
}
