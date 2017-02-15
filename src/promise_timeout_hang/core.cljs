(ns promise-timeout-hang.core
  (:require [cljs-lambda.util :as lambda]
            [cljs-lambda.context :as ctx]
            [cljs-lambda.macros :refer-macros [deflambda]]
            [cljs.reader :refer [read-string]]
            [cljs.nodejs :as nodejs]
            [cljs.core.async :as async]
            [promesa.core :as p])
  (:require-macros [cljs.core.async.macros :refer [go]]))


(deflambda promise-hang [{:keys [timeout] :as input} ctx]
           (prn "Starting function")
           (-> (p/delay timeout)
               (p/then (fn [] (prn "Finished function")))))
