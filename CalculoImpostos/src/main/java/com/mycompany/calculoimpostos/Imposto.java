package com.mycompany.calculoimpostos;

import java.util.HashMap;
import java.util.Map;

public class Imposto {
    public static void main(String[] args) {
        Map<String, Double> productPrices = new HashMap<>();
        productPrices.put("Notebook", 2500.0);
        productPrices.put("Smartphone", 1800.0);
        productPrices.put("Refrigerator", 3000.0);
        
        Map<String, Double> servicePrices = new HashMap<>();
        servicePrices.put("SoftwareDevelopment", 5000.0);
        servicePrices.put("FinancialConsulting", 3000.0);
        servicePrices.put("GraphicDesign", 2000.0);

        double freight = 50.0;
        double insurance = 20.0;
        String productLocation = "Sao Paulo";
        String serviceLocation = "Rio de Janeiro";
        double profitMargin = 0.20;

        for (String product : productPrices.keySet()) {
            double cost = productPrices.get(product);
            double finalPrice = calculateFinalProductPrice(cost, productLocation, freight, insurance, profitMargin);
            System.out.println("Final price for " + product + ": R$" + finalPrice);
        }

        for (String service : servicePrices.keySet()) {
            double cost = servicePrices.get(service);
            double finalServicePrice = calculateFinalServicePrice(cost, serviceLocation, profitMargin);
            System.out.println("Final price for " + service + ": R$" + finalServicePrice);
        }
    }

    public static double calculateFinalProductPrice(double cost, String location, double freight, double insurance, double profitMargin) {
        double baseCalculation = cost + freight + insurance;
        double ipi = baseCalculation * 0.15;
        double icms = calculateIcms(location, baseCalculation);
        double profit = cost * profitMargin;

        return cost + ipi + icms + profit;
    }

    public static double calculateFinalServicePrice(double cost, String location, double profitMargin) {
        double iss = calculateIss(location, cost);
        double profit = cost * profitMargin;

        return cost + iss + profit;
    }

    public static double calculateIcms(String location, double baseCalculation) {
        if (location.equals("Rio de Janeiro")) {
            return baseCalculation * 0.12;
        } else if (location.equals("Distrito Federal")) {
            return baseCalculation * 0.07;
        }
        return baseCalculation * 0.18;
    }

    public static double calculateIss(String location, double cost) {
        if (location.equals("Sao Paulo")) {
            return cost * 0.05;
        } else if (location.equals("Rio de Janeiro")) {
            return cost * 0.03;
        }
        return cost * 0.02;
    }
}
