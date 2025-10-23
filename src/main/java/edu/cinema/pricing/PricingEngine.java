package edu.cinema.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.util.List;

public class PricingEngine {

    public double basePrice(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("Ticket type cannot be null");
        }
        switch (type) {
            case ADULT:   return 10.00;
            case CHILD:   return  6.00;
            case SENIOR:  return  7.50;
            case STUDENT: return  8.00;
            default:
                // Enum exhaustif, mais on garde un guard par sécurité
                throw new IllegalArgumentException("Unsupported ticket type: " + type);
        }
    }

    /**
     * Calcule le total en respectant l'ordre :
     * 1) -20% le mercredi (sur le panier)
     * 2) +2€ par billet si 3D
     * 3) -10% groupe si nb billets >= 4 (sur le panier après étapes 1 & 2)
     */
    public PriceBreakdown computeTotal(List<TicketType> tickets, DayOfWeek day, boolean is3D) {
        if (tickets == null) {
            throw new IllegalArgumentException("tickets cannot be null");
        }
        if (day == null) {
            throw new IllegalArgumentException("day cannot be null");
        }

        // 1) Subtotal (prix de base)
        double subtotal = tickets.stream()
                .mapToDouble(this::basePrice)
                .sum();

        // 2) Mercredi : -20% du panier
        double wednesdayDisc = (day == DayOfWeek.WEDNESDAY) ? (subtotal * 0.20) : 0.0;
        double afterWednesday = subtotal - wednesdayDisc;

        // 3) 3D : +2€ par billet
        double threeDSurcharge = is3D ? (tickets.size() * 2.0) : 0.0;
        double after3D = afterWednesday + threeDSurcharge;

        // 4) Groupe (>=4) : -10% du panier
        boolean isGroup = tickets.size() >= 4;
        double groupDisc = isGroup ? (after3D * 0.10) : 0.0;

        // 5) Total arrondi au centime
        double total = roundToCents(after3D - groupDisc);

        // Par cohérence d'affichage, on arrondit aussi les composants
        double subtotalR = roundToCents(subtotal);
        double wednesdayR = roundToCents(wednesdayDisc);
        double surchargeR = roundToCents(threeDSurcharge);
        double groupR = roundToCents(groupDisc);

        return new PriceBreakdown(subtotalR, wednesdayR, surchargeR, groupR, total);
    }

    /** Arrondi bancaire classique au centime (HALF_UP). */
    private double roundToCents(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
