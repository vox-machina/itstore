(ns voxmachina.itstore.postrepo-fs-test
  (:require  [clojure.test :refer [deftest is testing]]
             [voxmachina.itstore.postrepo :as its]
             [voxmachina.itstore.postrepo-fs :as itsfile]))

(def data-path (str (System/getenv "HOME") "/.isn/test/data"))

(def pr-fs (itsfile/repo {:data-path data-path}))

(deftest create-post-test
  (testing "Receive correct representation on post creation"
    (is (= {:name "FSTest"} (its/create pr-fs "posts/flibble.edn" {:name "FSTest"})))))
