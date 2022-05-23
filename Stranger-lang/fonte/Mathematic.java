import java.lang.Math;

class Mathematic{
    
    public double sum(Double a, Double b){ return a + b;}

    public double less(Double a, Double b){ return a - b;}

    public double mutiplication(Double a, Double b){ return a * b;}

    public double division(Double a, Double b){ return a / b;}

    public double potencia(Double a, Double b){ return Math.pow(a, b);}

    public double raiz(Double a){
        int aInt = a.intValue();
        return Math.sqrt(aInt);
    }

    public double resto(Double a, Double b){
        return a % b;
    }

    public double cousin(Double a){
        int aInt = a.intValue();
        int count = 0;
        for (int i = 1; i <= aInt; i++) {
            if (aInt % i == 0) { 
                count++;
            }
        }   
        if (count == 2)
            return 1;
        else
            return 0;
    }
}