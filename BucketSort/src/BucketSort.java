import java.util.ArrayList;
import java.util.Arrays;

public class BucketSort {
    private int lungime;
    private ArrayList<Double> vector;

    public BucketSort(double[] vector) {
        this.vector=new ArrayList<>();
        for(int i=0;i<vector.length;i++) {
            this.vector.add(vector[i]);
        }
        lungime=vector.length;
        sort();
    }
    public Double maxValue()
    {
        double max=vector.get(0);
        for(int i=1;i<vector.size();i++)
        {
            if(max<vector.get(i))
                max=vector.get(i);
        }
        return max;
    }
    public void sort() {
        int nrBucket=Math.abs(maxValue().intValue());
        ArrayList<Double> []bucket = new ArrayList[nrBucket];
        for(int i=0;i<nrBucket;i++)
        {
            bucket[i]=new ArrayList<>();
        }
        for (int i = 0; i < lungime; i++)
        {
            int bi =vector.get(i).intValue()/10;
            bucket[bi].add(vector.get(i));
        }
        for (int i = 0; i < nrBucket; i++)
            bucket[i].sort(Double::compareTo);
        int outPos = 0;
        for (int i = 0; i < nrBucket; i++)
        {
            for (int j = 0; j < bucket[i].size(); j++)
            {
                vector.remove(outPos);
                vector.add(outPos++,bucket[i].get(j));
            }
        }
    }

    public ArrayList<Double> getVector() {
        return vector;
    }

}
