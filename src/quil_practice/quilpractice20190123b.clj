(ns quil-practice.quilpractice20190123b
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(defn lttr-file-name [l] (str "/resources/letters/cp6/" l ".png"))
(defn assoc-lttr-img [mp lt] (assoc mp (keyword (str lt)) (q/load-image (lttr-file-name lt))))


(defn setup []
  {:lttr (reduce assoc-lttr-img {} "abcdefghijklmnopqrstuvwxyz")
   :skull {:s2lt (q/load-image "/resources/skulls/cp_6_skull_2_t.png")
           :s2lb (q/load-image "/resources/skulls/cp_6_skull_2_b.png")
           :s2rt (q/load-image "/resources/skulls/cp_6_skull_2_t_xflip.png")
           :s2rb (q/load-image "/resources/skulls/cp_6_skull_2_b_xflip.png")}        
   :setting [
             {:img [:lttr :m] :x 200 :y 0 :h 50 :w 50}
             {:img [:lttr :o] :x 245 :y 0 :h 50 :w 50}
             {:img [:lttr :r] :x 290 :y 0 :h 50 :w 50}
             {:img [:lttr :e] :x 335 :y 0 :h 50 :w 50}
             
             {:img [:lttr :f] :x 200 :y 45 :h 50 :w 50}
             {:img [:lttr :u] :x 245 :y 45 :h 50 :w 50}
             {:img [:lttr :c] :x 290 :y 45 :h 50 :w 50}
             {:img [:lttr :k] :x 335 :y 50 :h 50 :w 50}
             {:img [:lttr :e] :x 370 :y 45 :h 50 :w 50}
             {:img [:lttr :r] :x 405 :y 45 :h 50 :w 50}
             {:img [:lttr :y] :x 440 :y 45 :h 50 :w 50}
             
             {:img [:lttr :c] :x 200 :y 95 :h 50 :w 50}
             {:img [:lttr :o] :x 245 :y 90 :h 50 :w 50}
             {:img [:lttr :m] :x 290 :y 95 :h 50 :w 50}
             {:img [:lttr :i] :x 335 :y 95 :h 50 :w 50}
             {:img [:lttr :n] :x 370 :y 95 :h 50 :w 50}
             {:img [:lttr :g] :x 405 :y 90 :h 50 :w 50}
             
             {:img [:lttr :s] :x 200 :y 140 :h 50 :w 50}
             {:img [:lttr :o] :x 245 :y 140 :h 50 :w 50}
             {:img [:lttr :o] :x 290 :y 140 :h 50 :w 50}
             {:img [:lttr :n] :x 335 :y 140 :h 50 :w 50}
             {:img [:lttr :i] :x 370 :y 140 :h 50 :w 50}
             {:img [:lttr :s] :x 415 :y 140 :h 50 :w 50}
             {:img [:lttr :h] :x 460 :y 140 :h 50 :w 50}
             
             {:img [:skull :s2lt] :x 20 :y 0 :h 200 :w 200}
             {:img [:skull :s2lb] :x 45 :y 150 :h 150 :w 100}
             {:img [:skull :s2rt] :x 480 :y 0 :h 200 :w 200}
             {:img [:skull :s2rb] :x 505 :y 150 :h 150 :w 100}]})
             
  

(defn draw [state]
    (q/background 0)
    
    (doseq [i (:setting state)]
      (q/image (get-in state  (:img i)) (:x i) (:y i) (:h i) (:w i)
       )))


(q/defsketch practice
  :size [666 233]
  :setup setup
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])
  
