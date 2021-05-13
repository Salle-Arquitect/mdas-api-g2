package com.lasalle.sd2.g2.pokemons.infrastructure.repository;

import com.lasalle.sd2.g2.pokemons.domain.PokemonId;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryFavoriteRepositoryTest {

    @Test
    void singleIncrement() {
        InMemoryFavoriteRepository repo = new InMemoryFavoriteRepository();
        PokemonId id = new PokemonId(321);

        repo.increase(id);

        assertEquals(1L, repo.getValue(id));
    }

    @Test
    void randomIncrements() {
        InMemoryFavoriteRepository repo = new InMemoryFavoriteRepository();
        Random rand = new Random();
        Integer pokemonInteger = rand.nextInt(897) + 1; // XXX In pokeapi the last pokemon is 898
        PokemonId id = new PokemonId(pokemonInteger);

        int loops = rand.nextInt(200) + 2;
        for (int i = 0; i < loops; i++) {
            repo.increase(id);
        }

        assertEquals(loops, repo.getValue(id));
    }

    @Test
    void avoidIncrement() {
        InMemoryFavoriteRepository repo = new InMemoryFavoriteRepository();
        PokemonId id = new PokemonId(100);

        assertEquals(0L, repo.getValue(id));
    }
}
