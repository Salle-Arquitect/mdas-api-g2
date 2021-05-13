package com.lasalle.sd2.g2.pokemons.domain;

public interface PokemonFavoriteRepository {

    void increase(PokemonId id);

    Long getValue(PokemonId id);
}
