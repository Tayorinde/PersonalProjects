#ifndef TRIE_H
#define TRIE_H

#include <string>
#include <vector>

class Trie {
public:
    Trie();
    Trie(const Trie& other);
    ~Trie();
    Trie& operator=(const Trie& other);

    bool insert(const std::string& word);
    int count() const;
    int getSize() const;
    bool find(const std::string& word) const;
    int completeCount(const std::string& prefix) const;
    std::vector<std::string> complete(const std::string& prefix) const;

private:
    struct TrieNode {
        TrieNode* children[26]; // Assuming only lowercase English letters
        bool isEndOfWord;
        TrieNode();
    };

    TrieNode* root;
    int wordCount;
    int nodeCount;

    void copyTrie(const Trie& other, TrieNode*& current, TrieNode* otherNode);
    void deleteTrie(TrieNode* current);
    int countWordsStartingFromNode(TrieNode *node) const;
    void findCompletions(const std::string &prefix, TrieNode *current, std::vector<std::string> &completions) const;
};

#endif
