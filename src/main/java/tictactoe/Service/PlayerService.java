package tictactoe.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import tictactoe.Exception.PlayerException;
import tictactoe.Model.Player.Player;
import tictactoe.Model.Player.PlayerProperties;

/**
 * Contains two players and methods for alternating turns
 */
@Service
@RequestScope
public class PlayerService {
    private final Player p1;
    private final Player p2;

    public PlayerService(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Sets the player for the next turn
     */
    public void nextTurn() {
        // If P1 just went, make P2 next turn
        if (p1.isTurn()) {
            p1.setTurn(false);
            p2.setTurn(true);
        }
        // If P2 just went, make P1 next turn
        else {
            p1.setTurn(true);
            p2.setTurn(false);
        }
    }

    /**
     * Gets the player for the next turn
     * @return player for next turn
     */
    public Player getNextPlayer() {
        if (p1.isTurn()) {
            return p1;
        }
        else {
            return p2;
        }
    }

    /**
     * Updates the players based off of the game properties configuration
     * @param pp1 Player Properties for p1
     * @param pp2 Player Properties for p2
     * @throws PlayerException error info
     */
    public void configurePlayers(PlayerProperties pp1, PlayerProperties pp2) throws PlayerException {
        // Set color and symbol if player hasn't yet been updated
        if (!playersConfigured()) {
            // Update Player 1
            p1.setColor(pp1.getColor());
            p1.setSymbol(pp1.getSymbol());
            p1.setConfigured(true);

            // Update Player 2
            p2.setColor(pp2.getColor());
            p2.setSymbol(pp2.getSymbol());
            p2.setConfigured(true);
        }
        // Throw exception since players have already been configured
        else {
            throw new PlayerException("Players have already been configured");
        }
    }

    /**
     * Checks to ensure both players are updated
     * @return true/false
     */
    public boolean playersConfigured() {
        return p1.isConfigured() && p2.isConfigured();
    }
}
