(ns page.countdown
  (:require
   [reagent.core :as r]
   [user :refer [timeout]]
   [lib.ui :refer [add-page-site]]))

(defn countdown-page [route-data]
  (let [state (r/atom 0)]
    (fn [route-data]
      (timeout #(swap! state inc) 1000)
      [:div
       "Seconds Elapsed: "
       @state])))

(add-page-site countdown-page :demo-countdown)