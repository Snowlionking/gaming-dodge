package services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InboundServiceTest {

    private InboundService inboundService = new InboundService();

    @Test
    void doesKeenInboundAlwaysReturnAnInboundValue() {
        assertEquals(inboundService.clamp(8, 1, 10), 8);

        assertEquals(inboundService.clamp(0, 1, 10), 1);

        assertEquals(inboundService.clamp(100, 1, 10), 10);

        assertEquals(inboundService.clamp(1, 1, 10), 1);

        assertEquals(inboundService.clamp(10, 1, 10), 10);
    }

}
