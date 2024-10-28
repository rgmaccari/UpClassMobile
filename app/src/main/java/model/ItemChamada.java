package model;

public class ItemChamada {
    private String alunoRa;
    private String tvNome;
    private boolean checkboxPresenca;

    public ItemChamada() {
    }

    public ItemChamada(String alunoRa, String tvNome, boolean checkboxPresenca) {
        this.alunoRa = alunoRa;
        this.tvNome = tvNome;
        this.checkboxPresenca = checkboxPresenca;
    }

    public String getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(String alunoRa) {
        this.alunoRa = alunoRa;
    }

    public String getTvNome() {
        return tvNome;
    }

    public void setTvNome(String tvNome) {
        this.tvNome = tvNome;
    }

    public boolean isCheckboxPresenca() {
        return checkboxPresenca;
    }

    public void setCheckboxPresenca(boolean checkboxPresenca) {
        this.checkboxPresenca = checkboxPresenca;
    }
}
