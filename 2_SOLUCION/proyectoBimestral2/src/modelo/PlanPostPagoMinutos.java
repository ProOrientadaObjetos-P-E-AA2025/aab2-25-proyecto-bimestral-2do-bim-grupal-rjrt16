package modelo;

public class PlanPostPagoMinutos extends Plan {
    private double megasGigas;
    private double costoMegasGigas;
    private int tarifaBase;
    private int idPlan;

    public PlanPostPagoMinutos(double megasGigas, double costoMegasGigas, int tarifaBase, int idPlan) {
        this.tipoPlan = "PostPagoMinutos";
        this.megasGigas = megasGigas;
        this.costoMegasGigas = costoMegasGigas;
        this.tarifaBase = tarifaBase;
        this.idPlan = idPlan;
    }

    @Override
    public double calcularPagoMensual() {
        return (megasGigas * costoMegasGigas) + tarifaBase;
    }
}