package lunarlander;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tog
 */
public class GameState
{

    private float pos;
    private float speed;
    private final float tolerance;
    private boolean crashed;

    public GameState(float pos, float speed, float tolerance)
    {
        this.pos = pos;
        this.speed = speed;
        this.tolerance = tolerance;
        this.crashed = false;
    }

    //Must be called for every 10 millisecond
    public void update(boolean thrust)
    {

        float dt = 0.01f;
        float acc = -9.82f;
        if (thrust)
        {
            acc = 5.0f;
        }
        speed = speed + acc * dt;
        pos = pos + speed * dt;
        if (pos <= 0)
        {
            pos = 0.0f;
            if (speed < -tolerance)
            {
                crashed = true;
            }
            speed = 0.0f;
        }
    }

    public float getPos()
    {
        return pos;
    }

    public boolean getCrashed()
    {
        return crashed;
    }
}
