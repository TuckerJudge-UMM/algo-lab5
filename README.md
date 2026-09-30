# algo-lab5
Source:
https://en.wikipedia.org/wiki/Gale%E2%80%93Shapley_algorithm

Main fn is assignPairs() and we have a helper function that tests if all pairs are satisfactory isSatisfactoryPairs()
This algorithm follows the Gale-Shipley Algorithm, where all programmers receive their best possible match. Because all companies have offers to all programmers and all programmers have ranked choices for companies, then their always exists a valid pairs of size n.

Worst case, O(N^2) because all pair ratings are compared to find optimal solution in direction of programmers. N is the size of both matrices.
This algorithm will assess all possible pairs in the worst case making it O(n^2) where n is the size of both matrices.
