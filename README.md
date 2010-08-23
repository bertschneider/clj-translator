clj-translator
==================

This little clojure app uses the Google Translation API to translate text from a base to a target language.
To do so a simple url call via the Apache HttpClient wrapper is sufficient.

Example
-----------
	user> (use 'de.herrnorbert.translator)
	nil
	user> (translate :de :en "Die Gedanken sind frei!")
	"The thoughts are free!"
	user> (translate :en :de "size")
	"Größe"


