package text_adventure;
/*
 * Payload for Commands From Input class
 */
public class Command{
    /* Text Adventure has the following Valid Moves */
    public enum ACTION{
        ATTACK, /* Standard Attack, only consuming turn */
        SPECIAL, /* Item or Spell, consume turn and item/mana */
        LIST, /* List out Spell or Item, does not consume */
        DETAIL /* Flavortext Something, does not consume */
    }

    public enum TYPE{
        ITEM,
        SPELL,
        MONSTER,
        PLAYER
    }

    public ACTION action;
    public int index;
    public TYPE type;
    public String name = null;

    public Command(){}
}