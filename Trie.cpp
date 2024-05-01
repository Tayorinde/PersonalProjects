#include "Trie.h"
#include <iostream>

Trie::TrieNode::TrieNode() {
    for (int i = 0; i < 26; ++i) {
        children[i] = nullptr;
    }
    isEndOfWord = false;
}

Trie::Trie() {
    root = new TrieNode();
    wordCount = 0;
    nodeCount = 1;
}

Trie::Trie(const Trie& other) {
    root = new TrieNode();
    wordCount = other.wordCount;
    nodeCount = 1;
    copyTrie(other, root, other.root);
}

Trie::~Trie() {
    deleteTrie(root);
}

Trie& Trie::operator=(const Trie& other) {
    if (this == &other) {
        return *this;
    }

    deleteTrie(root);

    root = new TrieNode();
    wordCount = other.wordCount;
    nodeCount = 1;
    copyTrie(other, root, other.root);

    return *this;
}

bool Trie::insert(const std::string& word) {
    TrieNode* current = root;
    for (char ch : word) {
        int index = ch - 'a';
        if (!current->children[index]) {
            current->children[index] = new TrieNode();
            nodeCount++;
        }
        current = current->children[index];
    }

    if (current->isEndOfWord) {
        return false; // Duplicate word
    }

    current->isEndOfWord = true;
    wordCount++;
    return true;
}

int Trie::count() const {
    return wordCount;
}

int Trie::getSize() const {
    return nodeCount;
}

bool Trie::find(const std::string& word) const {
    TrieNode* current = root;
    for (char ch : word) {
        int index = ch - 'a';
        if (!current->children[index]) {
            return false;
        }
        current = current->children[index];
    }
    return current->isEndOfWord;
}

int Trie::completeCount(const std::string& prefix) const {
    TrieNode* current = root;
    for (char ch : prefix) {
        int index = ch - 'a';
        if (!current->children[index]) {
            return 0;
        }
        current = current->children[index];
    }

    return countWordsStartingFromNode(current);
}

std::vector<std::string> Trie::complete(const std::string& prefix) const {
    std::vector<std::string> completions;
    TrieNode* current = root;
    for (char ch : prefix) {
        int index = ch - 'a';
        if (!current->children[index]) {
            return completions;
        }
        current = current->children[index];
    }

    findCompletions(prefix, current, completions);

    return completions;
}

void Trie::copyTrie(const Trie& other, TrieNode*& current, TrieNode* otherNode) {
    for (int i = 0; i < 26; ++i) {
        if (otherNode->children[i]) {
            current->children[i] = new TrieNode();
            nodeCount++;
            copyTrie(other, current->children[i], otherNode->children[i]);
        }
    }
    current->isEndOfWord = otherNode->isEndOfWord;
}

void Trie::deleteTrie(TrieNode* current) {
    for (int i = 0; i < 26; ++i) {
        if (current->children[i]) {
            deleteTrie(current->children[i]);
        }
    }
    delete current;
    nodeCount--;
}

int Trie::countWordsStartingFromNode(TrieNode* node) const {
    int count = 0;
    if (node->isEndOfWord) {
        count++;
    }

    for (int i = 0; i < 26; ++i) {
        if (node->children[i]) {
            count += countWordsStartingFromNode(node->children[i]);
        }
    }

    return count;
}

void Trie::findCompletions(const std::string& prefix, TrieNode* current, std::vector<std::string>& completions) const {
    if (current->isEndOfWord) {
        completions.push_back(prefix);
    }

    for (int i = 0; i < 26; ++i) {
        if (current->children[i]) {
            char ch = 'a' + i;
            findCompletions(prefix + ch, current->children[i], completions);
        }
    }
}
