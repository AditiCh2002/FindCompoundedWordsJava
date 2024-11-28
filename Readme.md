Overview-This program identifies the longest compounded word and the second longest compounded word from two input files containing sorted word lists. A compounded word is one that can be formed by combining smaller words found in the same list. The results, along with the time taken to process the files, are saved in an output file.


Approach-
1.The program reads all words from the input files and stores them in a HashSet for fast lookups.
2.Each word is checked to see if it can be split into smaller words from the set using a backtracking approach.
3.This ensures that every possible combination of smaller words is considered.
4.The program keeps track of the two longest compounded words and logs the results to Output.txt along with the processing time.

Time Complexity-
The program uses efficient word lookups and backtracking to validate compound words.
Word existence checks: O(1) (using HashSet).
Compound validation: Approximately O(n × m²), where:
n = total number of words.
m = average length of a word.

Space Complexity-
Storing words in the HashSet: O(n × m).
Recursive function call stack: O(m) (proportional to the length of the longest word).

