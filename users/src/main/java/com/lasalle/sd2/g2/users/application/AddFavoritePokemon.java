package com.lasalle.sd2.g2.users.application;

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

    public AddFavoritePokemon(UsersRepository repository) {
        this.repository = repository;
    }

    public void execute(String userId, AddFavoritePokemonRequestBody requestBody) throws UserNotFoundException, PokemonAlreadyFavoriteException {
        User user = repository.findByUserId(new UserId(UUID.fromString(userId)));
        user.addFavoritePokemon(new Pokemon(requestBody.getPokemonId()));
        repository.save(user);
    }
}
