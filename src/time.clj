(ns time
  (:require
   [taoensso.timbre :as log :refer [tracef debug debugf info infof warn error errorf]]
   [modular.date :refer [now-str]]
   [clojure.core.async :as async  :refer [<! <!! >! >!! put! chan go go-loop]]
   [webly.ws.core :refer [send-all! send-response connected-uids]]))

(defn start-time-pusher! []
  (go-loop []
    (<! (async/timeout 5000)) ; 5 seconds
    (let [snow (now-str)]
      (info "sending time: " snow)
      (send-all! [:demo/time {:time snow}]))
    (recur)))

(start-time-pusher!)
