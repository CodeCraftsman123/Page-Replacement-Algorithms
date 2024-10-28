package com.programming;
import java.util.Scanner;
import java.util.ArrayList;

public class FIFO
{
    private static String referenceString;

    private static void implementFIFO(int totalNumberOfFrames)
    {
        ArrayList<Character>frames = new ArrayList<>();
        int index = 0;
        int totalNumberOfPageFaults = 0;
        for(int i = 0 ; i < referenceString.length() ;i++)
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
                    frames.set(index, referenceString.charAt(i));
                    index = ((index + 1) % totalNumberOfFrames);
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

        System.out.println("Enter the reference string:");
        scanner.nextLine();
        referenceString = scanner.nextLine();

       FIFO.implementFIFO(totalNumberOfFrames);
    }
}
