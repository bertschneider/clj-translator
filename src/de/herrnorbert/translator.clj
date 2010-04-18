(ns de.herrnorbert.translator
  "A little translator which uses the Google Translation API"
  (:gen-class)
  (:use [de.herrnorbert.utils.url-utils]
        [de.herrnorbert.utils.request-utils]))

;
; ---- Some constants ----
;
(def base-url "http://ajax.googleapis.com/ajax/services/language/translate")
(def base-response-path ["responseData" "translatedText"])
(def base-params {:v "1.0",
                  :q ""
                  :langpair ""})

;
; ---- Translate URL ----
;
(defn trans-param [from to]
  "Concats the from and to part of the request."
  (str (name from) "|" (name to)))

(defn build-translate-url [from to text]
  "Builds the url of the translation request."
  (build-url base-url
             (-> base-params
                 (assoc :langpair (trans-param from to))
                 (assoc :q text))))

;
; ---- Translate ----
;
(defn translate [from to text]
  "Translates the given text from :from to :to via Googles Translation API.
  from and to have to be valid language keys!
  Example: (translate :en :de \"Die Gendanken sind frei!\""
  (get-in
   (request-json-url (build-translate-url from to text))
   base-response-path))
