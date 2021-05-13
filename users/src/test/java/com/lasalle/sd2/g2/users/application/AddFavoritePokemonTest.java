package com.lasalle.sd2.g2.users.application;

import com.lasalle.sd2.g2.pokemons.domain.PokemonFavoriteRepository;
import com.lasalle.sd2.g2.users.application.dto.AddFavoritePokemonRequestBody;
import com.lasalle.sd2.g2.users.domain.User;
import com.lasalle.sd2.g2.users.domain.exceptions.PokemonAlreadyFavoriteException;
import com.lasalle.sd2.g2.users.domain.exceptions.UserNotFoundException;
import com.lasalle.sd2.g2.users.domain.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddFavoritePokemonTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PokemonFavoriteRepository favoriteRepository;

    @Mock
    private User user;

    @Test
    void executeThrowsUserNotFoundException() throws UserNotFoundException {
        when(usersRepository.findByUserId(any())).thenThrow(UserNotFoundException.class);

        AddFavoritePokemonRequestBody requestBody = new AddFavoritePokemonRequestBody(1);
        AddFavoritePokemon addFavoritePokemon = new AddFavoritePokemon(usersRepository, favoriteRepository);
        assertThrows(UserNotFoundException.class, () -> addFavoritePokemon.execute(UUID.randomUUID().toString(), requestBody));
    }

    @Test
    void executeAddedSuccessfully() throws UserNotFoundException, PokemonAlreadyFavoriteException {
        when(usersRepository.findByUserId(any())).thenReturn(user);
        doNothing().when(usersRepository).save(user);

        AddFavoritePokemonRequestBody requestBody = new AddFavoritePokemonRequestBody(123);
        AddFavoritePokemon addFavoritePokemon = new AddFavoritePokemon(usersRepository, favoriteRepository);

        addFavoritePokemon.execute(UUID.randomUUID().toString(), requestBody);

        verify(usersRepository, times(1)).save(any());
    }
}
