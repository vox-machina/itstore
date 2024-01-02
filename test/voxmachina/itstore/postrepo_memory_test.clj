(ns voxmachina.itstore.postrepo-memory-test
  (:require [clojure.test :refer [deftest is testing]]
            [voxmachina.itstore.postrepo :as its]
            [voxmachina.itstore.postrepo-memory :as itsmem]))

(def pr-mem (itsmem/repo (atom {})))

(deftest create-post-test
  (testing "Receive correct representation on post creation"
    (is (= {"1" {:name "CreateTest"}} (its/create pr-mem "1" {:name "CreateTest"})))))

(deftest fetch-post-test
  (testing "Retrieve a post from post repo"
    (is (= {:name "CreateTest"} (its/fetch pr-mem "1")))))

