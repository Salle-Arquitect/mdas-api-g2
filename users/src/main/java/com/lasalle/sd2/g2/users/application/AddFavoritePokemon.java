package com.lasalle.sd2.g2.users.application;

import com.lasalle.sd2.g2.pokemons.application.IncreasePokemonFavorite;
import com.lasalle.sd2.g2.pokemons.domain.PokemonFavoriteRepository;
import com.lasalle.sd2.g2.users.application.dto.AddFavoritePokemonRequestBody;
import com.lasalle.sd2.g2.users.domain.Pokemon;
import com.lasalle.sd2.g2.users.domain.User;
import com.lasalle.sd2.g2.users.domain.UserId;
import com.lasalle.sd2.g2.users.domain.UsersRepository;
import com.lasalle.sd2.g2.users.domain.exceptions.PokemonAlreadyFavoriteException;
import com.lasalle.sd2.g2.users.domain.exceptions.UserNotFoundException;

import java.util.UUID;

public class AddFavoritePokemon {

    private final UsersRepository repository;
    private final IncreasePokemonFavorite increasePokemonFavorite;

    public AddFavoritePokemon(UsersRepository user, PokemonFavoriteRepository favorite) {
        repository = user;
        increasePokemonFavorite = new IncreasePokemonFavorite(favorite);
    }

    public void execute(String userId, AddFavoritePokemonRequestBody requestBody) throws UserNotFoundException, PokemonAlreadyFavoriteException {
        User user = repository.findByUserId(new UserId(UUID.fromString(userId)));

        Pokemon pokemon = new Pokemon(requestBody.getPokemonId());
        user.addFavoritePokemon(pokemon);

        repository.save(user);
        increasePokemonFavorite.execute(pokemon.getId());
    }
}
