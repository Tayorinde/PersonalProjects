//NAME: Tayo- Ayorinde Jehoshua
//Project Name: Project #2 – FIGHT!
//Description: a combat simulator for a simplified roll playing game

#include <iostream>
#include <ctime>
#include <cstdlib>
#include "character.h"

using namespace std;
int main()
{
    // Seed the random number generator
    // srand(time(NULL));
    srand(0);
    cout << rand() % 20 + 1 << endl;

    // Prompt user for character details for two characters
    string name1, name2, role1, role2;
    int hitPoints1, hitPoints2, attackBonus1, attackBonus2, damageBonus1, damageBonus2, armorClass1, armorClass2;

    cout << "First character name: ";
    // Input and initialize Character 1
    cin >> name1;

    cout << name1 << "'s role: ";
    cin >> role1;

    cout << name1 << " the " << role1 << "'s hit points?";
    cin >> hitPoints1;

    cout << name1 << " the " << role1 << "'s attack bonus?";
    cin >> attackBonus1;

    cout << name1 << " the " << role1 << "'s damage bonus?";
    cin >> damageBonus1;

    cout << name1 << " the " << role1 << "'s armor class?";
    cin >> armorClass1;

    cout << "Character summary" << endl;
    cout << "----------------" << endl;
    Character character1(name1, role1, hitPoints1, attackBonus1, damageBonus1, armorClass1);
    character1.print(cout);

    cout << "Second character name: ";

    // Input and initialize Character 1
    cin >> name2;
    cout << name2 << "'s role: ";
    cin >> role2;
    cout << name2 << " the " << role2 << "'s hit points?";
    cin >> hitPoints2;
    cout << name2 << " the " << role2 << "'s attack bonus?";
    cin >> attackBonus2;
    cout << name2 << " the " << role2 << "'s damage bonus?";
    cin >> damageBonus2;
    cout << name2 << " the " << role2 << "'s armor class?";
    cin >> armorClass2;
    cout << "Character summary" << endl;
    cout << "----------------" << endl;
    // Create Character objects
    Character character2(name2, role2, hitPoints2, attackBonus2, damageBonus2, armorClass2);
    character2.print(cout);

    // Simulate combat until one character reaches zero hit points
    cout << "Simulated combat: " << endl;
    // Simulate combat until one character reaches zero hit points
    while (character1.getHealth() > 0 && character2.getHealth() > 0)
    {
        // Character 1 attacks Character 2
        character1.attack(character2);
        if (character2.getHealth() <= 0)
        {

            cout << character2.getName() << " has been defeated!" << endl;
            break;
        }

        character2.attack(character1);

        // Check if Character 1 is still alive
        if (character1.getHealth() <= 0)
        {
            cout << character1.getName() << " has been defeated!" << endl;
            break;
        }
    }

    // Determine the winner
    if (character1.getHealth() > 0)
    {
        cout << character1.getName() << " wins!" << endl;
    }
    else if (character2.getHealth() > 0)
    {
        cout << character2.getName() << " wins!" << endl;
    }
    else
    {
        cout << "It's a draw!" << endl;
    }

    return 0;
}