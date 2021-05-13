package com.lasalle.sd2.g2.pokemons.application;

import com.lasalle.sd2.g2.pokemons.domain.PokemonFavoriteRepository;
import com.lasalle.sd2.g2.pokemons.domain.PokemonId;

public class IncreasePokemonFavorite {

    private final PokemonFavoriteRepository repository;

    public IncreasePokemonFavorite(PokemonFavoriteRepository favorite) {
        repository = favorite;
    }

    public void execute(Integer pokemonId) {
        repository.increase(new PokemonId(pokemonId));
    }
}
