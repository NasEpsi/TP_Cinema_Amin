package edu.cinema.pricing;

import java.util.Objects;

public final class PriceBreakdown {
    private final double subtotal;         // somme des prix de base
    private final double wednesdayDisc;    // remise -20% si mercredi (valeur positive)
    private final double threeDSurcharge;  // +2€ * nbBillets si 3D
    private final double groupDisc;        // remise -10% si groupe >=4 (valeur positive)
    private final double total;            // total final arrondi à 2 décimales

    public PriceBreakdown(double subtotal, double wednesdayDisc, double threeDSurcharge, double groupDisc, double total) {
        this.subtotal = subtotal;
        this.wednesdayDisc = wednesdayDisc;
        this.threeDSurcharge = threeDSurcharge;
        this.groupDisc = groupDisc;
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getWednesdayDisc() {
        return wednesdayDisc;
    }

    public double getThreeDSurcharge() {
        return threeDSurcharge;
    }

    public double getGroupDisc() {
        return groupDisc;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "PriceBreakdown{" +
                "subtotal=" + subtotal +
                ", wednesdayDisc=" + wednesdayDisc +
                ", threeDSurcharge=" + threeDSurcharge +
                ", groupDisc=" + groupDisc +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceBreakdown)) return false;
        PriceBreakdown that = (PriceBreakdown) o;
        return Double.compare(that.subtotal, subtotal) == 0
                && Double.compare(that.wednesdayDisc, wednesdayDisc) == 0
                && Double.compare(that.threeDSurcharge, threeDSurcharge) == 0
                && Double.compare(that.groupDisc, groupDisc) == 0
                && Double.compare(that.total, total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subtotal, wednesdayDisc, threeDSurcharge, groupDisc, total);
    }
}
