(ns quil-practice.quilpractice20190428
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/euclid/core.clj

(defn calc-x
  "Calculates the x value on a circle
   with a `radius` and offset by `x-offset`"  
  [x-offset radius rad]
  (+ x-offset (* radius (q/cos rad))))

(defn calc-y
  "Calculates the y value on a circle
   with a `radius` and offset by `y-offset`"  
  [y-offset radius rad]
  (+ y-offset (* radius (q/sin rad))))

(defn orbit-seq
  "Returns lazy-seq of positions of an orbiting point.
   Providing a positioned `circle` and a rate of change `rad-delta`
   with optional `start-rad`, a lazy sequence of coordinates around 
   the circle will e created." 
  ([{:keys [x y r] :as circle} rad-delta] (orbit-seq circle rad-delta 0))
  ([{:keys [x y r] :as circle} rad-delta start-rad]

   (iterate (fn [point]
              (let [next-rad (+ (:rad point) rad-delta)]
                (assoc point
                       :rad next-rad
                       :x (calc-x x r next-rad)
                       :y (calc-y y r next-rad))))
            {:rad start-rad
             :x (calc-x x r start-rad)
             :y (calc-y y r start-rad)})))

(defn setup []
  (q/frame-rate 30)    
  (let
    [mx (/ (q/width) 2)
     my (/ (q/height) 2)
     circles [{:x (+ mx 50) :y my :r 100}             
              {:x (- mx 50) :y my :r 100}]    
     orbits [(orbit-seq (circles 0) -0.2)            
             (orbit-seq (circles 1) 0.2)]]    
    {:circles circles
     :orbits orbits}))

(defn update-state [state]
  (update state :orbits #(map rest %)))
  

(defn draw [state]
  (q/background 255)
  (q/stroke-weight 1)
  
  (let [circles (:circles state)
        orbits  (:orbits state)]
    
    (q/no-fill)
    (q/stroke 0 0 255)
    (doseq [{:keys [x y r]} circles]
      (q/ellipse x y (* 2 r) (* 2 r)))
    
    (q/fill 255 0 0)
    (doseq [[cur-orbit] orbits]
      (q/ellipse (:x cur-orbit) (:y cur-orbit) 10 10))))
    

(q/defsketch euclid
  :size [500 500]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
