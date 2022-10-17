/**
 * @author ${naeblessed}
 */

import java.lang.Math;

public class DogFlea {
    private int fleesOnDogOne = 19;
    private int fleesOnDogTwo = 1;
    private int timeCounter = 1;
    private int total = fleesOnDogOne + fleesOnDogTwo;

    public int getTimeCounter(){
        return this.timeCounter;
    }

    public void setInitialState(int fleesOnDogOne, int fleesOnDogTwo){                                                  // set model to the Initial State
        this.fleesOnDogOne = fleesOnDogOne;
        this.fleesOnDogTwo = fleesOnDogTwo;
        this.timeCounter = 1;
        this.total = this.fleesOnDogOne + this.fleesOnDogTwo;
    }
    public void simulateModel20iterations(){                                                                            // Simulation for 20 iterations (task 1)
        while(timeCounter < 20){
            double r = Math.random();
            double fromOneToTwoProbability = (double)fleesOnDogOne/total;
            if(r <= fromOneToTwoProbability){
                fleesOnDogOne -= 1;
                fleesOnDogTwo += 1;
            }else{
                fleesOnDogOne += 1;
                fleesOnDogTwo -= 1;
            }
            timeCounter += 1;
            if(fleesOnDogOne == total)
                break;
            System.out.println("Time Passed: " + timeCounter + " Generated r: " + r +
                    " From One to Two probability: " + fromOneToTwoProbability +
                    " Flees on Dog One: " + fleesOnDogOne + " Flees on Dog Two: " + fleesOnDogTwo);
        }
    }

    public void simulateModelUntilInitialState(){                                                                       // simulation until model returns to the initial state (task 2)
        while(true){
            double r = Math.random();
            double fromOneToTwoProbability = (double)fleesOnDogOne/total;
            if(r <= fromOneToTwoProbability){
                fleesOnDogOne -= 1;
                fleesOnDogTwo += 1;
            }else{
                fleesOnDogOne += 1;
                fleesOnDogTwo -= 1;
            }
            timeCounter += 1;
            if(fleesOnDogOne == total){
                System.out.println("Time Needed: " + timeCounter);
                break;
            }
        }
    }

    public static void main(String[] args) {                                                                            // creating my model and simulating(both tasks)
        double averageTimeNeeded = 0.0;

        DogFlea myModel = new DogFlea();
        myModel.simulateModel20iterations();                                                                            // simulating for 20 iterations and print states
        myModel.setInitialState(19,1);

        int amountOfSimulations = 20;
        for (int i = 0; i < amountOfSimulations; i++) {                                                                 // performing 20 simulations
            System.out.print("Simulation Number: " + i + " ");
            myModel.simulateModelUntilInitialState();
            averageTimeNeeded += myModel.getTimeCounter();
            myModel.setInitialState(19, 1);
        }
        System.out.println("Average time needed in " + amountOfSimulations + " simulations is: " + averageTimeNeeded/amountOfSimulations);
    }
}
