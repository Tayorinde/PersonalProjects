#include <iostream>
#include <fstream> // For file operations
#include "Trie.h"

int main()
{
    Trie myTrie;

    // Open the "wordlist_mac.txt" file containing a list of words
    std::ifstream wordFile("wordlist_mac.txt");
    if (!wordFile.is_open())
    {
        std::cerr << "Failed to open the wordlist_mac.txt file." << std::endl;
        return 1;
    }

    std::string word;
    while (std::getline(wordFile, word))
    {
        // Insert words from the file into the Trie
        myTrie.insert(word);
    }

    wordFile.close();
    while (true)
    {
        // Test functions
        std::cout << "Please enter a word prefix (or press enter to exit): " << std::endl;
        std::string prefix;

        std::getline(std::cin, prefix);

        if (prefix.empty())
        {
            break;
            // i am back
        }

        // to find completions for the entered prefix
        std::vector<std::string> completions = myTrie.complete(prefix);

        if (completions.empty())
        {
            std::cout << "No completions found for the prefix " << prefix << ". " << std::endl;
        }
        else
        {
            std::cout << "There are " << completions.size() << "completionss for prefix " << prefix << " Show completions? ";
            std::string response;
            std::getline(std::cin, response);

            if (response == "yes" || response == "Yes")
            {
                std::cout << "Completions " << std::endl;
                std::cout << "........" << std::endl;
                for (const std::string &completion : completions)
                {
                    std::cout << completion << std::endl;
                }
            }
        }
    }
    return 0;
}
