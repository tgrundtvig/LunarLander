/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import applicationapi.graphics.Color;
import applicationapi.graphics.Sprite;
import applicationapi.graphics.SpriteBuilder;
import applicationapi.graphics.SpriteFactory;

/**
 *
 * @author tog
 */
public class SpriteUtil
{
    public static Sprite createBall(SpriteFactory f, int r, Color fill, Color border, int anchorX, int anchorY)
    {
        int size = r + r + 1;
        int r2 = r*r;
        SpriteBuilder bld = f.newSprite(size, size);
        for(int y = 0; y < size; ++y)
        {
            for(int x = 0; x < size; ++x)
            {
                int dx = x - r;
                int dy = y - r;
                int dist2 = dx*dx + dy*dy;
                if(dist2 <= r2)
                {
                    bld.setPixel(x, y, fill);
                }
                else
                {
                    bld.setPixel(x, y, border);
                }
            }
        }
        bld.setAnchor(anchorX, anchorY);
        return bld.build();
    }
    
    public static Sprite createBox(SpriteFactory f, int width, int height, Color c, int anchorX, int anchorY)
    {
        SpriteBuilder bld = f.newSprite(width, height);
        for(int y = 0; y < height; ++y)
        {
            for(int x = 0; x < width; ++x)
            {
                bld.setPixel(x, y, c);
            }
        }
        bld.setAnchor(anchorX, anchorY);
        return bld.build();
    }
}
