package modelo;
public class PlanPostPagoMinutosMegasEconomico extends Plan {

    private int minutos;
    private double costoMinuto;
    private double megas;
    private double costoGigas;
    private double porcentajeDescuento;
    private int idPlan;
//  
    public PlanPostPagoMinutosMegasEconomico(int minutos, double costoMinuto,
            double megas, double costoGiga, double porcentajeDescuento, int idPlan) {
        this.tipoPlan = "PostPagoMinutosMegasEconomico";
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.megas = megas;
        this.costoGigas = costoGiga;
        this.porcentajeDescuento = porcentajeDescuento;
        this.idPlan = idPlan;
    }

    @Override
    public double calcularPagoMensual() {
        double pagoSinDescuento = (minutos * costoMinuto) + (megas * costoGigas);
        return pagoSinDescuento - (pagoSinDescuento * porcentajeDescuento / 100);
    }
}
