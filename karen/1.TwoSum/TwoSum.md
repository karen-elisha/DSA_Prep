# Two Sum — HashMap One-Pass Solution

This README explains, line by line, how this solution solves the "Two Sum" problem.

## The Problem

Given an array `nums` and an integer `target`, return the **indices** of the two numbers that add up to `target`. Exactly one valid answer exists, and you can't use the same element twice.

## The Code

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }
            seen.put(nums[i], i);
        }
        return new int[] {}; // no solution found
    }
}
```

## The Core Idea

Instead of checking every pair of numbers (which takes nested loops), this solution flips the question around:

> "For the number I'm looking at right now, what other number would I *need* to see to hit the target?"

That needed number is called the **complement**: `complement = target - nums[i]`.

If that complement has already appeared earlier in the array, we're done — we just found our pair. A `HashMap` lets us check "has this complement appeared before?" in **O(1)** time instead of scanning the array again.

## Line-by-Line Walkthrough

### 1. The map declaration
```java
Map<Integer, Integer> seen = new HashMap<>(); // value -> index
```
`seen` stores every number we've already visited, mapping **value → its index**. This is what lets us do instant lookups instead of re-scanning.

### 2. The loop
```java
for (int i = 0; i < nums.length; i++) {
```
A single pass through the array — this is why the algorithm is O(n) instead of O(n²).

### 3. Computing the complement
```java
int complement = target - nums[i];
```
This asks: "what value, paired with `nums[i]`, would sum to `target`?"

### 4. Checking if we've already seen it
```java
if (seen.containsKey(complement)) {
    return new int[] { seen.get(complement), i };
}
```
If `complement` is already a key in `seen`, that means some earlier index held exactly the value we need. We immediately return:
- `seen.get(complement)` → the index where that earlier value was found
- `i` → the current index

Together, these two indices are the answer.

### 5. Recording the current number
```java
seen.put(nums[i], i);
```
This only runs if we *didn't* find a match. We add the current number and its index to the map so that future iterations can find it as a complement.

### 6. Fallback
```java
return new int[] {};
```
If the loop finishes without returning, no valid pair exists. (The problem guarantees a solution exists, so this line mostly exists for safety/completeness.)

## Dry Run

`nums = [2, 7, 11, 15]`, `target = 9`

| i | nums[i] | complement (9 - nums[i]) | complement in `seen`? | `seen` after this step |
|---|---|---|---|---|
| 0 | 2 | 7 | No | `{2: 0}` |
| 1 | 7 | 2 | **Yes** (index 0) → return `[0, 1]` | — |

**Output:** `[0, 1]`, since `nums[0] + nums[1] = 2 + 7 = 9`. ✅

### A trickier example
`nums = [3, 2, 4]`, `target = 6`

| i | nums[i] | complement | in `seen`? | `seen` after |
|---|---|---|---|---|
| 0 | 3 | 3 | No | `{3: 0}` |
| 1 | 2 | 4 | No | `{3: 0, 2: 1}` |
| 2 | 4 | 2 | **Yes** (index 1) → return `[1, 2]` | — |

**Output:** `[1, 2]`, since `nums[1] + nums[2] = 2 + 4 = 6`. ✅ (Notice this pair is *not* adjacent by value order but is handled correctly, unlike a naive `nums[i] + nums[i+1]` check.)

## Why This Approach Is Correct

- **Every pair is implicitly considered.** By the time index `i` is processed, every index `0..i-1` has already been stored in the map. So checking `seen.containsKey(complement)` is equivalent to checking every prior element without an explicit inner loop.
- **No double-counting the same element.** We check `seen` *before* inserting `nums[i]`, so an element can never pair with itself unless it legitimately appears twice at different indices.
- **First valid pair found is returned immediately**, which works because the problem guarantees exactly one solution.

## Complexity

| Metric | Complexity | Why |
|---|---|---|
| Time | O(n) | Single pass; each `containsKey` / `get` / `put` on a HashMap is O(1) on average |
| Space | O(n) | In the worst case, we store almost every element in the map before finding a match |

## Comparison to Brute Force

```java
// Brute force: O(n²) time, O(1) space
for (int i = 0; i < nums.length; i++) {
    for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
            return new int[] { i, j };
        }
    }
}
```

The HashMap version trades a bit of extra memory (O(n)) for a much faster runtime (O(n) instead of O(n²)), which matters a lot as input size grows.