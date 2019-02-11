(ns quil-practice.quilpractice20190210b
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defrecord Bit [img dx dy h w])


(defn lttr-file-name [l] (str "/resources/letters/cp6/" l ".png"))
(defn assoc-lttr-img [mp lt] (assoc mp (keyword (str lt)) (q/load-image (lttr-file-name lt))))

(defn create-bit-line [imgs dx h w]
  (for [i (range (count imgs))]
    {:dx (* dx i)
     :dy 0
     :img (nth imgs i)
     :h h
     :w w}))

(defn render-block [blk state]
  (let [blk-x (blk :x)
        blk-y (blk :y)
        bts (:bits blk)]
     (doseq [i bts]
       (q/image (get-in state (:img i)) (+ blk-x (:dx i)) (+ blk-y (:dy i)) (:h i) (:w i)))))
  
(defn setup []
  {:lttr (reduce assoc-lttr-img {} "abcdefghijklmnopqrstuvwxyz")
   :skull {:s2lt (q/load-image "/resources/skulls/cp_6_skull_2_t.png")
           :s2lb (q/load-image "/resources/skulls/cp_6_skull_2_b.png")
           :s2rt (q/load-image "/resources/skulls/cp_6_skull_2_t_xflip.png")
           :s2rb (q/load-image "/resources/skulls/cp_6_skull_2_b_xflip.png")}    
   :setting [{:x 200 
               :y 0
               :bits (create-bit-line [[:lttr :m] [:lttr :o] [:lttr :r] [:lttr :e]] 45 50 50)}
             
             {:x 200 
              :y 45
              :bits (create-bit-line [[:lttr :f] [:lttr :u] [:lttr :c] [:lttr :k] [:lttr :e] [:lttr :r] [:lttr :y]] 45 50 50)}
             
             {:x 200 
              :y 100
              :bits (create-bit-line [[:lttr :c] [:lttr :o] [:lttr :m] [:lttr :i] [:lttr :n] [:lttr :g]] 45 50 50)}
             
             {:x 200 
              :y 150
              :bits (create-bit-line [[:lttr :s] [:lttr :o] [:lttr :o] [:lttr :n] [:lttr :i] [:lttr :s] [:lttr :h]] 45 50 50)}
             
             {:x 0
              :y 0
              :bits [
                     (->Bit [:skull :s2lt] 20 0   200 200)
                     (->Bit [:skull :s2lb] 45 150 150 100)]}
             {:x 500
              :y 0
              :bits [
                     (->Bit [:skull :s2rt] 0  0   200 200)
                     (->Bit [:skull :s2rb] 25 150 150 100)]}]})
                      
                      
(defn draw [state]
  (q/background 0)
  (let [stg (:setting state)]
       (doseq [blk stg]
        (render-block blk state))))


(q/defsketch practice
  :size [666 233]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
  
