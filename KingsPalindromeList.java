/**
 * Reads a list of numbers, and can reconstruct the corresponding list of 
Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO: Documentaion
 * 
 * The program has different uses depending on what the task is.For insance, if the task
 * is 1 it will take the Kings palindrome list(which has random numbers) and will chech to 
 * see which of the numbers are not palindromes.When the program come by a number that is not 
 * a palindrom(by using the IsPalindrome subprogram) it will replace it with the closes bigger
 *  palindrome that is(for example : 432 is replaced by 434 because 434 is palindrome
 *  and 434 >= 432).
 * 
 * If the task is 2, it will output the number which indicates the length of the biggest magic set.
 * It can do this by comparing each number with each number, and couts how many palindorme can be 
 * found inside them.For insance, if a palindrome is bigger than the nexxt one, then it calculates
 *  the difference in length and cuts the bigger number accordingly so that they are the same
 *  length.If the new number, that was cut, and the one previous one are equal, then we know that
 *  these to numbers are from the same magic set.
 * 
 * If the task is 3, then the progrm outputs the numbers in the positions 
 * that are stored in the variable positionMax.If positionMax ddoes not store
 * any positions,than the program outputs the biggest number from the list.
 * 
 * END TODO
 * 
 * @author <Andrei David Nechitoaia>
 * @ID <1806130>
 * 
 */

import java.util.Scanner;


public class KingsPalindromeList{


    //Subprogram that calcuates the power of a base:
    int intPow(int base, int pow){
        int result;
        result  = 1;
        while(pow != 0){
            result  = result  * base;
            pow--;
        }
        return result ;
    }

    //Subprogram that calculates the length of a number
    int numLength(int num){
        int k = 0;
        if(num > 9){
            while (num != 0){
                k++;
                num /= 10;
            }
            return k;
        }
        else return 1;
    }

    //Subprogram that cuts a number of digits both from the start and the end of a number
    int cutNum(int num, int cut){
        num = (num / intPow( 10, cut)) % intPow(10, numLength(num) - (2 * cut));
        return num;
    }


    //Subprogram that outputs 1 if an inputed number is a palindrome, and 0 otherwise
    int  isPalindrome(int  num){
        int  reversedNum;
        reversedNum = 0;

        int  originalNum;
        originalNum = num;

        int  remainder;

        while(num != 0){
            remainder = num % 10;
            reversedNum = reversedNum * 10 + remainder;
            num = num / 10;
        }
        
        if(reversedNum == originalNum)return 1;
        else return 0;  
    }

    Scanner sc = new Scanner(System.in);

    //Main program:
    void solve(){

        int i;
        int task = sc.nextInt();
        int  n = sc.nextInt();
        int[] numbers = new int[n];

        
        //Initializing an array(the Kings palindrome list)
        for(i = 0; i < n; i++){
            numbers[i] = sc.nextInt();
        }

        //If a number from The Kings Palindrome list is not a palindrome, 
        //than it gives enought units to that number to make it a palindrome
        //This is the task 1:
        for(i = 0; i < n; i++){
            if(isPalindrome(numbers[i]) == 0){
                while(isPalindrome(numbers[i]) == 0){
                    numbers[i]++;
                }
            }

        }

        //If task is 1 than the  program outputs the correct palindrome list

        if(task == 1){
        for(i = 0; i < n; i++){
              System.out.print(numbers[i]+ " ");
        }
    }

    //Else, if task is 2, it compares eevry number with every number:
    //If the length of a number is bigger, than if the bigger number is 
    //cut accordingly to have the same lenght as the other number and they 
    //are the same, than we found two numbers of the same magic set
        
        //In positionMax it will remember the positions to the largest magic set
        int  positionMax;
        positionMax = 1;

        int  position;
        position = 1;

        int  k;
        k = 0;

        //In kMax it will remember the largest magic set
        int  kMax;
        kMax = 0;

    for(i = 0; i<n;i++){

        position = 1;
        k = 0;

        for(int  j = 0; j<n; j++){

            //if two elements have different lenght than the variable 
            //difference will memorize it
            int  difference;
            difference = 0;

            if(numLength(numbers[j]) >= numLength(numbers[i])) {

                difference = numLength(numbers[j]) - numLength(numbers[i]);

            if(numbers[i] == cutNum(numbers[j], difference /2 )){
                 k++;

                 //the variable positions remebers every position in every magic set and, at the end,
                 // only remembers the positions to the largest one.
                 position = position * 10 + j;
                }
            }
        }

        //When found the bigest magic set, kMax remembers its length
        if(k > kMax) {
            kMax = k;
            positionMax = position;
        }
    }

    //If task is 2 than it outputs the length of the largest magis set.

   if (task == 2){
    System.out.println(kMax);
   }

   //If task is 3, then the program outputs every position remebereed in the number positions,
   //the positions of those numbers in the largest magic set
   if(task == 3){
   int  p;
   p=0;
   for(i=0 ; i < kMax; i++){
    if(kMax > 1){
    while(positionMax > 1){
        p = positionMax % 10;
        System.out.print(numbers[p] + " ");
        positionMax /= 10;
    }
}

    else {
        int max;
        max = 0;
        for(i = 0; i < n; i++){
            if(numbers[i] > max){
                max = numbers[i];
            }
        }
        System.out.println(max);

    }
             }
        }
    }  

    public static void main(String[] a){
        new KingsPalindromeList().solve();
    }
}