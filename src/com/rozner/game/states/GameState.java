package com.rozner.game.states;

import com.rozner.game.Handler;
import com.rozner.game.entities.creatures.Player;
import com.rozner.worlds.TestWorld;
import com.rozner.worlds.World;
import com.rozner.worlds.WorldManager;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private TestWorld world;
    private WorldManager worldManager = WorldManager.getInstance();

    public GameState(Handler handler) {
        super(handler);
        world = worldManager.getCurrentWorld();
        handler.setWorld(world);
        if (world == null) {
            player = new Player(handler, 0, 0);
        } else {
            player = new Player(handler, world.getPlayerSpawnX(), world.getPlayerSpawnY());
        }
    }

    @Override
    public void tick() {
        player.tick();
        worldManager.tick();

    }

    @Override
    public void render(Graphics g) {
        worldManager.render(g);
        player.render(g);

    }
}
