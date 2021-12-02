package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    @Test
    public void getFavorisNeighbourWithSuccess() {
        service.getFavorisNeighbours().clear();
        for (int i = 0; i < 4; i++) {
            Neighbour neighbour = service.getNeighbours().get(i);
            if (!neighbour.isFavorite()) {
                neighbour.setFavorite(true);
            }
        }
        assertEquals(service.getFavorisNeighbours().size(), 4);
    }
    @Test
    public void addFavorisNeighbourWithSuccess() {
        service.getFavorisNeighbours().clear();
        Neighbour neighbour = service.getNeighbours().get(5);
        neighbour.setFavorite(true);
        assertEquals(service.getFavorisNeighbours().size(), 1);
        assertEquals(neighbour, service.getFavorisNeighbours().get(0));
    }
    @Test
    public void deleteFavorisNeighbourWithSuccess() {
        service.getFavorisNeighbours().clear();
        for (int i = 0; i < 6; i++) {
            Neighbour neighbour = service.getNeighbours().get(i);
            if (!neighbour.isFavorite()) {
                neighbour.setFavorite(true);
            }
        }
        Neighbour n = service.getFavorisNeighbours().get(3);
        n.setFavorite(false);
        assertFalse(service.getFavorisNeighbours().contains(n));
        assertEquals(service.getFavorisNeighbours().size(), 5);
    }
    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = new Neighbour((long) service.getNeighbours().size(), "Mon voisin Test", "https://i.pravatar.cc/150", "", "", "");
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }
}
