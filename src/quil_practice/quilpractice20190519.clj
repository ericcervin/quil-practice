(ns quil-practice.quilpractice20190519
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;quil rewrite of https://github.com/dantheobserver/drawing-experiments/blob/master/src/experiments/08_bay_bridge.clj
        
            
(defn setup []
  {:bridge/height 200
   :bridge/width 300
   :bridge/section-width 80
   :beam/count 100})
         
(defn plot-y
  "Finds the y position for a beam"
  [x width max-height]
  (-> (* q/TWO-PI x)
      (/ width)
      (+ (/ (* 3 q/PI) 2))
      q/sin
      inc
      (/ 2)
      (* max-height)))


(defn next-beam
  [step width max-height]
  (fn [[x0 _]]
    [(+ step x0)
     (plot-y x0 width max-height)]))

(defn bridge-section
  [width max-height beam-count]
  (let [step (/ width beam-count)]
    (iterate (next-beam step width max-height) [0 0])))

(defn update-state [state]
   state) 

(defn draw [state]
  (q/background 0)
  (q/stroke 255)
  (q/stroke-weight 2)
  (let [height (:bridge/height state)
        width  (:bridge/width state)
        count  (:beam/count state)
        sections (take-while
                  (fn [[x _]](< x width))
                  (bridge-section width height count))]
       
    (doseq [[x1 y1] sections]
      (q/line x1 300 x1 (- 300 y1)))))
             

(q/defsketch bay-bridge
  :size [800 300]
  :setup setup
  :draw draw
  :update update-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
