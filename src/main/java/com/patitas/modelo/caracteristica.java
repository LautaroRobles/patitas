package com.patitas.modelo;

import java.util.List;

public class Caracteristica {
  private TipoCaracteristica tipoCaracteristica;
  private String valor;
  
 public TipoCaracteristica getTipoCaracteristica(){
  return tipoCaracteristica;
 }
 
 public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica){
  this.tipoCaracteristica = tipoCaracteristica 
 }
  
 public String getValor(){
  return valor;
 }
 
 public void setValor(String valor){
  this.valor = valor
 }
}
  
