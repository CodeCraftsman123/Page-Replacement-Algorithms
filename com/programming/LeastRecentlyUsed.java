package com.programming;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class LeastRecentlyUsed
{
    private static String referenceString;

    private static char searchLeastRecentlyUsedPage(int index,int totalNumberOfFrames)
    {
        HashSet<Character>hs = new HashSet<>();
        char value=' ';
        while(index >= 0)
        {
            if(hs.size() == (totalNumberOfFrames - 1))//Last page we are looking for
            {
                if(!hs.contains(referenceString.charAt(index)))
                {
                    hs.add(referenceString.charAt(index));
                    value = referenceString.charAt(index);
                    break;
                }
            }
            else
            {
                hs.add(referenceString.charAt(index));
            }
            index--;
        }

        return value;
    }

    private static void startExecution(int totalNumberOfFrames)
    {
        int totalNumberOfPageFaults = 0;
        ArrayList<Character>frames = new ArrayList<>();
        for(int i = 0; i < referenceString.length() ;i++)
        {
            if(frames.contains(referenceString.charAt(i)))
                System.out.println(frames+"->hit");
            else
            {
                if (frames.size() < totalNumberOfFrames)
                {
                    frames.add(referenceString.charAt(i));
                    totalNumberOfPageFaults++;
                    System.out.println(frames);
                }
                else
                {
                   char frameToBeRemoved = LeastRecentlyUsed.searchLeastRecentlyUsedPage((i-1),totalNumberOfFrames);
                   for(int j = 0 ; j < frames.size() ;j++)
                   {
                       if(frames.get(j).equals(frameToBeRemoved))
                       {
                           frames.set(j,referenceString.charAt(i));
                           totalNumberOfPageFaults++;
                           break;
                       }
                   }
                    System.out.println(frames);
                }
            }
        }
        System.out.println("Total number of page faults:"+totalNumberOfPageFaults);
    }


    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total number of frames:");
        int totalNumberOfFrames = scanner.nextInt();
        

        System.out.println("Enter the reference string:");
        scanner.nextLine();
        referenceString = scanner.nextLine();

        LeastRecentlyUsed.startExecution(totalNumberOfFrames);


    }
}
