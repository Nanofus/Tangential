package fi.nano.tangential.gameLogic;

import fi.nano.tangential.gameLogic.entities.Actor;
import fi.nano.tangential.gameLogic.enums.Direction;
import fi.nano.tangential.gameLogic.enums.Stance;
import static fi.nano.tangential.gameLogic.enums.Stance.*;
import java.util.Random;

/**
 * Luokka, joka vastaa tekoälyn ohjaamien vihollishahmojen siirroista.
 *
 * @author Nanofus
 */
public class AI {

    private final Level level;
    private Actor me;

    private Stance stance = Wander;

    private Actor target;
    private Direction targetDirection;
    private boolean noticedTarget = false;
    private int noticeDistance = 15;

    private Random random = new Random();

    /**
     * Konstruktori luo uuden AI-instanssin
     * @param me Actor jolle AI kuuluu
     * @param level Taso
     */
    public AI(Actor me, Level level) {
        if (level != null) {
            target = level.GetPlayer();
        }

        this.me = me;
        this.level = level;
    }

    /**
     * Metodi antaa AI:lle luvan tehdä siirtonsa.
     */
    public void MakeMove() {
        int action = random.nextInt(2);
        //int action = 0;

        UpdateStance();

        switch (action) {
            case 0:
                if (stance == Wander) {
                    MoveRandomly();
                } else if (stance == Chase) {
                    Chase();
                    //} else if (stance == Flee) {
                    //    Flee();
                }
                break;
            case 1: //Skippaa vuoro
                SkipTurn();
                break;
        }

        //System.out.println("AI made a move!");
    }

    private void UpdateStance() {
        if (level != null) {
            target = level.GetPlayer();

            float health = me.GetHealth();
            float maxHealth = me.GetMaxHealth();

            if (level.GetDistance(me.GetPosition(), target.GetPosition()) <= noticeDistance) {
                stance = Chase;
                //} else if (health <= (maxHealth / 4)) {
                //stance = Flee;
            } else {
                stance = Wander;
            }
        }
    }

    /**
     * Palauttaa suunnan, jossa pelaaja on tästä katsottuna
     *
     * @return Suunta
     */
    public Direction GetTargetDirection() {
        return level.PositionDirection(me.GetPosition(), target.GetPosition());
    }

    private void Chase() {
        int choice = random.nextInt(7);
        targetDirection = GetTargetDirection();
        switch (targetDirection) {
            case UP:
                if (choice == 0) {
                    me.Move(1, 0);
                } else if (choice == 1) {
                    me.Move(-1, 0);
                } else {
                    me.Move(0, -1);
                }
                break;
            case DOWN:
                if (choice == 0) {
                    me.Move(1, 0);
                } else if (choice == 1) {
                    me.Move(-1, 0);
                } else {
                    me.Move(0, 1);
                }
                break;
            case LEFT:
                if (choice == 0) {
                    me.Move(0, -1);
                } else if (choice == 1) {
                    me.Move(0, 1);
                } else {
                    me.Move(-1, 0);
                }
                break;
            case RIGHT:
                if (choice == 0) {
                    me.Move(0, 1);
                } else if (choice == 1) {
                    me.Move(0, -1);
                } else {
                    me.Move(1, 0);
                }
                break;
        }
    }

    /*private void Flee() {
     targetDirection = GetTargetDirection();
     switch (targetDirection) {
     case UP:
     me.Move(0, -1);
     break;
     case DOWN:
     me.Move(0, 1);
     break;
     case LEFT:
     me.Move(-1, 0);
     break;
     case RIGHT:
     me.Move(1, 0);
     break;
     }
     }*/
    private void MoveRandomly() {
        int moveDirection = random.nextInt(4);
        switch (moveDirection) {
            case 0:
                me.Move(-1, 0);
                break;
            case 1:
                me.Move(1, 0);
                break;
            case 2:
                me.Move(0, 1);
                break;
            case 3:
                me.Move(0, -1);
                break;
        }
    }

    private void SkipTurn() {
        me.Rest();
    }

    public Stance GetStance() {
        return stance;
    }

}
