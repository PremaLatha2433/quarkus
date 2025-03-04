package org.bnbc;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.bnbc.entities.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

/**
 * https://quarkus.io/guides/hibernate-orm-panache#first-an-example
 */
@QuarkusTest
public class PanacheFunctionalityTest {

    @Test
    public void testPanacheMocking() {
        PanacheMock.mock(Room.class);
        Mockito.when(Room.count()).thenReturn(23L);
        Assertions.assertEquals(23, Room.count());
        Mockito.when(Room.count()).thenReturn(42L);
        Assertions.assertEquals(42, Room.count());
        Room r = new Room();
        Mockito.when(Room.findById(12L)).thenReturn(r);
        Assertions.assertSame(r, Room.findById(12L));
        Assertions.assertNull(Room.findById(42L));
        Mockito.when(Room.findOrdered()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(Room.findOrdered().isEmpty());

    }

    }
