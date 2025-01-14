package com.lasalle.sd2.g2.users.infrastructure.controller;

import com.google.gson.Gson;
import com.lasalle.sd2.g2.pokemons.infrastructure.repository.InMemoryFavoriteRepository;
import com.lasalle.sd2.g2.users.application.AddFavoritePokemon;
import com.lasalle.sd2.g2.users.application.dto.AddFavoritePokemonRequestBody;
import com.lasalle.sd2.g2.users.application.dto.GenericErrorResponseBody;
import com.lasalle.sd2.g2.users.domain.exceptions.PokemonAlreadyFavoriteException;
import com.lasalle.sd2.g2.users.domain.exceptions.UserNotFoundException;
import com.lasalle.sd2.g2.users.infrastructure.repository.InMemoryUsersRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class UsersFavoriteServlet extends HttpServlet {

    private static final long serialVersionUID = -3472618031018163346L;

    private static final String USER_ID_HEADER = "user_id";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AddFavoritePokemon addFavoritePokemon = new AddFavoritePokemon(new InMemoryUsersRepository(), new InMemoryFavoriteRepository());

        String userId = req.getHeader(USER_ID_HEADER);
        BufferedReader reader = req.getReader();
        Gson gson = new Gson();

        AddFavoritePokemonRequestBody requestBody = gson.fromJson(reader, AddFavoritePokemonRequestBody.class);

        try {
            addFavoritePokemon.execute(userId, requestBody);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (UserNotFoundException | PokemonAlreadyFavoriteException e) {
            GenericErrorResponseBody responseBody = new GenericErrorResponseBody(e.getMessage());
            resp.getWriter().println(new Gson().toJson(responseBody));
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
