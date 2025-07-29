package modelo;

public abstract class Plan {
    protected String tipoPlan;

    public String getTipoPlan() {
        return tipoPlan;
    }

    public abstract double calcularPagoMensual();
}