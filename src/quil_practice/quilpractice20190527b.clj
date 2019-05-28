(ns quil-practice.quilpractice20190527b
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;(attempted) quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/experiments/10_photo_manip.clj
;;really lacking what clojure2d can do with filter-channel
;;would be more efficient updating the pixel array with update-pixels

(defn rotate-pixel-rgb [n p]
  (let [r (q/red p)
        g (q/green p)
        b (q/blue p)
        r2 (rem (+ r n) 256)
        g2 (rem (+ g n) 256)
        b2 (rem (+ b n) 256)]
    (q/color r2 g2 b2)))


(defn setup []
  (q/frame-rate 12)
  (let [img  (q/load-image "/resources/img-a.jpg")]        
    (q/resize-sketch (.width img) (.height img))
    {:img img}))
       


(defn update-state [state]
  (let [img (:img state)]
    (dotimes [x (q/width)]
      (dotimes [y (q/height)]
        (let [p (q/get-pixel img x y)]
          (q/set-pixel img x y (rotate-pixel-rgb 10 p)))))    
    {:img img})) 

(defn draw [state]
  (q/background 0)
  (q/image (:img state) 0 0))
  

(q/defsketch pixel-manipulation
  :size [50 50]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
