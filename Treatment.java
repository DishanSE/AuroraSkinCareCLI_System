public class Treatment {
    public static double calculatingFinalAmount(double basePrice){
        double tax = basePrice * 0.025;
        return Math.round((basePrice + tax) * 100.0) / 100.0;
    }
}
