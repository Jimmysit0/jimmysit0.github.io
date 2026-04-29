(ns website.frontend.app
  (:require [reagent.core :as r]        
            [reagent.dom.client :as rdomc]))

(defn get-system-theme []
  (if (and (exists? js/window.matchMedia)
           (.-matches (js/window.matchMedia "(prefers-color-scheme: dark)")))
    "dark"
    "light"))

(defonce theme (r/atom (get-system-theme)))

(defn toggle-theme []
  (let [new-theme (if (= @theme "light") "dark" "light")]
    (reset! theme new-theme)
    (.setAttribute (.-documentElement js/document) "data-theme" new-theme)))

(defn theme-toggle-btn []
  [:button.theme-toggle-btn {:on-click toggle-theme
                             :aria-label "Alternar tema"}
   ;; svg del sol
   [:svg.icon-sun {:viewBox "0 0 24 24" :fill "none" :stroke "currentColor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}
    [:circle {:cx "12" :cy "12" :r "5"}]
    [:line {:x1 "12" :y1 "1" :x2 "12" :y2 "3"}]
    [:line {:x1 "12" :y1 "21" :x2 "12" :y2 "23"}]
    [:line {:x1 "4.22" :y1 "4.22" :x2 "5.64" :y2 "5.64"}]
    [:line {:x1 "18.36" :y1 "18.36" :x2 "19.78" :y2 "19.78"}]
    [:line {:x1 "1" :y1 "12" :x2 "3" :y2 "12"}]
    [:line {:x1 "21" :y1 "12" :x2 "23" :y2 "12"}]
    [:line {:x1 "4.22" :y1 "19.78" :x2 "5.64" :y2 "18.36"}]
    [:line {:x1 "18.36" :y1 "5.64" :x2 "19.78" :y2 "4.22"}]]

   ;; svg de la luna 
   [:svg.icon-moon {:viewBox "0 0 24 24" :fill "none" :stroke "currentColor" :stroke-width "2" :stroke-linecap "round" :stroke-linejoin "round"}
    [:path {:d "M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"}]]])

(defn definition-item [num content]
  [:div.definition-item
   [:span.definition-number num]
   [:div.definition-text content]])

(defn dict-link [text href]
  [:a {:href href :target "_blank"} text])

(defn footer-section []
  [:footer.footer
   [:span "made with ❤️ powered by "
    [dict-link "shadow-cljs" "https://shadow-cljs.org"]
    " source "
    [dict-link "github" "https://github.com/Jimmysit0/website"] 
    ]])

(defn dictionary-portfolio []
  [:div
   [theme-toggle-btn]

   [:div.portfolio-container
    [:div.header-section
     [:h1.term-title "jimmysit0" [:span.term-article ", el"]]
     [:p.pronunciation "[Juan Andrés Pedrozo] he/him"]]

    [:hr.divider]

    [:div.definition-list
     [definition-item "1"
      [:span "computer science student at "
       [dict-link "Universidad Nacional de Colombia" "https://unal.edu.co"]]]
     [definition-item "2" "passionate about functional programming, mathematics, and problem-solving"]
     [definition-item "3"
      [:span "someone you can contact via "
       [dict-link "email" "mailto:jpedrozoa@unal.edu.co"] ", "
       [dict-link "github" "https://github.com/jimmysit0"] ", "
       [dict-link "cv" "https://jimmysit0.s-ul.eu/AIcWXkKP"]]]
     [definition-item "4"
      [:span "someone looking for new projects to join"]]
     [definition-item "5"
      [:span "someone who strives to learn new things, and is interested in: "
       [dict-link "Clojure" "https://clojure.org"] ", "
       [dict-link "Java" "https://www.java.com/"] ", "
       [dict-link "C++" "https://isocpp.org/"]]]]]
   
   [footer-section]])

(defonce root (rdomc/create-root (.getElementById js/document "app")))

(defn ^:export init []
  (.setAttribute (.-documentElement js/document) "data-theme" @theme)
  (rdomc/render root [dictionary-portfolio]))