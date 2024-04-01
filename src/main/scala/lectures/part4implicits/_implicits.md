# Implicits

implicit classes - add additional method to existing classes  
avoid implicit def as much as possible  
IF you need conversions, make them specific

Compiler looks for implicits conversions before type erasure

Drawbacks of Magnet Patter
1 - verbose
2 - hard to read
3 - you can't name or place default arguments
4 - call by name doesn't work correctly