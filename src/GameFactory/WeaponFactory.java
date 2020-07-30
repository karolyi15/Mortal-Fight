package GameFactory;

public class WeaponFactory implements AbstractFactory{

    @Override
    public Character getCharacter(CharacterStyle characterStyle) {
        return null;
    }

    @Override
    public Weapon getWeapon(WeaponStyle weaponStyle) {
        if(weaponStyle == WeaponStyle.SIMPLEWEAPON){
            return new SimpleWeapon();
        }else if(weaponStyle == WeaponStyle.SPECIALWEAPON){
            return null;
        }else{
            return null;
        }
    }
}
