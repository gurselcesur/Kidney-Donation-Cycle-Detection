# Kidney-Donation-Cycle-Detection

The goal is to detect whether a given kidney recipient can receive a kidney through a donation cycle. The assignment involves building and manipulating adjacency list representations of graphs and reinforcing the understanding of BFS (Breadth-First Search) and DFS (Depth-First Search) algorithms.

## Problem Statement
A set of **kidney recipients** and **donors** is given. Each donor is associated with a recipient, but the donor might not be directly compatible with that recipient. However, a compatible **donation cycle** may exist, allowing the recipient to receive a kidney indirectly through a chain of donations.

A **kidney donation cycle** is defined as a sequence of recipients `(r0, r1, ..., rn-1)` where:
1. All `r1, ..., rn-1` are kidney recipients.
2. Each `ri` (except the last recipient) has an associated donor who is compatible with `ri+1`.
3. The last recipient `rn-1` has an associated donor who is compatible with `r0`, forming a cycle.

## Input Format
The program reads input from the console in the following format:

1. An integer `n`: the number of recipients.
2. An integer `m`: the number of donors.
3. A single line with `m` comma-separated integers indicating which recipient each donor is associated with.
4. An `m x n` matrix representing the **HLA match scores** between donors and recipients (each row contains `n` comma-separated integers).
5. A single integer: the **query recipient** (the recipient we check for being in a donation cycle).

## Output Format
The program outputs a **single boolean value** (`True` or `False`):
- **True**: If a donation cycle exists that includes the query recipient.
- **False**: If no such cycle exists.

## Example
### Input
```
3
4
0,1,1,2
0, 50, 71
30, 0, 100
90, 20, 0
2
```

### Graph Representation
This input represents the following directed graph:
```
0 → 1 (HLA score 71 ✅)
1 → 2 (HLA score 100 ✅)
2 → 0 (HLA score 90 ✅)
```
Since there is a cycle that includes recipient **2**, the output should be:

### Output
```
True
```

## Implementation
- The program constructs a **directed graph** where recipients are nodes and edges represent valid kidney donations (HLA score ≥ 60).
- It then performs **graph traversal (DFS/BFS)** to check if the **query recipient is part of a cycle**.
- The worst-case **time complexity is O(nm)**.

## Running the Program
- Run the program and **paste input into the console**, then press enter.
- The program will output either `True` or `False`.

## Notes
- If a recipient does not have a direct donor but can receive a kidney through a cycle, they are considered eligible (`True`).
- If no cycle exists that includes the query recipient, the output is `False`.

## Example Test Cases:
| Test Case      | Expected Output |
|---------------|----------------|
| `example.txt`  | True           |
| `slides.txt`   | False          |
| `straightline.txt` | False      |
| `biggerloop.txt` | True        |
| `almostcycle.txt` | False      |
| `complete.txt` | True          |
| `lollypop0.txt` | False        |
| `lollypop5.txt` | True         |

## License
This project is for educational purposes only and should not be used for actual medical decision-making.
