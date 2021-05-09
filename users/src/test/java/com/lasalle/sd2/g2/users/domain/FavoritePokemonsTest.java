package com.lasalle.sd2.g2.users.domain;

import com.lasalle.sd2.g2.users.domain.exceptions.PokemonAlreadyFavoriteException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavoritePokemonsTest {

    @Mock
    private Pokemon pokemon;

    @Test
    void create() {
        FavoritePokemons favoritePokemons = FavoritePokemons.create();
        assertNotNull(favoritePokemons);
    }

    @Test
    void add() throws PokemonAlreadyFavoriteException {
        Set<Pokemon> pokemons = new HashSet<>();
        FavoritePokemons favoritePokemons = new FavoritePokemons(pokemons);
        favoritePokemons.add(pokemon);

        assertTrue(pokemons.contains(pokemon));
        assertEquals(1, pokemons.size());
    }

    @Test
    void addThrowsPokemonAlreadyFavorite() throws PokemonAlreadyFavoriteException {
        Set<Pokemon> pokemons = new HashSet<>();
        FavoritePokemons favoritePokemons = new FavoritePokemons(pokemons);
        favoritePokemons.add(pokemon);

        assertThrows(PokemonAlreadyFavoriteException.class, () -> favoritePokemons.add(pokemon));

        assertTrue(pokemons.contains(pokemon));
        assertEquals(1, pokemons.size());
    }
}
