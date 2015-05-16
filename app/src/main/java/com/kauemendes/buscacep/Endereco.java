package com.kauemendes.buscacep;

/**
 * Created by kauemendes on 3/25/15.
 */
import com.google.gson.Gson;

public class Endereco {

    private Integer resultado;
    private String resultado_txt;
    private String uf;
    private String cidade;
    private String bairro;
    private String tipo_logradouro;
    private String logradouro;

    public Integer getResultado() {
        return resultado;
    }
    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }
    public String getResultadoTxt() {
        return resultado_txt;
    }
    public void setResultadoTxt(String resultado_txt) {
        this.resultado_txt = resultado_txt;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getTipoLogradouro() {
        return tipo_logradouro;
    }
    public void setTipoLogradouro(String tipo_logradouro) {
        this.tipo_logradouro = tipo_logradouro;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Endereco parseJSON(String json){
        try {
            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(json, Endereco.class);
            return endereco;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}