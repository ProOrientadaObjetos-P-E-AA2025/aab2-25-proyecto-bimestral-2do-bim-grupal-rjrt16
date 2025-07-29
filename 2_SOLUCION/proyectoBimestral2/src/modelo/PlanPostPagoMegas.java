package modelo;
public class PlanPostPagoMegas extends Plan{
    private int minutos;
    private double costoMinutos;
    private int gigas;
    private double costoGigas;
    private int idPlan;

    public PlanPostPagoMegas(int minutos, double costoMinutos, int gigas, double costoGigas, int idPlan) {
        this.minutos = minutos;
        this.costoMinutos = costoMinutos;
        this.gigas = gigas;
        this.costoGigas = costoGigas;
        this.idPlan = idPlan;
    }

    @Override
    public double calcularPagoMensual() {
        double pagoMensual = (minutos * costoMinutos) + (gigas * costoGigas);
        return pagoMensual;
    }
 
}
