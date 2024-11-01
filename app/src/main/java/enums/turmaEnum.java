package enums;

public enum turmaEnum {
    PRIMEIRO_ANO_A("1A"),
    PRIMEIRO_ANO_B("1B"),
    SEGUNDO_ANO_A("2A"),
    SEGUNDO_ANO_B("2B");

    public final String descricao;

    private turmaEnum(String descricao) {

        this.descricao = descricao;
    }
}
