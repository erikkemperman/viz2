package org.powertac.visualizer.repository;

import org.powertac.visualizer.domain.Game;
import org.powertac.visualizer.domain.enumeration.GameType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Game entity.
 */
public interface GameRepository extends JpaRepository<Game,Long> {

    @Query("select game from Game game where game.owner.login = :login")
    List<Game> findByOwnerIsCurrentUser(@Param("login") String login);

    @Query("select game from Game game where game.shared = TRUE or game.owner.login = :login")
    List<Game> findByOwnerIsCurrentUserOrShared(@Param("login") String login);

    @Query("select game from Game game where"
    + " (game.shared = TRUE or game.owner.login = :login)"
    + " and game.name = :name and game.type = :type")
    List<Game> findByNameAndType(@Param("login") String login, @Param("name") String name, @Param("type") GameType type);

}
