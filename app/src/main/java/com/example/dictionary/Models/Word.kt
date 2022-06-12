package com.example.dictionary.Models

class Word {
    var titleWord : String? = null
    var descWord : String? = null

    constructor(titleWord: String?, descWord: String?) {
        this.titleWord = titleWord
        this.descWord = descWord
    }

    constructor()

    override fun toString(): String {
        return "Word(titleWord=$titleWord, descWord=$descWord)"
    }
}