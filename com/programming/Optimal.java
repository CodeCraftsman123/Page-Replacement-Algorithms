package com.programming;
import java.util.Scanner;
import java.util.ArrayList;

public class Optimal
{
    private static String referenceString;

    private static char searchPageToBeReplaced(int index,ArrayList<Character>frames)
    {
        ArrayList<Integer>storeIndex = new ArrayList<>();
        for(Character element:frames)
        {
            boolean usedInFuture = false;
            for(int i = index; i < referenceString.length() ; i++)
            {
                if(element.equals(referenceString.charAt(i)))
                {
                    usedInFuture = true;
                    storeIndex.add(i);
                    break;
                }
            }

            if(!usedInFuture)//That page is not present in the next part of the reference String
            {
                return element;
            }
        }

        //Find max index amongst the reference string
        int maxValue = Integer.MIN_VALUE;
        int indexOfMaxValue = 0;
        for(int i = 0 ; i < storeIndex.size() ;i++)
        {
            if(storeIndex.get(i) > maxValue)
            {
                maxValue = storeIndex.get(i);
                indexOfMaxValue = i;
            }
        }

        return frames.get(indexOfMaxValue);
    }

    private static void implementOptimal(int totalNumberOfFrames)
    {
        ArrayList<Character>frames = new ArrayList<>();
        int totalNumberOfPageFaults = 0;

        for(int i = 0; i < referenceString.length() ;i++)
        {
            if(frames.contains(referenceString.charAt(i)))
            {
                System.out.println(frames+"->hit");
            }
            else
            {
                if(frames.size()<totalNumberOfFrames)
                {
                    frames.add(referenceString.charAt(i));
                    totalNumberOfPageFaults++;
                    System.out.println(frames);
                }
                else
                {
                    char replaceThisPage = Optimal.searchPageToBeReplaced((i+1),frames);
                    for(int j = 0 ; j < frames.size() ; j++)
                    {
                        if(frames.get(j).equals(replaceThisPage))
                        {
                            frames.set(j,referenceString.charAt(i));
                            break;
                        }
                    }
                    totalNumberOfPageFaults++;
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

        System.out.println("Enter the reference String:");
        scanner.nextLine();
        referenceString = scanner.nextLine();

        Optimal.implementOptimal(totalNumberOfFrames);

    }
}