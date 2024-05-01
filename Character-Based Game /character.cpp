#include "character.h"
#include <ctime>
#include <cstdlib>
using namespace std;

//----------------------------------------------------------------
// Character::Character: Default constructor for Character class.
// Initializes member variables with default values.
//----------------------------------------------------------------
Character::Character()
{

    name = "invalid";
    role = "N/A";
    hitPoints = 0;
    attackBonus = 0;
    damageBonus = 0;
    armorClass = 0;
}
//----------------------------------------------------------------
// Character::Character: Parameterized constructor for Character class.
// Initializes member variables with provided values.
//
// Parameters:
//    name1 (string) - The character's name.
//    role1 (string) - The character's role.
//    hitPoints1 (int) - The character's hit points.
//    attackBonus1 (int) - The character's attack bonus.
//    damageBonus1 (int) - The character's damage bonus.
//    armorClass1 (int) - The character's armor class.
Character::Character(string name1, string role1, int hitPoints1, int attackBonus1, int damageBonus1, int armorClass1)
{
    // Initialize member variables with provided values
    name = name1;
    role = role1;
    hitPoints = hitPoints1;
    attackBonus = attackBonus1;
    damageBonus = damageBonus1;
    armorClass = armorClass1;
}
//----------------------------------------------------------------
// Character::print: Print character details to the given ostream.
//
// Parameters:
//    os (ostream&) - The output stream to print to.
//----------------------------------------------------------------
void Character::print(ostream &os) const
{
    // Print character details to the ostream
    os << "Name: " << name << endl;
    os << "Role: " << role << endl;
    os << "Hit Points: " << hitPoints << endl;
    os << "Attack Bonus: " << attackBonus << endl;
    os << "Damage Bonus: " << damageBonus << endl;
    os << "Armor Class: " << armorClass << endl;
}
//----------------------------------------------------------------
// Character::attack: Perform an attack on another character.
//
// Parameters:
//    otherCharacter (Character&) - The character to attack.
//----------------------------------------------------------------
void Character::attack(Character &otherCharacter)
{
    cout << name << " attacks!" << endl;
    // Implement the attack logic
    // Roll a 20-sided die and add the attack bonus
    int attackRoll = (rand() % 20) + 1;
    cout << "Attack roll: " << attackRoll << " + " << attackBonus << " = " << attackRoll + attackBonus;
    // Check if the attack hits based on armor class
    if (attackRoll + attackBonus > otherCharacter.armorClass)
    {
        cout << " --> HIT!" << endl;

        // Roll a 10-sided die and add the damage bonus
        int damageRoll = (rand() % 10) + 1;

        cout << "Damage: " << damageRoll << " + " << damageBonus << " = " << damageRoll + damageBonus << endl;
        // Apply damage to the other character
        otherCharacter.damage(damageRoll + damageBonus);

        cout << otherCharacter.getName() << " has " << otherCharacter.getHealth() << " hit points remaining." << endl;
    }
    else
    {
        cout << " --> MISS!" << endl;
    }
}
//----------------------------------------------------------------
// Character::damage: Apply damage to the character.
//
// Parameters:
//    amount (int) - The amount of damage to apply.
//----------------------------------------------------------------
void Character::damage(int amount)
{
    // Subtract the given amount from hit points
    hitPoints -= amount;

    // Ensure hit points don't go below zero
    if (hitPoints < 0)
    {
        hitPoints = 0;
    }
}
//----------------------------------------------------------------
// Character::getHealth: Get the character's current hit points.
//
// Returns:
//    The character's current hit points (int).
//----------------------------------------------------------------
int Character::getHealth() const
{
    return hitPoints;
}
//----------------------------------------------------------------
// Character::getName: Get the character's name.
//
// Returns:
//    The character's name (string).
//----------------------------------------------------------------
string Character::getName() const
{
    return name;
}

//----------------------------------------------------------------
// Character::getRole: Get the character's role.
//
// Returns:
//    The character's role (string).
//----------------------------------------------------------------
string Character::getRole() const
{
    return role;
}
