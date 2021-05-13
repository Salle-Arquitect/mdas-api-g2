package com.lasalle.sd2.g2.pokemons.infrastructure.repository;

import com.lasalle.sd2.g2.pokemons.domain.PokemonFavoriteRepository;
import com.lasalle.sd2.g2.pokemons.domain.PokemonId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryFavoriteRepository implements PokemonFavoriteRepository {

    private static final Map<Integer, Long> FAVORITE_MAP = new ConcurrentHashMap<>();

    @Override
    public void increase(PokemonId id) {
        Integer pokemonId = id.getId();
        FAVORITE_MAP.put(pokemonId, FAVORITE_MAP.getOrDefault(pokemonId, 0L) + 1);
    }

    @Override
    public Long getValue(PokemonId id) {
        return FAVORITE_MAP.getOrDefault(id.getId(), 0L);
    }
}
