clj-translator
==================

This little clojure app uses the Google Translation API to translate text from a base to a target language.
To do so a simple url call is sufficient, so the programm isnt very complex.

Example
-----------
	user> (use 'de.herrnorbert.translator)
	nil
	user> (translate :de :en "Die Gedanken sind frei!")
	"The thoughts are free!"
	user> (translate :en :de "size")
	"Größe"

Problems
-----------
There are some problems with some unicode characters.
