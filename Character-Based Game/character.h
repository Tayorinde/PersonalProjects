#include <string>
#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

class Character {
private:
    string name;
    string role;
    int hitPoints;
    int attackBonus;
    int damageBonus;
    int armorClass;

public:
    // Constructors

    //------------------------------------------------------------
    // Character::Character: Default constructor for the Character class.
    // Initializes member variables with default values.
    //------------------------------------------------------------
    Character();

    //------------------------------------------------------------
    // Character::Character: Parameterized constructor for the Character class.
    // Initializes member variables with provided values.
    //
    // Parameters:
    //    name (string) - The character's name.
    //    role (string) - The character's role.
    //    hitPoints (int) - The character's hit points.
    //    attackBonus (int) - The character's attack bonus.
    //    damageBonus (int) - The character's damage bonus.
    //    armorClass (int) - The character's armor class.
    //------------------------------------------------------------
    Character(string name, string role, int hitPoints, int attackBonus, int damageBonus, int armorClass);

    // Member functions

    //------------------------------------------------------------
    // Character::print: Print character details to the given ostream.
    //
    // Parameters:
    //    os (ostream&) - The output stream to print to.
    //------------------------------------------------------------
    void print(ostream& os) const;

    //------------------------------------------------------------
    // Character::attack: Perform an attack on another character.
    //
    // Parameters:
    //    otherCharacter (Character&) - The character to attack.
    //------------------------------------------------------------
    void attack(Character& otherCharacter);

    //------------------------------------------------------------
    // Character::damage: Apply damage to the character.
    //
    // Parameters:
    //    amount (int) - The amount of damage to apply.
    //------------------------------------------------------------
    void damage(int amount);

    //------------------------------------------------------------
    // Character::getHealth: Get the character's current health (hit points).
    //
    // Returns:
    //    int - The character's current hit points.
    //------------------------------------------------------------
    int getHealth() const;

    //------------------------------------------------------------
    // Character::getName: Get the character's name.
    //
    // Returns:
    //    string - The character's name.
    //------------------------------------------------------------
    string getName() const;

    //------------------------------------------------------------
    // Character::getRole: Get the character's role.
    //
    // Returns:
    //    string - The character's role.
    //------------------------------------------------------------
    string getRole() const;
};
