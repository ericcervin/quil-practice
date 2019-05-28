(ns quil-practice.quilpractice20190527
  (:require [quil.core :as q]
            [quil.middleware :as m]))


(defn pxl-rgb [n pxls]
  (let [p (aget pxls n)
        r (q/red p)
        g (q/green p)
        b (q/blue p)]
    [r g b]))

(defn setup []
  (q/frame-rate 12)
  (let [img  (q/load-image "/resources/img-a.jpg")        
        pxls  (q/pixels img)]
    {:pxls pxls}))
       

(defn update-state [state]
   state) 

(defn draw [state]
  (let [pxls (:pxls state)]
    (doseq [i (range 5)]
      (let [xy (* i 50)
            hw (- 500 (* 2 xy))]
        (apply q/fill (pxl-rgb (+ (q/frame-count) i) pxls))
        (q/rect xy xy hw hw))))  
  
  #_(if (< (q/frame-count) 720) 
     (do (println (q/frame-count))
       (q/save-frame "/target/output-####.png"))))    

(q/defsketch nested-pxl-squares
  :size [500 500]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
