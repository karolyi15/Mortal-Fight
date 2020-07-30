package GameFactory;

public class CharacterFactory implements AbstractFactory{

    @Override
    public Character getCharacter(CharacterStyle characterStyle) {
        if(characterStyle == CharacterStyle.WARRIOR){
            return new Warrior();
        }else{
            return null;
        }
    }

    @Override
    public Weapon getWeapon(WeaponStyle weaponStyle) {
        return null;
    }

}
