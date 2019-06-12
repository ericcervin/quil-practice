(ns quil-practice.quilpractice20191231
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defrecord Bit [img dt dx dy h w])


(defn lttr-file-name [l] (str "/resources/letters/cp6/" l ".png"))
(defn assoc-lttr-img [mp lt] (assoc mp (keyword (str lt)) (q/load-image (lttr-file-name lt))))

(defn create-bit-line [imgs dx h w]
  (for [i (range (count imgs))]
    {:dx (* dx i)
     :dt i
     :dy 0
     :img (nth imgs i) 
     :h h
     :w w}))

(defn render-block [blk state]
  (let [blk-x (blk :x)
        blk-y (blk :y)
        bts (blk :bits)
        btime (blk :bt)]
    (if (> (q/frame-count) btime)
     (doseq [i bts]
       (if (> (q/frame-count)(+ btime (:dt i)))
         (q/image (get-in state (:img i)) (+ blk-x (:dx i)) (+ blk-y (:dy i)) (:h i) (:w i)))))))
  
(defn setup []
  (q/frame-rate 12)
  {:lttr (reduce assoc-lttr-img {} "abcdefghijklmnopqrstuvwxyz")
   :skull {:s2lt (q/load-image "/resources/skulls/cp_6_skull_2_t.png")
           :s2lb (q/load-image "/resources/skulls/cp_6_skull_2_b.png")
           :s2rt (q/load-image "/resources/skulls/cp_6_skull_2_t_xflip.png")
           :s2rb (q/load-image "/resources/skulls/cp_6_skull_2_b_xflip.png")}    
   :setting [{:x 160 
              :y 0
              :bt 12
              :bits (create-bit-line [[:lttr :m] [:lttr :o] [:lttr :r] [:lttr :e]] 45 50 50)}
             
             {:x 160 
              :y 55
              :bt 18
              :bits (create-bit-line [[:lttr :f] [:lttr :u] [:lttr :c] [:lttr :k] [:lttr :e] [:lttr :r] [:lttr :y]] 45 50 50)}
             
             {:x 160 
              :y 120
              :bt 24
              :bits (create-bit-line [[:lttr :c] [:lttr :o] [:lttr :m] [:lttr :i] [:lttr :n] [:lttr :g]] 45 50 50)}
             
             {:x 160 
              :y 180
              :bt 30
              :bits (create-bit-line [[:lttr :s] [:lttr :o] [:lttr :o] [:lttr :n] [:lttr :i] [:lttr :s] [:lttr :h]] 45 50 50)}
             
             {:x 140 
              :y 240
              :bt 36
              :bits (create-bit-line [[:lttr :i] [:lttr :n]] 45 50 50)}
             
             {:x 120 
              :y 300
              :bt 42
              :bits (create-bit-line [[:lttr :t] [:lttr :w] [:lttr :o]] 45 50 50)}
            
             {:x 320
              :y 220
              :bt 70
              :bits [
                     (->Bit [:skull :s2rt] 0 0  0   200 200)
                     (->Bit [:skull :s2rb] 0 25 150 150 100)]}
             
             
             {:x 0 
              :y 360
              :bt 48
              :bits (create-bit-line [[:lttr :d] [:lttr :a] [:lttr :z] [:lttr :z] [:lttr :l] [:lttr :i] [:lttr :n] [:lttr :g]] 45 50 50)}
             
             {:x 0 
              :y 420
              :bt 54
              :bits (create-bit-line [[:lttr :d] [:lttr :i] [:lttr :m] [:lttr :e] [:lttr :n] [:lttr :s] [:lttr :i] [:lttr :o] [:lttr :n] [:lttr :s]] 45 50 50)}
             
             {:x -40
              :y 0
              :bt 6
              :bits [
                     (->Bit [:skull :s2lt] 0 20 0   200 200)
                     (->Bit [:skull :s2lb] 0 45 150 150 100)]}]})
             
             
            
                      
                      
(defn draw [state]
  (q/background 0)
  
  (let [stg (:setting state)]
       (doseq [blk stg]
        (render-block blk state))))


(q/defsketch practice
  :size [500 500]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
  
