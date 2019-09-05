package com.rozner.game.entities.creatures;

import com.rozner.game.Handler;
import com.rozner.game.gfx.Animation;
import com.rozner.game.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {


    private int animationCounter = 0;
    private int animationIndex;
    private BufferedImage[] animation;
    private BufferedImage[] moveAnimation = Assets.playerMoveAnimationRight;
    private BufferedImage[] stillAnimation = Assets.playerStillRight;

    private Animation animRight;



    public Player(Handler handler, float x, float y) {

        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 36;
        bounds.y = 36;
        bounds.width = 18;
        bounds.height = 24;

        animRight = new Animation(500, Assets.playerMoveAnimationRight);
    }

    @Override
    public void tick() {
        animRight.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
            animation = moveAnimation;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            animation = moveAnimation;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
            stillAnimation = Assets.playerStillLeft;
            moveAnimation = Assets.playerMoveAnimationLeft;
            animation = moveAnimation;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
            stillAnimation = Assets.playerStillRight;
            moveAnimation = Assets.playerMoveAnimationRight;
            animation = moveAnimation;
        }
        if (!handler.getKeyManager().up && !handler.getKeyManager().down && !handler.getKeyManager().left && !handler.getKeyManager().right) {
            animation = stillAnimation;
        }


    }

    @Override
    public void render(Graphics g) {
        animationCounter++;
        if (animationCounter > 5) {
            animationCounter = 0;
            animationIndex++;



        }
        if (animationIndex > animation.length-1) {
            animationIndex = 0;
        }
        g.drawImage(animation[animationIndex], (int) (x - handler.getGameCamera().getxOffset()), (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.red);
        /*g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
        */
    }

    private BufferedImage getCurrentAnimationFrame(){
        return  animRight.getCurrentFrame();
    }
}
