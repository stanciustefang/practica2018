import java.util.ArrayList;
import java.util.Arrays;

public class BucketSort {
    private int lungime;
    private ArrayList<Double> vector;

    public BucketSort(ArrayList<Double> vector) {
        this.vector=vector;
        lungime=vector.size();
        sort();
    }
    public BucketSort(double[] vector,boolean neg) {
        this.vector=new ArrayList<>();
        for(int i=0;i<vector.length;i++) {
            this.vector.add(vector[i]);
        }
        lungime=vector.length;
        if(neg)
            sortMixed();
        else
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
        int nrBucket=maxValue().intValue();
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
    public void sortMixed(){
        ArrayList<Double> Neg=new ArrayList<>();
        ArrayList<Double> Pos=new ArrayList<>();
        for(int i=0;i<lungime;i++)
        {
            if(vector.get(i)<0)
                Neg.add(-vector.get(i));
            else
                Pos.add(vector.get(i));
        }
        BucketSort negSort=new BucketSort(Neg);
        BucketSort posSort=new BucketSort(Pos);
        for(int i=0;i<Neg.size();i++)
        {
            vector.remove(i);
            vector.add(i,-negSort.getVector().get(Neg.size()-i-1));
        }
        for(int i=Neg.size();i<lungime;i++)
        {
            vector.remove(i);
            vector.add(i,posSort.getVector().get(i-Neg.size()));
        }
    }

    public ArrayList<Double> getVector() {
        return vector;
    }
    public void reset()
    {
        vector.clear();
        lungime=0;

    }
}
