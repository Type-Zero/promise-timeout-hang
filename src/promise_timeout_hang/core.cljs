(ns promise-timeout-hang.core
  (:require [cljs-lambda.util :as lambda]
            [cljs-lambda.context :as ctx]
            [cljs-lambda.macros :refer-macros [deflambda]]
            [cljs.reader :refer [read-string]]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :as async]
            [promesa.core :as p])
  (:require-macros [cljs.core.async.macros :refer [go]]))


(deflambda promise-hang [input ctx]
           (prn "Starting function")
           (-> (p/delay 5000)
               (p/then (fn [] (prn "Finished function")))))

(deflambda promise-timeout [input ctx]
           (prn "Starting function")
           (-> (p/delay 5000)
               (p/timeout 2000)
               (p/then (fn [] (prn "Finished function")))))
