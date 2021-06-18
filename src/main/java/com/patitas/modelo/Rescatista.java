package com.patitas.modelo;

import java.util.List;
import java.util.Date;

public class Rescatista {
  private String nombre;
  private String apellido;
  private Date fechaNacimiento;
  private String tipoDocumento;
  private String nroDocumento;
  private String direccion;
  private List<Contacto> contactos;

  public String getNombre() {
        return nombre;
    }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  
  public String getApellido() {
        return apellido;
    }
  
  public void setApellido(String apellido){
    this.apellido = apellido;
  }

  public Date getFechaNacimiento(){
    return fechaNacimiento;
}
  public void setFechaNacimiento(int anio, int mes, int dia){
    fechaNacimiento = new Date(anio, mes, dia);
  }
  public String getTipoDocumento() {
        return tipoDocumento;
    }
  
  public void setTipoDocumento(String tipoDocumento){
    this.tipoDocumento = tipoDocumento;
  }

  public String getnroDocumento() {
        return nroDocumento;
    }
  
  public void setNroDocumento(String nroDocumento){
    this.nroDocumento = nroDocumento;
  }
  
  public String getDireccion() {
        return direccion;
    }
  
  public void setDireccion(String direccion){
    this.direccion = direccion;
  }
  
  public List<Contacto> getContactos() {
        return contactos;
    }
  
  public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}
  
