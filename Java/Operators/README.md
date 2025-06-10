## Operators
- a + b: + is Operator, a and b are operands
- Categories of Operator
    - Arithmetic Operators (/, -. +, %, *)
    - Relational Operators (==, !=, >, <, >=, <=)
    - Logical Operators (Logical AND &&, Logical OR ||)
    - Unary Operators (Requires single operand, ++, --, -, +, !)
    - Assignment Operators (Used to assign new value to the variable, =, +=, -=, *=, /=, %=)
    - Bitwise Operators(http://www.csc.villanova.edu/~mdamian/Past/csc2400fa13/assign/Figs/4basicgates.gif) (Works on bit and is very fast, Bitwsie AND &, Bitwsie OR |, Bitwsie XOR ^, Bitwsie NOT ~)
    - Bitwise shift Operators (Used to shift the bits to a number of left or right << Signed Left Shift, >> Signed Right Shift, Unsigned Left Shift >>>, There is no <<< Unsigned Left Shift as << and <<< are equal)

Better to check the Udemy lecture :D 
---

## Two's Complement Wraparound in 4-bit Binary

### Basics of Two's Complement (4-bit)

- **Range**:  
  - Minimum: `1000` = **-8**  
  - Maximum: `0111` = **+7**

- **Wraparound Behavior**:  
  Adding `1` to the most positive value (`0111` = 7) causes a wraparound:
  ```Text
    0111  (7)
  + 0001  (1)
  ------------
    1000  (-8)
  ```

---

### What Happens If You Add 1 to -8?

Let's add `1` to `1000` (-8):

  ```Text
    1000  (-8)
  + 0001  (1)
  ------------
    1001  (-7)
  ```
- `1001` is a two’s complement number.
  - Invert: `0110`
  - Add 1: `0110 + 1 = 0111 = 7`
  - Therefore, `1001` = **-7**

So, adding 1 to -8 results in -7.

---

### Full 4-bit Two’s Complement Range

| Binary | Decimal |
|--------|---------|
| 0000   | 0       |
| 0001   | +1      |
| 0010   | +2      |
| 0011   | +3      |
| 0100   | +4      |
| 0101   | +5      |
| 0110   | +6      |
| 0111   | +7      |
| 1000   | -8      |
| 1001   | -7      |
| 1010   | -6      |
| 1011   | -5      |
| 1100   | -4      |
| 1101   | -3      |
| 1110   | -2      |
| 1111   | -1      |

---
