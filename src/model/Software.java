package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Software {
    private String nome;
    private String licenca;
    private String tipoLicenca;
    private String unidade;
    private LocalDate dataAquisicao;
    private LocalDate dataVencimento;
    private String patrimonio;
    private String usuarios;
    private String informacoesGerais;

    public Software(String nome, String licenca, String tipoLicenca, String unidade,
                    LocalDate dataAquisicao, LocalDate dataVencimento, String patrimonio,
                    String usuarios, String informacoesGerais) {

        this.nome = nome;
        this.licenca = licenca;
        this.tipoLicenca = tipoLicenca;
        this.unidade = unidade;
        this.dataAquisicao = dataAquisicao;
        this.dataVencimento = dataVencimento;
        this.patrimonio = patrimonio;
        this.usuarios = usuarios;
        this.informacoesGerais = informacoesGerais;
    }

    // Getters
    public String getNome() { return nome; }
    public String getLicenca() { return licenca; }
    public String getTipoLicenca() { return tipoLicenca; }
    public String getUnidade() { return unidade; }
    public LocalDate getDataAquisicao() { return dataAquisicao; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public String getPatrimonio() { return patrimonio; }
    public String getUsuarios() { return usuarios; }
    public String getInformacoesGerais() { return informacoesGerais; }
    public String getDataAquisicaoFormatada() {
        return dataAquisicao != null ? dataAquisicao.format(DateTimeFormatter.ofPattern("yyyy-dd-MM")) : ""; }
    public String getDataVencimentoFormatada() {
        return dataVencimento != null ? dataVencimento.format(DateTimeFormatter.ofPattern("yyyy-dd-MM")) : ""; }


    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setLicenca(String licenca) { this.licenca = licenca; }
    public void setTipoLicenca(String tipoLicenca) { this.tipoLicenca = tipoLicenca; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
    public void setDataAquisicao(LocalDate dataAquisicao) { this.dataAquisicao = dataAquisicao; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
    public void setPatrimonio(String patrimonio) { this.patrimonio = patrimonio; }
    public void setUsuarios(String usuarios) { this.usuarios = usuarios; }
    public void setInformacoesGerais(String informacoesGerais) { this.informacoesGerais = informacoesGerais; }
}
