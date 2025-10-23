package edu.cinema.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PricingEngine")
class PricingEngineTest {

    private final PricingEngine engine = new PricingEngine();

    @Nested
    @DisplayName("basePrice()")
    class BasePriceTests {

        @ParameterizedTest(name = "Prix de base {0} = {1}€")
        @CsvSource({
                "ADULT,10.00",
                "CHILD,6.00",
                "SENIOR,7.50",
                "STUDENT,8.00"
        })
        void basePrice_nominal(TicketType type, double expected) {
            assertEquals(expected, engine.basePrice(type), 1e-9);
        }

        @Test
        void basePrice_null_throws() {
            assertThrows(IllegalArgumentException.class, () -> engine.basePrice(null));
        }
    }

    @Nested
    @DisplayName("computeTotal()")
    class ComputeTotalTests {

        @Test
        @DisplayName("Préconditions: tickets == null")
        void nullTickets_throws() {
            assertThrows(IllegalArgumentException.class, () -> engine.computeTotal(null, DayOfWeek.MONDAY, false));
        }

        @Test
        @DisplayName("Préconditions: day == null")
        void nullDay_throws() {
            assertThrows(IllegalArgumentException.class, () -> engine.computeTotal(Collections.emptyList(), null, false));
        }

        @Test
        @DisplayName("Panier vide → total 0.00")
        void emptyCart_totalZero() {
            PriceBreakdown p = engine.computeTotal(Collections.emptyList(), DayOfWeek.MONDAY, false);
            assertAll(
                    () -> assertEquals(0.00, p.getSubtotal(), 1e-9),
                    () -> assertEquals(0.00, p.getWednesdayDisc(), 1e-9),
                    () -> assertEquals(0.00, p.getThreeDSurcharge(), 1e-9),
                    () -> assertEquals(0.00, p.getGroupDisc(), 1e-9),
                    () -> assertEquals(0.00, p.getTotal(), 1e-9)
            );
        }

        @ParameterizedTest(name = "1 billet {0} lundi non-3D → total {1}€")
        @CsvSource({
                "ADULT,10.00",
                "CHILD,6.00",
                "SENIOR,7.50",
                "STUDENT,8.00"
        })
        void singleTicket_noOptions(TicketType t, double expected) {
            PriceBreakdown p = engine.computeTotal(Collections.singletonList(t), DayOfWeek.MONDAY, false);
            assertEquals(expected, p.getTotal(), 1e-9);
        }

        @Test
        @DisplayName("Mercredi uniquement (exemple énoncé)")
        void wednesdayOnly_matchesExample() {
            PriceBreakdown p = engine.computeTotal(
                    Arrays.asList(TicketType.ADULT, TicketType.SENIOR, TicketType.CHILD),
                    DayOfWeek.WEDNESDAY, false);
            assertAll(
                    () -> assertEquals(23.50, p.getSubtotal(), 1e-9),
                    () -> assertEquals(4.70, p.getWednesdayDisc(), 1e-9),
                    () -> assertEquals(0.00, p.getThreeDSurcharge(), 1e-9),
                    () -> assertEquals(0.00, p.getGroupDisc(), 1e-9),
                    () -> assertEquals(18.80, p.getTotal(), 1e-9)
            );
        }

        @Test
        @DisplayName("3D uniquement (exemple énoncé)")
        void threeDOnly_matchesExample() {
            PriceBreakdown p = engine.computeTotal(
                    Arrays.asList(TicketType.ADULT, TicketType.CHILD),
                    DayOfWeek.MONDAY, true);
            assertAll(
                    () -> assertEquals(16.00, p.getSubtotal(), 1e-9),
                    () -> assertEquals(0.00, p.getWednesdayDisc(), 1e-9),
                    () -> assertEquals(4.00, p.getThreeDSurcharge(), 1e-9),
                    () -> assertEquals(0.00, p.getGroupDisc(), 1e-9),
                    () -> assertEquals(20.00, p.getTotal(), 1e-9)
            );
        }

        @Test
        @DisplayName("Groupe uniquement (exemple énoncé)")
        void groupOnly_matchesExample() {
            PriceBreakdown p = engine.computeTotal(
                    Arrays.asList(TicketType.STUDENT, TicketType.STUDENT, TicketType.STUDENT, TicketType.STUDENT),
                    DayOfWeek.MONDAY, false);
            assertAll(
                    () -> assertEquals(32.00, p.getSubtotal(), 1e-9),
                    () -> assertEquals(0.00, p.getWednesdayDisc(), 1e-9),
                    () -> assertEquals(0.00, p.getThreeDSurcharge(), 1e-9),
                    () -> assertEquals(3.20, p.getGroupDisc(), 1e-9),
                    () -> assertEquals(28.80, p.getTotal(), 1e-9)
            );
        }

        @Test
        @DisplayName("Mercredi + 3D + Groupe (exemple énoncé)")
        void wednesday_3d_group_matchesExample() {
            PriceBreakdown p = engine.computeTotal(
                    Arrays.asList(TicketType.ADULT, TicketType.ADULT, TicketType.ADULT, TicketType.ADULT),
                    DayOfWeek.WEDNESDAY, true);
            assertAll(
                    () -> assertEquals(40.00, p.getSubtotal(), 1e-9),
                    () -> assertEquals(8.00, p.getWednesdayDisc(), 1e-9),   // 20% de 40
                    () -> assertEquals(8.00, p.getThreeDSurcharge(), 1e-9), // 4 billets * 2€
                    () -> assertEquals(4.00, p.getGroupDisc(), 1e-9),       // 10% de (32 + 8) = 4
                    () -> assertEquals(36.00, p.getTotal(), 1e-9)
            );
        }

        @Test
        @DisplayName("Ordre des règles (Mercredi → 3D → Groupe)")
        void orderOfRules_isRespected() {
            // Construction d’un panier où l’ordre se voit : 3D s’applique avant la remise groupe
            PriceBreakdown p = engine.computeTotal(
                    Arrays.asList(TicketType.ADULT, TicketType.ADULT, TicketType.ADULT, TicketType.STUDENT),
                    DayOfWeek.WEDNESDAY, true);
            // Subtotal = 10 + 10 + 10 + 8 = 38
            // Wednesday -20% => 30.40
            // 3D + 4*2 => +8 => 38.40
            // Group -10% => -3.84 => 34.56
            assertEquals(34.56, p.getTotal(), 1e-9);
        }

        @Test
        @DisplayName("Arrondi au centime (stabilité)")
        void rounding_twoDecimals() {
            // Cas mélangeant remises et surcharge pour vérifier l'arrondi final
            PriceBreakdown p = engine.computeTotal(
                    Arrays.asList(TicketType.SENIOR, TicketType.STUDENT, TicketType.CHILD, TicketType.CHILD),
                    DayOfWeek.WEDNESDAY, true);
            // Subtotal = 7.50 + 8.00 + 6.00 + 6.00 = 27.50
            // Wednesday -20% => 22.00
            // 3D +4*2 => +8 => 30.00
            // Group -10% => -3.00 => 27.00
            assertEquals(27.00, p.getTotal(), 1e-9);
            // Vérifie aussi que chaque composant a bien 2 décimales "logiquement"
            assertEquals(27.50, p.getSubtotal(), 1e-9);
            assertEquals(5.50, p.getWednesdayDisc(), 1e-9);
            assertEquals(8.00, p.getThreeDSurcharge(), 1e-9);
            assertEquals(3.00, p.getGroupDisc(), 1e-9);
        }
    }
}
