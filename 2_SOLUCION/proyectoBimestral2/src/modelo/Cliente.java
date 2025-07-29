package modelo;

public class Cliente {
    private String nombre;
    private String cedula;
    private String ciudad;
    private String numeroCelular;
    private String marca;
    private String modelo;
    private double pagoMensual;
    private int edad;
    private String pais;
    private int tipoPlan;

    public Cliente(String nombre, String cedula, String ciudad, String numeroCelular, 
                  String marca, String modelo, double pagoMensual, int edad, 
                  String pais, int tipoPlan) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.ciudad = ciudad;
        this.numeroCelular = numeroCelular;
        this.marca = marca;
        this.modelo = modelo;
        this.pagoMensual = pagoMensual;
        this.edad = edad;
        this.pais = pais;
        this.tipoPlan = tipoPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(int tipoPlan) {
        this.tipoPlan = tipoPlan;
    }
   
  @Override
    public String toString() {
        return String.format(
            "Nombre: %s\nCédula: %s\nCiudad: %s\nCelular: %s\n" +
            "Marca: %s\nModelo: %s\nPago: %.2f\nEdad: %d\n" +
            "País: %s\nTipo Plan: %d",
            nombre, cedula, ciudad, numeroCelular, 
            marca, modelo, pagoMensual, edad, pais, tipoPlan
        );
    }
    
}