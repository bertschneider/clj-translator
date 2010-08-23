(ns de.herrnorbert.translator
  "A little translator which uses the Google Translation API."
  (:gen-class)
  (:use [de.herrnorbert.languages]))

;
; ---- Some constants ----
;
(def base-url "http://ajax.googleapis.com/ajax/services/language/translate")
(def base-response-path [:content :responseData :translatedText])
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
  (str base-url "?" (com.twinql.clojure.http/encode-query
                     (-> base-params
                         (assoc :langpair (trans-param from to))
                         (assoc :q text)))))

;
; ---- Translate ----
;
(defn translate [from to text]
  "Translates the given text from :from to :to via Googles Translation API.
  from and to have to be valid language keys!
  Example: (translate :en :de \"Die Gendanken sind frei!\""
  (-> (build-translate-url from to text)
      (com.twinql.clojure.http/get :as :json :headers {"charset" "utf-8"})
      (get-in base-response-path)))

