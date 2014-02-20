/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lunarlander;

import applicationapi.Application;
import applicationapi.Device;
import applicationapi.graphics.Canvas;
import applicationapi.graphics.Color;
import applicationapi.graphics.Sprite;
import applicationapi.graphics.SpriteFactory;
import applicationapi.input.keyboard.Key;
import applicationapi.input.keyboard.KeyEvent;
import applicationapi.input.keyboard.KeyboardListener;
import util.SpriteUtil;

/**
 *
 * @author tog
 */
public class LunarLander implements Application, KeyboardListener
{
    private final float ROCKET_HEIGHT = 100.0f;
    private Sprite body;
    private Sprite leftLeg;
    private Sprite rightLeg;
    private Sprite thrust;
    private Sprite pad;
    private boolean quit;
    private int width;
    private int height;
    private GameState state;
    private long stateTime;
    private boolean thrustOn;
    
    @Override
    public boolean initialize(Device device)
    {
        this.state = new GameState(ROCKET_HEIGHT, 0, 5.0f);
        this.thrustOn = false;
        quit = false;
        width = device.getScreen().getWidth();
        height = device.getScreen().getHeight();
        SpriteFactory sf = device.getScreen().getSpriteFactory();
        Color blank = sf.newColor(1, 1, 1, 0);
        Color black = sf.newColor(0, 0, 0, 1);
        Color red = sf.newColor(1, 0, 0, 1);
        body = SpriteUtil.createBall(sf, 30, black, blank, 30, 50);
        leftLeg = SpriteUtil.createBox(sf, 10, 40, black, 30, 0);
        rightLeg = SpriteUtil.createBox(sf, 10, 40, black, -20, 0);
        thrust = SpriteUtil.createBox(sf, 21, 21, red, 10, -10);
        pad = SpriteUtil.createBox(sf, 70, 10, black, 0, 0);
        device.getKeyboard().addKeyboardListener(this);
        this.stateTime = 0;
        return true;
    }

    @Override
    public boolean update(long time)
    {
        while(time >= stateTime + 10)
        {
            stateTime += 10;
            state.update(thrustOn);
        }    
        return !quit;
    }

    @Override
    public void draw(Canvas canvas)
    {
        if(state.getCrashed())
        {
            canvas.drawSprite(width/2, height-10, body);
        }
        else
        {
            int posX = (int) (0.5f * width);
            int posY = (int) ((1.0f - (state.getPos()/ROCKET_HEIGHT)) * (height-50));
            if(thrustOn) canvas.drawSprite(posX, posY, thrust);
            canvas.drawSprite(posX, posY, body);
            canvas.drawSprite(posX, posY, leftLeg);
            canvas.drawSprite(posX, posY, rightLeg);
            canvas.drawSprite((width / 2) - 35, height-10, pad);
        }
    }

    @Override
    public void destroy()
    {
        //Do nothing...
    }

    @Override
    public void onKeyPress(KeyEvent ke)
    {
        if(ke.getKey() == Key.VK_SPACE)
        {
            thrustOn = true;
        }
        else if(ke.getKey() == Key.VK_ESC)
        {
            quit = true;
        }
    }

    @Override
    public void onKeyRelease(KeyEvent ke)
    {
        if(ke.getKey() == Key.VK_SPACE)
        {
            thrustOn = false;
        }
    }
    
}
