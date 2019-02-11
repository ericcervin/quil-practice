(ns quil-practice.quilpractice20190210
  (:require [quil.core :as q]
            [quil.middleware :as m]))




(defn lttr-file-name [l] (str "/resources/letters/cp6/" l ".png"))
(defn assoc-lttr-img [mp lt] (assoc mp (keyword (str lt)) (q/load-image (lttr-file-name lt))))

(defrecord Bit [img dx dy h w])

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
              :bits [
                     (->Bit [:lttr :m] 0   0 50 50)
                     (->Bit [:lttr :o] 45  0 50 50)
                     (->Bit [:lttr :r] 90  0 50 50)
                     (->Bit [:lttr :e] 135 0 50 50)]}
             {:x 200 
              :y 45
              :bits [
                     (->Bit [:lttr :f] 0   0 50 50)
                     (->Bit [:lttr :u] 45  0 50 50)
                     (->Bit [:lttr :c] 90  0 50 50)
                     (->Bit [:lttr :k] 135 0 50 50)
                     (->Bit [:lttr :e] 170 0 50 50)
                     (->Bit [:lttr :r] 205 0 50 50)
                     (->Bit [:lttr :y] 245 0 50 50)]}
             
             {:x 200 
              :y 100
              :bits [
                     (->Bit [:lttr :c] 0   0 50 50)
                     (->Bit [:lttr :o] 45  0 50 50)
                     (->Bit [:lttr :m] 90  0 50 50)
                     (->Bit [:lttr :i] 135 0 50 50)
                     (->Bit [:lttr :n] 170 0 50 50)
                     (->Bit [:lttr :g] 205 0 50 50)]}
             {:x 200 
              :y 150
              :bits [
                     (->Bit [:lttr :s] 0   0 50 50)
                     (->Bit [:lttr :o] 45  0 50 50)
                     (->Bit [:lttr :o] 90  0 50 50)
                     (->Bit [:lttr :n] 135 0 50 50)
                     (->Bit [:lttr :i] 170 0 50 50)
                     (->Bit [:lttr :s] 205 0 50 50)
                     (->Bit [:lttr :h] 245 0 50 50)]}
             
             {:x 0
              :y 0
              :bits [
                     (->Bit [:skull :s2lt] 20 0   200 200)
                     (->Bit [:skull :s2lb] 45 150 150 100)]}
             {:x 480
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
  
