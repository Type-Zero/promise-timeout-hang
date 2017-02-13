(defproject promise-timeout-hang "0.1.0-SNAPSHOT"
  :description "FIXME"
  :url "http://please.FIXME"
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [org.clojure/clojurescript "1.8.51"]
                 [org.clojure/core.async    "0.2.395"]
                 [io.nervous/cljs-lambda    "0.3.4"]]
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-npm       "0.6.0"]
            [lein-doo       "0.1.7"]
            [io.nervous/lein-cljs-lambda "0.6.4"]]
  :npm {:dependencies [[source-map-support "0.4.0"]]}
  :source-paths ["src"]
  :cljs-lambda
  {:defaults      {:role "FIXME"}
   :resource-dirs ["static"]
   :functions
   [{:name   "promise-hang"
     :invoke promise-timeout-hang.core/promise-hang}
    {:name   "promise-timeout"
     :invoke promise-timeout-hang.core/promise-timeout}]}
  :cljsbuild
  {:builds [{:id "promise-timeout-hang"
             :source-paths ["src"]
             :compiler {:output-to     "target/promise-timeout-hang/promise_timeout_hang.js"
                        :output-dir    "target/promise-timeout-hang"
                        :source-map    "target/promise-timeout-hang/promise_timeout_hang.js.map"
                        :target        :nodejs
                        :language-in   :ecmascript5
                        :optimizations :advanced}}]})
