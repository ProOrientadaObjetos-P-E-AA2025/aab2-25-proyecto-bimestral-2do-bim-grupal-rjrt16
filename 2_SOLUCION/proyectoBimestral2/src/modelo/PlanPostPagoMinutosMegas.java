package modelo;
public class PlanPostPagoMinutosMegas extends Plan {
    private double minutosNacionales;
    private double costoMinutoNacional;
    private double minutosInternacionales;
    private double costoMinutoInternacional;
    private int idPlan;

    public PlanPostPagoMinutosMegas(double minutosNacionales, double costoMinutoNacional, double minutosInternacionales, double costoMinutoInternacional, int idPlan) {
        this.tipoPlan = "PostPagoMinutosMegas";
        this.minutosNacionales = minutosNacionales;
        this.costoMinutoNacional = costoMinutoNacional;
        this.minutosInternacionales = minutosInternacionales;
        this.costoMinutoInternacional = costoMinutoInternacional;
        this.idPlan =idPlan;
    }
    @Override
    public double calcularPagoMensual() {
        double pagoMensual = (minutosNacionales * costoMinutoNacional) + (minutosInternacionales * costoMinutoInternacional);
        return pagoMensual;
    }
}
