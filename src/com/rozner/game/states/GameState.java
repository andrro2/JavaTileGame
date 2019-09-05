package com.rozner.game.states;

import com.rozner.game.Handler;
import com.rozner.game.entities.creatures.Player;
import com.rozner.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 500,500);
    }

    @Override
    public void tick() {
        player.tick();
        world.tick();

    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);

    }
}
